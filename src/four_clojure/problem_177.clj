(ns four-clojure.problem-177
  (:require [clojure.test :refer :all]))

(defn balanced? [s]
  (let [inv {\[ \] \( \) \{ \}}]
    (loop [[fst & rst] (clojure.string/replace s #"[^\(\[\{\}\]\)]" "")
           st '()]
      (if-not fst
        (empty? st)
        (condp #(some %2 %1) #{fst}
          [\[ \( \{] (recur rst (conj st fst))
          [\] \) \}] (if (= (inv (peek st)) fst)
                            (recur rst (pop st))
                            false)
          (assert false))))))

(def __ balanced?)

(deftest problem-177-test
  (is (__ "This string has no brackets."))
  (is (__ "class Test {
      public static void main(String[] args) {
        System.out.println(\"Hello world.\");
      }
    }"))
  (is (not (__ "(start, end]")))
  (is (not (__ "())")))
  (is (not (__ "[ { ] } ")))
  (is (__ "([]([(()){()}(()(()))(([[]]({}()))())]((((()()))))))"))
  (is (not (__ "([]([(()){()}(()(()))(([[]]({}([)))())]((((()()))))))")))
  (is (not (__ "[")))
  )