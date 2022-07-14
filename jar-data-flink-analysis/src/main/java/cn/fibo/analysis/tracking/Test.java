package cn.fibo.analysis.tracking;

import com.alibaba.fastjson.JSON;
import org.json.JSONObject;

/**
 * @author lisw
 * @program jar-data-analysis-all
 * @description
 * @createDate 2022-05-20 22:08:31
 * @slogan 长风破浪会有时，直挂云帆济沧海。
 **/
public class Test {

    public static void main(String[] args) {
        String json = "{\"$name\":\"1\"}";
        JSON.parseObject(json, model.class);
        System.out.printf("''''''");
    }


   static class model{
        private String _$name;

       public String get_$name() {
           return _$name;
       }

       public void set_$name(String _$name) {
           this._$name = _$name;
       }
   }

}
