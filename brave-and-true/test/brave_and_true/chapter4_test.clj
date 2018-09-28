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
  (testing "map can"))
