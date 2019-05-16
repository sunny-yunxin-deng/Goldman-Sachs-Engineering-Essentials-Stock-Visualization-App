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

/**
 * This class will define a company and its attributes
 * Look at resources/data/companyInfo.json
 */
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Company {

    // TODO - Think back to your modelling session
    // Define the attributes of a Company based on the
    // provided data in resources/data
    @JsonProperty
    private String _symbol;
    @JsonProperty
    private String _name;
    @JsonProperty
    private String _headquartersCity;
    @JsonProperty
    private String _headquartersStateOrCountry;
    @JsonProperty
    private int _numberOfEmployees;
    @JsonProperty
    private String _sector;
    @JsonProperty
    private String _industry;


    // TODO - add getter and setter methods for your attributes
    public String getSymbol() {
        return _symbol;
    }

    public void setSymbol(String symbol){
        _symbol = symbol;
    }

    public String getName(){
        return _name;
    }

    public void setName(String name){
        _name = name;
    }

    public String getHeadquartersCity() {
        return _headquartersCity;
    }

    public void setHeadquartersCity(String newHC){
        _headquartersCity = newHC;
    }

    public String getHeadquartersStateOrCountry(){
        return _headquartersStateOrCountry;
    }

    public void setHeadquartersStateOrCountry(String newHSC){
        _headquartersStateOrCountry = newHSC;
    }

    public int getNumberOfEmployees(){
        return _numberOfEmployees;
    }

    public void setNumberOfEmployees(int newNE){
        _numberOfEmployees = newNE;
    }

    public String getSector(){
        return _sector;
    }

    public void setSector(String newSector){
        _sector = newSector;
    }

    public String getIndustry(){
        return _industry;
    }

    public void setIndustry(String industry){
        _industry = industry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;

        Company company = (Company) o;

        if (getIndustry() != company.getIndustry()) return false;
        if (getSymbol() != company.getSymbol()) return false;
        if (getSector() != company.getSector()) return false;
        if (getNumberOfEmployees() != company.getNumberOfEmployees()) return false;
        if (getHeadquartersCity() != company.getHeadquartersCity()) return false;
        if (getName() != company.getName()) return false;
        return getHeadquartersStateOrCountry() == company.getHeadquartersStateOrCountry();

    }
}
