(ns polispeech.core
    (:use [compojure.core :only [ANY GET POST defroutes]]
          [polispeech.resources :only [render-main get-speech]]
        ring.middleware.cors
        org.httpkit.server)
    (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.middleware.reload :as reload]))

(defroutes routes
    (ANY "/" [] render-main)
    (ANY "/speech" [] get-speech)
    (route/resources "/"))

(def app
    (-> routes
        handler/site
        reload/wrap-reload
    (wrap-cors
        :access-control-allow-origin #".+")))

(defn -main
    ([]
        (run-server app {:port 3000}))
    ([port] ;heroku!
        (run-server app {:port (Integer. port) :join? false})))
