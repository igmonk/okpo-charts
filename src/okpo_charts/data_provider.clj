(ns okpo-charts.data-provider
  (:require [clojure.string :as str]
            [okpo-charts.data-store :as ds]
            [okpo-charts.util :as util]
            [okpo-charts.playstore-app :as pa]))

(defn csv-data->maps [csv-data]
  (let [header-keys (util/strs->keywords (first csv-data))]
    (map zipmap (repeat header-keys) (rest csv-data))))

(defn app-data->playstore-app [{:keys [rating reviews installs price] :as app-data}]
  (-> app-data
      (assoc :rating (read-string rating))
      (assoc :reviews (read-string reviews))
      (assoc :installs (read-string (-> installs
                                        (str/replace #"\+" "")
                                        (str/replace #"," ""))))
      (assoc :price (read-string (str/replace price #"\$" "")))
      (dissoc :last-updated)))

(defn app-data-filter-pred [{:keys [rating installs]}]
  (and
    (util/not-neg-real? rating)
    (nat-int? installs)))

(defn apps-data->playstore-apps [apps-data]
  (->> (map app-data->playstore-app apps-data)
       (filter app-data-filter-pred)))

(defn get-playstore-apps []
  (-> (ds/get-data)
      (csv-data->maps)
      (apps-data->playstore-apps)
      (util/conform-schema ::pa/playstore-app)))

(def get-playstore-apps-memo (memoize get-playstore-apps))
