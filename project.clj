(defproject quil "2.2.1-SNAPSHOT"
  :description "(mix Processing Clojure)"
  :url "http://github.com/quil/quil"

  :mailing-list {:name "Quil Mailing List"
                 :archive "https://groups.google.com/forum/?fromgroups#!forum/clj-processing"
                 :post "clj-processing@googlegroups.com"}

  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.5.1"]
                 [quil/processing-core "2.2.1"]
                 [quil/processing-pdf "2.2.1"]
                 [quil/processing-dxf "2.2.1"]
                 [quil/jogl-all-fat "2.1.5"]
                 [quil/gluegen-rt-fat "2.1.5"]
                 [quil/processing-js "1.4.8"]
                 [com.lowagie/itext "2.1.7"]]

  :hooks [cljx.hooks]

  :aot [quil.helpers.applet-listener quil.applet]

  :test-selectors {:default (complement :manual)
                   :manual :manual}

  :cljx {:builds [{:source-paths ["src/cljx/quil"]
                   :output-path "target/gen/clj/quil"
                   :rules :clj}
                  {:source-paths ["src/cljx/quil"]
                   :output-path "target/gen/cljs/quil"
                   :rules :cljs}

                  {:source-paths ["test/cljx"]
                   :output-path "target/gentest/clj"
                   :rules :clj}
                  {:source-paths ["test/cljx"]
                   :output-path "target/gentest/cljs"
                   :rules :cljs}]}

  :source-paths ["src/clj" "target/gen/clj" "src/cljs" "target/gen/cljs"]
  :test-paths ["test/clj" "target/gentest/clj"]
  :resource-paths ["resources"]

  :profiles {:dev {:dependencies [[hiccup "1.0.5"]
                                  [garden "1.1.6"]
                                  [clj-http "0.9.1"]]
                   :plugins [[com.keminglabs/cljx "0.4.0"]]}

             :cljs-testing {:hooks [leiningen.cljsbuild]
                            :plugins [[lein-cljsbuild "1.0.3"]]
                            :dependencies [[prismatic/dommy "0.1.2"]]

                            :cljsbuild
                            {:builds [{:source-paths ["target/classes" "test/clj" "test/cljs" "target/gentest/cljs"]
                                       :compiler
                                       {:output-to "target/js/main.js"
                                        :optimizations :whitespace
                                        :preamble ["processing.js"]
                                        :externs ["externs/processing-externs.js"]
                                        :pretty-print true}}]}}

             :test-1.5.1 [:cljs-testing
                          {:dependencies [[org.clojure/clojure "1.5.1"]
                                          [org.clojure/clojurescript "0.0-2234"]]}]

             :test-1.6.0 [:cljs-testing
                          {:dependencies [[org.clojure/clojure "1.6.0"]
                                          [org.clojure/clojurescript "0.0-2280"]]}]})
