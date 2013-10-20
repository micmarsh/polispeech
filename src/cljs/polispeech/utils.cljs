(ns polispeech.utils
    (:require
        [goog.net.XhrIo :as xhr])
    (:use
        [cljs.core.async :only [chan close!]])
    (:use-macros
        [cljs.core.async.macros :only [go alt!]]))

(defn selected-text[selector]
    (let [items-array (aget selector "selectedOptions")
          item (aget items-array 0)
          text (aget item "value")]
        text))

(defn event-elem [event]
    (aget event "srcElement"))

(defn log [thing]
    (.log js/console thing))

(defn get-req [url]
    (let [ch (chan 1)]
        (xhr/send url
            (fn [event]
                (let [resp (-> event .-target .getResponseText)]
                    (go (>! ch resp)
                        (close! ch)))))
        ch))
