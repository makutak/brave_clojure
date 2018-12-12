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

(def operators-order [{:operator '+ :order 1}
                      {:operator '- :order 1}
                      {:operator '* :order 2}
                      {:operator '/ :order 2}])

(def priorities (map :operator (filter #(= (:order %) 2) operators-order)))
(def normals (map :operator (filter #(= (:order %) 1) operators-order)))

(defn calc?
  [ops values]
  (and (not (nil? (first ops)))
       (not (nil? (first values)))))

(defn calc
  [operator operand1 operand2]
  (eval (list operator
              operand1
              operand2)))


(defn calc-order
  [operator]
  (:order (first (filter #(= (:operator %) operator) operators-order))))

(defn parse
  [infixed]
  (loop [tokens infixed
         ops '()
         numbers '()]
    (do
      (println " ")
      (println "tokens" tokens)
      (println "ops" ops)
      (println "numbers" numbers))
    (let [token (first tokens)
          remains (rest tokens)]
      (do (println "token" token)
          (println "remains" remains))
      (cond
        (nil? token) (calc (first ops)  (second numbers) (first numbers))
        (number? token) (recur (rest tokens)
                               ops
                               (cons token numbers))
        (ifn? token) (cond
                       (empty? ops) (recur remains
                                           (cons token ops)
                                           numbers)
                       ;; 計算可能で、かつ、現演算子がスタックの一番の上の演算子より優先順位が高ければ、
                       ;; 現演算子で計算する
                       (and (calc? ops numbers)
                            (some #(= token %) priorities)
                            (> (calc-order token)
                               (calc-order (first ops))))

                       (do
                         (println "priority")
                         (recur (rest remains)
                                ops
                                (cons (calc token
                                            (first numbers)
                                            (first remains))
                                      (rest numbers))))
                       ;;計算可能で、現演算子がスタックの一番の上の演算子と同じ優先順位の場合
                       ;;スタックの値を計算する
                       (and (calc? ops numbers)
                            (= (calc-order token)
                               (calc-order (first ops))))
                       (do
                         (println "normal")
                         (recur remains
                                (cons token (rest ops))
                                (cons (calc (first ops)
                                            (second numbers)
                                            (first numbers))
                                      (rest (rest numbers)))))
                       ;;計算不可能なときはスタックに積んで次へ行く
                       :else
                       (do
                         (println "else")
                         (recur remains
                                (cons token ops)
                                numbers)))))))
