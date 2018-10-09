(ns fwpd.core-test
  (:require [clojure.test :refer :all]
            [fwpd.core :refer :all]))

(deftest filename-test
  (testing "slurp reading file and return string"
    (is (= "Edward Cullen,10\nBella Swan,0\nCharlie Swan,0\nJacob Black,3\nCarlisle Cullen,6\n"
           (slurp filename)))))
