(ns four-clojure.problem-56
  (:require [clojure.test :refer :all]))

;(defn my-distinct [coll]
;  (->> coll
;       (reduce
;         (fn [[seen ret] x] (if (seen x)
;                              [seen ret]
;                              [(conj seen x) (conj ret x)]))
;         [#{} []])
;       (second)))

(defn my-distinct
  ([coll] (my-distinct coll #{}))
  ([[f & xs] seen]
   (when f
     (if (contains? seen f)
       (recur xs seen)
       (cons f (my-distinct xs (conj seen f)))))))

(def __ my-distinct)

(deftest problem-56-test
  (is (= (__ [1 2 1 3 1 2 4]) [1 2 3 4]))
  (is (= (__ [:a :a :b :b :c :c]) [:a :b :c]))
  (is (= (__ '([2 4] [1 2] [1 3] [1 3])) '([2 4] [1 2] [1 3])))
  (is (= (__ (range 50)) (range 50)))
  )
