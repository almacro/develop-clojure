(defproject lakepend "0.1.0-SNAPSHOT"
  :description "Generate statistics from Lake Pend Oreille dataset"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/java.jdbc "0.7.11"]
                 [org.xerial/sqlite-jdbc "3.32.3.2"]
                 [clj-http "3.10.3"]
                 [clj-time "0.15.2"]]
  :main ^:skip-aot lakepend.core
  :target-path "target/%s"
  :uberjar-name "lakepend.jar"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
