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
