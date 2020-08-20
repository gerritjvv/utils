(ns combinatorics.core)

(defn pow [n k]
   "n ^ k"
   (Math/pow (long n) (long k)))


(defn fact [n]
   (loop [i (long n) acc 1]
     (if (= i 1)
         acc
         (recur (dec i) (* acc i)))))


(defn choose [n k]
  "n choose k"
  (->
    (fact n)
    (/ (fact (- n k)))
    (/ (fact k))))
