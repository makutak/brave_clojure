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

(deftest strings
  (testing "str"
    (def name "Chewbacca")
    (is (= "\"Uggllglglglglglglglll\" - Chewbacca")
        (str "\"Uggllglglglglglglglll\" - " name))))

(deftest maps
  (testing "make-hash-map"
    (is (hash-map :a 1 :b 1)
        {:a 1 :b 1}))
  (testing "get value in maps"

    ;;(get map key) keyに対応したvalueが返る。
    (is (= 1
           (get {:a 0 :b 1} :b)))

    ;;(get map key) ネストしたmapにも対応。
    (is (= {:c "ho hum"}
           (get {:a 0 :b {:c "ho hum"}} :b)))

    ;;存在しないkeyを指定すると、nilが返る。
    (is (= nil
           (get {:a 0 :b 1} :c))) ;;

    ;;(get map key not-found) keyが存在しないときに返す値を指定できる。
    (is (= "unicorns?"
           (get {:a 0 :b 1} :c "unicorns?")))

    ;;get-in: ([m ks] [m ks not-found]) ネストしたmapから値を取るには、get-in を使う。
    (is (= "ho hum"
           (get-in {:a 0 :b {:c "ho hum"}} [:b :c])))

    ;;get は省略できる。
    (is (= "The Human Coffeepot"
           ({:name "The Human Coffeepot"} :name)))))

(deftest vectors
  ;;make vectors by literal.
  (testing "make vectors by literal"
    (is (= [3 2 1]
           [3 2 1])))
  ;;make vectors by vector function.
  (testing "make vectors by function"
    (is (= [3 2 1]
           (vector 3 2 1))))
  ;;get Nth elements of a vector.
  (testing "get element by index"
    (is (= 1
           (get [3 2 1] 2))))
  ;;conj is additional elements to vectors.
  (testing "add additional element"
    (is (= [1 2 3 4]
           (conj [1 2 3] 4)))))

(deftest lists
  ;;make lists by literal.
  (testing "make lists by literal"
    (is (= '(1 2 3 4)
           '(1 2 3 4))))
  ;;make lists by function.
  (testing "make lists by function"
    (is (= '(1 "two" {3 4})
           (list 1 "two" {3 4}))))
  ;;get Nth elements of a list.
  (testing "get element by index"
    (is (= :a
           (nth '(:a :b :c) 0)))
    (is (= :c
           (nth '(:a :b :c) 2))))
  ;;elements are added to the beginning of a list.
  (testing "add elements to lists"
    (is (= '(4 1 2 3)
           (conj '(1 2 3) 4)))))

;;sets are collections of unique values.
(deftest sets
  (testing "make sets by literal"
    (is (= #{"kurt vonnegut" 20 :icicle}
           #{"kurt vonnegut" 20 :icicle})))
  (testing "make sets by fucntion"
    (is (= #{1 2}
           (hash-set 1 1 2 2)))))
