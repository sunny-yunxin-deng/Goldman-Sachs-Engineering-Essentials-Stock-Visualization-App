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
import AsyncSelect from 'react-select/lib/Async';
import Select from 'react-select';
import axios from 'axios';
//import {Typeahead} from 'react-bootstrap-typeahead'; UNCOMMENT this line if you are using the react-bootstrap-typeeahead component

/* If you chose to use react-boostrap-typeahead, look at AsyncTypeahead for a component that 
 * provides auto-complete suggestions as you type. This would require adding a search handler 
 * method for an onSearch prop.
 * https://github.com/ericgio/react-bootstrap-typeahead/blob/master/example/examples/AsyncExample.react.js
 */

 
export default class CompanyInput extends React.Component {

    /**
     * TODO
     * Prefetch the data required to display options fo the typeahead component. Initialize a state array with
     * this data and pass it via props to the typeahead component that will be rendered.
     * https://github.com/ericgio/react-bootstrap-typeahead/blob/master/docs/Data.md
     * e.g.
     * options : [
     *   GS,
     *   AAPL,
     *   FB,
     * ]
     * If you are having difficulty with this, you may hard code the options array from the company data provided for the
     * services.
     */
    constructor(props) {
        super(props);
        this.state = {
            showcompanyinfo: false, //TODO: Use this boolean to determine if the company information should be rendered
            company : {
                symbol: '',
                name: '',
                city: '',
                state: '',
                sector: '',
                industry: ''
            },
            inputValue: "",
            companyList: []
            /**
             * TODO
             * Add any additional state to pass via props to the typeahead component.
             */
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleInputChange = this.handleInputChange.bind(this);
        this.filterTickers = this.filterTickers.bind(this);
        this.promiseOptions = this.promiseOptions.bind(this);
    }

    componentDidMount(){
        axios.get("/api/company/all")
         .then((response) => {

             this.setState(state => {
                 response.data.forEach( (company) => {
                     state.companyList.push({value: company, label:company})
                 })
                 return state;
             })
         })
         .catch()
    }
    
    componentWillReceiveProps(props){
      if(this.state.inputValue != props.ticker){
          this.setState({
              inputValue:props.ticker
          })
      }
  }

    //handleChange(event) {
      //  if (event.length > 0) {
            /**
             * TODO
             * Make a request to your service to GET company information for the selected company and set it in state.
             * The information you will need to determine the URL will be contained in the 'event[0]' object,
             * e.g. event[0] (event[0].symbol if your options are an array of objects) provides you the symbol selected.
             * The URL will be on your localhost (e.g. http://localhost:8000/service_path/some_param) where
             * your service is running. Your service MUST be running for the request to work (you can add a catch function
             * to handle errors). If you successfully retrieve this information, you can set the state objects
             * and render it.
             */
        //    this.setState({showinfo: true});

            //this.props.onChange(..);  Call this.props.onChange with the selected symbol to propagate it
            // to the App component, which will handle it via its own onChane prop,
            // ultimately  used to fetch the data for the LineChart component.

       // }
       // else {
       //     this.setState({showinfo: false});
       //     this.props.onChange(undefined);
       // }
   // }

    handleInputChange(newValue) {
        //console.log(newValue)
        const inputValue = newValue.replace(/\W/g, '');

        //this.props.onChange(newValue);
        // this.setState({
        //     showcompanyinfo: true
        //   });
      };
  
      handleChange(valuetype,actionmeta) {
          if(actionmeta.action == "select-option"){
              axios.get(`/api/company/${valuetype.value}`)
              .then( (response) => {
                this.props.onChange(response.data._symbol)
                this.setState({
                  inputValue:response.data._symbol
                })
              })
          }
      };
  
      filterTickers(inputValue) {
        return this.state.tickerList.filter(i =>
          i.label.toLowerCase().includes(inputValue.toLowerCase())
        );
      };
  
  
      promiseOptions = (inputValue) => {
          new Promise(resolve => {
            setTimeout(() => {
              resolve(this.filterTickers(inputValue));
            }, 1000);
          })
        };
  


    render() {

        /**
         * TODO
         * Render a typeahead component that uses the data prefetched from your service to display a list of companies or
         * ticker symbols. The props you use can be stored as state objects.
         * On change should fetch the company information and display Company, Ticker Symbol, City, State/Country, Sector, and Industry information.
         * https://github.com/ericgio/react-bootstrap-typeahead/blob/master/docs/Props.md
         */

        return (
            <div className="stockticker">
                <div className="ticker-input">
                  <div className="stockticker-typeahead">
                  <pre>Company Name: </pre>
                    {/* <AsyncSelect
                      placeholder='Search...'
                      cacheOptions
                      backspaceRemovesValue={true}
                      loadOptions={this.promiseOptions}
                      defaultOptions={this.state.tickerList}
                      onInputChange={this.handleInputChange} 
                      onChange = {this.handleChange}
                    /> */}
                    <Select
                      placeholder='Search...'
                      cacheOptions
                      backspaceRemovesValue={true}
                      //loadOptions={this.promiseOptions}
                      options={this.state.companyList}
                      onInputChange={this.handleInputChange} 
                      onChange = {this.handleChange}
                      value = {{label:this.state.inputValue, value:this.state.inputValue}}
                    />
                      {/* useful props if you decide to use react-bootstrap-typeahead
                        <Typeahead
                             align=
                             filterBy=
                             labelKey=
                             onChange={this.handleChange}
                             minLength=
                             placeholder="Company Name/Ticker"
                             options=
                        />
                      */}
                    </div>
                </div>
                <div>
                    <br></br>
                      {this.state.showcompanyinfo && 
                      <div>
                      <p><strong>Company: </strong></p>
                      <p><strong>Ticker Symbol: </strong></p>
                      <p><strong>City: </strong></p>
                      <p><strong>State/Country: </strong></p>
                      <p><strong>Sector: </strong></p>
                      <p><strong>Industry: </strong></p>
                      </div>
                      }
                    </div>
                {
                    /**
                     *  TODO
                     *  Create a div element that shows a company information when the ticker changes. You will need to use a conditional here
                     *  to help control rendering and pass these states as props to the component. This conditional can
                     *  be maintained as a state object.
                     *  http://reactpatterns.com/#conditional-rendering
                     */
                }
            </div>
        );
    }

}
