(ns okpo-charts.main-frame
  (:require [seesaw.core :refer :all]
            [okpo-charts.data-provider :as dp]
            [okpo-charts.playstore-app-metrics :as metrics])
  (:import [org.jfree.chart ChartFactory ChartPanel]
           [org.jfree.chart.plot PlotOrientation]
           [org.jfree.data.category CategoryDataset]
           [org.jfree.data.general DefaultPieDataset PieDataset]
           [org.jfree.data.category DefaultCategoryDataset]))

(defn- display [f content]
  (config! f :content content)
  content)

(defn- control-button [txt action-fn]
  (let [btn (button :text txt)]
    (listen btn :action action-fn)
    btn))

(defn- create-chart-frame [content]
  (invoke-later
    (-> (frame
          :title "Chart"
          :content content
          :on-close :exit)
        pack!
        show!)))

(defn- show-pie-chart [^String title ^PieDataset dataset]
  (-> (ChartFactory/createPieChart title dataset true true false)
      (ChartPanel. false)
      create-chart-frame))

(defn- show-bar-chart [^String title ^String y-title ^CategoryDataset dataset]
  (-> (ChartFactory/createBarChart title "Category" y-title dataset PlotOrientation/VERTICAL true true false)
      (ChartPanel. false)
      create-chart-frame))

(defn- show-cpc [e]
  (let [dataset (DefaultPieDataset.)]
    (doseq [[category price] (metrics/count-per-category (dp/get-playstore-apps-memo))]
      (.setValue dataset category price))
    (show-pie-chart "Count per Category" dataset)))

(defn- show-mepc [e]
  (let [mepc (metrics/most-expensive-per-category (dp/get-playstore-apps-memo))
        dataset (DefaultCategoryDataset.)]
    (doseq [[category app] mepc]
      (.addValue dataset (:price app) category category))
    (show-bar-chart "Most Expensive per Category" "Price, $" dataset)))

(defn- show-mppc [e]
  (let [mppc (metrics/most-popular-per-category (dp/get-playstore-apps-memo))
        dataset (DefaultCategoryDataset.)]
    (doseq [[category app] mppc]
      (.addValue dataset (:installs app) category category))
    (show-bar-chart "Most Popular per Category" "Installs" dataset)))

(defn- create-control-buttons []
  (let [cpc-btn (control-button "Count per Category" show-cpc)
        mepc-btn (control-button "Most Expensive per Category" show-mepc)
        mppc-btn (control-button "Most Popular per Category" show-mppc)]
    [cpc-btn mepc-btn mppc-btn]))

(defn- create-listbox []
  (let [lb (listbox :model (dp/get-playstore-apps-memo))]
    (scrollable lb)))

(defn- create-frame []
  (let [f (frame
            :title "OKPO - Charts"
            :content "Hello, BRO"
            :on-close :exit)]

    (display f (border-panel
                 :north (horizontal-panel :items (create-control-buttons))
                 :center (create-listbox)
                 :vgap 5 :hgap 5 :border 5))

    f))

(defn show-frame []
  (invoke-later
    (-> (create-frame)
        pack!
        show!)))
