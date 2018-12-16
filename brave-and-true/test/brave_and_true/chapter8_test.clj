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

(deftest unless-test
  (testing "unless"
    (is (= false
           (unless true
                   true
                   false)))
    (is (= "fuga"
           (unless (= 1 1)
                   (str "hoge")
                   (str "fuga")))))
  (testing "expanded is if special form"
    (is (= '(if true
              false
              true)
           (macroexpand '(unless true
                                  true
                                  false))))))
