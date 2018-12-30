(ns brave-and-true.chapter8-test
  (:require [clojure.test :exclude [report] :refer :all]
            [brave-and-true.core :refer :all]
            [brave-and-true.chapter8 :refer :all]))

(deftest ^:not-lein my-print-test
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
           (macroexpand-1 '(when true
                             (println 1)
                             (println 2)
                             (+ 1 2)))))))

(deftest ^:not-lein unless-test
  (testing "unless"
    (is (= false
           (unless true
                   true
                   false)))
    (is (= "fuga"
           (unless (= 1 1)
                   (str "hoge")
                   (str "fuga")))))
  (testing "expanded unless is if special form"
    (is (= '(if true
              false
              true)
           (macroexpand-1 '(unless true
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
                "(= 1 2) was not successful: false" "\n")
           (with-out-str
             (doseq-macro report (= 1 1) (= 1 2)))))))

(deftest error-messages-for-test
  (testing "if invalid, return seq of error messages"
    (is (= '("empty")
           (error-messages-for "" ["empty" not-empty]))))
  (testing "if valid, return empty seq"
    (is (= '()
           (error-messages-for "not-empty" ["empty" not-empty])))))

(deftest validate-test
  (testing "validate order"
    (is (= {:email ["Your email address doesn't look like an email address."]}
           (validate order-details order-details-validations)))
    (is (= {:name ["Please enter a name."]}
           (validate {:name "" :email "example@example.com"} order-details-validations)))
    (is (= {:name ["Please enter a name."]
            :email ["Please enter an email address."
                    "Your email address doesn't look like an email address."]}
           (validate {:name "" :email ""} order-details-validations)))
    (is (= {}
           (validate {:name "foo" :email "example@example.com"} order-details-validations)))))

(deftest if-valid-test
  (testing "if valid, return :success"
    (is (= {:status "success" :details ""}
           (if-valid  {:name "foo" :email "example@example.com"} order-details-validations my-errors
                      {:status "success" :details ""}
                      {:status "failure" :details my-errors}))))
  (testing "if invalid, return :failure and error-messages"
    (is (= {:status "failure"
            :details {:email ["Your email address doesn't look like an email address."]}}
           (if-valid order-details order-details-validations my-errors
                     {:status "success" :details ""}
                     {:status "failure" :details my-errors})))))

(deftest when-valid-test
  (testing "if valid, exectute like when"
    (is (= {:status "success"}
           (when-valid {:name "foo" :email "example@example.com"} order-details-validations
                       (println "success")
                       {:status "success"}))))
  (testing "if invalid, return null"
    (is (= nil
           (when-valid order-details order-details-validations
                       (println "success")
                       {:status "success"})))))

(deftest my-or-test
  (testing "or"
    (is (= true
           (my-or (= 1 2) (= 1 1))))
    (is (= false
           (my-or (= 1 2) (= 1 2))))))

(defattrs test-c-int :intelligence test-c-str :strength test-c-dex :dexterity)

(deftest defattrs-test
  (testing "define an arbitrary number of attribute-retrieving functions"
    (is (= 10
           (test-c-int character)))
    (is (= 4
           (test-c-str character)))
    (is (= 5
           (test-c-dex character)))))
