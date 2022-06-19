(ns basic
  (:require [clojure.repl :refer [source apropos dir pst doc find-doc]]
            [clojure.string :as cs]
            [clojure.set :as cset]
            [clojure.test :refer [is are]]))

;; Lists can be expressed by function or a quoted form


;; They are Clojure seqs (sequences), so they allow access to the first as well as the rest

;; LISTS

(= 1 (first (quote (1 2 3 4 5))))

(rest (quote (1 2 3 4 5)))

(cons :a (quote (:b :c :d :e)))

(conj (quote (:b :c :d :e)) :a)

;;You can use a list like a stack to get the first element

(peek (quote (:a :b :c :d :e)))

;; pop -> the others

;; VECTOR 

;; Conjoining to a vector is different than to a list

(conj [111 222] 333)

;; any index if you wish
(nth [:peanut :butter :and :jelly] 3)

;; slicing [start-index, before-end-index]

(subvec [:peanut :butter :and :jelly] 1 3)

;; Equality with collections is in terms of values
(= (list 1 2 3) (vector 1 2 3))

(cset/union #{1 4 3 2} #{3 2 5})

;; HASH-MAP OR AS I CALL IT, SCUFFED JS OBJECTS

;; calling object value by key

(get {:a 1, :b 2} :b)

;; But map keys need not be keywords

({2006 "Torino", 2010 "Vancouver", 2014 "Sochi"} 2010)

;; you can provide your own default if not included in map

(get {:a 1, :b 2} :c :key-not-found)


;; checking if key exists

(contains? {:a nil, :b nil} :b)

;; adding new items to map

(assoc {:1 "January"} :2 "February")


;; remove by dissoc-ing the key
(dissoc {:1 "January"} :2)


(sort (keys {2010 "Vancouver" 2014 "Sochi" 2006 "Torino"}))
(sort (vals {2006 "Torino" 2010 "Vancouver" 2014 "Sochi"}))

(let [square (fn [n] (* n n))]
  (square 9))

;; FUNCTION

(#(* 15 %) 4)

(((fn [] +)) 4 5)

;;Functions can also take other functions as input

;; Higher-order functions take function arguments

(#(% 5)
 (fn [n] (* n n)))

((fn [f] (f 5))
 (fn [n] (* n n)))

;; CONDITIONALS

(if (false? (= 4 5))
  :a
  :b)
(> 4 3)

(nil? 0)

(not (empty? ()))

(if-not (= "impending" "boobies")
  'doom
  'doom)

;; ??????

(let [explain-defcon-level (fn [exercise-term]
                             (case exercise-term
                               :fade-out          :you-and-what-army
                               :double-take       :call-me-when-its-important
                               :round-house       :o-rly
                               :fast-pace         :thats-pretty-bad
                               :cocked-pistol     :sirens
                               :say-what?))]
  (explain-defcon-level :cocked-pistol))

;; PARTITION

(= '((0 1) (2 3)) (#(vec (partition % %2)) 2 (range 4)))

(partition 3 [:a :b :c :d :e])
;; => ((:a :b :c)), care if not enough will be thrown away. if you want otherwise use partitio-all instead

(partition-all 3 (range 5))

(partition 3 4 (range 21))

;; weird ass shit

(partition 3 3 [:this :are "my" "words"] (range 7))
;; => ((0 1 2) (3 4 5) (6 :this :are))

;; HIGHER ORDER FUNCTION

;;The map function relates a sequence to another
(= (quote (4 8 12))
   (map (fn
          [x]
          (* 4 x)) [1 2 3]))