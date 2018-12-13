(ns brave-and-true.chapter8
  (:require [clojure.core]))

(defmacro my-print
  [expression]
  (list 'let ['result expression]
        (list 'println 'result)
        'result))