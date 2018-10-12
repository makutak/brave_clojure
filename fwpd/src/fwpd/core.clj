(ns fwpd.core)

(def filename "suspects.csv")

(def vamp-keys [:name :glitter-index])

(defn str->int
  [str]
  (Integer. str))

(def conversions {:name identity
                  :glitter-index str->int})

(defn convert
  [vamp-key value]
  ((get conversions vamp-key) value))

;; #に続けて文字列を書くと、その文字列は正規表現リテラルとして扱われる
(defn parse
  "Convert a CSV into rows of column."
  [string]
  (map #(clojure.string/split % #",")
       (clojure.string/split string #"\n")))

;; (map str '(1 2 3) '("a" "b" "c")) -> ("1a" "2b" "3c")
;; つまり、 (list (str 1 "a") (str 2 "b") (str 3 "c") みたいな感じで展開される。
;; 複数の引数を受け取れる述語関数は各seqの各要素に対して、その関数をmappingして返す。
(defn mapify
  "Return a seq of maps like {:name \"Edward Cullen\" :glitter-index 10"
  [rows]
  (map (fn [unmapped-row]
         (reduce (fn [row-map [vamp-key value]]
                   (assoc row-map vamp-key (convert vamp-key value)))
                 {}
                 (map vector vamp-keys unmapped-row)))
       rows))
