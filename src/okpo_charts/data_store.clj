(ns okpo-charts.data-store
  (:require [clojure.java.io :as io]
            [clojure.data.csv :as csv]))

(defn get-data []
  (with-open [reader (io/reader (io/resource "data/googleplaystore.csv"))]
    (doall
      (csv/read-csv reader))))
