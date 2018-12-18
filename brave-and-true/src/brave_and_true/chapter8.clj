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
  `(do (println "Great squid of Madrid, this is bad code:"
                (quote ~bad))
       (println "Sweet gorilla of Manila, this is good code:"
                (quote ~good))))
