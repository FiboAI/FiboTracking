import { defineStore } from "pinia";


const AnalysisListStore = defineStore('AnalysisList', {
    state: () => ({
        analysis_list: [
            {
                title:'事件分析',
                key:"/analysis/event",
                modelType:0
            },{
                title:'漏斗分析',
                key:"/analysis/funnel",
                modelType:1
            },{
                title:'留存分析',
                key:"/analysis/remain",
                modelType:2
            },{
                title:'自定义查询',
                key:"/analysis/customQueries",
                modelType:3
            },
        ]
    }),
})


export default AnalysisListStore;
