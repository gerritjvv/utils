(ns install.clojure
  (:require [common.sh :refer [sh!]]))


(defn install-tools []
 (sh! "brew" "upgrade" "clojure")
 (sh! "brew" "upgrade" "babashka")
 (sh! "git" "clone" "git@github.com:gerritjvv/clojure-deps-edn.git" "~/.clojure"))

(defn -main [& args]
  (install-tools))
