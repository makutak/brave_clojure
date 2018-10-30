(ns pegthing.core-test
  (:require [clojure.test :refer :all]
            [pegthing.core :refer :all]))

(deftest tri*-test
  (testing "return triaugnler number sequence."
    (is (= '(1 3 6 10 15)
           (take 5 (tri*))))
    (is (= '(1 3 6 10 15 21 28)
           (take 7 (tri*))))
    (is (= '(0 1 3 6 10)
           (take 5 (tri* 0 0))))))

(deftest triangular?-test
  (testing "return args is the number triangular number"
    (is (= true
           (triangular? 6)))
    (is (= false
           (triangular? 4)))
    (is (= true
           (triangular? 5050)))))

(deftest row-tri-test
  (testing "return the triangular number at the end of row"
    (is (= 15
           (row-tri 5)))
    (is (= 21
           (row-tri 6)))
    (is (= 5050
           (row-tri 100)))))

(deftest row-num-test
  (testing "return number the position in the row"
    (is (= 2
           (row-num 2)))
    (is (= 2
           (row-num 3)))
    (is (= 5
           (row-num 15)))))

(deftest assoc-in-test
  (testing "assoc-in make nested map"
    (is (= {:cookie {:monster {:vocals "Finntroll"}}}
           (assoc-in {} [:cookie :monster :vocals] "Finntroll")))))

(deftest get-in-test
  (testing "get-in look up values in nested map"
    (is (= "Finntroll"
           (get-in {:cookie {:monster {:vocals "Finntroll"}}} [:cookie :monster :vocals])))
    (is (= {:vocals "Finntroll"}
           (get-in {:cookie {:monster {:vocals "Finntroll"}}} [:cookie :monster])))))

(deftest connect-test
  (testing "return board if destination is begger than max-position"
    (is (= {}
           (connect {} 1 1 1 2)))
    (is (= {}
           (connect {} 15 1 2 400))))
  (testing "return connection between two postion"
    (is (= {1 {:connection {4 2}}, 4 {:connection {1 2}}}
           (connect {} 15 1 2 4)))
    (is (= {2 {:connection {7 4}}, 7 {:connection {2 4}}}
           (connect {} 15 2 4 7)))))

(deftest connect-right-test
  (testing "return connection if pos can connect ritght"
    (is (= {4 {:connection {6 5}} 6 {:connection {4 5}}}
           (connect-right {} 15 4)))
    (is (= {7 {:connection {9 8}} 9 {:connection {7 8}}}
           (connect-right {} 15 7)))
    (is (= {12 {:connection {14 13}} 14 {:connection {12 13}}}
           (connect-right {} 15 12))))
  (testing "return board if pos can not connect right"
    (is (= {}
           (connect-right {} 15 1)))
    (is (= {}
           (connect-right {} 15 5)))
    (is (= {}
           (connect-right {} 15 9)))))

(deftest connect-down-left-test
  (testing "return connectoin if pos can connect down left"
    (is (= {1 {:connection {4 2}} 4 {:connection {1 2}}}
           (connect-down-left {} 15 1)))
    (is (= {3 {:connection {8 5}} 8 {:connection {3 5}}}
           (connect-down-left {} 15 3))))
  (testing "return board if pos can not connect down left"
    (is (= {}
           (connect-down-left {} 15 7)))
    (is (= {}
           (connect-down-left {} 15 15)))))

(deftest connect-down-right-test
  (testing "return connection if pos can connect down right"
    (is (= {1 {:connection {6 3}} 6 {:connection {1 3}}}
           (connect-down-right {} 15 1)))
    (is (= {2 {:connection {9 5}} 9 {:connection {2 5}}}
           (connect-down-right {} 15 2))))
  (testing "return board if pos can not connect down right"
    (is (= {}
           (connect-down-right {} 15 9)))
    (is (= {}
           (connect-down-right {} 6 3)))))
