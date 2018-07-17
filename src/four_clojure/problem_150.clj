(ns four-clojure.problem-150
  (:require [clojure.test :refer :all]
            [spyscope.core :refer :all]))

;(defn palindr [n]
;  (letfn [(is-p [nn]
;            (let [s (seq (str nn))] (= s (reverse s))))]
;    (lazy-seq
;      (if (is-p n)
;        (cons n (palindr (inc n)))
;        (palindr (inc n))
;        ))))

;(defn palindr [n]
;  (letfn [(get-d [nn]
;            (when (not= nn 0)
;              (cons (mod nn 10) (get-d (quot nn 10)))))
;          (is-p [nn]
;            (if (zero? nn)
;              true
;              (let [s (get-d nn)] (= s (reverse s)))))]
;    (lazy-seq
;      (if (is-p n)
;        (cons n (palindr (inc n)))
;        (palindr (inc n))
;        ))))

(defn palindr [n]
  (letfn [(from-arr [nn] (BigInteger. (apply str nn)))
          (to-arr [nn] (if (zero? nn) [0] (map #(- (int %) (int \0)) (str nn))))
          (step [nn even?]
            (lazy-seq
              (let [nd (to-arr nn)
                    bot (reverse (if even? nd (butlast nd)))
                    cur (from-arr (concat nd bot))]
                (cons cur (if (every? #(= 9 %) nd)
                            (step (if even? (inc nn) (quot (inc nn) 10))
                                  (not even?))
                            (step (inc nn) even?))))))]
    (let [nd (to-arr n)
          len (count nd)]
      (drop-while
        #(< % n)
        (step (from-arr (take (+ (quot len 2) (mod len 2)) nd))
              (even? len))))))

(def __ palindr)

(deftest problem-150-test
  (is (= (take 26 (__ 0))
         [0 1 2 3 4 5 6 7 8 9
          11 22 33 44 55 66 77 88 99
          101 111 121 131 141 151 161]))

  (is (= (take 16 (__ 162))
         [171 181 191 202
          212 222 232 242
          252 262 272 282
          292 303 313 323]))

  (is (= (take 6 (__ 1234550000))
         [1234554321 1234664321 1234774321
          1234884321 1234994321 1235005321]))

  (is (= (first (__ (* 111111111 111111111)))
         (* 111111111 111111111)))

  (is (= (set (take 199 (__ 0)))
         (set (map #(first (__ %)) (range 0 10000)))))

  (is (= true
         (apply < (take 6666 (__ 9999999)))))

  (is (= (nth (__ 0) 10101)
     9102019))
  )
