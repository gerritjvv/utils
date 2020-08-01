(ns install.mactools
 (:require [common.sh :refer [sh!]]))

(defn brew-install [lib]
 (sh! "brew" "install" lib))

(defn -main [& args]
  (let [tools ["rlwrap"
               "curl"
               "ripgrep"]]
  (doseq [tool tools]
    (brew-install tool))))

