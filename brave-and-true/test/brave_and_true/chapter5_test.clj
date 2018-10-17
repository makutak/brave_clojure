(ns brave-and-true.chapter5-test
  (:require [clojure.test :refer :all]
            [brave-and-true.core :refer :all]
            [brave-and-true.chapter5 :refer :all]))


(deftest sum-test
  (testing "sum from list"
    (is (= 10
           (sum '(10))))
    (is (= 55
           (sum '(1 2 3 4 5 6 7 8 9 10)))))
  (testing "sum from list and initial value"
    (is (= 15
           (sum '(10) 5)))
    (is (= 155
           (sum '(1 2 3 4 5 6 7 8 9 10) 100)))))
