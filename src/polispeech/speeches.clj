(ns polispeech.speeches
    (:use
        [clojure.string :only [split trim join]]))

(def NEWLINE_REGEX #"\n")
(def HTML_BREAK "<br/>")

(defn htmlize-newlines [no-html]
    "Replaces '\\n' with '<br/>'"
    (->> (split no-html NEWLINE_REGEX)
        (map trim)
        (join HTML_BREAK )))
