(ns four-clojure.problem-93
  (:require [clojure.test :refer :all]))

(defn part-flat [s]
  (letfn [(has-seq [x] (some sequential? x))]
    (filter (complement has-seq) (tree-seq has-seq seq s))))

(def __ part-flat)

(deftest problem-93-test
  (is (= (__ [["Do"] ["Nothing"]])
         [["Do"] ["Nothing"]]))

  (is (= (__ [[[[:a :b]]] [[:c :d]] [:e :f]])
         [[:a :b] [:c :d] [:e :f]]))

  (is (= (__ '((1 2)((3 4)((((5 6)))))))
         '((1 2)(3 4)(5 6))))
  )
