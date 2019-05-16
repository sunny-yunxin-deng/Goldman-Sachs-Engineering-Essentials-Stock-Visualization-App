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

package resources;
import javax.validation.constraints.Null;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import utility.FileHelper;
import pojo.*;
import java.util.*;


@Path("stock")
public class StockResource {
    public static int myCompare(String a, String b, String delim1, String delim2) {
        String[] outputA = a.split(delim1);
        String[] outputB = b.split(delim2);

        if (outputA.length != 3) {
            return -1;
        }
        if (outputB.length != 3) {
            return -1;
        }

        if (Integer.parseInt(outputA[2]) < Integer.parseInt(outputB[2])) {
            return -1;
        }
        else if (Integer.parseInt(outputA[2]) > Integer.parseInt(outputB[2])) {
            return 1;
        }

        if (Integer.parseInt(outputA[0]) < Integer.parseInt(outputB[0])) {
            return -1;
        }
        else if (Integer.parseInt(outputA[0]) > Integer.parseInt(outputB[0])) {
            return 1;
        }

        if (Integer.parseInt(outputA[1]) < Integer.parseInt(outputB[1])) {
            return -1;
        }
        else if (Integer.parseInt(outputA[1]) > Integer.parseInt(outputB[1])) {
            return 1;
        }

        return 0;
    }

    // TODO - Add a @GET resource to get stock data
    // Your service should return data based on 3 inputs
    // Stock ticker, start date and end date
    @GET // stock/info/ADBE/4_13_2019/4_15_2019
    @Path("info/{tick}/{startDate}/{endDate}")
    @Produces(MediaType.APPLICATION_JSON)
    public TreeMap<String, Double> getStockData(@PathParam("tick") String tick,
                              @PathParam("startDate") String startDate,
                              @PathParam("endDate") String endDate) throws java.io.IOException {

        List<Stock> stocks = FileHelper.readAllStocks("historicalStockData.json");
        HashMap<String, Double> dailyClosePriceMap = new HashMap<String, Double>();
        for (Stock stock : stocks) {
            if(stock.getName().equalsIgnoreCase(tick)) {
                dailyClosePriceMap = stock.getDailyClosePrice().get(0);
                //System.out.println(dailyClosePriceMap.toString());
            }
        }

        class ValueComparator implements Comparator<String> {
            Map<String, Double> base;

            public ValueComparator(Map<String, Double> base) {
                this.base = base;
            }

            // Note: this comparator imposes orderings that are inconsistent with
            // equals.
            public int compare(String a, String b) {
                return myCompare(a, b, "/", "/");

                /*
                if (base.get(a) >= base.get(b)) {
                    return -1;
                } else {
                    return 1;
                } // returning 0 would merge keys */
            }
        }

        //dailyClosePriceMap.put(startDate, 0.0);
        //dailyClosePriceMap.put(endDate, 0.0);
        for (String key : dailyClosePriceMap.keySet()) {
            if (myCompare(startDate, key, "/", "-") > 0) {
                dailyClosePriceMap.remove(key);
            }
            else if (myCompare(key, endDate, "-", "/") > 0) {
                dailyClosePriceMap.remove(key);
            }
        }
        ValueComparator bvc = new ValueComparator(dailyClosePriceMap);
        TreeMap<String, Double> sorted_map = new TreeMap<String, Double>(bvc);
        sorted_map.putAll(dailyClosePriceMap);

        /*
        for (String key : sorted_map.keySet()) {
            if (myCompare(startDate, key, "-") > 0) {
                sorted_map.remove(key);
            }
            else if (myCompare(key, endDate, "-") > 0) {
                sorted_map.remove(key);
            }
        }*/
        //sorted_map.put("1/3/13", 0.0);

        return sorted_map;


        /*
        String[] dateSet = new String[dailyClosePriceMap.keySet().size()];
        int counter = 0;
        /*for (String key : dailyClosePriceMap.keySet()) {
            dateSet[counter] = key;
            counter++;
        }*/

        //ArrayList<String> dateList = pojo.Date.consecutiveListOfDates(startDate, endDate, dateSet);
        //return dateList;
        //return dailyClosePriceMap;
//        return Response.ok().entity(dateList).build();
//        return dateList;
    }
}

