(ns four-clojure.problem-106
  (:require [clojure.test :refer :all]
            [four-clojure.utils :refer :all]
            [spyscope.core :refer :all]))

(defn path-len
  ([start end] (path-len start end #{} 1 99999))
  ([cur end visited cur-path min-path]
   (cond
     (= cur end) cur-path
     (>= cur-path min-path) min-path
     :default
     (->> [(* cur 2) (when (even? cur) (/ cur 2)) (+ cur 2)]
          (filter (complement nil?))
          (filter (complement visited))
          (filter #(<= % (* end 30)))
          (reduce
            #(min %1 (path-len %2 end (conj visited cur) (inc cur-path) %1))
            min-path)))))

(path-len 3 12)

(def __ path-len)

(deftest problem-106-test
  (is (= 1 (__ 1 1)))  ; 1
  (is (= 3 (__ 3 12))) ; 3 6 12
  (is (= 3 (__ 12 3))) ; 12 6 3
  (is (= 3 (__ 5 9)))  ; 5 7 9
  (is (= 9 (__ 9 2)))  ; 9 18 20 10 12 6 8 4 2
  (is (= 5 (__ 9 12))) ; 9 11 22 24 12
  )
