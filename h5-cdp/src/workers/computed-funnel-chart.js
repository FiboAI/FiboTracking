onmessage = (event) => {
    computedFunnelChart(event.data)
}

function computedFunnelChart(data){

var   arr = []


for(let key in data.total){
    if(key.substring(0,4)=='step'){
        arr[Number(key.substring(4))-1]={num:data.total[key]}
    }

}


let lastValue
arr.forEach((value,index)=>{
    
    if(index){
        var num = value.num/lastValue.num*100
        value.scale = isNaN(num)?0 : num.toFixed(2)
    }
    lastValue = value
})

var num = arr[arr.length-1].num /arr[0].num*100
arr[0].scale = num?num.toFixed(2):0



var Xdata = []
if(data.type===0){
    let num = 0
    for(let key in data.total){
        if(key.substring(0,4)=='step'){
            Number(key.substring(4))>num?num =  Number(key.substring(4)) : ''
        }
    }
    Xdata = data.datas.map(value=>{
        return (value['step'+num]/value['step1']*100).toFixed(2)
    })
}else{
    Xdata = data.datas.map(value=>{
        return (value['step'+(data.type+1)]/value['step'+data.type]*100).toFixed(2)
    })
}


var serirs = {
        data:Xdata,
        type:'line'
    }









postMessage({
    conversion:arr,
    serirs
})
close()
}


