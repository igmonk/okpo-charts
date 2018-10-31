(ns okpo-charts.playstore-app-metrics)

(defn- count-per-category-reducer [acc {:keys [category]}]
  (update acc category #(inc (or % 0))))

(defn count-per-category [apps]
  (reduce count-per-category-reducer {} apps))

(defn- most-expensive-per-category-reducer [acc {:keys [category] :as app}]
  (update acc category #(max-key :price app (or % {:price 0}))))

(defn most-expensive-per-category [apps]
  (reduce most-expensive-per-category-reducer {} apps))

(defn- most-popular-per-category-reducer [acc {:keys [category] :as app}]
  (update acc category #(max-key :installs app (or % {:installs 0}))))

(defn most-popular-per-category [apps]
  (reduce most-popular-per-category-reducer {} apps))
