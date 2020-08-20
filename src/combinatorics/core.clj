(ns combinatorics.core)

(defn pow [n k]
   "n ^ k"
   (Math/pow (long n) (long k)))


(defn fact [n]
   (loop [i (long n) acc (double 1)]
     (if (<= i 1)
         acc
         (recur (dec i) (* acc i)))))


(defmulti choose (fn [n k & {:keys [rep order] :or {rep false order false}}] {:rep rep :order order}))


(defmethod choose {:rep false :order false} [n k & _]
  (->
    (fact n)
    (/ (fact (- n k)))
    (/ (fact k))))


(defmethod choose {:rep true :order false} 
  [n k & _]
   (choose (-> n (+ k) dec) (dec k) :rep false :order false))


(defmethod choose {:rep false :order true}
 [n k & _]
  (/ (fact n) (fact (- n k))))


(defmethod choose {:rep true :order true}
  [n k & _]
  (pow n k))
