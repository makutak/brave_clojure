(ns brave-and-true.chapter7-test
  (:require [clojure.test :refer :all]
            [brave-and-true.core :refer :all]
            [brave-and-true.chapter7 :refer :all]))

(deftest backwards-test
  (testing "backwards"
    (is (= "i am backwards"
           (backwards ("backwards" "am " "i " str))))
    (is (= ["bar" "foo"]
           (backwards ("foo" "bar" vector))))))

(deftest eval-test
  (testing "eval"
    (is (= 3
           (eval addition-list)))
    (is (= 100
           (eval (list * 10 10))))
    (is (= 10
           (eval (list / 100 10))))
    (is (= ["foo" "bar"]
           (eval (list vector "foo" "bar"))))
    (is (= 13
           (eval (concat addition-list [10]))))
    (is (= 13
           lucky-number))))

(deftest infix-test
  (testing "infix"
    (is (= 2
           (eval
            (let [infix (read-string "(1 + 1)")]
              (list (second infix)
                    (first infix)
                    (last infix))))))))

(deftest ignore-last-operand-test
  (testing "passing fun call and evaludated value expect last item"
    (is (= 3
           (ignore-last-operand (+ 1 2 3))))
    (is (= 10
           (ignore-last-operand (+ 3 7 (println "hello world")))))))

(deftest ^:not-lein macroexpand-test
  (testing "using macroexpand"
    (is (= '(+ 1 1)
           (macroexpand '(ignore-last-operand (+ 1 1 1)))))
    (is (= '(+ 3 7)
           (macroexpand '(ignore-last-operand (+ 3 7 (println "hello world"))))))))

(deftest infix-macro-test
  (testing "be able to calculate infix notation"
    (is (= 10
           (infix (3 + 7))))
    (is (= -99
           (infix (1 - 100))))
    (is (= 100
           (infix (10 * 10))))
    (is (= 5
           (infix (25 / 5))))))

(deftest ^:not-lein arrow-macro-test
  (testing "function is same"
    (is (= '(read-string (slurp (clojure.java.io/resource path)))
           (macroexpand
            '(-> path
                 clojure.java.io/resource
                 slurp
                 read-string))))))

(deftest exercise-1-test
  (testing "print my name and favorite sci-fi movie"
    (is (= '("kouno" "star wars")
           (eval (read-string "(list \"kouno\" \"star wars\"))"))))
    (is (= '("kouno" "star wars")
           (eval (read-string "'(\"kouno\" \"star wars\"))"))))))

(deftest parse-test
  (testing "calculate infix"
    (is (= 8
           (int (parse '(1 + 3 * 4 - 5)))))
    (is (= 85
           (int (parse '(38 + 48 - 2 / 2)))))
    (is (= -29
           (int (parse '(1 * 3 - 8 * 4)))))
    (is (= 15
           (int (parse '(1 - 2 * 3 + 4 * 5 )))))))
