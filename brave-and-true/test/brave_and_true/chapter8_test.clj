(ns brave-and-true.chapter8-test
  (:require [clojure.test :exclude [report] :refer :all]
            [brave-and-true.core :refer :all]
            [brave-and-true.chapter8 :refer :all]))

(deftest my-print-test
  (testing "my-print is resutn args"
    (is (= "hoge"
           (my-print "hoge"))))
  (testing "expand let form"
    (is (= '(let* [result "my-print!"]
              (println result)
              result)
           (macroexpand '(my-print "my-print!")))))
  (testing "calc"
    (is (= 2
           (my-print (+ 1 1))))))

(deftest when-test
  (testing "when is macro"
    (is (= '(if true
              (do
                (println 1)
                (println 2)
                (+ 1 2)))
           (macroexpand '(when true
                           (println 1)
                           (println 2)
                           (+ 1 2)))))))

(deftest unless-test
  (testing "unless"
    (is (= false
           (unless true
                   true
                   false)))
    (is (= "fuga"
           (unless (= 1 1)
                   (str "hoge")
                   (str "fuga")))))
  (testing "expanded is if special form"
    (is (= '(if true
              false
              true)
           (macroexpand '(unless true
                                  true
                                  false))))))

(deftest syntax-quote-test
  (testing "syntax quote returns fully qualified symbols"
    (is (= 'clojure.core/+
           `+))
    (is (= '(clojure.core/+ 1 1)
           `(+ 1 1)))))

(deftest unquote-test
  ;; ruby の "string #{foo}" みたいな感じに使えるらしい
  (testing "inside syntax quote, using tilde return evaluated value"
    (is (= '(clojure.core/+ 1 2)
           `(+ 1 ~(inc 1))))))

(deftest code-critic-test
  (testing "println bad and good"
    (is (= (str "Great squid of Madrid, this is bad code: (1 + 1)\n"
                "Sweet gorilla of Manila, this is good code: (+ 1 1)\n")
           (with-out-str (code-critic (1 + 1) (+ 1 1)))))))

(deftest with-mischief-test
  (testing "let's bindings message is Good Job!"
    (is (= (str "Here's how I feel about that thing you did: " message "\n"
                "I still need to say: Oh, big deal!\n" )
           (with-out-str
             (with-mischief
               (println "Here's how I feel about that thing you did:" message )))))))

(deftest macro-with-doseq-test
    (testing "macro receive evaluated value"
    (is (= (str "(= 1 1) was successful: true" "\n"
                "(= 1 2) was successful: false" "\n")
           (with-out-str
             (doseq [code ['(= 1 1) '(= 1 2)]]
               (brave-and-true.chapter8/report code)))))))
