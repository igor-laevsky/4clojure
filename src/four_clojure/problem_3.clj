(ns four-clojure.problem-3
  (:require [clojure.test :refer :all]))

(def __ "HELLO WORLD")

(deftest problem-3-tests
  (is (= __ (.toUpperCase "hello world"))))
