(ns brave-and-true.chapter3 (:gen-class))

(defn title
  []
  (str "this is chapter-3"))

(defn error-message
  [severity]
  (str "OH GOD! IT'S A DISASTER! WE'RE "
       (if (= severity :mild)
         "MILDLY INCONVENIENCED!"
         "DOOOOOOOMED!")))

(defn too-enthusiastic ;;function name.
  "Return a cheer that might be a bit too enthusiastic"
  ;;Docstring.
  ;;In repl, clojure.repl/doc 'fun-name', then display docstring.
  [name] ;;paramater
  (str "OH. MY. GOD! " name " YOU ARE MOST DEFINITELY LIKE THE BEST "
       "MAN SLASH WOMAN EVER I LOVE YOU AND WE SHOULD RUN AWAY SOMEWHERE")) ;;function body


(defn x-chop
  "Describe the kind of chop you'are inflicting on someone."
  ([name chop-type]
   (str "I " chop-type " chop " name "! Take that!"))
  ([name]
   (x-chop name "karate")))

(defn codger-communication
  [whippersnapper]
  (str "Get off my lawn, " whippersnapper "!!!"))

(defn codger
  [& whippersnappers]
  (map codger-communication whippersnappers))

(defn favorite-things
  [name & things]
  (str "Hi, " name ", here are my favorite things: "
       (clojure.string/join ", " things)))

(defn my-first
  [[first-things]]
  first-things)

(defn chooser
  [[first-choise second-choise & unimportant-choise]]
  (str "Your first choise is: " first-choise "\n"
       "Your second choise is: " second-choise
       (if (nil? unimportant-choise)
         ""
         (str "\n"
              "We're ignoring the rest of your choices. Here they are in case you need to cry over them: "
              (clojure.string/join ", " unimportant-choise)))))

(defn announce-treasure-location
  [{lat :lat lng :lng}]
  (clojure.string/join ", "
                       (list
                        (str "Treasure lat: " lat)
                        (str "Treasure lng: " lng))))

(defn announce-treasure-location-2
  [{:keys [lat lng]}]
  (clojure.string/join ", "
                       (list
                        (str "Treasure lat: " lat)
                        (str "Treasure lng: " lng))))

(defn receive-treasure-location
  [{:keys [lat lng] :as treasure-location}]
  (str treasure-location))

(defn illustrative-function
  []
  (+ 1 304)
  30
  "Joe")

(defn number-comment
  [x]
  (if (> x 6)
    "Oh my gosh! What a big number!"
    "That's number's OK, I guess"))

(defn inc-maker
  [inc-by]
  (fn [num] (+ num inc-by)))

(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(defn symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts
         final-body-parts []]
    (if (empty? remaining-asym-parts)
        final-body-parts
        (let [[part & remaining] remaining-asym-parts]
          (recur remaining
                 (into final-body-parts
                       (set [part (matching-part part)])))))))

(defn recursive-printer
  ([]
   (recursive-printer 0))
  ([iteration]
   (if (> iteration 3)
     (str "Good Bye!!: " iteration)
     (recursive-printer (inc iteration)))))

(defn my-reduce
  ([f initial coll]
   (loop [result initial
          remaining coll]
     (if (empty? remaining)
       result
       (recur (f result (first remaining))
              (rest remaining)))))
  ([f [head & tail]]
   (my-reduce f head tail)))

;;reduce の メリット
;; 1. loop に比べてコードが短くなる
;; 2. collection の要素に対して処理し結果を返すという目的が明確になる
;; 3. 関数をより抽象化できる
(defn better-symmetrize-body-parts
  [asym-body-parts]
  (reduce (fn [final-body-parts part]
            (into final-body-parts (set [part (matching-part part)])))
          []
          asym-body-parts))

(defn hit
  [asym-body-parts]
  (let [sym-parts (better-symmetrize-body-parts asym-body-parts)
        body-part-size-num (reduce + (map :size sym-parts))
        target (rand body-part-size-num)]
    (loop [[part & remaining] sym-parts
           accumulated-size (:size part)]
      (if (> accumulated-size target)
        part
        (recur remaining (+ accumulated-size (:size (first remaining))))))))
