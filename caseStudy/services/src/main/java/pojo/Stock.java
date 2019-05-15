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
    @JsonProperty
    private List<HashMap<Object, Object>> dailyClosePrice;

public Stock() {

}
    /** Getters */
    public String getName () {
        return name;
    }
    public List<HashMap<Object, Object>> getDailyClosePrice () {
        return dailyClosePrice;
    }

    /** Setters */
    public void setName (String name) {
        this.name = name;
    }

    public void setDailyClosePrice (List<HashMap<Object, Object>> dailyClosePrice) {
        this.dailyClosePrice = dailyClosePrice;
    }

    /** For local testing */
    public static void main(String[] args) {
//        System.out.println("Testing");
//        Stock s = new Stock("yo");
//        System.out.println(s.getName());
//        s.setName("changed");
//        System.out.println(s.getName());
    }
    
}
