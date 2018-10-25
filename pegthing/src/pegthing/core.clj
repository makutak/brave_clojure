(ns pegthing.core
  (require [clojure.set :as set])
  ;; allows you to run the program from the command line
  (:gen-class))

;;allows functions to refer to those names before they're define.
(declare successful-move prompt-move game-over query-rows)

(defn tri*
  "Generates lazy sequence of triaugnler number."
  ([] (tri* 0 1))
  ([sum n]
   (let [new-sum (+ sum n)]
     (cons new-sum (lazy-seq (tri* new-sum (inc n)))))))

(def tri (tri*))

(defn triangular?
  "Is the number triangular? e.g. 1, 3, 6, 10, 15, etc"
  [n]
  (= n (last (take-while #(>= n %) tri))))

(defn row-tri
  "The triangular number at the end of row n"
  [n]
  (last (take n tri)))

(defn row-num
  "Returns row number the position belongs to: pos 1 in row 1,
  positions 2 and 3 in row 2, etc"
  [pos]
  (inc (count (take-while #(> pos %) tri))))


;; (defn -main
;;   "I don't do a whole lot ... yet."
;;   [& args]
;;   (println "Hello, World!"))
