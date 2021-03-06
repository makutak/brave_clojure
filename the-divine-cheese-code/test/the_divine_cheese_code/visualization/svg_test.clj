(ns the-divine-cheese-code.visualization.svg-test
  (:require [clojure.test :refer :all]
            [the-divine-cheese-code.visualization.svg :refer :all])
  (:refer-clojure :exclude [min max]))

(deftest latlng->point-test
  (testing "convert map to commpa-separated string"
    (is (= "hoge, fuga"
           (latlng->point {:lat "hoge" :lng "fuga"})))))

(deftest points-test
  (testing "convert vector to space-separated and commpa-separated string"
    (is (= "hoge, fuga foo, bar"
           (points [{:lat "hoge" :lng "fuga"}
                    {:lat "foo"  :lng "bar"}])))))

(def testdatas [{:lat 100 :lng 1000}
               {:lat 10 :lng 100}
               {:lat 91 :lng 1024}])

(deftest comparator-over-maps-test
  (testing "return new map"
    (is (= {:lat 10 :lng 100}
           ((comparator-over-maps clojure.core/min [:lat :lng]) testdatas)))
    (is (= {:lat 100 :lng 1024}
           ((comparator-over-maps clojure.core/max [:lat :lng]) testdatas)))))

(deftest translate-to-00-test
  (testing "return new map which value is subtracted"
    (is (= [{:lat 0 :lng 0}]
           (translate-to-00 [{:lat 10 :lng 10}])))))

(deftest scale-test
  (testing "return new map which value is multiplied"
    (is (= [{:lat 100 :lng 100}]
           (scale 1 1 [{:lat 10 :lng 10}])))))
