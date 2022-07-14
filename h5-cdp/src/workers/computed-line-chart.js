onmessage = (event) => {
    let start_date = new Date('2022-05-01').getTime();
    let end_date = new Date('2022-05-05').getTime();
    // console.log((end_date - start_date) / (24 * 60 * 60 * 1000))
    // computedDataFunc(event.data)
    computedLineChart(event.data)
}

function computedLineChart(value) {
    let datas = value.datas;
    let times = value.times;
    let modelDetail = value.modelDetail;
    let chartTypeStr = modelDetail.chartType==0?'line':'bar'
    let selectBy = modelDetail.selectPropertyParams.map(x => {return x.name});
    let groupByCn =modelDetail.groupPropertyParams.map(x => {return x.propertyCn});
    let serirs = [];
    let serirs_map = new Map();
    // console.log(value);
    datas.forEach((item) => {
        let groupStr = "";
        groupByCn.forEach(function(g,i){
            groupStr+=item[g]+"-";
        })
        selectBy.forEach(function(s,i){
            let fieldName = groupStr+s;
            let datesMap=new Map();
            if(serirs_map.has(fieldName)){
                datesMap = serirs_map.get(fieldName);
            }
            datesMap.set(item.dates,item[s]);
            serirs_map.set(fieldName,datesMap);
        })
    })

    serirs_map.forEach(function(value,key){

        let seriesObject = {
            name: key,
            type: chartTypeStr,
            data:[]
        };
        let fieldValue= [];
        times.forEach(function(time){
            if(value.has(time)){
                fieldValue.push(value.get(time));
            }else{
                fieldValue.push("0");
            }
        })
        seriesObject.data=fieldValue
        serirs.push(seriesObject);
    })


    postMessage({
        legend: [...serirs_map.keys()],
        xAxis: times,
        serirs: serirs
    })

    close()
}

function getDateStr(startDate, endDate, dayLength) {
    var str = startDate;
    for (var i = 0; ; i++) {
        var getDate = getTargetDate(startDate, dayLength);
        startDate = getDate;
        if (getDate <= endDate) {
            str += ',' + getDate;
        } else {
            break;
        }
    }
    return str.split(",");
}

function getTargetDate(date, dayLength) {
    dayLength = dayLength + 1;
    var tempDate = new Date(date);
    tempDate.setDate(tempDate.getDate() + dayLength);
    var year = tempDate.getFullYear();
    var month = tempDate.getMonth() + 1 < 10 ? "0" + (tempDate.getMonth() + 1) : tempDate.getMonth() + 1;
    var day = tempDate.getDate() < 10 ? "0" + tempDate.getDate() : tempDate.getDate();
    return year + "-" + month + "-" + day;
}

function computedDataFunc(datas) {
    var times = getDateStr('2022-05-01', '2022-05-31', 0);
    //指标数组
    var fieldArrays = ["全站流量的总次数", "全站流量的总用户数"];
    //按.....查看数组
    var groupbyArrays = ["platform_type"];

    var legend = [];
    for (var i = 0; i < datas.length; i++) {
        let obj = datas[i];
        for (var k = 0; k < fieldArrays.length; k++) {
            if (groupbyArrays.length > 0) {
                for (var p = 0; p < groupbyArrays.length; p++) {
                    let title = fieldArrays[k] + "-" + obj[groupbyArrays[p]];
                    if (legend.indexOf(title) == -1) {
                        legend.push(title);
                    }
                }
            } else {
                legend.push(fieldArrays[k]);
            }
        }
    }
    var series = [];
    for (var i = 0; i < legend.length; i++) {
        var obj = {};
        obj.name = legend[i];
        obj.type = "line";
        let arr = legend[i].split("-");
        let timesArrayData = [];
        for (var t = 0; t < times.length; t++) {
            let timeValue = 0;
            for (var k = 0; k < datas.length; k++) {
                //指标Key
                let fieldKey = arr[0];
                if (arr.length > 1) {
                    let istrue = true;
                    if (datas[k]["dates"] == times[t]) {
                        for (var a = 1; a <= arr.length; a++) {
                            if (datas[k][groupbyArrays[a - 1]] != arr[a]) {
                                istrue = false;
                                break;
                            }
                        }
                    } else {
                        istrue = false;
                    }
                    if (istrue) {
                        timeValue = datas[k][fieldKey];
                    }
                } else {
                    if (datas[k]["dates"] == times[t]) {
                        timeValue = datas[k][fieldKey];
                        break;
                    }
                }
            }
            timesArrayData[t] = timeValue;
        }
        obj.data = timesArrayData;
        series.push(obj);
    }

    // console.log("series====");
    // console.log(series);
    // console.log("legend====");
    // console.log(legend);
}
