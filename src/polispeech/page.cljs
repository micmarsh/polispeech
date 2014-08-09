(ns polispeech.page
    (:use
        [dommy.core :only [listen! set-html!]]
        [polispeech.utils :only [selected-text event-elem log]]
        [polispeech.speeches :only [htmlize-newlines get-political-speech]])
    (:use-macros
        [dommy.macros :only (sel1)]))

(set! *print-fn* #(.log js/console %))

(defn- surrounding-page [page theme] page)

(defn make-speech [theme]
  (let [raw-speech (get-political-speech theme)
        with-html (htmlize-newlines raw-speech)]
    (surrounding-page with-html theme)))


(def speech-elem (sel1 :div#speech))
(def selector-elem (sel1 :select#theme))

(def event-text (comp selected-text event-elem))
(def set-speech! (partial set-html! speech-elem))

(listen! selector-elem :change
    (fn [event]
        (log (event-elem event))
        (let [theme (event-text event)]
             (-> theme
              make-speech
              set-speech!))))

(-> "mainstream" make-speech set-speech!)


