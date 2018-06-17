(ns four-clojure.problem-59
  (:require [clojure.test :refer :all]))

;(defn my-juxt [& funcs]
;  (fn [& args]
;    (letfn [(step [f & funcs]
;              (lazy-seq
;                (if funcs
;                  (cons (apply f args) (apply step funcs))
;                  [(apply f args)])))]
;      (apply step funcs))))

;(defn my-juxt
;  ([] identity)
;  ([f] (fn [& args] [(apply f args)]))
;  ([f & fs]
;   (fn [& args]
;     (lazy-seq (cons (apply f args) (apply (apply my-juxt fs) args))))))

;(defn my-juxt
;  ([] identity)
;  ([f] (fn [& args] [(apply f args)]))
;  ([f g] (fn [& args] (list (apply f args) (apply g args))))
;  ([f g & fs]
;   (comp flatten (reduce my-juxt (list* f g fs)))))

(defn my-juxt [& funcs]
  (fn [& args]
    (map #(apply % args) funcs)))

(def __ my-juxt)

(deftest problem-59-tests
  (is (= [21 6 1] ((__ + max min) 2 3 5 1 6 4)))
  (is (= ["HELLO" 5] ((__ #(.toUpperCase %) count) "hello")))
  (is (= [2 6 4] ((__ :a :c :b) {:a 2, :b 4, :c 6, :d 8 :e 10})))
  )