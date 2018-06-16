(ns four-clojure.problem-46
  (:require [clojure.test :refer :all]))

(defn flip [func]
  (fn [& args]
    (apply func (reverse args))))

(def __ flip)

(deftest proble-46-test
  (is (= 3 ((__ nth) 2 [1 2 3 4 5])))
  (is (= true ((__ >) 7 8)))
  (is (= 4 ((__ quot) 2 8)))
  (is (= [1 2 3] ((__ take) [1 2 3 4 5] 3)))
  )