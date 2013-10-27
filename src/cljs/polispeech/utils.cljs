(ns polispeech.utils
    (:require
        [goog.net.XhrIo :as xhr])
    (:use
        [cljs.core.async :only [chan close!]])
    (:use-macros
        [cljs.core.async.macros :only [go alt!]]))

(defn- location-part [part]
    (aget js/location part))

(def HOST (location-part "host"))
(def PROTOCOL (location-part "protocol"))

;TODO: (not a macro b/c compile time) that accounts for ff v. chrome
;(maybe dommy has a way to do this)
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
