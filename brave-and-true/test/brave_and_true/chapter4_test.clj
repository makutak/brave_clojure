(ns brave-and-true.chapter4-test
  (:require [clojure.test :refer :all]
            [brave-and-true.core :refer :all]
            [brave-and-true.chapter4 :refer :all]))

(deftest titlelize-test
  (testing "titleize test"
    (is (= "Clojure for the Brave and True"
           (titlelize "Clojure")))
    (is (= " for the Brave and True"
           (titlelize ""))))
  (testing "map returns list"
    (is (= '("Hamsters for the Brave and True" "Ragnarok for the Brave and True")
           (map titlelize ["Hamsters" "Ragnarok"])))
    (is (= '("Empty for the Brave and True" "Decorating for the Brave and True")
           (map titlelize '("Empty" "Decorating"))))
    (is (= '("Elbows for the Brave and True" "Soap Carving for the Brave and True")
           (map titlelize #{"Elbows" "Soap Carving"})))
    (is (= '("Winking for the Brave and True")
           (map #(titlelize (second %)) {:uncomfortable-thing "Winking"})))))

(deftest seq-test
  (testing "seq abstracts list into sequence"
    (is (= true
           (seq? (seq '(1 2 3))))))
  (testing "seq abstracts vector into sequence"
    (is (= true
           (seq? (seq [1 2 3])))))
  (testing "seq abstracts set into sequence"
    (is (= true
           (seq? (seq #{1 2 3})))))
  (testing "seq abstracts hash-map into sequence"
    (is (= true
           (seq? (seq {:name "Bill Compton" :occupation "Dead mopey guy"})))))
  (testing "the seq back into a map by using into"
    (is (= true
           (map? (into {} (seq {:a 1 :b 2 :c 3})))))))

(deftest map-test
  (testing "map can receive one collection"
    (is (= '(2 3 4)
           (map inc [1 2 3]))))
  (testing "map can receive multiple collection"
    (is (= '("aA" "bB" "cC")
           (map str ["a" "b" "c"] ["A" "B" "C"])))))

(def human-consumption-data   [8.1 7.3 6.6 5.0])
(def critter-consumption [0.0 0.2 0.3 1.1])
(def expected (map (fn [h c] {:human h :critter c}) human-consumption-data critter-consumption))

(deftest unify-diet-data-test
  (testing "two data into a single map"
    (is (= expected
           (map unify-diet-data human-consumption-data critter-consumption)))))


(deftest stats-test
  (testing "return a set of calculations on different collections of numbers"
    (is (= '(17 3 17/3)
           (stats [3 4 10])))
    (is (= '(144 5 144/5)
           (stats [80 1 44 13  6])))))
