(ns polispeech.speeches
    (:use
        [polispeech.utils :only [get-req]])
    (:use-macros
        [cljs.core.async.macros :only [go]]))

(def LOCALHOST "localhost:3000")
(def HOST (str "http://" LOCALHOST "/"))
(def SPEECH_URL (str HOST "speech"))

(defn get-speech [theme]
    (let [url (str SPEECH_URL "?theme=" theme)]
    (go (<! (get-req url)))))
