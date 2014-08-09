(ns polispeech.speeches
    (:use
        [clojure.string :only [split trim join]]
        [generator.parser :only [eval-grammar]]
        [polispeech.templates :only [political-speech]]))

(def NEWLINE_REGEX #"\n")
(def HTML_BREAK "</p><p>")

(defn htmlize-newlines [no-html]
    "Replaces '\\n' with '<br/>'"
    (->> (split no-html NEWLINE_REGEX)
        (map trim)
        (join HTML_BREAK )))

;perhaps not ideal to be banging these strings together,
;maybe couple string generation and some pre-hiccuping
(defn- get-speech [template theme]
    (str "<p>"
        (eval-grammar template [(keyword theme)]) "</p>"))

(def get-political-speech
    (partial get-speech political-speech))
