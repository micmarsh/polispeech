(ns polispeech.pages
    (:use
        [polispeech.speeches :only [main-speech-from htmlize-newlines]]
        [hiccup.core :only [html]]))


(def PAGE_TITLE "Political Speech Generator")

(def PAGE_HEADER (html [:head [:title PAGE_TITLE]]))

(def ALLOWED_THEMES #{"mainstream" "radical"})


;TODO a better way to make those two args one thing,
;move that select stuff elsewhere
(defn- surrounding-page [speech current-theme]
    (let [selected? #(= % current-theme)]
        [:body
            [:h1 PAGE_TITLE]
            [:p#speech speech]
            [:select#theme
                (for [theme ALLOWED_THEMES]
                    [:option
                    (if (selected? theme)
                        {:value theme :selected "_"}
                        {:value theme})
                    theme])]]))

(defn main-page [theme]
    (let [raw-speech (main-speech-from theme)
          with-html (htmlize-newlines raw-speech)]
          (str PAGE_HEADER
            (html (surrounding-page with-html theme)))))
