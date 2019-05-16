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
import Highcharts from 'highcharts';
import HighchartsReact from 'highcharts-react-official'


export default class LineChart extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            options:{
                chart: {
                    type: 'spline'
                    },
                title: {
                    text: 'Stock Prices'
                    },
                xAxis:[{
                    title:{
                        text:"Date"
                    },
                    labels:{
                        format:"{value: %b/%e/%Y}"
                    }
                }],
                yAxis:[{
                    title:{
                        text:"Price"
                    },
                    // labels:{
                    //     format:"{value: %b/%e/%Y}"
                    // }
                }],
                series: [
                    {
                        name:[this.props.ticker],
                        data: []
                    }
                ]
            },
            nodata:!props.data
        }
        console.log(!this.props.data)

    }

    componentDidMount() {
        //const chartRef = this.refs.chartComponent.chart;
        //this.setState({options:options})
    }

    componentWillReceiveProps(props) {

       if(props.data){
            let data = []
            Object.entries(props.data).forEach(([key, val]) => {
                data.push({
                    x: Date.parse(key),
                    y: val,
                    name:key,
                    color: "#0000FF"
                })
            });
            this.setState({
                options:{
                    series: [
                        { 
                            name:[props.ticker],
                            data: data
                        }
                    ]
                },
                nodata:false
            })
       }
       else{
           this.setState({
               nodata:true
           })
       }
    }

    componentWillUnmount() {
        //this.state.chart.destroy();
    }


    render() {
        const {options} = this.state
        return (
            <div id='chart'>
                <HighchartsReact 
                    highcharts={Highcharts}
                    options={options}
                />
                {/* {this.state.nodata && <h1> No data available for these dates :(</h1> } */}
            </div>
        )
    }
}

// const options = {
//     chart: {
//         zoomType: 'x'
//     },
//     title: {
//         text: 'USD to EUR exchange rate over time'
//     },
//     subtitle: {
//         text: document.ontouchstart === undefined ?
//             'Click and drag in the plot area to zoom in' : 'Pinch the chart to zoom in'
//     },
//     xAxis: {
//         type: 'datetime'
//     },
//     yAxis: {
//         title: {
//             text: 'Exchange rate'
//         }
//     },
//     legend: {
//         enabled: false
//     },
//     plotOptions: {
//         area: {
//             fillColor: {
//                 linearGradient: {
//                     x1: 0,
//                     y1: 0,
//                     x2: 0,
//                     y2: 1
//                 },
//                 stops: [
//                     [0, Highcharts.getOptions().colors[0]],
//                     [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
//                 ]
//             },
//             marker: {
//                 radius: 2
//             },
//             lineWidth: 1,
//             states: {
//                 hover: {
//                     lineWidth: 1
//                 }
//             },
//             threshold: null
//         }
//     },

//     series: [{
//         //type: '',
//         name: 'Prices',
//         data: null
//     }]
// }