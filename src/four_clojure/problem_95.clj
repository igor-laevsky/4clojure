(ns four-clojure.problem-95
  (:require [clojure.test :refer :all]))

(defn is-tree [t]
  (cond
    (nil? t) true
    (sequential? t) (and (= (count t) 3)
                         (is-tree (nth t 1))
                         (is-tree (nth t 2)))
    :default false))

(def __ is-tree)

(deftest problem-95-tests
  (is (= (__ nil) true))

  (is (= (__ 'a) false))

  (is (= (__ '()) false))

  (is (= (__ '(:a (:b nil nil) nil))
   true))

  (is (= (__ '(:a (:b nil nil)))
   false))

  (is (= (__ [1 nil [2 [3 nil nil] [4 nil nil]]])
   true))

  (is (= (__ [1 [2 nil nil] [3 nil nil] [4 nil nil]])
   false))

  (is (= (__ [1 [2 [3 [4 nil nil] nil] nil] nil])
   true))

  (is (= (__ [1 [2 [3 [4 false nil] nil] nil] nil])
   false))

  (is (= (__ '(:a nil ()))
   false)))