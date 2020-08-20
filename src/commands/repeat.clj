(ns commands.repeat
   (:require  [common.sh :refer [repeat!]]))


(defn -main [& args]
  (let [[seconds & cmds] args]

    (apply repeat! (Integer/parseInt (str seconds)) cmds)))
