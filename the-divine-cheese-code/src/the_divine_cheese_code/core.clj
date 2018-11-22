(ns the-divine-cheese-code.core)
;; Ensure that the SvG code is evaluated
(require 'the-divine-cheese-code.visualization.svg)
;; Refer the namespace so that you don't have to use the
;; fully qualified name to reference svg function
(refer 'the-divine-cheese-code.visualization.svg)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
