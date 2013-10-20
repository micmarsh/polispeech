(ns polispeech.resources
    (:use
        [polispeech.pages :only [main-page]]
        [polispeech.speeches :only [get-political-speech htmlize-newlines]]
        [liberator.core :refer [resource defresource]]
        [liberator.dev :refer [wrap-trace]]))


(defn- get-params [context]
    (get-in context [:request :params]))

(defresource render-main
    :available-media-types ["text/html"]
    :allowed-methods [:get]
    :handle-ok (fn [context]
        (let [theme (:theme (get-params context))]
            (main-page theme)))

)

(defresource get-speech
    :available-media-types ["text/plain"]
    :allowed-methods [:get]
    :handle-ok (fn [context]
        (let [theme (:theme (get-params context))
              make-result (comp htmlize-newlines get-political-speech)]
            (make-result theme)))
)
