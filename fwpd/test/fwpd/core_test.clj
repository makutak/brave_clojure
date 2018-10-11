(ns fwpd.core-test
  (:require [clojure.test :refer :all]
            [fwpd.core :refer :all]))

(deftest filename-test
  (testing "slurp reading file and return string"
    (is (= "Edward Cullen,10\nBella Swan,0\nCharlie Swan,0\nJacob Black,3\nCarlisle Cullen,6\n"
           (slurp filename)))))

(deftest convert-test
  (testing "convert resturns passed value if vamp-key is ':name'."
    (is (= "hoge"
           (convert :name "hoge"))))
  (testing "convert returns int value if vamp-key is :glitter-index"
    (is (= 3
           (convert :glitter-index "3")))))

(deftest parse-test
  (testing "parse converts string to seq of vectors"
    (is (= '(["Tokyo" "Tokyo"] ["Kanagawa" "Yokohama"] ["Chiba" "Chiba"] ["Saitama" "Saitama"])
           (parse "Tokyo,Tokyo\nKanagawa,Yokohama\nChiba,Chiba\nSaitama,Saitama\n")))))
