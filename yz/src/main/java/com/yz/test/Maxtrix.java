package com.yz.test;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Maxtrix {

    String[] strs = new String[]{"A","B","C","D","E","F"};

    public Integer[][] getMaxtrix(String[] strs) {
        Map<Integer,Integer> indexTags = new HashMap();
        TwoKeyMap twoKeyMap = getTwoKeyMap();
        Integer[][] maxtrix = new Integer[strs.length][strs.length];
        for(int i=0;i<strs.length;i++) {
            for(int j=0;j<strs.length;j++){
                if(i==j){
                    maxtrix[i][j] = 0;
                    continue;
                }

                Integer value = twoKeyMap.getValue(strs[i],strs[j]);
                maxtrix[i][j] = value;
                if(i==0){
                    if(maxtrix[i][j] == null){
                        indexTags.put(j,1);
                        maxtrix[i][j] = 0;
                    }
                }else{
                    if(indexTags != null && indexTags.size()>0 && indexTags.get(i) != null && indexTags.get(i)==1){
                        maxtrix[i][j] = 0;
                    }

                    if(maxtrix[i][j] == null){
                        maxtrix[i][j] = 0;
                    }
                }
            }
        }
        return maxtrix;
    }

    public TwoKeyMap getTwoKeyMap(){
        TwoKeyMap twoKeyMap = new TwoKeyMap();
        twoKeyMap.put(strs[0],strs[1],1);
        twoKeyMap.put(strs[0],strs[2],1);
        twoKeyMap.put(strs[0],strs[3],1);
        twoKeyMap.put(strs[0],strs[4],1);
        twoKeyMap.put(strs[0],strs[5],1);
        twoKeyMap.put(strs[1],strs[2],1);
        twoKeyMap.put(strs[1],strs[3],1);
        twoKeyMap.put(strs[2],strs[4],1);
        twoKeyMap.put(strs[2],strs[5],1);
        return twoKeyMap;
    }

    public void startSearch(String element,Integer[][] orderMaxtrixList,String[]  strs ,Integer from,
                            Map<String,LinkedList<String>> map,Map<String,LinkedList<String>> resultMap,
                            Map<String,LinkedList<String>> resultMap2,Integer count) {
        int i= from;
        if( map.get(element) == null){
            map.put(element,new LinkedList<>());
        }
        count++;
        for(int j=i+1;j<strs.length;j++){
            if(orderMaxtrixList[i][j] != null && orderMaxtrixList[i][j] != 0){
                map.get(element).add(strs[j]);
                System.out.println("1"+JSONObject.toJSONString(map));
                from = j;
                resultMap.put(element+","+count+","+j,map.get(element));
                System.out.println("2"+JSONObject.toJSONString(resultMap));
                startSearch(element,orderMaxtrixList,strs,from,map,resultMap,resultMap2,count);
            }
        }
        LinkedList<String> list2 =  new LinkedList<>();
        for(String str : map.get(element)){
            if(!str.equals(strs[i])) {
                list2.add(str);
            }
        }

        map.put(element,list2);
        System.out.println("3"+JSONObject.toJSONString(map));
    }

    public static void main(String[] args){
        Maxtrix maxtrix = new Maxtrix();
        Integer[][] maxtrixs = maxtrix.getMaxtrix(maxtrix.strs);

        for(int i=0;i<maxtrix.strs.length;i++){
            System.out.println(JSONObject.toJSONString(maxtrixs[i]));
        }
        Map<String,LinkedList<String>> map = new HashMap();
        Map<String,LinkedList<String>> resultMap = new HashMap();
        Map<String,LinkedList<String>> resultMap2 = new HashMap();
        Integer count = 0;
        maxtrix.startSearch(maxtrix.strs[0],maxtrixs,maxtrix.strs ,0,map,resultMap,resultMap2,count);
        System.out.println(JSONObject.toJSONString(resultMap));
    }
}
