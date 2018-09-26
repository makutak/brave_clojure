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
