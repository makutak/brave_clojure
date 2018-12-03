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
