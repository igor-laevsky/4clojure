(ns four-clojure.problem-101
  (:require [clojure.test :refer :all]
            [four-clojure.utils :refer :all]
            [spyscope.core :refer :all]))

;(defn lev-dist [a b]
;  (cond
;    (empty? a) (count b)
;    (empty? b) (count a)
;    (= (last a) (last b)) (recur (drop-last a) (drop-last b))
;    :else (min (inc (lev-dist (drop-last a) b))
;               (inc (lev-dist a (drop-last b)))
;               (inc (lev-dist (drop-last a) (drop-last b))))))

(defn lev-dist [a b]
  (letfn [(iter [prev letter]
            (loop [i 1
                   res [(inc (first prev))]]
              (if (>= i (count prev))
                res
                (recur (inc i)
                       (conj res
                             (min
                               (inc (prev i))
                               (inc (last res))
                               (+ (prev (- i 1) ) (if (= letter (nth a (dec i))) 0 1))))))))]
    (->> b
         (reduce iter (vec (range 0 (inc (count a)))))
         (last))))

;(defn lev-dist [a b]
;  (letfn [(lcs [[x-h & x-t :as x] [y-h & y-t :as y]]
;            (cond
;              (empty? x) (count y)
;              (empty? y) (count x)
;              (= x-h y-h) (lcs x-t y-t)
;              :else (+ 1 (min (lcs x y-t) (lcs x-t y) (lcs x-t y-t)))))]
;    (lcs a b)))

(def __ lev-dist)

(deftest problem-101-test
  (is (= (__ "kitten" "sitting") 3))
  (is (= (__ "closure" "clojure") (__ "clojure" "closure") 1))
  (is (= (__ "xyx" "xyyyx") 2))
  (is (= (__ "" "123456") 6))
  (is (= (__ "Clojure" "Clojure") (__ "" "") (__ [] []) 0))
  (is (= (__ [1 2 3 4] [0 2 3 4 5]) 2))
  (is (= (__ '(:a :b :c :d) '(:a :d)) 2))
  (is (= (__ "ttttattttctg" "tcaaccctaccat") 10))
  (is (= (__ "gaattctaatctc" "caaacaaaaaattt") 9))
  )
