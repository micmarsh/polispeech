(ns polispeech.page
    (:use
        [dommy.core :only [listen! set-html!]]
        [polispeech.utils :only [selected-text event-elem log]]
        [polispeech.speeches :only [htmlize-newlines get-political-speech]]
    (:use-macros
        [hiccups.core :only (html)]
        [dommy.macros :only [sel1]])))

(defn make-speech [theme]
  (let [raw-speech (get-political-speech theme)
        with-html (htmlize-newlines raw-speech)]
    (html (surrounding-page with-html theme))))


(def speech (sel1 :div#speech))
(def selector (sel1 :select#theme))

(def event-text (comp selected-text event-elem))
(def set-speech! (partial set-html! speech))

(listen! selector :change
    (fn [event]
        (log (event-elem event))
        (let [theme (event-text event)
              new-speech (make-speech theme)]
            (set-speech! new-speech))))

(-> "mainstream" make-speech set-speech!)


