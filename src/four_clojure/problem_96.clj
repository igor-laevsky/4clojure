(ns four-clojure.problem-96
  (:require [clojure.test :refer :all]
            [four-clojure.problem-95 :refer [is-tree]]
            [clojure.tools.trace :refer :all]))



(defn sym?
  ([t]
   (do
     (assert (is-tree t))
     (cond
       (nil? t) false
       (sequential? t) (sym? (nth t 1) (nth t 2)))))

  ([lhs rhs]
   (cond
     (nil? lhs) (nil? rhs)
     :default (and (= (first lhs) (first rhs))
                   (and (sym? (nth lhs 1) (nth rhs 2))
                        (sym? (nth lhs 2) (nth rhs 1)))))))

(def __ (trace-vars sym?))

(deftest problem-96-test
  (is  (= (__ '(:a (:b nil nil) (:b nil nil))) true))
  (is  (= (__ '(:a (:b nil nil) nil)) false))
  (is  (= (__ '(:a (:b nil nil) (:c nil nil))) false))
  (is (= (__ [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]
                [2 [3 nil [4 [6 nil nil] [5 nil nil]]] nil]])
       true))
  (is (= (__ [1 [2 [3 [4 [5 nil nil] [6 nil nil]] nil] nil]
                [2 [3 [4 [5 nil nil] [6 nil nil]] nil] nil]])
       false))
  (is (= (__ [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]
            [2 [3 nil [4 [6 nil nil] nil]] nil]])
       false))
  )
