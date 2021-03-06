(ns brave-and-true.chapter5-test
  (:require [clojure.test :refer :all]
            [brave-and-true.core :refer :all]
            [brave-and-true.chapter5 :refer :all]))

(deftest sum-test
  (testing "sum of list"
    (is (= 10
           (sum '(10))))
    (is (= 55
           (sum '(1 2 3 4 5 6 7 8 9 10)))
        (= 0
           (sum '()))))
  (testing "sum of list and initial value"
    (is (= 15
           (sum '(10) 5)))
    (is (= 155
           (sum '(1 2 3 4 5 6 7 8 9 10) 100)))
    (is (= 45
           (sum '() 45)))))

(deftest sum-use-recur-test
  (testing "sum of list by using sum-use-recur"
    (is (= 10
           (sum-use-recur '(10))))
    (is (= 55
           (sum-use-recur '(1 2 3 4 5 6 7 8 9 10)))
        (= 0
           (sum-use-recur '()))))
  (testing "sum of list and initial value by using sum-use-recur"
    (is (= 15
           (sum-use-recur '(10) 5)))
    (is (= 155
           (sum-use-recur '(1 2 3 4 5 6 7 8 9 10) 100)))
    (is (= 45
           (sum-use-recur '() 45)))))

(deftest clean-test
  (testing "removing trailing spaces"
    (is (= "aaaaa aaaaaa aaaaaaaaaaaaaaaaaa"
           (clean "aaaaa aaaaaa aaaaaaaaaaaaaaaaaa ")))
    (is (= ""
           (clean "              "))))
  (testing "replace lol to LOL"
    (is (= "LOL"
           (clean "lol")))
    (is (= "hogehogeLOLfugafuga"
           (clean "hogehogelolfugafuga"))))
  (testing "removing trailing spaces and replace lol to LOL"
    (is (= "My boa constrictor is so sassy LOL!"
           (clean "My boa constrictor is so sassy lol!  ")))))

(deftest comp-test
  (testing "multiplies the numbers two args and then increments the result"
    (is (= 7
           ((comp inc *) 2 3)))
    (is (= 10
           ((comp inc *) 3 3)))))

(deftest character-test
  (testing "return character's intelligence"
    (is (= 10
           (c-int character))))
  (testing "return character's strength"
    (is (= 4
           (c-str character))))
  (testing "return character's dexterity"
    (is (= 5
           (c-dex character)))))

(deftest spell-slots-test
  (testing "devide intelligence by 2 and add 1 and to integer."
    (is (= 6
           (spell-slots character)))
    (is (= 4
           (spell-slots {:attribute {:intelligence 7}})))))

(deftest spell-slots-comp-test
  (testing "return same value using spell-slots function"
    (is (= 6
           (spell-slots-comp character)))
    (is (= 4
           (spell-slots-comp {:attribute {:intelligence 7}})))))

(deftest two-comp-test
  (testing "return same value using comp"
    (is (= 7
           ((two-comp inc *) 2 3)))
    (is (= 10
           ((two-comp inc *) 3 3)))))

(deftest sleepy-identity-test
  (testing "return the given value after 1 second"
    (is (= "hoge"
           (sleepy-identity "hoge")))))

(deftest memo-sleepy-identity-test
  (testing "return the given value after 1second"
    (is (= "hoge"
           (memo-sleepy-identity "hoge"))))
  (testing "return the given value immediately"
    (is (= "hoge"
           (memo-sleepy-identity "hoge")))))

(deftest attr-test
  (testing "return passed key's value"
    (is (= (:intelligence (:attribute character))
           (attr :intelligence)))
    (is (= (:strength (:attribute character))
           (attr :strength)))
    (is (= (:dexterity (:attribute character))
           (attr :dexterity))))
  (testing "return null if passed key is not exist"
    (is (= nil
           (attr :hoge)))))

(deftest my-comp-test
  (testing "return value is same value using comp"
    (is (= ((comp inc inc) 1)
           ((my-comp inc inc) 1)))
    (is (= (map (comp str #(* 2 %) inc) (range 1 11))
           (map (my-comp str #(* 2 %) inc) (range 1 11))))))

(deftest my-assoc-in-test
  (testing "return value is same value using assoc-in"
    (let [user {:name "John" :age 18 :attribute {:birthday "2000-10-10"
                                                 :faborite-food "sushi"}}]
      (is (= (assoc-in user [:age] 19)
             (my-assoc-in user [:age] 19)))
      (is (= (assoc-in user [:attribute :faborite-food] "humberger")
             (my-assoc-in user [:attribute :faborite-food] "humberger"))))))

(deftest update-in-use-test
  (testing "return update value"
    (let [expected {:name "yurina kumai"
                    :birthday "1993-08-03"
                    :attribute {:group "Berryz Kobo"
                                :height 183
                                :blood-type "B"}}]
      (is (= expected
             (update-in kumai [:attribute :height] + 7))))))

(deftest my-update-in-test
  (testing "return same value using update-in"
    (let [u {:name "Paul" :age 20}]
      (is (= (update-in u [:age] + 10)
             (my-update-in u [:age] + 10))))
    (let [expected {:name "yurina kumai"
                    :birthday "1993-08-03"
                    :attribute {:group "Berryz Kobo"
                                :height 183
                                :blood-type "B"}}]
      (is (= (update-in kumai [:attribute :height] + 7)
             (my-update-in kumai [:attribute :height] + 7))))))
