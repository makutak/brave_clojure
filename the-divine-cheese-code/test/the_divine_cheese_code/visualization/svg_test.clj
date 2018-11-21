(ns the-divine-cheese-code.core-test
  (:require [clojure.test :refer :all]
            [the-divine-cheese-code.visualization.svg :refer :all]))

(deftest latlng->point-test
  (testing "convert map to commpa-separated string"
    (is (= "hoge, fuga"
           (latlng->point {:lat "hoge" :lng "fuga"})))))

(deftest points-test
  (testing "convert vector to space-separated and commpa-separated string"
    (is (= "hoge, fuga foo, bar"
           (points [{:lat "hoge" :lng "fuga"}
                    {:lat "foo"  :lng "bar"}])))))
