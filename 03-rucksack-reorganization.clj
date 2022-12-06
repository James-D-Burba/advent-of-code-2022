(require '[clojure.string :as str])
(require '[clojure.set :as set])

(defn getPriority [item]
  (let [numValue (int item)]
    (if (> 97 numValue)
      (- numValue 38)
      (- numValue 96))))

(defn findMisplacedItem [rucksack]
  (let [sackSize (count rucksack)
        sackMidpoint (/ sackSize 2)
        pocket1 (set (char-array (subs rucksack 0 sackMidpoint)))
        pocket2 (set (char-array (subs rucksack sackMidpoint sackSize)))]
    (first (set/intersection pocket1 pocket2))))

(def ruckSacks
  (str/split
   (slurp "/home/jimmy/src/advent-of-code-2022/03-input.txt")
   #"\n"))

(reduce #(+ %1 (getPriority (findMisplacedItem %2))) 0 ruckSacks)