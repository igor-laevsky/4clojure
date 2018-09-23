(ns four-clojure.problem-111
  (:require [clojure.test :refer :all]))

(defn fits? [word input-field]
  (let [field (->> input-field
                   (map #(clojure.string/replace (apply str %) " " ""))
                   (map #(clojure.string/replace (apply str %) "_" ".")))
        all-lines (concat field (apply map vector field))
        line-fit? (fn [line]
                    (->> (partition-by #(= % \#) line)
                         (filter #(not= % '(\#)))
                         (map #(apply str %))
                         (map #(re-matches (re-pattern (str "^" % "$")) word))
                         (some (complement nil?))))]
    (->> all-lines
         (map line-fit?)
         (some (complement nil?))
         (boolean))))

(def __ fits?)

(__ "the" ["c _ _ _"
           "d _ # e"
           "r y _ _"])

(deftest problem-111-test
  (is (= true  (__ "the" ["_ # _ _ e"])))
  (is (= false (__ "the" ["c _ _ _"
                          "d _ # e"
                          "r y _ _"])))
  (is (= true  (__ "joy" ["c _ _ _"
                          "d _ # e"
                          "r y _ _"])))
  (is (= false (__ "joy" ["c o n j"
                          "_ _ y _"
                          "r _ _ #"])))
  (is (= true  (__ "clojure" ["_ _ _ # j o y"
                              "_ _ o _ _ _ _"
                              "_ _ f _ # _ _"])))
  )
