(defproject polispeech "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                [words "0.2.1"]
                [marshmacros "0.2.1"]
                [liberator "0.9.0"]
                [compojure "1.1.5"]
                [http-kit "2.1.13"]
                [ring/ring-devel "1.1.8"]
                [ring-cors "0.1.0"]
                [hiccup "1.0.4"]
                [org.clojure/clojurescript "0.0-1913"]]

  :min-lein-version "2.1.2"

  :main polispeech.core

  :plugins [[lein-cljsbuild "0.3.3"]]

  :cljsbuild {:builds
              [{:source-paths ["src/cljs"]
                :compiler {:output-to "resources/public/js/speeches.js"
                           :optimizations :advanced
                           :pretty-print true}}]}
  )
