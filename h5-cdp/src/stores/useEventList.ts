import { defineStore } from "pinia";
import type { CascaderOption } from "@arco-design/web-vue";
import { getGroupEventList } from "_apis/analysis";
import type { EventGroupData } from "_types/analysis";

const eventAnalysisStore = defineStore("EventAnalysis", {
    state: () => ({
      event_list: <Array<CascaderOption>>[],
      event_list_NoVirtual: <Array<CascaderOption>>[],
    }),
    actions: {
      getEventlist() {
        // =====================获取事件列表================
        let event_list = reactive<Array<CascaderOption>>([]);
        let event_list_NoVirtual = reactive<Array<CascaderOption>>([]);
        const getDataListFunc=(): void=> {
          getGroupEventList()
            .then((res) => {
              if (res.datas) {
                event_list.length = 0;
                event_list_NoVirtual.length = 0
                event_list.push(...parseDatas(res.datas,true));
                event_list_NoVirtual.push(...parseDatas(res.datas,false));
                this.event_list = event_list
                localStorage.setItem("eventList", JSON.stringify(event_list));
                this.event_list_NoVirtual = event_list_NoVirtual
              }
            })
            .catch((err) => {});
        }
        getDataListFunc();
        // 把数据转换成级联选择对应格式
        const parseDatas = (datas: EventGroupData,haveVirtual:boolean): CascaderOption[] => {
          let list: Array<CascaderOption> = [];
          datas.forEach((item, index) => {
            let data: CascaderOption = {
              label: item.name,
              value: index,
            };
            data.children = item.eventDataDtoList.map(
              (childItem): CascaderOption => {
                let child_data: CascaderOption = {
                  label: childItem.nameCn,
                  value: haveVirtual?`${childItem.nameEn}-${childItem.isVirtual}`:childItem.nameEn,
                  is_virtual: childItem.isVirtual,
                };
                return child_data;
              }
            );
            list.push(data);
          });
          return list;
        };
      },
    },
  });
  
  export default eventAnalysisStore;