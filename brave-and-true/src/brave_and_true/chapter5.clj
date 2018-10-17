(ns brave-and-true.chapter5 (:gen-class))

(defn sum
  ([vals]
   (sum vals 0))
  ([vals accumulating-total]
   (if (empty? vals)
     accumulating-total
     (sum (rest vals)
          (+ (first vals) accumulating-total)))))
