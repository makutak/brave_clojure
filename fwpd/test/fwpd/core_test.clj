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

(deftest mapify-test
  (testing "return seq of map"
    (is (= '({:name "Edward Cullen", :glitter-index 10}
             {:name "Bella Swan", :glitter-index 0}
             {:name "Charlie Swan", :glitter-index 0}
             {:name "Jacob Black", :glitter-index 3}
             {:name "Carlisle Cullen", :glitter-index 6})
           (mapify (parse (slurp filename)))))))

(deftest glitter-filter-test
  (testing "return filtering seq of map less than minimum-glitter"
    (is (= '({:name "Edward Cullen", :glitter-index 10}
             {:name "Jacob Black", :glitter-index 3}
             {:name "Carlisle Cullen", :glitter-index 6})
           (glitter-filter 3 (mapify (parse (slurp filename))))))
    (is (= '({:name "Edward Cullen", :glitter-index 10})
           (glitter-filter 10 (mapify (parse (slurp filename))))))
    (is (= '()
           (glitter-filter 20 (mapify (parse (slurp filename))))))))

(deftest name-list-test
  (testing "fliter results to list of name"
    (is (= '("Edward Cullen" "Jacob Black" "Carlisle Cullen")
           (name-list (glitter-filter 3 (mapify (parse (slurp filename)))))))))

(deftest append-test
  (testing "append new element to list"
    (is (= '({:name "saki" :color "yellow"}
             {:name "momoko" :color "pink"}
             {:name "chinami" :color "orange"})
           (append '({:name "saki" :color "yellow"}
                     {:name "momoko" :color "pink"})
                   {:name "chinami" :color "orange"})))))
