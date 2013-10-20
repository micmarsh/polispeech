(ns polispeech.utils)

(defn selected-text[selector]
    (let [items-array (aget selector "selectedOptions")
          item (aget items-array 0)
          text (aget item "value")]
        text))

(defn event-elem [event]
    (aget event "srcElement"))

(defn log [thing]
    (.log js/console thing))
