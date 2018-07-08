(ns four-clojure.problem-121
  (:require [clojure.test :refer :all]))

(defn calc [expr]
  (fn do [binds]
    (cond
      (number? expr) expr

      (symbol? expr) (binds expr)

      (sequential? expr)
      (let [[op-sym & opers] expr
            opers-val (map #((calc %) binds) opers)
            op ({'+ + '- - '/ / '* *} op-sym)]
        (apply op opers-val))

      :default (assert false)
      )))


(def __ calc)

(deftest problem-121-test
  (is (= 2 ((__ '(/ a b))
             '{b 8 a 16})))
  (is (= 8 ((__ '(+ a b 2))
             '{a 2 b 4})))
  (is (= [6 0 -4]
         (map (__ '(* (+ 2 a)
                      (- 10 b)))
              '[{a 1 b 8}
                {b 5 a -2}
                {a 2 b 11}])))
  (is (= 1 ((__ '(/ (+ x 2)
                    (* 3 (+ y 1))))
             '{x 4 y 1})))
  )