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

(def identities
  [{:alias "Batman" :real "Bruce Wayne"}
   {:alias "Spider-Man" :real "Peter Parker"}
   {:alias "Santa" :real "Your mom"}
   {:alias "Easter Bunny" :real "Your dad"}])

(deftest using-map-test
  (testing "map get value from a collection of map data structures"
    (is (= '("Bruce Wayne" "Peter Parker" "Your mom" "Your dad")
           (map :real identities)))))

(deftest assoc-test
  (testing "assoc build map"
    (is (= {:max 30}
           (assoc {} :max 30))))
  (testing "assoc update map"
    (is (= {:max 30 :min 10}
           (assoc
            (assoc {} :max 30)
            :min
            10)))))

(deftest reduce-test
  (testing "maps'value update"
    (is (= {:max 31 :min 11}
           (reduce (fn [new-map [key val]]
                     (assoc new-map key (inc val)))
                   {}
                   {:max 30 :min 10}))))
  (testing "filter map"
    (is (= {:human 4.1}
           (reduce (fn [new-map [key val]]
                     (if (> val 4)
                       (assoc new-map key val)
                       new-map))
                   {}
                   {:human 4.1 :critter 3.9})))
    (is (= {:critter 3.9}
           (reduce (fn [new-map [key val]]
                     (if (< val 4)
                         (assoc new-map key val)
                         new-map))
                   {}
                   {:human 4.1 :critter 3.9})))))

(deftest take-test
  (testing "take return the first n elements of the sequence"
    (is (= '(1 2 3)
           (take 3 (range 1 11))))))

(deftest drop-test
  (testing "drop returns the sequence with the first n element removed"
    (is (= '(4 5 6 7 8 9 10)
           (drop 3 (range 1 11))))))

(def food-journal
  [{:month 1 :day 1 :human 5.3 :critter 2.3}
   {:month 1 :day 2 :human 5.1 :critter 2.0}
   {:month 2 :day 1 :human 4.9 :critter 2.1}
   {:month 2 :day 2 :human 5.0 :critter 2.5}
   {:month 3 :day 1 :human 4.2 :critter 3.3}
   {:month 3 :day 2 :human 4.0 :critter 3.8}
   {:month 4 :day 1 :human 3.7 :critter 3.9}
   {:month 4 :day 2 :human 3.7 :critter 3.6}])

(def less-than-3-month '({:month 1, :day 1, :human 5.3, :critter 2.3}
                         {:month 1, :day 2, :human 5.1, :critter 2.0}
                         {:month 2, :day 1, :human 4.9, :critter 2.1}
                         {:month 2, :day 2, :human 5.0, :critter 2.5}))

(deftest take-while-test
  (testing "return month less than 3 data"
    (is (= less-than-3-month
           (take-while #(< (:month %) 3) food-journal)))))


(def more-than-3-month '({:month 3, :day 1, :human 4.2, :critter 3.3}
                         {:month 3, :day 2, :human 4.0, :critter 3.8}
                         {:month 4, :day 1, :human 3.7, :critter 3.9}
                         {:month 4, :day 2, :human 3.7, :critter 3.6}))

(def feburary-and-march '({:month 2, :day 1, :human 4.9, :critter 2.1}
                          {:month 2, :day 2, :human 5.0, :critter 2.5}
                          {:month 3, :day 1, :human 4.2, :critter 3.3}
                          {:month 3, :day 2, :human 4.0, :critter 3.8}))

(deftest drop-while-test
  (testing "return month more than 3 data"
    (is (= more-than-3-month
           (drop-while #(< (:month %) 3) food-journal)))))

(deftest take-while-and-drop-while-test
  (testing "return 2 and 3 month data"
    (is (= feburary-and-march
           (take-while #(< (:month %) 4)
                       (drop-while #(< (:month %) 2) food-journal))))))

(def more-than-5-human '({:month 2, :day 1, :human 4.9, :critter 2.1}
                         {:month 3, :day 1, :human 4.2, :critter 3.3}
                         {:month 3, :day 2, :human 4.0, :critter 3.8}
                         {:month 4, :day 1, :human 3.7, :critter 3.9}
                         {:month 4, :day 2, :human 3.7, :critter 3.6}))

(def january-and-feburary '({:month 1, :day 1, :human 5.3, :critter 2.3}
                            {:month 1, :day 2, :human 5.1, :critter 2.0}
                            {:month 2, :day 1, :human 4.9, :critter 2.1}
                            {:month 2, :day 2, :human 5.0, :critter 2.5}))

(deftest filter-test
  (testing "return more than human 5 data"
    (is (= more-than-5-human
           (filter #(< (:human %) 5) food-journal))))
  (testing "return january and feburary"
    (is (= january-and-feburary
           (filter #(< (:month %) 3) food-journal)))))

;; filterは引数をすべて評価するが、take-while、drop-whileはそうではない。
;; take-whileは与えられた関数が成り立つまでのリストを返し、
;; drop-whileは与えられた関数が成り立つまでを切り捨てて、残りのリストを返す。
;; なので、ソート済みのseqに対しては、take-while, drop-whileが有用。

(deftest some-test
  (testing "return true if it exists"
    (is (= true
           (some #(> (:critter %) 3) food-journal))))
  (testing "return nil if it not exist"
    (is (= nil
           (some #(> (:critter %) 5) food-journal))))
  (testing "return first true value use and function"
    (is (= {:month 3, :day 1, :human 4.2, :critter 3.3}
           ;; 'and' returns the value of the last expr
           (some #(and (> (:critter %) 3) %) food-journal)))))

(deftest sort-test
  (testing "sort returns ascending order seq"
    (is (= '(1 2 3)
           (sort [3 2 1]))))
  ;; key-functionの結果により、sort-byはソート順を決める
  (testing "sort-by returns string length ascending order seq"
    (is (= '("c" "bb" "aaa")
           (sort-by count ["aaa" "bb" "c"]))))
  (testing "sort returns alphabetical order seq"
    (is (= '("aaa" "bb" "c")
           (sort '("c" "bb" "aaa"))))))
