(ns brave-and-true.chapter5
  (:gen-class)
  (:require [clojure.tools.trace :as trace])
  (:require [clojure.string :as s]))

;; traceはciderで可能
;; C-c M-t v <function-name> => function-nameのtraceができる。
;; C-c M-t n => namespace全体の関数のtraceができる。

(defn sum
  ([vals]
   (sum vals 0))
  ([vals accumulating-total]
   (if (empty? vals)
     accumulating-total
     (sum (rest vals)
          (+ (first vals) accumulating-total)))))

(defn sum-use-recur
  ([vals]
   (sum-use-recur vals 0))
  ([vals accumulating-total]
   (if (empty? vals)
     accumulating-total
     (recur (rest vals)
            (+ (first vals) accumulating-total)))))

(defn clean
  [text]
  (s/replace (s/trim text) #"lol" "LOL"))

(def character
  {:name "Smooches McCutes"
   :attribute {:intelligence 10
               :strength 4
               :dexterity 5}})

(def c-int (comp :intelligence :attribute))
(def c-str (comp :strength :attribute))
(def c-dex (comp :dexterity :attribute))

(defn spell-slots
  [char]
  (int (inc (/ (c-int char) 2))))

(def spell-slots-comp (comp int inc #(/ % 2) c-int))

(defn two-comp
  [f g]
  (fn [& args]
    (f (apply g args))))

(defn sleepy-identity
  "Return the given value after 1 second"
  [x]
  (Thread/sleep 1000)
  x)

;;同じ引数で呼ばれるときは、1秒待たずに実行される
(def memo-sleepy-identity (memoize sleepy-identity))

;; Exercises
;; 1
(defn attr
  [key]
  (get-in character  [:attribute key]))

;; 2 Implement the comp function.
(defn my-comp
  ([] identity)
  ([f] f)
  ([f g]
   (fn
     ([] (f (g)))
     ([& args] (f (apply g args)))))
  ([f g & fs]
   (reduce my-comp (list* f g fs))))

;; 3 Implement the assoc-in function.
(defn my-assoc-in
  [m [k & ks] v]
  (if ks
    (assoc m k (my-assoc-in (get m k) ks v))
    (assoc m k v)))

;; 4 Look up and use the update-in function.
(def kumai {:name "yurina kumai"
            :birthday "1993-08-03"
            :attribute {:group "Berryz Kobo"
                        :height 176
                        :blood-type "B"}})


;; 5 Implement update-in.
(defn my-update-in
  [m [k & ks] f & args]
  (if ks
    (assoc m k (apply my-update-in (get m k) ks f args))
    (assoc m k (apply f (get m k) args))))
