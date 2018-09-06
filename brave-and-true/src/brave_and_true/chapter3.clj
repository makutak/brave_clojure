(ns brave-and-true.chapter3 (:gen-class))

(defn foo
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
