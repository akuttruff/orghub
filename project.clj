(defproject orghub "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :min-lein-version "2.7.1"

  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/clojurescript "1.10.238"]
                 [org.clojure/core.async  "0.4.474"]
                 ;; sql stuff
                 [org.clojure/java.jdbc "0.7.6"]
                 [org.postgresql/postgresql "42.2.2"]
                 ;; ring / compojure / support libs
                 [ring/ring-core "1.6.3"]
                 [ring/ring-json "0.4.0"]
                 [buddy/buddy-hashers "1.3.0"]
                 ;; cljs dependencies
                 [cheshire "5.8.0"]
                 [cljs-http "0.1.45"]
                 [compojure "1.6.1"]
                 [reagent "0.8.1"]
                 [reagent-utils "0.3.1"]
                 [secretary "1.2.3"]]

  :plugins [[clj-sql-up "0.4.1"]
            [lein-figwheel "0.5.16"]
            [lein-cljsbuild "1.1.7" :exclusions [[org.clojure/clojure]]]
            [lein-ring "0.12.4"]]

  :clj-sql-up {:database "jdbc:postgresql://orghub:orghub@127.0.0.1:5432/orghub"
               :deps [[org.postgresql/postgresql "42.2.2"]]}
  ;; :ring {:handler orghub.server/app}

  :source-paths ["src"]
  :cljsbuild {:builds
              [{:id "dev"
                :source-paths ["src"]

                ;; The presence of a :figwheel configuration here
                ;; will cause figwheel to inject the figwheel client
                ;; into your build
                :figwheel {:on-jsload "orghub.core/on-js-reload"
                           :open-urls ["http://localhost:3449"]}

                :compiler {:main orghub.core
                           :asset-path "js/compiled/out"
                           :output-to "resources/public/js/compiled/orghub.js"
                           :output-dir "resources/public/js/compiled/out"
                           :source-map-timestamp true
                           ;; To console.log CLJS data-structures make sure you enable devtools in Chrome
                           ;; https://github.com/binaryage/cljs-devtools
                           :preloads [devtools.preload]}}
               ;; This next build is a compressed minified build for
               ;; production. You can build this with:
               ;; lein cljsbuild once min
               {:id "min"
                :source-paths ["src"]
                :compiler {:output-to "resources/public/js/compiled/orghub.js"
                           :main orghub.core
                           :optimizations :advanced
                           :pretty-print false}}]}

  :figwheel {;; :http-server-root "public" ;; default and assumes "resources"
             :css-dirs ["resources/public/css"] ;; watch and update CSS

             :ring-handler orghub.server/app
             :server-port 3449

             ;; :repl false ;; to disable REPL
             ;; to configure a different figwheel logfile path
             ;; :server-logfile "tmp/logs/figwheel-logfile.log"
             :server-logfile false}


  ;; Setting up nREPL for Figwheel and ClojureScript dev
  ;; Please see:
  ;; https://github.com/bhauman/lein-figwheel/wiki/Using-the-Figwheel-REPL-within-NRepl
  :profiles {:dev {:dependencies [[binaryage/devtools "0.9.9"]
                                  [figwheel-sidecar "0.5.16"]
                                  [cider/piggieback "0.3.1"]]
                   ;; need to add dev source path here to get user.clj loaded
                   :source-paths ["src" "dev"]
                   ;; for CIDER
                   ;; :plugins [[cider/cider-nrepl "0.12.0"]]
                   :repl-options {:nrepl-middleware [cider.piggieback/wrap-cljs-repl]}
                   ;; need to add the compliled assets to the :clean-targets
                   :clean-targets ^{:protect false} ["resources/public/js/compiled"
                                                     :target-path]}})
