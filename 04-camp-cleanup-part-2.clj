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

(defn setsOverlap [set1 set2]
  (>= (count (set/intersection set1 set2)) 1))

(def overlappingSets (filter #(apply setsOverlap %) sets))

(count overlappingSets)