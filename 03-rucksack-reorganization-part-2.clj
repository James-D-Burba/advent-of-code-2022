(require '[clojure.string :as str])
(require '[clojure.set :as set])

(defn getPriority [item]
  (let [numValue (int item)]
    (if (> 97 numValue)
      (- numValue 38)
      (- numValue 96))))

(def ruckSacks
  (str/split
   (slurp "/home/jimmy/src/advent-of-code-2022/03-input.txt")
   #"\n"))

(defn stringToSet [string]
  (set (char-array string)))

(def ruckSackSets (mapv stringToSet ruckSacks))

(defn groupRucksacks [ruckSacks groupedRucksacks]
  (if (= (count ruckSacks) 0)
    groupedRucksacks
    (groupRucksacks
     (subvec ruckSacks 3)
     (conj groupedRucksacks (subvec ruckSacks 0 3)))))

(def groupedRucksackSets (groupRucksacks ruckSackSets []))

(reduce #(+ %1 (getPriority (first (apply set/intersection %2)))) 0 groupedRucksackSets)