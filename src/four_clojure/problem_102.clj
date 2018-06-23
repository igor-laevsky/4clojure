(ns four-clojure.problem-102
  (:require [clojure.test :refer :all]))

;(defn to-camel [s]
;  (as-> (clojure.string/split s #"-") vvv
;        (cons (first vvv) (map clojure.string/capitalize (next vvv)))
;        (clojure.string/join vvv)))

(defn to-camel [s]
  (let [ss (clojure.string/split s #"-")
        cap (map clojure.string/capitalize (next ss))]
    (clojure.string/join (cons (first ss) cap))))

(def __ to-camel)

(deftest problem-102-test
  (is (= (__ "something") "something"))
  (is (= (__ "multi-word-key") "multiWordKey"))
  (is (= (__ "leaveMeAlone") "leaveMeAlone"))
  )