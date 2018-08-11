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
