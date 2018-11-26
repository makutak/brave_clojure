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

(def testdata [{:min 100 :max 1000}
               {:min 10 :max 100}
               {:min 91 :max 1024}])

(deftest comparator-over-maps-test
  (testing "return new map"
    (is (= {:min 10 :max 100}
           ((comparator-over-maps clojure.core/min [:min :max]) testdata)))
    (is (= {:min 100 :max 1024}
           ((comparator-over-maps clojure.core/max [:min :max]) testdata)))))
