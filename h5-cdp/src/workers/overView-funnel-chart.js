onmessage = (event) => {
    computedFunnelChart(event.data)
}

function computedFunnelChart(data){
var   arr = []
var Xdata = data.times
    var funnelStepDataEntityList = data.modelData.funnelDataEntity.funnelStepDataEntityList;
data.datas.forEach(value => {
    // Xdata.push(value.dates)
    console.log(JSON.stringify(value))
    let num = value['step'+(funnelStepDataEntityList.length)]/value.step1
    arr.push((isNaN(num)?0:Number((num*100).toFixed(2))))
});
var serirs = {
        data:arr,
        type:'line'
    }
postMessage({
    Xdata:Xdata,
    serirs
})
close()
}


