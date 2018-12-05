(ns brave-and-true.chapter7
  (:require [clojure.core]))

(defmacro backwards
  [form]
  (reverse form))

(def addition-list (list + 1 2))

(eval (list 'def 'lucky-number (concat addition-list [10])))

;; macroは reader -> macroexpand -> evaluator という順で実行される
(defmacro ignore-last-operand
  [fun-call]
  (butlast fun-call))

(defmacro infix
  [infixed]
  (list (second infixed)
        (first infixed)
        (last infixed)))

;; Exercises
;; 1 reference to test

;; 2
(def priority-ops '(* /))

(defn calc-infix
  [infixed]
  (eval (cond
          (= (count infixed) 3) (list (second infixed)
                                      (first infixed)
                                      (last infixed))
          :else (let [rev (reverse infixed)
                      operand (first rev)
                      operator (second rev)
                      rest-expr (nthrest rev 2)]
                  (if (some #(= operator %) priority-ops)
                    (list operator operand (calc-infix rest-expr))
                    (list operator  (calc-infix rest-expr) operand))))))
