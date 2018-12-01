(ns brave-and-true.chapter7
  (:require [clojure.core]))

(defmacro backwards
  [form]
  (reverse form))
