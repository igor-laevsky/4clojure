(ns four-clojure.problem-74
  (:require [clojure.test :refer :all]
            [spyscope.core :refer :all]))

;(defn perfect-square [ss]
;  (as-> ss vvv
;        (clojure.string/split vvv #",")
;        (map #(Integer/parseInt %) vvv)
;        (filter #(== (Math/pow (int (Math/sqrt %)) 2) %) vvv)
;        (clojure.string/join "," vvv)))

(defn perfect-square [ss]
  (-> ss
        (clojure.string/split #",")
        (->> (map #(Integer/parseInt %))
             (filter #(== (Math/pow (int (Math/sqrt %)) 2) %))
             (clojure.string/join ","))))

(def __ perfect-square)

(deftest problem-74-test
  (is (= (__ "4,5,6,7,8,9") "4,9"))
  (is (= (__ "15,16,25,36,37") "16,25,36"))
  )