(ns polispeech.pages
    (:use
        [polispeech.speeches :only [htmlize-newlines]]
        [hiccup.core :only [html]]
        [words.parser :only [eval-grammar]]
        [polispeech.templates :only [political-speech]]))

(def PAGE_TITLE "Political Speech Generator")

(def PAGE_HEADER (html [:head [:title PAGE_TITLE]]))

(defn- surrounding-page [speech]
    [:body
        [:h1 PAGE_TITLE]
        [:speech speech]])

(defn main-page [theme]
    (let [raw-speech (eval-grammar political-speech (keyword theme))
          with-html (htmlize-newlines raw-speech)]
          (str PAGE_HEADER
            (html (surrounding-page with-html)))))
