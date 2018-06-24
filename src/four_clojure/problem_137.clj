(ns four-clojure.problem-137
  (:require [clojure.test :refer :all]))

(defn digits [n base]
  (loop [res '()
         cur-n n]
    (if (= cur-n 0)
      (if (empty? res) '(0) res)
      (recur (cons (mod cur-n base) res) (quot cur-n base)))))

(defn __ [n base] (digits n base))

(deftest problem-137-test
  (is (= [1 2 3 4 5 0 1] (__ 1234501 10)))
  (is (= [0] (__ 0 11)))
  (is (= [1 0 0 1] (__ 9 2)))
  (is (= [1 0] (let [n (rand-int 100000)](__ n n))))
  (is (= [16 18 5 24 15 1] (__ Integer/MAX_VALUE 42)))
  )