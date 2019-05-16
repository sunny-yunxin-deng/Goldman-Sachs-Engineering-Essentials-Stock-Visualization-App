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

package utility;
import pojo.Company;

import java.util.List;

/**
 * Utility class to validate inputs
 */



public class InputValidator {

    // TODO - write a method that will validate your JSON input files
    public boolean validateJSON() {
        return true;
    }
    // TODO - write a method that will validate the inputs to the Company Resource


    // TODO - write a method that will validate the inputs to the Stock Resource

    //get json --> validate -->
    public static List<Company> validateAllCompanies(List<Company> companyList) throws InvalidJsonInputException{
        for (Company company: companyList) {
            if (company.getNumberOfEmployees()<0){
                throw new InvalidJsonInputException("Invalid Company Data");
            }
            if (company.getIndustry() == null){
                company.setIndustry("Not Available");
            }
            if (company.getHeadquartersStateOrCountry() == null){
                company.setHeadquartersStateOrCountry("Not Available");
            }

            if (company.getHeadquartersCity() == null){
                company.setHeadquartersCity("Not Available");
            }

            if (company.getSector() == null){
                company.setSector("Not Available");
            }

        }
        return companyList;
    }

    public static String validateStockTicker(String input) throws InvalidJsonInputException{
        if (input == null){
            throw new InvalidJsonInputException("Input Stock Ticker Cannot be Null");
        }
        return input;
    }


}
