(ns lakepend.db
  (:require [clojure.java.jdbc :as jdbc]
            [clojure.string :as str]))

(def db-conf {:classname "org.sqlite.JDBC"
              :subprotocol "sqlite"
              :subname "resources/database.db"})

(defn insert-weather-data
  [weather-data]
  (jdbc/insert-multi! db-conf :weather weather-data))

(defn find-last-datetime
  []
  (-> (jdbc/query db-conf "SELECT recorded_at FROM weather ORDER BY recorded_at DESC LIMIT 1")
      first
      :recorded_at))

(def avg-median-query
  "SELECT
  AVG(wind_speed) AS avg_wind_speed,
  AVG(air_temp) AS avg_air_temp,
  AVG(baro) AS avg_baro,
  MEDIAN(wind_speed) AS med_wind_speed,
  MEDIAN(air_temp) AS med_air_temp,
  MEDIAN(baro) AS med_baro
  FROM weather
  WHERE recorded_at >= ? AND recorded_at <= ?")

(defn find-data-in-range
  [start-ms end-ms]
  (first 
   (jdbc/query db-conf [avg-median-query start-ms end-ms])))
