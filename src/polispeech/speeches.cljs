(ns polispeech.speeches
    (:use
        [polispeech.utils :only [get-req HOST PROTOCOL log]]))

(def SPEECH_URL (str PROTOCOL "//" HOST "/speech"))

(defn get-speech [theme]
    (let [url (str SPEECH_URL "?theme=" theme)
        result (get-req url)]
    result))
