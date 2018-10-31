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
    (is (= {1 {:connections {4 2}}, 4 {:connections {1 2}}}
           (connect {} 15 1 2 4)))
    (is (= {2 {:connections {7 4}}, 7 {:connections {2 4}}}
           (connect {} 15 2 4 7)))))

(deftest connect-right-test
  (testing "return connection if pos can connect ritght"
    (is (= {4 {:connections {6 5}} 6 {:connections {4 5}}}
           (connect-right {} 15 4)))
    (is (= {7 {:connections {9 8}} 9 {:connections {7 8}}}
           (connect-right {} 15 7)))
    (is (= {12 {:connections {14 13}} 14 {:connections {12 13}}}
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
    (is (= {1 {:connections {4 2}} 4 {:connections {1 2}}}
           (connect-down-left {} 15 1)))
    (is (= {3 {:connections {8 5}} 8 {:connections {3 5}}}
           (connect-down-left {} 15 3))))
  (testing "return board if pos can not connect down left"
    (is (= {}
           (connect-down-left {} 15 7)))
    (is (= {}
           (connect-down-left {} 15 15)))))

(deftest connect-down-right-test
  (testing "return connection if pos can connect down right"
    (is (= {1 {:connections {6 3}} 6 {:connections {1 3}}}
           (connect-down-right {} 15 1)))
    (is (= {2 {:connections {9 5}} 9 {:connections {2 5}}}
           (connect-down-right {} 15 2))))
  (testing "return board if pos can not connect down right"
    (is (= {}
           (connect-down-right {} 15 9)))
    (is (= {}
           (connect-down-right {} 6 3)))))

(deftest add-pos-test
  (testing "return the peg and performs connections"
    (is (= {1 {:connections {6 3, 4 2}, :pegged true}
            4 {:connections {1 2}}
            6 {:connections {1 3}}}
           (add-pos {} 15 1)))))

(def five-rows-board {1  {:pegged true, :connections {6 3, 4 2}},
                      2  {:pegged true, :connections {9 5, 7 4}},
                      3  {:pegged true, :connections {10 6, 8 5}},
                      4  {:pegged true, :connections {13 8, 11 7, 6 5, 1 2}},
                      5  {:pegged true, :connections {14 9, 12 8}},
                      6  {:pegged true, :connections {15 10, 13 9, 4 5, 1 3}},
                      7  {:pegged true, :connections {9 8, 2 4}},
                      8  {:pegged true, :connections {10 9, 3 5}},
                      9  {:pegged true, :connections {7 8, 2 5}},
                      10 {:pegged true, :connections {8 9, 3 6}},
                      11 {:pegged true, :connections {13 12, 4 7}},
                      12 {:pegged true, :connections {14 13, 5 8}},
                      13 {:pegged true, :connections {15 14, 11 12, 6 9, 4 8}},
                      14 {:pegged true, :connections {12 13, 5 9}},
                      15 {:pegged true, :connections {13 14, 6 10}},
                      :rows 5})

(def five-rows-board {1  {:pegged true, :connections {6 3, 4 2}},
                      2  {:pegged true, :connections {9 5, 7 4}},
                      3  {:pegged true, :connections {10 6, 8 5}},
                      4  {:pegged true, :connections {13 8, 11 7, 6 5, 1 2}},
                      5  {:pegged true, :connections {14 9, 12 8}},
                      6  {:pegged true, :connections {15 10, 13 9, 4 5, 1 3}},
                      :rows 5})

(def three-rows-board {:rows 3,
                       1 {:pegged true, :connections {4 2, 6 3}},
                       4 {:connections {1 2, 6 5}, :pegged false},
                       6 {:connections {1 3, 4 5}, :pegged false},
                       2 {:pegged true},
                       3 {:pegged true},
                       5 {:pegged true}})

(deftest new-board-test
  (testing "return a board with the given number of rows"
    (is (= five-rows-board
           (new-board 5)))))

(deftest pegged?-test
  (testing "return true if the position has a peg"
    (is (= true
           (pegged? five-rows-board 5))))
  (testing "return false if the position does not have a peg"
    (is (= false
           (pegged? three-rows-board 4)))))
