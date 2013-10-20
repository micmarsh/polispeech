(ns polispeech.pages
    (:use
        [words.parser :only [eval-grammar]]
        [clojure.string :only [split trim join]]
        [hiccup.core :only [html]]
        [polispeech.templates :only [political-speech]]))

(def allowed-themes #{:mainstream :radical})

(def NEWLINE_REGEX #"\n")
(def HTML_BREAK "<br/>")

(defn htmlize-newlines [no-html]
    "Replaces '\\n' with '<br/>'"
    (->> (split no-html NEWLINE_REGEX)
        (map trim)
        (join HTML_BREAK )))

(defn main-page [theme]
    (let [raw-speech (eval-grammar political-speech (keyword theme))
          with-html (htmlize-newlines raw-speech)]
            (html [:p with-html])))
