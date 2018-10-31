(ns okpo-charts.util-test
  (:require [clojure.test :refer :all]
            [okpo-charts.util :refer :all]))

(deftest str->keyword-test
  (testing "str->keyword"
    (is (= :a (str->keyword "a")))
    (is (= :abc (str->keyword "abc")))
    (is (= :abc (str->keyword "ABC")))
    (is (= :a-b-c (str->keyword "a b c")))
    (is (= :a-b-c (str->keyword "A B C")))))

(deftest strs->keywords-test
  (testing "strs->keywords"
    (is (= [:a :abc :abc :a-b-c :a-b-c]
           (strs->keywords ["a" "abc" "ABC" "a b c" "A B C"])))))

(deftest not-neg-real?-test
  (testing "not-neg-real?"
    (is (not-neg-real? 1))
    (is (not-neg-real? 1.1))
    (is (not-neg-real? 0))
    (is (not (not-neg-real? -1)))
    (is (not (not-neg-real? -1.1)))))
