(ns polispeech.resources
    (:use [polispeech.pages :only [main-page]]
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
