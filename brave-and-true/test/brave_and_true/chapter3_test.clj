(ns brave-and-true.chapter3-test
  (:require [clojure.test :refer :all]
            [brave-and-true.core :refer :all]
            [brave-and-true.chapter3 :refer :all]))

(deftest foo-test
  (testing "foo-test"
    (is (= "this is chapter-3" (foo)))))
