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

import utility.FileHelper;
import pojo.Company;
import utility.InputValidator;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;
import utility.InvalidJsonInputException;
// TODO - add your @Path here
@Path("companies")
public class CompanyResource {

    // TODO - Add a @GET resource to get company data
    // Your service should return data for a given stock ticker
    @GET
    @Path("{symbol}/companyInfo")
    @Produces(MediaType.APPLICATION_JSON)

    public Response getCompanyInfo(@PathParam("symbol") String symbol) throws IOException, InvalidJsonInputException{
        symbol = InputValidator.validateStockTicker(symbol);
        List<Company> companies = FileHelper.readAllCompanies("/Users/yunxindeng/Desktop/Sophomore/Spring2019/Engineering Essentials/EngineeringEssentials/caseStudy/services/src/main/resources/data/companyInfo.json");
        for(Company company: companies){
            if (company.getSymbol().equalsIgnoreCase(symbol)) {
                return Response.ok().entity(company).build();
            }
        }

        return Response.ok().entity("No matches found for ticker " + symbol).build();
    }

}
