(ns four-clojure.problem-108
  (:require [clojure.test :refer :all]
            [four-clojure.utils :refer :all]))

;(defn find-smallest [& colls]
;  (->> (apply interleave colls)
;       (reductions #(sort (conj %1 %2)) '())
;       (drop 1)
;       (map (partial partition-by identity))
;       (inspect)
;       (mapcat seq)
;       (filter #(= (count %) (count colls)))
;       (ffirst)
;    )
;  )

(defn find-smallest [& colls]
  (loop [cur (seq colls)]
    (when (every? not-empty cur)
      (let [fsts (map first cur)
            m (apply max fsts)
            n (map #(drop-while (partial > m) %) cur)]
        (if (every? #(= m %) fsts)
          m
          (recur n))))))

(def __ find-smallest)

(deftest problem-108-test
  (is (= 3 (__ [3 4 5])))
  (is (= 4 (__ [1 2 3 4 5 6 7] [0.5 3/2 4 19])))
  (is (= 7 (__ (range) (range 0 100 7/6) [2 3 5 7 11 13])))
  (is (= 64 (__ (map #(* % % %) (range)) ;; perfect cubes
                (filter #(zero? (bit-and % (dec %))) (range)) ;; powers of 2
                (iterate inc 20)))) ;; at least as large as 20)
  )