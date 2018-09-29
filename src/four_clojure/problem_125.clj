(ns four-clojure.problem-125
  (:require [clojure.test :refer :all]))

(defn __ []
  (fn [] (let [ret (str "(fn [] (let [ret (str %c%s%c)] (format ret (char 34) ret (char 34))))")] (format ret (char 34) ret (char 34)))))

(char 34)

((fn [x] (str x x))"(fn [x] (str x x))")
