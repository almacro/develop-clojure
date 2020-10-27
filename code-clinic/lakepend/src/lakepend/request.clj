(ns lakepend.request
  (:require [clojure.java.io :as io]
            [clj-http.client :as http]))

;; Need curl -LJO behavior
(defn build-url
  [year]
  ; data cached from https://lpo.dt.navy.mil/data/DM/Environmental_Data_Deep_Moor_{YEAR}.txt
  (str "https://github.com/lyndadotcom/LPO_weatherdata/raw/master/Environmental_Data_Deep_Moor_" 
       year ".txt"))

(defn fetch-for-year
  [year]
  (let [url (build-url year)]
    (try
      (http/get url {:insecure true :as :stream})
      (catch Exception _ nil))))

;; read data file from /resources
;; (defn fetch-for-year 
;;  [year]
;;   (io/resource (str "Environmental_Data_Deep_Moor_" year ".txt")))

(defn fetch-seq-for-year
  [year]
  (some->> (fetch-for-year year)
           :body
           io/reader
           line-seq
           (drop 1)))
