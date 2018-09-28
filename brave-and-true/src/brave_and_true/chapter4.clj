(ns brave-and-true.chapter4 (:gen-class))

(defn title
  []
  (str "This is Chapter-4!!"))

(defn titlelize
  [topic]
  (str topic " for the Brave and True"))

(defn unify-diet-data
  [human critter]
  {:human human
   :critter critter})

(defn sum
  [numbers]
  (reduce + numbers))

(defn avg
  [numbers]
  (/ (sum numbers)
     (count numbers)))

(defn stats
  [numbers]
  (map #(% numbers) [sum count avg]))
