(ns brave-and-true.chapter8-test
  (:require [clojure.test :refer :all]
            [brave-and-true.core :refer :all]
            [brave-and-true.chapter8 :refer :all]))

(deftest my-print-test
  (testing "my-print is resutn args"
    (is (= "hoge"
           (my-print "hoge"))))
  (testing "expand let form"
    (is (= '(let* [result "my-print!"]
              (println result)
              result)
           (macroexpand '(my-print "my-print!")))))
  (testing "calc"
    (is (= 2
           (my-print (+ 1 1))))))

(deftest when-test
  (testing "when is macro"
    (is (= '(if true
              (do
                (println 1)
                (println 2)
                (+ 1 2)))
           (macroexpand '(when true
                           (println 1)
                           (println 2)
                           (+ 1 2)))))))
