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

import java.util.Iterator;
import java.util.List;

/**
 * Utility class to validate inputs
 */

class InvalidJsonInputException extends Exception{
    public InvalidJsonInputException(String s){
        super(s);
    }
}

public class InputValidator {

    // TODO - write a method that will validate your JSON input files

    // TODO - write a method that will validate the inputs to the Company Resource


    // TODO - write a method that will validate the inputs to the Stock Resource

    //get json --> validate -->
    public List<Company> validateAllCompanies(List<Company> companyList) throws InvalidJsonInputException{
        for (Company company: companyList ) {
            if (company.getNumberOfEmployees()<0){
                throw new InvalidJsonInputException("Invalid Company Data");
            }
        }
        return companyList;
    }


}
