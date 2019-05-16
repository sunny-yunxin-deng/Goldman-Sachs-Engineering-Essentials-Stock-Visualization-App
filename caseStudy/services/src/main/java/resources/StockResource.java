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
            return 1;
        }
        if (outputB.length != 3) {
            return 1;
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

//    @GET
//    @Path("{ticker}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getStockDataWithoutParams(@PathParam("ticker") String tick) throws java.io.IOException {
//        List<Stock> stocks = FileHelper.readAllStocks("historicalStockData.json");
//        HashMap<String, Double> readMap = new HashMap<String, Double>();
//        for (Stock stock : stocks) {
//            if(stock.getName().equalsIgnoreCase(tick)) {
//                readMap = stock.getDailyClosePrice().get(0);
//            }
//        }
//        class ValueComparator implements Comparator<String> {
//            Map<String, Double> base;
//            public ValueComparator(Map<String, Double> base) {
//                this.base = base;
//            }
//            // Note: this comparator imposes orderings that are inconsistent with
//            // equals.
//            public int compare(String a, String b) {
//                return myCompare(a, b, "/", "/");
//            }
//        }
//        HashMap<String, Double> dailyClosePriceMap = new HashMap<String, Double>();
//        ValueComparator bvc = new ValueComparator(dailyClosePriceMap);
//        TreeMap<String, Double> sorted_map = new TreeMap<String, Double>(bvc);
//        sorted_map.putAll(dailyClosePriceMap);
//        return Response.ok().entity(sorted_map).build();
//    }
    // TODO - Add a @GET resource to get stock data
    // Your service should return data based on 3 inputs
    // Stock ticker, start date and end date

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTickers() throws Exception {
        List<Stock> stocks = FileHelper.readAllStocks("historicalStockData.json");
        List<String> ticks = new ArrayList<String>();
        for (int i = 0; i < stocks.size(); i++) {
            ticks.add(stocks.get(i).getName());
        }
        return Response.ok().entity(ticks).build();
    }

    @GET
    @Path("{ticker}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStockData(@PathParam("ticker") String tick,
                              @QueryParam("startDate") String startDate,
                              @QueryParam("endDate") String endDate) throws java.io.IOException {

        List<Stock> stocks = FileHelper.readAllStocks("historicalStockData.json");

        HashMap<String, Double> readMap = new HashMap<String, Double>();
        for (Stock stock : stocks) {
            if(stock.getName().equalsIgnoreCase(tick)) {
                readMap = stock.getDailyClosePrice().get(0);
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
            }
        }


        if (startDate == null) {
            startDate = "0/0/0000";
        }
        else if (startDate.length() < 1) {
            startDate = "0/0/0000";
        }

        if (endDate == null) {
            endDate = "13/35/99999";
        }
        else if (endDate.length() < 1) {
            endDate = "13/35/99999";
        }

        HashMap<String, Double> dailyClosePriceMap = new HashMap<String, Double>();
        for (String key : readMap.keySet()) {
            if (myCompare(startDate, key, "/", "/") <= 0 && myCompare(key, endDate, "/", "/") <= 0) {
                dailyClosePriceMap.put(key, readMap.get(key));
            }
        }
        ValueComparator bvc = new ValueComparator(dailyClosePriceMap);
        TreeMap<String, Double> sorted_map = new TreeMap<String, Double>(bvc);
        sorted_map.putAll(dailyClosePriceMap);

        return Response.ok().entity(sorted_map).build();
    }
}

