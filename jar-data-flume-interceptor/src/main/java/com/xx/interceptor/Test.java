package com.xx.interceptor;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.xx.interceptor.util.Constant;
import com.xx.interceptor.util.GZipUtil;
import org.apache.commons.compress.utils.Charsets;
import org.apache.commons.lang.StringUtils;
import org.mortbay.jetty.Main;
import sun.misc.BASE64Decoder;

/**
 * @author lisw
 * @program jar-analysis-all
 * @description
 * @createDate 2022-04-18 18:09:58
 * @slogan 长风破浪会有时，直挂云帆济沧海。
 **/
public class Test {

    public static void main(String[] args) {
        BASE64Decoder decoder = new BASE64Decoder();
        String data = "解密失败！";
        String eventBody = "211.161.248.172 - - [18/Apr/2022:19:03:40 +0800] \"POST /sa?project=ruishi&openId=orLRi0RR3cfKin7vdZOf4_nckq-o&memberUid=95a8e3e2-cf94-4a6d-b900-3bd5c2f6b8e4&requestId=e13ee658-4979-45ad-a274-c773679495ca&signature=eFaAhcC3QrHkE6SFHVhak7jNJTzWEu3R427BKOAaTifGzEc5kWPgU%2B3wrmo45lrV%2BSSbANhWhiTG9LoZysBFz2GrMxTIrqEgY5NvdtV7OuBnh3nqS9wfEO80nSrd9P4Akiop5CvOpQuPVmBQejCqeN7LsR0u09CPerqHOxZgtjI%3D HTTP/1.1\"502 4020 \"http://127.0.0.1:8080/schaeffler/static/mp/schaeffler/mine/application.html?appid=wxe1a76a00bf7c1461&code=001cBE000Fr8qN17fJ200H7BwI3cBE0D\" \"ApiPOST Runtime +https://www.apipost.cn\" \"-\" data_list=W3siZGlzdGluY3RfaWQiOiIxNjUwMjcxOTE1MTg1LTcxNjQ0MC0wMTQ0MTQ5MWZhNzM0OS00NjE1ODMxNiIsImxpYiI6eyIkbGliIjoiQWxpcGF5TWluaSIsIiRsaWJfbWV0aG9kIjoiY29kZSIsIiRsaWJfdmVyc2lvbiI6IjEuMi4wIn0sInByb3BlcnRpZXMiOnsiJGxpYiI6IkFsaXBheU1pbmkiLCIkbGliX3ZlcnNpb24iOiIxLjIuMCIsIiRuZXR3b3JrX3R5cGUiOiJXSUZJIiwiJG1vZGVsIjoiaXBob25lMTQsNSIsIiRzY3JlZW5fd2lkdGgiOjM5MCwiJHNjcmVlbl9oZWlnaHQiOjg0NCwiJG9zIjoiaU9TIiwiJG9zX3ZlcnNpb24iOiJpT1MiLCIkbWFudWZhY3R1cmVyIjoiSVBIT05FIiwiJGFwcF9pZCI6IjIwMjEwMDMxMDk2ODA5NzkiLCIkdGltZXpvbmVfb2Zmc2V0IjotNDgwLCJwbGF0Zm9ybV90eXBlIjoiQUxJUEFZIiwiY2hhbm5lbCI6IiIsImNoYW5uZWxfdHlwZSI6Iua4oOmBkyIsIiRsYXRlc3Rfc2NlbmUiOiJhbGktMDAwMCIsImFjY291bnQiOiIyMDg4MjEyMTU1NTM2MzQ1IiwibG9naW5fbWV0aG9kIjoi57yT5a2Y55So5oi35L%2Bh5oGvIiwiaXNfcXVpY2tfbG9naW4iOmZhbHNlLCJpc19zdWNjZXNzIjp0cnVlLCIkaXNfZmlyc3RfZGF5Ijp0cnVlfSwidHlwZSI6InRyYWNrIiwiZXZlbnQiOiJMb2dpblJlc3VsdCIsIl90cmFja19pZCI6MTQzNzkzNzYwLCJ0aW1lIjoxNjUwMjc2MjkzNzYwLCJfZmx1c2hfdGltZSI6MTY1MDI3NjQ2Njk1Mn0seyJkaXN0aW5jdF9pZCI6IjE2NTAyNzE5MTUxODUtNzE2NDQwLTAxNDQxNDkxZmE3MzQ5LTQ2MTU4MzE2IiwibGliIjp7IiRsaWIiOiJBbGlwYXlNaW5pIiwiJGxpYl9tZXRob2QiOiJjb2RlIiwiJGxpYl92ZXJzaW9uIjoiMS4yLjAifSwicHJvcGVydGllcyI6eyIkbGliIjoiQWxpcGF5TWluaSIsIiRsaWJfdmVyc2lvbiI6IjEuMi4wIiwiJG5ldHdvcmtfdHlwZSI6IldJRkkiLCIkbW9kZWwiOiJpcGhvbmUxNCw1IiwiJHNjcmVlbl93aWR0aCI6MzkwLCIkc2NyZWVuX2hlaWdodCI6ODQ0LCIkb3MiOiJpT1MiLCIkb3NfdmVyc2lvbiI6ImlPUyIsIiRtYW51ZmFjdHVyZXIiOiJJUEhPTkUiLCIkYXBwX2lkIjoiMjAyMTAwMzEwOTY4MDk3OSIsIiR0aW1lem9uZV9vZmZzZXQiOi00ODAsInBsYXRmb3JtX3R5cGUiOiJBTElQQVkiLCJjaGFubmVsIjoiIiwiY2hhbm5lbF90eXBlIjoi5rig6YGTIiwiJGxhdGVzdF9zY2VuZSI6ImFsaS0wMDAwIiwiJHVybF9wYXRoIjoicGFnZXMvaG9tZS9ob21lIiwiJGlzX2ZpcnN0X3RpbWUiOmZhbHNlLCIkc2NlbmUiOiJhbGktMDAwMCIsIiR1cmxfcXVlcnkiOiJwaWQ9MjE2MyIsIiRpc19maXJzdF9kYXkiOnRydWV9LCJ0eXBlIjoidHJhY2siLCJldmVudCI6IiRNUExhdW5jaCIsIl90cmFja19pZCI6NTc2NDEzNzYzLCJ0aW1lIjoxNjUwMjc2MjkzNzYzLCJfZmx1c2hfdGltZSI6MTY1MDI3NjQ2Njk1Mn0seyJkaXN0aW5jdF9pZCI6IjE2NTAyNzE5MTUxODUtNzE2NDQwLTAxNDQxNDkxZmE3MzQ5LTQ2MTU4MzE2IiwibGliIjp7IiRsaWIiOiJBbGlwYXlNaW5pIiwiJGxpYl9tZXRob2QiOiJjb2RlIiwiJGxpYl92ZXJzaW9uIjoiMS4yLjAifSwicHJvcGVydGllcyI6eyIkbGliIjoiQWxpcGF5TWluaSIsIiRsaWJfdmVyc2lvbiI6IjEuMi4wIiwiJG5ldHdvcmtfdHlwZSI6IldJRkkiLCIkbW9kZWwiOiJpcGhvbmUxNCw1IiwiJHNjcmVlbl93aWR0aCI6MzkwLCIkc2NyZWVuX2hlaWdodCI6ODQ0LCIkb3MiOiJpT1MiLCIkb3NfdmVyc2lvbiI6ImlPUyIsIiRtYW51ZmFjdHVyZXIiOiJJUEhPTkUiLCIkYXBwX2lkIjoiMjAyMTAwMzEwOTY4MDk3OSIsIiR0aW1lem9uZV9vZmZzZXQiOi00ODAsInBsYXRmb3JtX3R5cGUiOiJBTElQQVkiLCJjaGFubmVsIjoiIiwiY2hhbm5lbF90eXBlIjoi5rig6YGTIiwiJGxhdGVzdF9zY2VuZSI6ImFsaS0wMDAwIiwiJHVybF9wYXRoIjoicGFnZXMvaG9tZS9ob21lIiwiJHNjZW5lIjoiYWxpLTAwMDAiLCIkdXJsX3F1ZXJ5IjoicGlkPTIxNjMiLCIkaXNfZmlyc3RfZGF5Ijp0cnVlfSwidHlwZSI6InRyYWNrIiwiZXZlbnQiOiIkTVBTaG93IiwiX3RyYWNrX2lkIjoyODM3MTM3NjUsInRpbWUiOjE2NTAyNzYyOTM3NjUsIl9mbHVzaF90aW1lIjoxNjUwMjc2NDY2OTUyfSx7ImRpc3RpbmN0X2lkIjoiMTY1MDI3MTkxNTE4NS03MTY0NDAtMDE0NDE0OTFmYTczNDktNDYxNTgzMTYiLCJsaWIiOnsiJGxpYiI6IkFsaXBheU1pbmkiLCIkbGliX21ldGhvZCI6ImNvZGUiLCIkbGliX3ZlcnNpb24iOiIxLjIuMCJ9LCJwcm9wZXJ0aWVzIjp7IiRsaWIiOiJBbGlwYXlNaW5pIiwiJGxpYl92ZXJzaW9uIjoiMS4yLjAiLCIkbmV0d29ya190eXBlIjoiV0lGSSIsIiRtb2RlbCI6ImlwaG9uZTE0LDUiLCIkc2NyZWVuX3dpZHRoIjozOTAsIiRzY3JlZW5faGVpZ2h0Ijo4NDQsIiRvcyI6ImlPUyIsIiRvc192ZXJzaW9uIjoiaU9TIiwiJG1hbnVmYWN0dXJlciI6IklQSE9ORSIsIiRhcHBfaWQiOiIyMDIxMDAzMTA5NjgwOTc5IiwiJHRpbWV6b25lX29mZnNldCI6LTQ4MCwicGxhdGZvcm1fdHlwZSI6IkFMSVBBWSIsImNoYW5uZWwiOiIiLCJjaGFubmVsX3R5cGUiOiLmuKDpgZMiLCIkbGF0ZXN0X3NjZW5lIjoiYWxpLTAwMDAiLCIkdXJsX3BhdGgiOiJwYWdlcy9ob21lL2hvbWUiLCIkdXJsX3F1ZXJ5IjoiIiwiJGlzX2ZpcnN0X2RheSI6dHJ1ZX0sInR5cGUiOiJ0cmFjayIsImV2ZW50IjoiJE1QVmlld1NjcmVlbiIsIl90cmFja19pZCI6MjA4MzYzNzY2LCJ0aW1lIjoxNjUwMjc2MjkzNzY2LCJfZmx1c2hfdGltZSI6MTY1MDI3NjQ2Njk1Mn0seyJkaXN0aW5jdF9pZCI6IjE2NTAyNzE5MTUxODUtNzE2NDQwLTAxNDQxNDkxZmE3MzQ5LTQ2MTU4MzE2IiwibGliIjp7IiRsaWIiOiJBbGlwYXlNaW5pIiwiJGxpYl9tZXRob2QiOiJjb2RlIiwiJGxpYl92ZXJzaW9uIjoiMS4yLjAifSwicHJvcGVydGllcyI6eyIkbGliIjoiQWxpcGF5TWluaSIsIiRsaWJfdmVyc2lvbiI6IjEuMi4wIiwiJG5ldHdvcmtfdHlwZSI6IldJRkkiLCIkbW9kZWwiOiJpcGhvbmUxNCw1IiwiJHNjcmVlbl93aWR0aCI6MzkwLCIkc2NyZWVuX2hlaWdodCI6ODQ0LCIkb3MiOiJpT1MiLCIkb3NfdmVyc2lvbiI6ImlPUyIsIiRtYW51ZmFjdHVyZXIiOiJJUEhPTkUiLCIkYXBwX2lkIjoiMjAyMTAwMzEwOTY4MDk3OSIsIiR0aW1lem9uZV9vZmZzZXQiOi00ODAsInBsYXRmb3JtX3R5cGUiOiJBTElQQVkiLCJjaGFubmVsIjoiMjE2MyIsImNoYW5uZWxfdHlwZSI6Iua4oOmBkyIsIiRsYXRlc3Rfc2NlbmUiOiJhbGktMDAwMCIsImNvbW1vZGl0eV9pZCI6IjY1NzY5MTg2NjQwOCIsIiRpc19maXJzdF9kYXkiOnRydWV9LCJ0eXBlIjoidHJhY2siLCJldmVudCI6IkNvbW1vZGl0eUV4cG9zdXJlIiwiX3RyYWNrX2lkIjo3NDY0NDMyMiwidGltZSI6MTY1MDI3NjI5NDMyMiwiX2ZsdXNoX3RpbWUiOjE2NTAyNzY0NjY5NTJ9LHsiZGlzdGluY3RfaWQiOiIxNjUwMjcxOTE1MTg1LTcxNjQ0MC0wMTQ0MTQ5MWZhNzM0OS00NjE1ODMxNiIsImxpYiI6eyIkbGliIjoiQWxpcGF5TWluaSIsIiRsaWJfbWV0aG9kIjoiY29kZSIsIiRsaWJfdmVyc2lvbiI6IjEuMi4wIn0sInByb3BlcnRpZXMiOnsiJGxpYiI6IkFsaXBheU1pbmkiLCIkbGliX3ZlcnNpb24iOiIxLjIuMCIsIiRuZXR3b3JrX3R5cGUiOiJXSUZJIiwiJG1vZGVsIjoiaXBob25lMTQsNSIsIiRzY3JlZW5fd2lkdGgiOjM5MCwiJHNjcmVlbl9oZWlnaHQiOjg0NCwiJG9zIjoiaU9TIiwiJG9zX3ZlcnNpb24iOiJpT1MiLCIkbWFudWZhY3R1cmVyIjoiSVBIT05FIiwiJGFwcF9pZCI6IjIwMjEwMDMxMDk2ODA5NzkiLCIkdGltZXpvbmVfb2Zmc2V0IjotNDgwLCJwbGF0Zm9ybV90eXBlIjoiQUxJUEFZIiwiY2hhbm5lbCI6IjIxNjMiLCJjaGFubmVsX3R5cGUiOiLmuKDpgZMiLCIkbGF0ZXN0X3NjZW5lIjoiYWxpLTAwMDAiLCJjb21tb2RpdHlfaWQiOiI1OTM0NjE4MzM0NTgiLCIkaXNfZmlyc3RfZGF5Ijp0cnVlfSwidHlwZSI6InRyYWNrIiwiZXZlbnQiOiJDb21tb2RpdHlFeHBvc3VyZSIsIl90cmFja19pZCI6MTgxMDg0MzI0LCJ0aW1lIjoxNjUwMjc2Mjk0MzI0LCJfZmx1c2hfdGltZSI6MTY1MDI3NjQ2Njk1Mn0seyJkaXN0aW5jdF9pZCI6IjE2NTAyNzE5MTUxODUtNzE2NDQwLTAxNDQxNDkxZmE3MzQ5LTQ2MTU4MzE2IiwibGliIjp7IiRsaWIiOiJBbGlwYXlNaW5pIiwiJGxpYl9tZXRob2QiOiJjb2RlIiwiJGxpYl92ZXJzaW9uIjoiMS4yLjAifSwicHJvcGVydGllcyI6eyIkbGliIjoiQWxpcGF5TWluaSIsIiRsaWJfdmVyc2lvbiI6IjEuMi4wIiwiJG5ldHdvcmtfdHlwZSI6IldJRkkiLCIkbW9kZWwiOiJpcGhvbmUxNCw1IiwiJHNjcmVlbl93aWR0aCI6MzkwLCIkc2NyZWVuX2hlaWdodCI6ODQ0LCIkb3MiOiJpT1MiLCIkb3NfdmVyc2lvbiI6ImlPUyIsIiRtYW51ZmFjdHVyZXIiOiJJUEhPTkUiLCIkYXBwX2lkIjoiMjAyMTAwMzEwOTY4MDk3OSIsIiR0aW1lem9uZV9vZmZzZXQiOi00ODAsInBsYXRmb3JtX3R5cGUiOiJBTElQQVkiLCJjaGFubmVsIjoiMjE2MyIsImNoYW5uZWxfdHlwZSI6Iua4oOmBkyIsIiRsYXRlc3Rfc2NlbmUiOiJhbGktMDAwMCIsImNvbW1vZGl0eV9pZCI6IjY0Nzc0ODU5NTg4MiIsIiRpc19maXJzdF9kYXkiOnRydWV9LCJ0eXBlIjoidHJhY2siLCJldmVudCI6IkNvbW1vZGl0eUV4cG9zdXJlIiwiX3RyYWNrX2lkIjo4MTgwMTQzNDMsInRpbWUiOjE2NTAyNzYyOTQzNDMsIl9mbHVzaF90aW1lIjoxNjUwMjc2NDY2OTUyfSx7ImRpc3RpbmN0X2lkIjoiMTY1MDI3MTkxNTE4NS03MTY0NDAtMDE0NDE0OTFmYTczNDktNDYxNTgzMTYiLCJsaWIiOnsiJGxpYiI6IkFsaXBheU1pbmkiLCIkbGliX21ldGhvZCI6ImNvZGUiLCIkbGliX3ZlcnNpb24iOiIxLjIuMCJ9LCJwcm9wZXJ0aWVzIjp7IiRsaWIiOiJBbGlwYXlNaW5pIiwiJGxpYl92ZXJzaW9uIjoiMS4yLjAiLCIkbmV0d29ya190eXBlIjoiV0lGSSIsIiRtb2RlbCI6ImlwaG9uZTE0LDUiLCIkc2NyZWVuX3dpZHRoIjozOTAsIiRzY3JlZW5faGVpZ2h0Ijo4NDQsIiRvcyI6ImlPUyIsIiRvc192ZXJzaW9uIjoiaU9TIiwiJG1hbnVmYWN0dXJlciI6IklQSE9ORSIsIiRhcHBfaWQiOiIyMDIxMDAzMTA5NjgwOTc5IiwiJHRpbWV6b25lX29mZnNldCI6LTQ4MCwicGxhdGZvcm1fdHlwZSI6IkFMSVBBWSIsImNoYW5uZWwiOiIyMTYzIiwiY2hhbm5lbF90eXBlIjoi5rig6YGTIiwiJGxhdGVzdF9zY2VuZSI6ImFsaS0wMDAwIiwiY29tbW9kaXR5X2lkIjoiNjU2MDQwODEyNjQ0IiwiJGlzX2ZpcnN0X2RheSI6dHJ1ZX0sInR5cGUiOiJ0cmFjayIsImV2ZW50IjoiQ29tbW9kaXR5RXhwb3N1cmUiLCJfdHJhY2tfaWQiOjMwODgyNDM0NiwidGltZSI6MTY1MDI3NjI5NDM0NiwiX2ZsdXNoX3RpbWUiOjE2NTAyNzY0NjY5NTJ9XQ%3D%3D\n";
        String method = "";
        try {
            if (eventBody.contains(Constant.DEFAULT_REQUEST_POST_URL) || eventBody.contains(Constant.DEFAULT_REQUEST_SA_POST_URL)){
                method = Constant.METHOD_POST;
            }else if (eventBody.contains(Constant.DEFAULT_REQUEST_GET_URL) || eventBody.contains(Constant.DEFAULT_REQUEST_SA_GET_URL)){
                method = Constant.METHOD_GET;
            }
            if (eventBody.length() == 0){
                System.out.println("不是采集url地址=========");
            }

            // 兼容开源SDK上行报文，通过分隔符分割数据
            String[] split = eventBody.split(Constant.DEFAULT_INDEXS_SEPARATOR);
            String preData = "";
            String project = "";
            boolean isData = false;
            boolean isGzip = false;
            boolean isIos = false;
            if (eventBody.contains("ios") || eventBody.contains("iOS") || eventBody.contains("IOS")){
                isIos = true;
            }
            for (int i = 0; i < split.length; i++) {
                //如果是GET方式，参数是在request节点中 url结尾中有"  HTTP/1.1"，
                if(Constant.METHOD_GET.equals(method)){
                    if(split[i].contains(Constant.PARAM_PROJECT_DATA)){
                        project = split[i].split(Constant.PARAM_PROJECT_DATA)[1];
                        project = project.split(Constant.DEFAULT_FIELD_SEPARATOR)[0];
                    }
                    if (split[i].contains(Constant.PARAM_DATA)  || split[i].contains(Constant.PARAM_DATA_LIST)){
                        String regexStr = split[i].contains(Constant.PARAM_DATA)?Constant.PARAM_DATA:Constant.PARAM_DATA_LIST;
                        preData = split[i].split(regexStr)[1];
                        //是否zgip压缩
                        if (preData.contains(Constant.PARAM_GZIP)){
                            isGzip = true;
                        }
                        preData = preData.split(Constant.DEFAULT_FIELD_SEPARATOR)[0];
                        isData = true;
                        break;
                    }
                }
                //POST方式，参数在dm节点中
                if (Constant.METHOD_POST.equals(method)){
                    if(split[i].contains(Constant.PARAM_PROJECT_DATA)){
                        project = split[i].split(Constant.PARAM_PROJECT_DATA)[1];
                        project = project.split(Constant.DEFAULT_FIELD_SEPARATOR)[0];
                    }
                    if (split[i].contains(Constant.PARAM_DATA)  || split[i].contains(Constant.PARAM_DATA_LIST)){
                        //是否zgip压缩
                        if (split[i].contains(Constant.PARAM_GZIP)){
                            isGzip = true;
                        }
                        String regexStr = split[i].contains(Constant.PARAM_DATA)?Constant.PARAM_DATA:Constant.PARAM_DATA_LIST;
                        preData = split[i].split(regexStr)[1];
                        if (preData.contains("&"+Constant.PARAM_GZIP)){
                            String regexGip = "&"+Constant.PARAM_GZIP;
                            String tempData = preData;
                            preData = tempData.split(regexGip)[0];
                        }
                        isData = true;
                        break;
                    }
                }
            }
            if (isIos){
                preData = java.net.URLDecoder.decode(preData, "utf-8");
                if (preData.contains("%")){
                    preData = java.net.URLDecoder.decode(preData, "utf-8");
                }
            }else {
                preData = java.net.URLDecoder.decode(preData, "utf-8");
            }
            if (isGzip){
                data = GZipUtil.uncompress(preData);
            }else {
                data=new String(decoder.decodeBuffer(preData), "UTF-8");
            }
            if(StringUtils.isNotBlank(project)){
                JsonObject returnData = new JsonParser().parse(data).getAsJsonObject();
                returnData.addProperty("project",project);
                data = returnData.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
