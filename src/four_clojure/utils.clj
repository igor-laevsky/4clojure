(ns four-clojure.utils
  (:require [clojure.test :refer :all]
            [clojure.pprint :refer :all]))

(defn inspect
  [val]
  (do
    (pprint val)
    val))
