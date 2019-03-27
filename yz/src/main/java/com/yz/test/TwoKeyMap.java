package com.yz.test;

import java.util.HashMap;
import java.util.Map;

public class TwoKeyMap {
    private Map<String, Map<String,Integer>> twoKeyMap = null;
    public TwoKeyMap(){
        twoKeyMap = new HashMap();
    }

    public void put(String key1,String key2,Integer value){
        if(twoKeyMap.get(key1) == null){
            Map<String,Integer> twoKeyMapValue = new HashMap<>();
            twoKeyMap.put(key1,twoKeyMapValue);
        }

        twoKeyMap.get(key1).put(key2,value);
    }

    public Integer getValue(String key1,String key2){
        if(twoKeyMap.get(key1) == null){
            return null;
        }

        return twoKeyMap.get(key1).get(key2);
    }
}
