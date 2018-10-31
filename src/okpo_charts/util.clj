(ns okpo-charts.util
  (:require [clojure.string :as str]
            [clojure.spec.alpha :as s]))

(defn str->keyword [s]
  (-> (str/replace s #" " "-")
      str/lower-case
      keyword))

(defn strs->keywords [strs]
  (map str->keyword strs))

(defn not-neg-real? [n]
  (and (number? n)
       (not (neg? n))))

(defn conform-schema [items schema]
  (s/conform (s/coll-of schema) items))
