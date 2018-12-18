(ns brave-and-true.chapter8
  (:require [clojure.core]))

(defmacro my-print
  [expression]
  (list 'let ['result expression]
        (list 'println 'result)
        'result))

(defmacro unless
  [test & body]
  (conj (reverse body) test 'if))

(defmacro code-critic
  "Phrase are courtesy Hermes Conrad from Futurama"
  [bad good]
  (list 'do
        (list 'println
              "Great squid of Madrid, this is bad code:"
              (list  'quote bad))
        (list 'println
              "Sweet gorilla of Manila, this is good code:"
              (list 'quote good))))
