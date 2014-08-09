(ns polispeech.utils)

(defn- location-part [part]
    (aget js/location part))

;for reference: ff selector element have an "options" array, and
; a "selectedIndex" property that can be used to get "item" below,
;then should grab text as value
(defn selected-text[selector]
    (let [items-array (aget selector "selectedOptions")
          item (aget items-array 0)
          text (aget item "value")]
        text))

(defn event-elem [event]
    (aget event "target"))

(defn get-hash []
    (let [result (.-hash js/location)]
        (if (empty? result)
            nil
            result)))

(defn set-hash! [word]
    (set! (.-hash js/location)
        (if (= \# (first word))
            word
            (str \# word))))

