"use strict";

// Shared Colors Definition
const primary = '#6993FF';
const success = '#1BC5BD';
const info = '#8950FC';
const warning = '#FFA800';
const danger = '#F64E60';

// Class definition
function generateBubbleData(baseval, count, yrange) {
    var i = 0;
    var series = [];
    while (i < count) {
        var x = Math.floor(Math.random() * (750 - 1 + 1)) + 1;
        var y = Math.floor(Math.random() * (yrange.max - yrange.min + 1)) + yrange.min;
        var z = Math.floor(Math.random() * (75 - 15 + 1)) + 15;

        series.push([x, y, z]);
        baseval += 86400000;
        i++;
    }
    return series;
}

function generateData(count, yrange) {
    var i = 0;
    var series = [];
    while (i < count) {
        var x = 'w' + (i + 1).toString();
        var y = Math.floor(Math.random() * (yrange.max - yrange.min + 1)) + yrange.min;

        series.push({
            x: x,
            y: y
        });
        i++;
    }
    return series;
}

var KTApexChartsDemo = function () {
    // Private functions
    var monthlyRequests = function () {
        const apexChart = "#monthlyRequests";
        var options = {
            series: [{
                name: 'Requests',
                data: requestPerMonth
            }],
            chart: {
                type: 'line',
                height: 350
            },
            plotOptions: {
                bar: {
                    horizontal: false,
                    columnWidth: '55%',
                    endingShape: 'rounded'
                },
            },
            dataLabels: {
                enabled: false
            },
            stroke: {
                show: true,
                width: 5,
                colors: ['smooth']
            },
            xaxis: {
                categories: monthNames,
            },
            yaxis: {
                title: {
                    text: 'Requests'
                },

            },
            fill: {
                type: 'gradient',
                gradient: {
                    shade: 'dark',
                    gradientToColors: [ '#FDD835'],
                    shadeIntensity: 1,
                    type: 'horizontal',
                    opacityFrom: 1,
                    opacityTo: 1,
                    stops: [0, 100, 100, 100]
                },
            },
            tooltip: {
                y: {
                    formatter: function (val) {
                        return  val + " requests";
                    }
                }
            },
            colors: [primary, success, warning]
        };

        var chart = new ApexCharts(document.querySelector(apexChart), options);
        chart.render();
    }

    var vehiclePerDepartment = function () {
        const apexChart = "#vehiclePerDepartment";
        var options = {
            series: Object.values(requestPerDepartmentData),
            chart: {
                width: 350,
                type: 'donut',
            },
            labels: Object.keys(requestPerDepartmentData),
            responsive: [{
                breakpoint: 480,
                options: {
                    chart: {
                        width: 200
                    },
                    legend: {
                        position: 'bottom'
                    }
                }
            }],
            colors: [primary, success, warning, danger, info]
        };

        var chart = new ApexCharts(document.querySelector(apexChart), options);
        chart.render();
    }

    var vehiclePerStatus = function () {
        const apexChart = "#vehiclePerStatus";
        var options = {
            series: Object.values(vehiclePerStatusData),
            chart: {
                width: 380,
                type: 'pie',
            },
            labels: Object.keys(vehiclePerStatusData),
            responsive: [{
                breakpoint: 480,
                options: {
                    chart: {
                        width: 200
                    },
                    legend: {
                        position: 'bottom'
                    }
                }
            }],
            colors: [primary, success, warning, danger, info]
        };

        var chart = new ApexCharts(document.querySelector(apexChart), options);
        chart.render();
    }


    return {
        // public functions
        init: function () {
            monthlyRequests();
            vehiclePerDepartment();
            vehiclePerStatus();
        }
    };
}();

jQuery(document).ready(function () {
    KTApexChartsDemo.init();
});
