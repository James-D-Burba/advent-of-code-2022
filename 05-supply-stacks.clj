(require '[clojure.string :as str])

(def lines
  (str/split
   (slurp "/home/jimmy/src/advent-of-code-2022/05-input.txt")
   #"\n"))

(def stackLines
  (filter #(str/starts-with? % "[") lines))

(def instructionLines
  (filter #(str/starts-with? % "move") lines))

;; subvec because for some reason the first element after the split is an empty string
(def instructions (mapv #(mapv (fn [val] (Integer/parseInt val)) (subvec (str/split % #"move\s|\sfrom\s|\sto\s") 1)) instructionLines))

(defn getStack [stackNo]
  (let [placeInLine (+ 1 (* (- stackNo 1) 4))]
    (reduce
     (fn [acc line]
       (let [letter (.charAt line placeInLine)]
         (if (not= letter \space)
           (conj acc letter)
           acc)))
     []
     stackLines)))

(def stacks (mapv getStack (range 1 10)))

(defn applyInstructions [instructions stacks]
  (let [numToMove (get instructions 0)
        fromStackIndex (- (get instructions 1) 1)
        toStackIndex (- (get instructions 2) 1)
        fromStack (get stacks fromStackIndex)
        toStack (get stacks toStackIndex)
        boxesToMove (subvec fromStack 0 numToMove)
        newFromStack (subvec fromStack numToMove)
        newToStack (apply conj (vec (rseq boxesToMove)) toStack)]
    (assoc
     (assoc stacks toStackIndex newToStack)
     fromStackIndex
     newFromStack)))

(def resultStacks (reduce #(applyInstructions %2 %1) stacks instructions))