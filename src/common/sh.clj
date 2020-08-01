(ns common.sh)


(defn expand-home [s]
   (clojure.string/replace s #"~" (System/getProperty "user.home")))

(defn sh! [& args]
  (.waitFor (-> (ProcessBuilder. (into [] (map expand-home) args)) .inheritIO .start)))

(defn exit! [msg]
  (prn msg)
  (System/exit 1))

(defn -main [& args]
  (apply sh! args))
