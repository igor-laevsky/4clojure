(defproject four-clojure "0.1.0-SNAPSHOT"
  :description "Solutions for some of the 4clojure.com tasks"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/tools.trace "0.7.9"]
                 [spieden/spyscope "0.1.7"]]
  :target-path "target/%s"
  :test-paths ["src"],
  :profiles {:uberjar {:aot :all}})
