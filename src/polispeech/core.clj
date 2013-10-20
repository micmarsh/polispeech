(ns polispeech.core
    (:use [compojure.core :only [ANY GET POST defroutes]]
          [polispeech.resources :only [render-main]]
        ring.middleware.cors
        org.httpkit.server)
    (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.middleware.reload :as reload]))

(defroutes routes
    (ANY "/" [] render-main)
    (route/resources "/"))

(def app
    (-> routes
        handler/site
        reload/wrap-reload
    (wrap-cors
        :access-control-allow-origin #".+")))

(defn -main [& args]
    (run-server app {:port 3000}))
