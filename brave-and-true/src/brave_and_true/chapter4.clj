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

(def vampire-database
  {0 {:makes-blood-puns? false, :has-pulse? true  :name "McFishwich"}
   1 {:makes-blood-puns? false, :has-pulse? true  :name "McMackson"}
   2 {:makes-blood-puns? true,  :has-pulse? false :name "Damon Salvatore"}
   3 {:makes-blood-puns? true,  :has-pulse? true  :name "Mickey Mouse"}})

(defn vampire-related-details
  [social-security-number]
  ;;(Thread/sleep 1000)
  (get vampire-database social-security-number))

(defn vampire?
  [record]
  (and (:makes-blood-puns? record)
       (not (:has-pulse? record))
       record))

(defn identify-vampire
  [social-security-numbers]
  (first (filter vampire?
                 (map vampire-related-details social-security-numbers))))

(defn even-numbers
  ;;lazy-seqは必要になるまで評価されないので、再帰がstack overflowになることもない
  ([] (even-numbers 0))
  ([n] (cons n (lazy-seq (even-numbers (+ n 2))))))

(defn my-conj
  [target & additoins]
  (into target additoins))

(defn my-into
  [target additons]
  (apply conj target additons))

(defn my-partial
  [partialize-fn & args]
  (fn [& more-args]
    (apply partialize-fn (into args more-args))))

(defn lousy-logger
  [log-level message]
  (condp = log-level
    :warn (clojure.string/lower-case message)
    :emergency (clojure.string/upper-case message)))

(def warn (partial lousy-logger :warn))
(def emergency (partial lousy-logger :emergency))

(def not-vampire? (complement vampire?))
(defn identify-human
  [social-security-numbers]
  (filter not-vampire?
          (map vampire-related-details social-security-numbers)))

(defn my-complement
  [fun]
  (fn [& args]
    (not (apply fun args))))

(def my-pos (my-complement neg?))
