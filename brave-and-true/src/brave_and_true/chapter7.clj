(ns brave-and-true.chapter7
  (:require [clojure.core]))

(defmacro backwards
  [form]
  (reverse form))

(def addition-list (list + 1 2))

(eval (list 'def 'lucky-number (concat addition-list [10])))

(defmacro ignore-last-operand
  [fun-call]
  (butlast fun-call))

(defmacro infix
  [infixed]
  (list (second infixed)
        (first infixed)
        (last infixed)))
