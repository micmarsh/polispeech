(ns style.core
    (:use [garden.def :only [defstyles]]
          [garden.units :only [px]]))

(def speech-margin "0 20% 0 20%")

(defstyles main
    [:body
        [:p
            {:margin speech-margin}]])

