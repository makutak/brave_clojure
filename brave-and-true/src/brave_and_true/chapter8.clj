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

(defmacro report
  [to-try]
  `(let [result# ~to-try]
    (if result#
       (println (quote ~to-try) "was successful:" result#)
       (println (quote ~to-try) "was not successful:" result#))))

(defmacro doseq-macro
  [macroname & args]
  `(do
     ~@(map (fn [arg] (list macroname arg)) args)))

(def order-details
  {:name "Mitchard Blimmons"
   :email "mitchard.blimmonsgmail.com"})

(def order-details-validations
  {:name
   ["Please enter a name." not-empty]
   :email
   ["Please enter an email address." not-empty
    "Your email address doesn't look like an email address."
    #(or (empty %) (re-seq #"@" %))]})

(defn error-messages-for
  "Return a seq of error messages"
  [to-validate message-validator-pairs]
  (map first (filter #(not ((second %) to-validate))
                     (partition 2 message-validator-pairs))))

(defn validate
  "Returns a map with a vector of errors for each key"
  [to-validate validations]
  (reduce (fn [errors validation]
            (let [[fieldname validation-check-group] validation
                  value (get to-validate fieldname)
                  error-messages (error-messages-for value validation-check-group)]
              (if (empty? error-messages)
                errors
                (assoc errors fieldname error-messages))))
          {}
          validations))

(defmacro if-valid
  "Handle validation more concisely"
  [to-validate validations errors-name & then-else]
  `(let [~errors-name (validate ~to-validate ~validations)]
     (if (empty? ~errors-name)
         ~@then-else)))

(defmacro when-valid
  [to-validate validations & then]
  `(let [errors-name# (validate ~to-validate ~validations)]
     (when (empty? errors-name#)
       ~@then)))

(defmacro my-or
  ([] nil)
  ([x] x)
  ([x & next]
   `(let [or# ~x]
      (if or#
        or#
        (my-or ~@next)))))

(def character
  {:name "Smooches McCutes"
   :attributes {:intelligence 10
                :strength 4
                :dexterity 5}})

(defmacro defattrs
  [& attributes]
  (let [pairs# (partition 2 attributes)]
    `(do
       ~@(map (fn [[fun-name attr-name]]
                (list 'def fun-name (comp attr-name :attributes)))
              pairs#))))
