(ns brave-and-true.chapter3-test
  (:require [clojure.test :refer :all]
            [brave-and-true.core :refer :all]
            [brave-and-true.chapter3 :refer :all]))

(deftest form
  (testing "form"
    (is (= 6 (+ 1 2 3)))
    (is (= (str "It was the panda " "in the library " "with a dust buster")
           "It was the panda in the library with a dust buster"))))
