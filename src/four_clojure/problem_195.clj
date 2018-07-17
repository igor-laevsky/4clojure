(ns four-clojure.problem-195
  (:require [clojure.test :refer :all]
            [clojure.tools.trace :refer [trace-vars]]))

(defn parens [n]
  (condp = n
    0 #{""}
    1 #{"()"}
    (set
      (for [i (range n)
            lhs (parens i)
            rhs (parens (- (- n 1) i))]
        (str "(" lhs ")" rhs)))))

(def __ parens)

(deftest problem-195-test
  (is (= [#{""} #{"()"} #{"()()" "(())"}] (map (fn [n] (__ n)) [0 1 2])))

  (is (= #{"((()))" "()()()" "()(())" "(())()" "(()())"} (__ 3)))

  (is (= 16796 (count (__ 10))))

  (is (= (nth (sort (filter #(.contains ^String % "(()()()())") (__ 9))) 6) "(((()()()())(())))"))

  (is (= (nth (sort (__ 12)) 5000) "(((((()()()()()))))(()))"))

  )
