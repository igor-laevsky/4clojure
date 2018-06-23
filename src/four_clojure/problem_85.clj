(ns four-clojure.problem-85
  (:require [clojure.test :refer :all]))

;(defn set-pow [s]
;  (loop [ss (seq s)
;         res #{}]
;    (if-not ss
;      (conj res #{})
;      (let [small (set-pow (set (next ss)))
;            small-el (set (map #(conj % (first ss)) small))]
;        (recur
;          (next ss)
;          (clojure.set/union res small small-el))))))

(defn set-pow [s]
  (loop [ss (seq s)
         res #{#{}}]
    (if-not ss
      res
      (recur
        (next ss)
        (into res (set (map #(conj % (first ss)) res)))))))

(def __ set-pow)

(deftest problem-85-test
  (is (= (__ #{1 :a}) #{#{1 :a} #{:a} #{} #{1}}))
  (is (= (__ #{}) #{#{}}))
  (is (= (__ #{1 2 3})
         #{#{} #{1} #{2} #{3} #{1 2} #{1 3} #{2 3} #{1 2 3}}))
  (is (= (count (__ (into #{} (range 10)))) 1024))
  )