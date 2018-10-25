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


;; (defn -main
;;   "I don't do a whole lot ... yet."
;;   [& args]
;;   (println "Hello, World!"))
