(ns docker.core
 (:require [common.sh :refer [sh! exit!]]))


(defn prune []
  "Prune all resources, images ,system etc from docker"
  (sh! "docker" "system" "prune" "-a")
  (sh! "docker" "volume" "prune" "-f"))


(defn -main [& args]
  (let [cmd (first args)]
   (condp = cmd
     "prune" (prune)
      (exit! (str "cmd prune|help")))))
