import dayjs, {ManipulateType} from "dayjs"
export default function () {
    // 获取当前是哪一天
    const getTime = (fmt: string = "YYYY-MM-DD") => dayjs().format(fmt)

    //获取几天以后的事件，负数为几天以前的事件
    const getAddTime = (diffNumber:number=1,unit: ManipulateType="day",fmt: string = "YYYY-MM-DD") => dayjs().add(diffNumber,unit).format(fmt)

    const parseTime = (date: string | number | Date, fmt: string = "YYYY-MM-DD") => dayjs(date).format(fmt)


    //获取某个维度的第一天时间
    //diffNumber 时间差，比如获取上周，则此处传-1，unit传week,addDay
    const lastTimeFirstDay =(diffNumber:number=0,unit: ManipulateType="week",addDay:number=1) => dayjs().add(diffNumber,unit).startOf(unit).add(addDay, 'day').format('YYYY-MM-DD');

    //获取某个维度的最后一天时间
    const lastTimeEndDay =(diffNumber:number=0,unit: ManipulateType="week",addDay:number=1) => dayjs().add(diffNumber,unit).endOf(unit).add(addDay, 'day').format('YYYY-MM-DD');



    return {
        getTime,
        parseTime,
        getAddTime,
        lastTimeFirstDay,lastTimeEndDay
    }
}
