(ns four-clojure.problem-148
  (:require [clojure.test :refer :all]
            [spyscope.core :refer :all]))

;(defn sum-n [n a b]
;  (+
;    (apply + (take-while #(< % n) (iterate (partial + a) 0)))
;    (apply + (take-while #(< % n) (iterate (partial + b) 0)))
;    (apply - (take-while #(< % n) (iterate (partial + (* a b)) 0)))
;  ))

;(defn sum-n [n a b]
;  (let [get (fn [x] (take (bigint (/ (- n 1) x)) (iterate (partial +' x) x)))
;        as (apply + (get a))
;        bs (apply + (get b))
;        abs (reduce - 0 (get (* a b)))]
;    (+ as bs abs)))

(defn sum-n [n a b]
  (let [g (fn [x] (let [nx (bigint (/ (- n 1) x))
                        lst (* x nx)]
                      (/ (* nx (+ x lst)) 2)))
        as (g a)
        bs (g b)
        abs (- 0 (g (* a b)))]
    (+ as bs abs)))

(def __ sum-n)

(deftest problem-148-test
  (is (= 0 (__ 3 17 11)))
  (is (= 23 (__ 10 3 5)))
  (is (= 233168 (__ 1000 3 5)))
  (is (= "2333333316666668" (str (__ 100000000 3 5))))
  (is (= "110389610389889610389610"
         (str (__ (* 10000 10000 10000) 7 11))))
  (is (= "1277732511922987429116"
         (str (__ (* 10000 10000 10000) 757 809))))
  (is (= "4530161696788274281"
         (str (__ (* 10000 10000 1000) 1597 3571))))
  )
