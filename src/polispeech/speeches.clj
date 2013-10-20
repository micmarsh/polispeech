(ns polispeech.speeches
    (:use
        [clojure.string :only [split trim join]]
        [words.parser :only [eval-grammar]]
        [polispeech.templates :only [political-speech]]
))

(def NEWLINE_REGEX #"\n")
(def HTML_BREAK "<br/>")

(defn htmlize-newlines [no-html]
    "Replaces '\\n' with '<br/>'"
    (->> (split no-html NEWLINE_REGEX)
        (map trim)
        (join HTML_BREAK )))

(defn- get-speech [template]
    (fn [theme]
        (eval-grammar template (keyword theme))))

(def get-political-speech (get-speech political-speech))
