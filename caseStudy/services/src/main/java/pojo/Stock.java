/**
 * Copyright 2019 Goldman Sachs.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package pojo;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

/**
 * This class will define a company's end-of-day stock price
 * Look at resources/data/historicalStockData.json
 */
public class Stock {
    @JsonProperty
    private String name;

    private SortedMap<Date, Double> dailyClosePrices;

    public Stock (String name, SortedMap<Date, Double> dailyClosePrices) {
        this.name = name;
        this.dailyClosePrices = dailyClosePrices;
    }

    /** Getters */
    public String getName () {
        return name;
    }
    public SortedMap<Date, Double> getDailyClosePrices () {
        return dailyClosePrices;
    }

    /** Setters */
    public void setName (String name) {
        this.name = name;
    }

    public void setDailyClosePrices (SortedMap<Date, Double> dailyClosePrices) {
        this.dailyClosePrices = dailyClosePrices;
    }

    /** For local testing */
    public static void main(String[] args) {
    }
    
}
