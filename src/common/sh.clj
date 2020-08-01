(ns common.sh)

(defn sh! [& args]
  (.waitFor (-> (ProcessBuilder. (into [] args)) .inheritIO .start)))

(defn exit! [msg]
  (prn msg)
  (System/exit 1))

(defn -main [& args]
  (apply sh! args))
