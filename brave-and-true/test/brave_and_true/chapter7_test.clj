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
