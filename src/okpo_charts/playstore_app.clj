(ns okpo-charts.playstore-app
  (:require [clojure.spec.alpha :as s]))

(s/def ::playstore-app
  (s/keys :req-un [::app
                   ::category
                   ::rating
                   ::reviews
                   ::installs
                   ::type
                   ::price
                   ::content-rating
                   ::genres
                   ::current-ver
                   ::android-ver]))

(s/def ::app string?)

(s/def ::category string?)

(s/def ::rating (s/and number? (complement neg?)))

(s/def ::reviews nat-int?)

(s/def ::installs nat-int?)

(s/def ::type string?)

(s/def ::price (s/and number? (complement neg?)))

(s/def ::content-rating string?)

(s/def ::genres string?)

(s/def ::current-ver string?)

(s/def ::android-ver string?)
