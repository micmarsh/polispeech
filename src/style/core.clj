(ns style.core
    (:use [garden.def :only [defstyles]]
          [garden.units :only [px em]]))


(def BOX_SIZING
    {
    :-webkit-box-sizing :border-box
    :-moz-box-sizing :border-box
    :box-sizing :border-box
    })

(def CENTER "0 auto")

(def TITLE_MARGIN CENTER)
(def TITLE_WIDTH (px 400))

(def SPEECH_MARGIN CENTER)
(def SPEECH_WIDTH (px 600))
(def SPECH_SIZE (px 20))

(def SELECTOR_SIZE (px 100))
(def SELECTOR_MARGIN "0 1.5em")

(defstyles main
    [:body
       ; ["*" BOX_SIZING]
        [:h1
            {:max-width TITLE_WIDTH
             :margin TITLE_MARGIN}]
        [:div#speech
            {:font-size SPECH_SIZE
             :max-width SPEECH_WIDTH
             :margin SPEECH_MARGIN}]
        [:select#theme
            {:max-width SELECTOR_SIZE
             :margin SELECTOR_MARGIN}]])

