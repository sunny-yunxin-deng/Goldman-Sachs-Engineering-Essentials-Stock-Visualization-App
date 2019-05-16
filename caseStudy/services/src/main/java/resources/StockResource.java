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


    // TODO - Add a @GET resource to get stock data
    // Your service should return data based on 3 inputs
    // Stock ticker, start date and end date
    @GET // stock/info/ADBE/4_13_2019/4_15_2019
    @Path("info/{tick}/{startDate}/{endDate}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStockData(@PathParam("tick") String tick,
                              @PathParam("startDate") String startDate,
                              @PathParam("endDate") String endDate) throws java.io.IOException {


        List<Stock> stocks = FileHelper.readAllStocks("historicalStockData.json");
        LinkedHashMap<String, Double> dailyClosePriceMap = new LinkedHashMap<String, Double>();
        for (Stock stock : stocks) {
            if(stock.getName().equalsIgnoreCase(tick)) {
                dailyClosePriceMap = stock.getDailyClosePrice().get(0);
            }
        }

        if (startDate == null) {
            startDate = "";
        }
        if (endDate == null) {
            endDate = "";
        }

        String[] dateSet = new String[dailyClosePriceMap.keySet().size()];
        int counter = 0;
        for (String key : dailyClosePriceMap.keySet()) {
            dateSet[counter] = key;
            counter++;
        }
        List<String> dateList = pojo.Date.consecutiveListOfDates(startDate, endDate, dateSet);
        LinkedHashMap<String, Double> newDailyClosePrices = new LinkedHashMap<String, Double>();
        for(String dateString : dateList) {
            Double value = dailyClosePriceMap.get(dateString);
            newDailyClosePrices.put(dateString, value);
        }


        return Response.ok().entity(newDailyClosePrices).build();

    }
}

