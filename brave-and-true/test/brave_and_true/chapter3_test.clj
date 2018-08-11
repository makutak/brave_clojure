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
  (testing "equal"
    (is (= true
           (= 1 1)))
    (is (= true
           (= nil nil)))
    (is (= false
           (= 1 2))))
  (testing "or"
    (is (= :large_I_mean_venti
           (or false nil :large_I_mean_venti :why_cant_I_just_say_large)))
    (is (= false
           (or (= 0 1) (= "yes" "no"))))
    (is (= nil
           (or nil))))
  (testing "and"
    (is (= :hot_coffee
           (and :free_wifi :hot_coffee)))
    (is (= nil
           (and :feelin_super_cool nil false)))))

(deftest naming-values-with-def
  (testing "def"
    (def failed-protagonist-names
      ["Larry Potter" "Doreen the Explorer" "The Incredible Bulk"])
    (is (= ["Larry Potter" "Doreen the Explorer" "The Incredible Bulk"]
           failed-protagonist-names)))
  (testing "error-message :mild"
    (is (= "OH GOD! IT'S A DISASTER! WE'RE MILDLY INCONVENIENCED!"
           (error-message :mild))))
  (testing "error-message :hoge"
    (is (= "OH GOD! IT'S A DISASTER! WE'RE DOOOOOOOMED!"
           (error-message :hoge)))))
