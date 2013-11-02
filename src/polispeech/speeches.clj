(ns polispeech.speeches
    (:use
        [clojure.string :only [split trim join]]
        [words.parser :only [eval-grammar]]
        [polispeech.templates :only [political-speech]]
        clojure.core.typed))

(ann eval-grammar [Map -> String])

(def-alias Regex java.util.regex.Pattern    )

(ann NEWLINE_REGEX Regex)
(def NEWLINE_REGEX #"\n")
(ann HTML_BREAK String)
(def HTML_BREAK "<br/>")

(ann htmlize-newlines [String -> String])
(defn htmlize-newlines [no-html]
    "Replaces '\\n' with '<br/>'"
    (->> (split no-html NEWLINE_REGEX)
        (map trim)
        (join HTML_BREAK )))

(ann get-speech [Map -> [String -> String]])
(defn- get-speech [template]
    (fn [theme]
        (eval-grammar template (keyword theme))))

(ann get-political-speech [String -> String])
(def get-political-speech (get-speech political-speech))
