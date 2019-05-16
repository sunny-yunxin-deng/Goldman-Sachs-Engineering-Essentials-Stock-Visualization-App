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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pojo.*;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import utility.FileHelper;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import static pojo.Company.*;
import static org.junit.Assert.*;
/**
 * Write your tests for the Company Resource here
 */
public class CompanyResourceTest extends JerseyTest {
    private static ObjectMapper mapper = new ObjectMapper();

    @Override
    protected Application configure() {
        return new ResourceConfig(
                CompanyResource.class
        );
    }
    // TODO - write a test for each method in the CompanyResource class
    // Think about both positive and negative test cases:
    // What happens if no inputs are passed?
    // What happens if the input is null?

    @Test
    public void testCorrectOutput(){
        Company avti = target().path("companies/ATVI/companyInfo").request().get(Company.class);
        Company akam = target().path("companies/AKAM/companyInfo").request().get(Company.class);
        String response = target().path("companies/SSSS/companyInfo").request().get(String.class);

        assertEquals("Activision Blizzard Inc", avti.getName());
        assertEquals("Akamai Technologies Inc.", akam.getName());
        assertEquals(response, "No matches found for ticker SSSS");

    }
}
