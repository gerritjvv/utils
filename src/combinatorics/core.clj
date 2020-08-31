(ns 
  "
   Choose: (N / K) => choose n k
  
   Use when selecting K items out of N

   Multi Choose (( N / K )) => choose n k :rep true :order false

   Use multi choose when:
     Distributed N things between K options 


  Remember the product rule and sum rule for combinations with tuples


  (require '[clojure.math.combinatorics :as combo])

  "

  combinatorics.core)


(defn pow [n k]
   "n ^ k"
   (Math/pow (long n) (long k)))


;; suprisingly fast up to 100! on modern hardware
;; (time (double (c/fact 100)))
;; "Elapsed time: 0.069909 msecs"
;;  9.332621544394418E157
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


;;;;;;; brute force arlgorithms to help validate

(defn sum-digits [v]
 (reduce + 0 (map #(- (int %) 48) (str v))))

(defn sum-of-equal [from to equal-to]
  "return all the numbers who'se digits in range from to add up to equal-to"
  (filter 
    #(= (sum-digits %) equal-to)
     (range from to)))


