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
           (hash-set 1 1 2 2))))
  (testing "set have only one of that value"
    (is (= #{:a :b}
           (conj #{:a :b} :a))))
  (testing "make set from existing vectors"
    (is (= #{3 4}
           (set [3 3 3 4 4]))))
  (testing "make set from existing lists"
    (is (= #{:a :b}
           (set '(:a :a :b :b :b)))))
  (testing "check for set memmbership"
    (is (= true
           (contains? #{:a :b} :a)))
    (is (= false
           (contains? #{:a :b} 3)))
    (is (= true
           (contains? #{nil} nil)))
    (is (= false
           (contains? #{:a :b} nil))))
  (testing "lookup the value"
    (is (= :a
           (get #{:a :b} :a)))
    (is (= :a
           (:a #{:a :b})))
    (is (= nil
           (get #{:a nil} nil)))
    (is (= nil
           (get #{:a :b} "kurt vonnegut")))))

(deftest calling-function
  (testing "calling function"
    (is (= 10
           (+ 1 2 3 4)))
    (is (= 24
           (* 1 2 3 4)))
    (is (= 1
           (first [1 2 3 4])))
    ;;the return value of 'or' is the first truthy value.
    (is (= 6
           ((or + -) 1 2 3)))
    ;;the return value of 'and' is the first falsey value or the last truthy value.
    (is (= 6
           ((and (= 1 1) +) 1 2 3)))
    ;;the return value of first is the first element in a sequence.
    (is (= 6
           ((first [+ 0]) 1 2 3)))
    (is (= 2.1
           (inc 1.1)))
    ;;map creates a new list by applying a function to each member of a collection.
    ;;map doesn’t return a vector, even though we supplied a vector as an argumen.
    (is (= '(1 2 3 4)
           (map inc [0 1 2 3])))
    (is (= 220
           (+ (inc 199) (/ 100 (- 7 2)))))))


(deftest too-enthusiastic-test
  (testing "too-enthusiastic-test"
    (is (= "OH. MY. GOD! Zelda YOU ARE MOST DEFINITELY LIKE THE BEST MAN SLASH WOMAN EVER I LOVE YOU AND WE SHOULD RUN AWAY SOMEWHERE"
           (too-enthusiastic "Zelda")))
    (is (= "OH. MY. GOD! hogehoge YOU ARE MOST DEFINITELY LIKE THE BEST MAN SLASH WOMAN EVER I LOVE YOU AND WE SHOULD RUN AWAY SOMEWHERE"
           (too-enthusiastic "hogehoge")))
    (is (= "OH. MY. GOD!  YOU ARE MOST DEFINITELY LIKE THE BEST MAN SLASH WOMAN EVER I LOVE YOU AND WE SHOULD RUN AWAY SOMEWHERE"
           (too-enthusiastic "")))))

(deftest arity-overloading-test
  (testing "arity overloading"
    (is (= "I slap chop Kanye West! Take that!"
           (x-chop "Kanye West" "slap")))
    (is (= "I karate chop Kanye West! Take that!"
           (x-chop "Kanye West")))))

(deftest rest-paramater-test
  (testing "rest paramater"
    (is (= '("Get off my lawn, Billy!!!"
             "Get off my lawn, Anne-Marie!!!"
             "Get off my lawn, The Incredible Bulk!!!")
           (codger "Billy" "Anne-Marie" "The Incredible Bulk")))
    (is (= '("Get off my lawn, Billy!!!")
           (codger "Billy")))
    (is (= "Hi, Doreen, here are my favorite things: gum, shoes, kara-te"
           (favorite-things "Doreen" "gum" "shoes" "kara-te")))))

(deftest destructuring-test
  (testing "by vector"
    (is (= "oven"
           (my-first ["oven" "bike" "war-axe"])))
    (is (= nil
           (my-first [])))
    (is (= "Your first choise is: Marmalade
Your second choise is: Handsome Jack
We're ignoring the rest of your choices. Here they are in case you need to cry over them: Pigpen, Aquaman"
           (chooser ["Marmalade" "Handsome Jack" "Pigpen" "Aquaman"])))
    (is (= "Your first choise is: hoge
Your second choise is: fuga"
           (chooser ["hoge" "fuga"]))))

  (testing "by list"
    (is (= "hoge"
           (my-first '("hoge" "fuga" "piyo"))))
    (is (= nil
           (my-first '())))
    (is (= "Your first choise is: Marmalade
Your second choise is: Handsome Jack
We're ignoring the rest of your choices. Here they are in case you need to cry over them: Pigpen, Aquaman"
           (chooser '("Marmalade" "Handsome Jack" "Pigpen" "Aquaman"))))
    (is (= "Your first choise is: hoge
Your second choise is: fuga"
           (chooser '("hoge" "fuga")))))

  (testing "by map"
    (is (= "Treasure lat: 28.22, Treasure lng: 81.33"
           (announce-treasure-location {:lat 28.22 :lng 81.33})))
    (is (= "Treasure lat: 28.22, Treasure lng: 81.33"
           (announce-treasure-location-2 {:lat 28.22 :lng 81.33})))
    (is (= "{:lat 28.22, :lng 81.33}"
           (receive-treasure-location {:lat 28.22 :lng 81.33})))))

(deftest function-body-test
  (testing "returns the last form evaluated"
    (is (= "Joe"
           (illustrative-function))))
  (testing "use if expression"
    (is (= "Oh my gosh! What a big number!"
           (number-comment 7)))
    (is (= "That's number's OK, I guess"
           (number-comment 6)))))

(deftest anonymous-function-test
  (testing "use fn form"
    (is (= '("Hi, Darth Vader" "Hi, Mr.Mango")
           (map (fn [name] (str "Hi, " name))
                ["Darth Vader" "Mr.Mango"])))
    (is (= 24
           ((fn [x] (* x 3)) 8))))
  (testing "use reader-macro"
    (is (= 24
           (#(* % 3) 8 )))
    (is (= '("Hi, Darth Vader" "Hi, Mr.Mango")
           (map #(str "Hi, " %)
                ["Darth Vader" "Mr.Mango"]))))
  (testing "fn and reader macro"
    (is (= "cornbread and butter beans"
           ((fn [name1 name2] (str name1 " and " name2))
            "cornbread" "butter beans")))
    (is (= "cornbread and butter beans"
           (#(str %1  " and " %2)
            "cornbread" "butter beans")))
    (is (= '(1 "blarg" :yip)
           ((fn [& rest] (identity rest)) 1 "blarg" :yip)))
    (is (= '(1 "blarg" :yip)
           (#(identity %&) 1 "blarg" :yip)))))

(deftest returning-function-test
  (testing "returning function"
    (is (function?
         (inc-maker 3))))
  (testing "return 10"
    (is (= 10
           (let [inc3 (inc-maker 3)]
             (inc3 7))))))

(deftest regular-expression-test
  (testing "map's value :name left is replaced right"
    (is (= {:name "right-eye" :size 1}
           (matching-part {:name "left-eye" :size 1})))
    (is (= {:name "right-shoulder" :size 3}
           (matching-part {:name "right-shoulder" :size 3}))))
  (testing "if left is none, return arguments"
    (is (= {:name "head" :size 3}
           (matching-part {:name "head" :size 3})))))

(def asym-hobbit-body-parts [{:name	"head" :size 3}
                             {:name	"left-eye" :size 1}
                             {:name	"left-ear" :size 1}
                             {:name	"mouth" :size 1}
                             {:name	"nose" :size 1}
                             {:name	"neck" :size 2}
                             {:name	"left-shoulder" :size 3}
                             {:name	"left-upper-arm" :size 3}
                             {:name	"chest" :size 10}
                             {:name	"back" :size 10}
                             {:name	"left-forearm" :size 3}
                             {:name	"abdomen" :size 6}
                             {:name	"left-kidney" :size 1}
                             {:name	"left-hand" :size 2}
                             {:name	"left-knee" :size 2}
                             {:name	"left-thigh" :size 4}
                             {:name	"left-lower-leg" :size 3}
                             {:name	"left-achilles" :size 1}
                             {:name	"left-foot" :size 2}])

(deftest let-and-loop-and-regular-expressions-test
  (testing " make proportional in body parts."
    (is (= [{:name "head", :size 3}
            {:name "left-eye", :size 1}
            {:name "right-eye", :size 1}
            {:name "left-ear", :size 1}
            {:name "right-ear", :size 1}
            {:name "mouth", :size 1}
            {:name "nose", :size 1}
            {:name "neck", :size 2}
            {:name "left-shoulder", :size 3}
            {:name "right-shoulder", :size 3}
            {:name "right-upper-arm", :size 3}
            {:name "left-upper-arm", :size 3}
            {:name "chest", :size 10}
            {:name "back", :size 10}
            {:name "left-forearm", :size 3}
            {:name "right-forearm", :size 3}
            {:name "abdomen", :size 6}
            {:name "left-kidney", :size 1}
            {:name "right-kidney", :size 1}
            {:name "left-hand", :size 2}
            {:name "right-hand", :size 2}
            {:name "right-knee", :size 2}
            {:name "left-knee", :size 2}
            {:name "right-thigh", :size 4}
            {:name "left-thigh", :size 4}
            {:name "right-lower-leg", :size 3}
            {:name "left-lower-leg", :size 3}
            {:name "right-achilles", :size 1}
            {:name "left-achilles", :size 1}
            {:name "right-foot", :size 2}
            {:name "left-foot", :size 2}]
           (symmetrize-body-parts asym-hobbit-body-parts)))))

(def dalmatian-list ["Pongo" "Perdita" "Puppy 1" "Puppy 2"])
(def x 0)

(deftest let-test
  (testing "let binds name to values"
    (is (= 3
           (let [x 3]
             3)))
    (is (= '("Pongo" "Perdita")
           (let [dalmatians (take 2 dalmatian-list)]
             dalmatians)))
    (is (= 0
           x))
    (is (= 1
           (let [x 1]
             x)))
    (is (= 1
           (let [x (inc x)]
             x)))
    (is (not (= 1
                x)))
    (is (= 0
           x))
    (is (= ["Pongo" '("Perdita" "Puppy 1" "Puppy 2")]
           (let [[pongo & dalmatians] dalmatian-list]
             [pongo dalmatians])))))
