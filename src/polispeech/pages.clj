(ns polispeech.pages
    (:use
        [polispeech.speeches :only [htmlize-newlines get-political-speech]]
        [hiccup.core :only [html]]
        clojure.core.typed))

;(ann ^:no-check hiccup.compiler/render-html [String  -> String])
(ann ^:no-check hiccup.core/html [Vec  -> String])


(ann PAGE_TITLE String)
(def PAGE_TITLE "Political Speech Generator")
(ann PAGE_HEADER String)
(def PAGE_HEADER (html [:head [:title PAGE_TITLE]]))

(ann ALLOWED_THEMES (Set String))
(def ALLOWED_THEMES #{"mainstream" "radical"})

(ann JS_VERSION String)
(def JS_VERSION "1.0.1")
(ann JS_LOCATION String)
(def JS_LOCATION (str "js/speeches.js/?v=" JS_VERSION))

(ann surrounding-page [String String -> Vec])
(defn- surrounding-page [speech current-theme]
    [:body
        [:h1 PAGE_TITLE]
        [:p#speech speech]
        [:select#theme
            (for> :- Vec
                [theme :- String
                 ALLOWED_THEMES]
                [:option
                    (if (= theme current-theme)
                        {:value theme :selected "_"}
                        {:value theme})
                theme])]
        [:script {:src JS_LOCATION}]])

(ann main-page [String -> String])
(defn main-page [theme]
    (let [raw-speech (get-political-speech theme)
          with-html (htmlize-newlines raw-speech)]
          (str PAGE_HEADER
            (html (surrounding-page with-html theme)))))
