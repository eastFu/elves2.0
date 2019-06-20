package cn.gyyx.elves.console.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class MapUtil {

    public static Map<String,Object> listToMapByKey(List<Map<String,Object>> list,String key)throws Exception{
        if(list == null || StringUtils.isEmpty( key )){
            throw new NullPointerException(  );
        }

        Map<String,Object> resultMap = new HashMap<>(  );


        Iterator<Map<String,Object>> it = list.iterator();
        while (it.hasNext()){
            Map<String,Object> mapFor  = it.next();
            String objKey = mapFor.get( key ) != null && StringUtils.isNotEmpty(mapFor.get( key ).toString()) ? mapFor.get( key ).toString() : "";
            resultMap.put(objKey,mapFor);
        }

        return resultMap;

    }


    public static List<Map<String,Object>> mapToList(Map<String,Object> map){
        List<Map<String,Object>> list = new LinkedList<>();

        for(String key : map.keySet()){
            Map<String,Object> mapObj = (Map<String, Object>) map.get(key);
            list.add( mapObj );
        }

        return list;
    }

}
