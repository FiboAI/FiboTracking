import useDate from "_hooks/useDate";
const { getTime,getAddTime,lastTimeFirstDay,lastTimeEndDay } = useDate();
export default function () {
    const rangeShortcuts:Array<{label:string,value:[string,string]}> = [
        {
            label:'今日',
            value:[getTime(),getTime()]
        },{
            label:'昨日',
            value:[getAddTime(-1),getAddTime(-1)]
        },{
            label:'本周',
            value:[lastTimeFirstDay(),getTime()]
        },{
            label:'上周',
            value:[lastTimeFirstDay(-1,'week'),lastTimeEndDay(-1,'week')]
        },{
            label:'本月',
            value:[lastTimeFirstDay(0,'month',0),getTime()]
        },{
            label:'上月',
            value:[lastTimeFirstDay(-1,'month',0),lastTimeEndDay(-1,'month',0)]
        },{
            label:'本年',
            value:[lastTimeFirstDay(0,'year'),getTime()]
        },{
            label:'去年',
            value:[lastTimeFirstDay(-1,'year',0),lastTimeEndDay(-1,'year',0)]
        }
    ]
    return {
        rangeShortcuts
    }
}
