(ns polispeech.page
    (:use
        [dommy.core :only [listen! set-html!]]
        [polispeech.utils :only [selected-text event-elem log]]
        [polispeech.speeches :only [get-speech]])
    (:use-macros
       [dommy.macros :only [sel1]]
       [cljs.core.async.macros :only [go]]))

(def speech (sel1 :p#speech))
(def selector (sel1 :select#theme))

(def event-text (comp selected-text event-elem))
(def set-speech! (partial set-html! speech))


(listen! selector :change
    (fn [event]
        (log (event-elem event))
        (go
            (let [theme (event-text event)
                  new-speech (get-speech theme)]
                (set-speech! (<! new-speech))))))


