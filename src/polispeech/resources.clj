(ns polispeech.resources
    (:use
        [polispeech.pages :only [main-page]]
        [polispeech.speeches :only [get-political-speech htmlize-newlines]]
        [liberator.core :refer [resource defresource]]
        [liberator.dev :refer [wrap-trace]]
        clojure.core.typed))

(ann ^:no-check get-in [Map (Vec clojure.lang.Keyword) -> Map])

(ann DEFAULT_THEME String)
(def DEFAULT_THEME "mainstream")

(ann get-params [Map -> Map])
(defn- get-params [context]
    (get-in context [:request :params]))
(ann get-theme [Map -> String])
(defn- get-theme [ctx]
    (or (:theme (get-params ctx)) DEFAULT_THEME))


(ann ^:no-check liberator.core/run-resource [Any Map -> Any])

(ann render-main Any)
(defresource render-main
    :available-media-types ["text/html"]
    :allowed-methods [:get]
    :handle-ok (fn> :- String
                 [context :- Map]
        (let [theme (get-theme context)]
            (main-page theme)))
)

(ann get-speech Any)
(defresource get-speech
    :available-media-types ["text/plain"]
    :allowed-methods [:get]
    :handle-ok (fn> :- String
                 [context :- Map]
        (let [theme (get-theme context)
              make-result (comp htmlize-newlines get-political-speech)]
            (make-result theme)))
)
