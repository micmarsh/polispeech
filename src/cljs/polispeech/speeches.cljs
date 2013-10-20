(ns polispeech.speeches
    (:use
        [polispeech.utils :only [get-req]]))

(def LOCALHOST "localhost:3000")
(def HEROKU "polispeech.herokuapp.com")
(def HOST (str "http://" HEROKU "/"))
(def SPEECH_URL (str HOST "speech"))

(defn get-speech [theme]
    (let [url (str SPEECH_URL "?theme=" theme)
        result (get-req url)]
    result))
