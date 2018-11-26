(ns the-divine-cheese-code.visualization.svg
  (:require [clojure.string :as s])
  (:refer-clojure :exclude [min max]))

(defn comparator-over-maps
  [comparison-fn ks]
  (fn [maps]
    (zipmap ks
            (map (fn [k] (apply comparison-fn (map k maps)))
                 ks))))

(def min (comparator-over-maps clojure.core/min [:lat :lng]))
(def max (comparator-over-maps clojure.core/max [:lat :lng]))

(defn translate-to-00
  [locations]
  (let [minrecord (min locations)]
    (map #(merge-with - % minrecord) locations)))

(defn scale
  [width height locations]
  (let [maxrecord (max locations)
        ratio {:lat (/ height (:lat maxrecord))
               :lng (/ width  (:lng maxrecord))}]
    (map #(merge-with * % maxrecord) locations)))

(defn latlng->point
  "Converting lat/lng map to commpa-separated string"
  [latlng]
  (str (:lat latlng) ", " (:lng latlng)))

(defn points
  [locations]
  (clojure.string/join " " (map latlng->point locations)))
