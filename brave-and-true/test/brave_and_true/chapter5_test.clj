(ns brave-and-true.chapter5-test
  (:require [clojure.test :refer :all]
            [brave-and-true.core :refer :all]
            [brave-and-true.chapter5 :refer :all]))

(deftest sum-test
  (testing "sum of list"
    (is (= 10
           (sum '(10))))
    (is (= 55
           (sum '(1 2 3 4 5 6 7 8 9 10)))
        (= 0
           (sum '()))))
  (testing "sum of list and initial value"
    (is (= 15
           (sum '(10) 5)))
    (is (= 155
           (sum '(1 2 3 4 5 6 7 8 9 10) 100)))
    (is (= 45
           (sum '() 45)))))

(deftest sum-use-recur-test
  (testing "sum of list by using sum-use-recur"
    (is (= 10
           (sum-use-recur '(10))))
    (is (= 55
           (sum-use-recur '(1 2 3 4 5 6 7 8 9 10)))
        (= 0
           (sum-use-recur '()))))
  (testing "sum of list and initial value by using sum-use-recur"
    (is (= 15
           (sum-use-recur '(10) 5)))
    (is (= 155
           (sum-use-recur '(1 2 3 4 5 6 7 8 9 10) 100)))
    (is (= 45
           (sum-use-recur '() 45)))))
