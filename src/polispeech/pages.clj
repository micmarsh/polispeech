(ns polispeech.pages
    (:use
        [words.parser :only [eval-grammar]]
        [clojure.string :only [split trim join]]
        [hiccup.core :only [html]]
        [polispeech.templates :only [political-speech]]))

(def NEWLINE_REGEX #"\n")
(def HTML_BREAK "<br/>")

(def PAGE_TITLE "Political Speech Generator")

(def PAGE_HEADER (html [:head [:title PAGE_TITLE]]))

(defn htmlize-newlines [no-html]
    "Replaces '\\n' with '<br/>'"
    (->> (split no-html NEWLINE_REGEX)
        (map trim)
        (join HTML_BREAK )))


(defn- surrounding-page [speech]
    [:body
        [:h1 PAGE_TITLE]
        [:speech speech]])

(defn main-page [theme]
    (let [raw-speech (eval-grammar political-speech (keyword theme))
          with-html (htmlize-newlines raw-speech)]
          (str PAGE_HEADER
            (html (surrounding-page with-html)))))
