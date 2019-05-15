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
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utility.FileHelper;
import pojo.*;
import java.util.*;

@Path("stock")
public class StockResource {
    // TODO - Add a @GET resource to get stock data
    // Your service should return data based on 3 inputs
    // Stock ticker, start date and end date
    @GET
    @Path("info/{tick}/{startDate}/{endDate}")
    @Produces(MediaType.APPLICATION_JSON)
    public Double getStockData(@PathParam("tick") String tick,
                              @QueryParam("startDate") String startDate,
                              @QueryParam("endDate") String endDate) throws java.io.IOException {

        List<Stock> stocks = FileHelper.readAllStocks("historicalStockData.json");
        List<HashMap<String, Double>> dailyClosePrice = new ArrayList<HashMap<String, Double>>();
        for (Stock stock : stocks) {
            if(stock.getName().equalsIgnoreCase(tick)) {
                dailyClosePrice = stock.getDailyClosePrice();
            }
        }
        return dailyClosePrice.get(0).get("4/11/2019");
    }
}

