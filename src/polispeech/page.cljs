(ns polispeech.page
    (:use
        [dommy.core :only [listen! set-html!]]
        [polispeech.speeches :only [htmlize-newlines get-political-speech]])
    (:require [polispeech.utils :as utils])
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

(def event-text (comp utils/selected-text utils/event-elem))
(def set-speech! (partial set-html! speech-elem))

(defn set-from-hash! []
  (->> (or (utils/get-hash)
           "#mainstream")
    (rest)
    (apply str)
    (make-speech)
    (set-speech!)))

(listen! selector-elem :change
    (fn [event]
        (let [theme (event-text event)]
             (utils/set-hash! theme)
             (set-from-hash!))))

(set-from-hash!)
