(require '[clojure.string :as str])
(require '[clojure.set :as set])

(def lines
  (str/split
   (slurp "/home/jimmy/src/advent-of-code-2022/04-input.txt")
   #"\n"))

(defn setsFromLines [line]
  (apply
   #(vector (set (range %1 (+ %2 1))) (set (range %3 (+ %4 1))))
   (mapv #(Integer/parseInt %) (str/split line #"-|,"))))

(def sets (mapv setsFromLines lines))

(defn setContainsOtherSet [set1 set2]
  (or (set/subset? set1 set2) (set/subset? set2 set1)))

(def overlappingSets (filter #(apply setContainsOtherSet %) sets))

(count overlappingSets)