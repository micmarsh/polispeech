(ns polispeech.pages
    (:use
        [polispeech.speeches :only [htmlize-newlines get-political-speech]]
        [hiccup.core :only [html]]))

(def PAGE_TITLE "Political Speech Generator")
(def PAGE_HEADER (html [:head [:title PAGE_TITLE]]))

(def ALLOWED_THEMES #{"mainstream" "radical"})

(def JS_VERSION "1.0.1")
(def JS_LOCATION (str "js/speeches.js/?v=" JS_VERSION))


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
    (let [raw-speech (get-political-speech theme)
          with-html (htmlize-newlines raw-speech)]
          (str PAGE_HEADER
            (html (surrounding-page with-html theme)))))
