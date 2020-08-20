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

(defn repeat! [sleep-seconds & args]
   "Run a command repeatedly"
   (try
     (apply sh! args)
     (catch Exception  e (prn e)))

   (Thread/sleep (int (* sleep-seconds 1000)))
   (recur sleep-seconds args))

(comment
  
  (sh! "ls" "/tmp")
  )
