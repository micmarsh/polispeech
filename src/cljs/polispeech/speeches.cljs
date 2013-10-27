(ns polispeech.speeches
    (:use
        [polispeech.utils :only [get-req HOST]]))

(def SPEECH_URL (str "http://" HOST "/speech"))

(defn get-speech [theme]
    (let [url (str SPEECH_URL "?theme=" theme)
        result (get-req url)]
    result))
