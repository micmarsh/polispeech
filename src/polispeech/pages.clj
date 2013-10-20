(ns polispeech.pages
    (:use
        [polispeech.speeches :only [htmlize-newlines]]
        [hiccup.core :only [html]]
        [words.parser :only [eval-grammar]]
        [polispeech.templates :only [political-speech]]))

(def PAGE_TITLE "Political Speech Generator")

(def PAGE_HEADER (html [:head [:title PAGE_TITLE]]))

(def ALLOWED_THEMES #{"mainstream" "radical"})

(def JS_LOCATION "js/speeches.js")

(defn- surrounding-page [speech current-theme]
    [:body
        [:h1 PAGE_TITLE]
        [:p#speech speech]
        [:select#theme
            (for [theme ALLOWED_THEMES]
                [:option
                    (if (= theme current-theme)
                        {:value theme :selected "_"}
                        {:value theme})
                theme])]
        [:script {:src JS_LOCATION}]])

(defn main-page [theme]
    (let [raw-speech (eval-grammar political-speech (keyword theme))
          with-html (htmlize-newlines raw-speech)]
          (str PAGE_HEADER
            (html (surrounding-page with-html theme)))))
