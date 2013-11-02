(ns style.core
    (:use [garden.def :only [defstyles]]
          [garden.units :only [px]]))

(def SPEECH_MARGIN "0 20% 0 20%")
(def TITLE_MARGIN "0 50% 0 %50")

(defstyles main
    [:body
        [:h1
            {:margin SPEECH_MARGIN}]
        [:p
            {:margin SPEECH_MARGIN}]])

