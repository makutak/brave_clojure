(ns the-divine-cheese-code.visualization.svg
  (:require [clojure.string :as s])
  (:refer-clojure :exclude [min max]))

(defn latlng->point
  "Converting lat/lng map to commpa-separated string"
  [latlng]
  (str (:lat latlng) ", " (:lng latlng)))

(defn points
  [locations]
  (clojure.string/join " " (map latlng->point locations)))
