(ns okpo-charts.playstore-app-metrics-test
  (:require [clojure.test :refer :all]
            [okpo-charts.playstore-app-metrics :refer :all]))

(deftest count-per-category-test
  (testing "count-per-category"
    (let [test-data [[[] {}]
                     [[{:category "abc" :name "item1"}
                       {:category "abc" :name "item2"}
                       {:category "abc" :name "item3"}
                       {:category "xyz" :name "item4"}
                       {:category "xyz" :name "item5"}
                       {:category "qwerty" :name "item6"}]
                      {"abc" 3 "xyz" 2 "qwerty" 1}]]]
      (doseq [[apps expected] test-data]
        (is (= expected (count-per-category apps)))))))

(deftest most-expensive-per-category-test
  (testing "most-expensive-per-category"
    (let [test-data [[[] {}]
                     [[{:category "abc" :name "item1" :price 10}
                       {:category "abc" :name "item2" :price 20}
                       {:category "abc" :name "item3" :price 30}
                       {:category "xyz" :name "item4" :price 40}
                       {:category "xyz" :name "item5" :price 50}
                       {:category "qwerty" :name "item6" :price 60}]
                      {"abc" {:category "abc" :name "item3" :price 30}
                       "xyz" {:category "xyz" :name "item5" :price 50}
                       "qwerty" {:category "qwerty" :name "item6" :price 60}}]]]
      (doseq [[apps expected] test-data]
        (is (= expected (most-expensive-per-category apps)))))))

(deftest most-popular-per-category-test
  (testing "most-popular-per-category"
    (testing "most-expensive-per-category"
      (let [test-data [[[] {}]
                       [[{:category "abc" :name "item1" :installs 10}
                         {:category "abc" :name "item2" :installs 20}
                         {:category "abc" :name "item3" :installs 30}
                         {:category "xyz" :name "item4" :installs 40}
                         {:category "xyz" :name "item5" :installs 50}
                         {:category "qwerty" :name "item6" :installs 60}]
                        {"abc" {:category "abc" :name "item3" :installs 30}
                         "xyz" {:category "xyz" :name "item5" :installs 50}
                         "qwerty" {:category "qwerty" :name "item6" :installs 60}}]]]
        (doseq [[apps expected] test-data]
          (is (= expected (most-popular-per-category apps))))))))
