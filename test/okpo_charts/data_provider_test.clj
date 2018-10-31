(ns okpo-charts.data-provider-test
  (:require [clojure.test :refer :all]
            [okpo-charts.data-provider :refer :all]))

(deftest csv-data->maps-test
  (testing "csv-data->maps"
    (let [test-data [[[] []]
                     [[["col1" "col2" "col3"]
                       ["val11" "val12" "val13"]
                       ["val21" "val22" "val23"]
                       ["val31" "val32" "val33"]]
                      [{:col1 "val11" :col2 "val12" :col3 "val13"}
                       {:col1 "val21" :col2 "val22" :col3 "val23"}
                       {:col1 "val31" :col2 "val32" :col3 "val33"}]]]]
      (doseq [[csv-data expected] test-data]
        (is (= expected (csv-data->maps csv-data)))))))

(deftest app-data-filter-pred-test
  (testing "app-data-filter-pred"
    (is (app-data-filter-pred {:rating 0 :installs 0}))
    (is (app-data-filter-pred {:rating 1.1 :installs 0}))
    (is (not (app-data-filter-pred {:rating -1 :installs 0})))
    (is (app-data-filter-pred {:rating 0 :installs 1}))
    (is (app-data-filter-pred {:rating 1.1 :installs 1}))
    (is (not (app-data-filter-pred {:rating 1.1 :installs -1})))
    (is (not (app-data-filter-pred {:rating 1.1 :installs 1.1})))))
