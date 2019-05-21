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

import React from 'react';
import './style/App.css';
import Charts from './components/Charts.js';
import Date from './components/Date.js';
import StockTicker from './components/StockTicker.js';
import CompanyInput from './components/CompanyInput';
import axios from 'axios';
import {Container} from 'reactstrap';

export default class App extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            showcompanyinfo:false,
            companyInfo:{}
            /**
             * TODO
             * Add state objects for the user inputs and anything else you may need to render the highchart.
             */
        };
        this.handleInput = this.handleInput.bind(this);
    }

    handleInput(type,value) {
      this.setState({
        [type]:value
      })
      if(type == "ticker" || type == "company"){
        axios.get(`/api/company/${value}`)
        .then((response) => {
          this.setState({
            companyInfo:response.data,
            showcompanyinfo:true
          })
        })
      }
    };


    render () {
      return (
          <div className="page-display centered">
            
            <h1><strong>Stock Visualization App</strong></h1>
            <br></br>
              <div className="input centered">

              <StockTicker onChange={(value) => {this.handleInput("ticker",value); }}  ticker = {this.state.ticker} />
              <CompanyInput onChange={(value) => {this.handleInput("ticker",value)}}   ticker = {this.state.ticker}/>
              {/**
               * TODO
               * Render the StockTicker and Date components. You can use the date component twice
               * for both the start and end dates.
               * Add onChange props to the StockTicker and both Date components.
               * These props methods should set state and help determine if the
               * highchart should be displayed by changing the state of that boolean.
               * Don't forget to bind these methods!
               */}
                <div className="date-range">
                  <Date text={"Start Date"} onChange={(value) => {this.handleInput("startDate",value)}} />
                </div>
                <div className="date-range">
                  <Date text={"End Date"} onChange={(value) => {this.handleInput("endDate",value)}} />
                </div>
              </div>


                 {/**
                   *  TODO
                   *  Create a div element that shows a highchart when the ticker, start date, end date
                   *  states ALL have values and nothing (null) otherwise. You will need to use a conditional here
                   *  to help control rendering and pass these states as props to the component. This conditional can
                   *  be maintained as a state object.
                   *  http://reactpatterns.com/#conditional-rendering
                   */}

                    {this.state.showcompanyinfo && 
                      <div>
                      <p><strong>Company: </strong> {this.state.companyInfo._name ?  this.state.companyInfo._name : "Unavailable"} </p>
                      <p><strong>Ticker Symbol: </strong> {this.state.companyInfo._symbol ?  this.state.companyInfo._symbol : "Unavailable"} </p>
                      <p><strong>City: </strong> {this.state.companyInfo._headquartersCity ?  this.state.companyInfo._headquartersCity : "Unavailable"}</p>
                      <p><strong>State/Country:  </strong>{this.state.companyInfo._headquartersStateOrCountry ? this.state.companyInfo._headquartersStateOrCountry  : "Unavailable"}</p>
                      <p><strong>Sector: </strong> {this.state.companyInfo.sector ?  this.state.companyInfo.sector : "Unavailable"}</p>
                      <p><strong>Industry: </strong>{this.state.companyInfo.industry ?  this.state.companyInfo.industry : "Unavailable"}</p>
                      </div>
                    }

                   {this.state.ticker && this.state.startDate && this.state.endDate && 
                     <Charts 
                        ticker = {this.state.ticker}
                        startDate = {this.state.startDate}
                        endDate = {this.state.endDate}
                      />
                  }

            </div>
      );
    }
}
