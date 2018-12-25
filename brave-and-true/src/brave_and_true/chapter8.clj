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

(defn critisize-code
  [critisism code]
  `(println ~critisism (quote ~code)))

(defmacro code-critic
  "Phrase are courtesy Hermes Conrad from Futurama"
  [bad good]
  `(do ~@(map #(apply critisize-code %)
             [["Great squid of Madrid, this is bad code:" bad]
              ["Sweet gorilla of Manila, this is good code:" good]])))

(def message "Good Job!")

(defmacro with-mischief
  [& stuff-to-do]
  `(let [message# "Oh, big deal!"]
    ~@stuff-to-do
    (println "I still need to say:" message#)))
