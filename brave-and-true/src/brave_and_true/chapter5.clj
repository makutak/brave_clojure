(ns brave-and-true.chapter5
  (:gen-class)
  (:require [clojure.tools.trace :as trace]))

(trace/trace-ns brave-and-true.chapter5)

(defn sum
  ([vals]
   (sum vals 0))
  ([vals accumulating-total]
   (if (empty? vals)
     accumulating-total
     (sum (rest vals)
          (+ (first vals) accumulating-total)))))

(defn sum-use-recur
  ([vals]
   (sum-use-recur vals 0))
  ([vals accumulating-total]
   (if (empty? vals)
     accumulating-total
     (recur (rest vals)
            (+ (first vals) accumulating-total)))))
