(ns brave-and-true.chapter3-test
  (:require [clojure.test :refer :all]
            [brave-and-true.core :refer :all]
            [brave-and-true.chapter3 :refer :all]))

(deftest form
  (testing "form"
    (is (= 6 (+ 1 2 3)))
    (is (= (str "It was the panda " "in the library " "with a dust buster")
           "It was the panda in the library with a dust buster"))))


(deftest control-flow
  (testing "if"
    (is (= "By Zeus's hammer!"
           (if true
             "By Zeus's hammer!"
             "By Aquaman's trident!")))
    (is (= "By Aquaman's trident!"
           (if false
             "By Zeus's hammer!"
             "By Aquaman's trident!")))
    ;;falseに対応するのがないときはnilが返る。
    (is (= nil
           (if false
             "By Odin's Elbow!"))))
  (testing "nil"
    (is (= false
           (nil? 1)))
    (is (= true
           (nil? nil)))
    ;;nil, false 以外の値はtrueとなる
    (is (= "bears beets Battlestar Galactica"
           (if "bears eat beets"
             "bears beets Battlestar Galactica")))
    (is (= "nil is falsey"
         (if nil
            "This won't be the result because nil is falsey"
            "nil is falsey"))))
