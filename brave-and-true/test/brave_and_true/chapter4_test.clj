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

(deftest concat-test
  (testing "concat returns the concatenation of the elements in the supplied colls"
    (is (= '(1 2 3 4)
           (concat [1 2] [3 4])))
    (is (= '(1 2 3 4 5 6)
           (concat [1 2] [3 4] '(5 6))))
    (is (= '(1)
           (concat [1])))))

(deftest identity-vampire-test
  (testing "the culprit is Damon"
    (is (= "Damon Salvatore"
           (:name (identify-vampire (range 0 5)))))))

(deftest repeat-test
  (testing "repeat creates sequence whose every member is the argument"
    (is (= '("foo" "foo" "foo" "foo" "foo")
           ;;repeat は無限に続くので、使うときは先頭からのいくつかの要素を取得しないといけない
           (take 5 (repeat "foo"))))
    (is (= '("na" "na" "na" "na" "na" "na" "na" "na" "Batman!")
           (concat
            (take 8 (repeat "na"))
            '("Batman!")))))
  (testing "repeatedly call the provided function to generate each element in the sequence"
    (is (seq?
         ;;ランダムで生成した10までの数字を持つ長さ3のseqを作る
         ;;=> 毎回関数を実行する。
         ;;repeatは、rand-intを初回だけ実行するので、同じ数字が並んだseqが返る。
         (take 3 (repeatedly (fn [] (rand-int 10)))))))
  (testing "generate even number seq"
    (is (= '(0 2 4 6 8 10)
           (take 6 (even-numbers))))
    (is (= '(10 12 14 16 18 20)
           (take 6 (even-numbers 10))))))

(deftest empty?-test
  (testing "return true if vector is nil."
    (is (= true
           (empty? []))))
  (testing "return false if vector is not nil."
    (is (= false
           (empty? ["no!"])))))

(deftest into-test
  ;;function identity is returns arg.
  (testing "map returns seq."
    (is (= '([:sunlight-reaction "Glitter!"])
           (map identity {:sunlight-reaction "Glitter!"}))))
  (testing "into returns not seq."
    (is (= {:sunlight-reaction "Glitter!"}
           (into {} (map identity {:sunlight-reaction "Glitter!"})))))
  (testing "map returns lists"
    (is (not (vector?
              (map identity [:garlic :sesame-oil :fried-eggs])))))
  (testing "into back to oridinal data structures."
    (is (vector?
         (into [] (map identity [:garlic :sesame-oil :fried-eggs])))))
  (testing "map converts list"
    (is (seq?
         (map identity [:garlic-clove :garlic-clove]))))
  (testing "into stick the values into a set"
    (is (set?
         (into #{} (map identity [:garlic-clove :garlic-clove])))))
  (testing "into add elements to a map"
    (is (= {:favorite-emotion "gloomy" :sunlight-reaction "Glitter!"}
           (into {:favorite-emotion "gloomy"} [[:sunlight-reaction "Glitter!"]]))))
  (testing "into add elements to a vector"
    (is (= ["cherry" "pine" "spruce"]
           (into ["cherry"] '("pine" "spruce")))))
  (testing "into returning new map with the element of the second map add to the first"
    (is (= {:favorite-animal "kitty"
            :relationship-with-teenager "creepy"
            :least-favorite-smell "dog"}
           (into {:favorite-animal "kitty"} {:least-favorite-smell "dog"
                                             :relationship-with-teenager "creepy"})))))
(deftest conj-test
  (testing "conj add elements to a collection"
    (is (= [0 [1]]
           (conj [0] [1]))))
  (testing  "if you want to return the same value as into, you pass to second argument as scalar."
    (is (= (into [0] [1])
           (conj [0] 1))))
  (testing "conj can accepet a lot of argument"
    (is (= [0 1 2 3 4 5]
           (conj [0] 1 2 3 4 5))))
  (testing "conj can add maps to another map"
    (is (= {:place "ye olde cemetarium"
            :time "midnight"}
           (conj {:time "midnight"}
                 [:place "ye olde cemetarium"]))))
  (testing "define conj in terms of into."
    (is (= [0 [1]]
           (my-conj [0] [1])))
    (is (= [0 1]
           (my-conj [0] 1)))
    (is (= [0 1 2 3 4 5]
           (my-conj [0] 1 2 3 4 5)))))

(deftest apply-test
  (testing "apply returns max value from vector"
    (is (= 3
           (apply max [0 1 2 3])))
    (is (= (max 0 1 2 3)
           (apply max [0 1 2 3]))))
  (testing "define into in terms of conj by using apply"
    (is (= (into [0] [1 2 3])
           (my-into [0] [1 2 3])))))

(deftest partial-test
  (testing "partial resturns new funcion"
    (is (= 13
           (let [add10 (partial + 10)]
             (add10 3))))
    (is (= 15
           (let [add10 (partial + 10)]
             (add10 5))))
    (is (= ["water" "earth" "air" "unobtainium" "adamantium"]
           (let [add-missing-elements (partial conj ["water" "earth" "air"])]
             (add-missing-elements "unobtainium" "adamantium"))))))
