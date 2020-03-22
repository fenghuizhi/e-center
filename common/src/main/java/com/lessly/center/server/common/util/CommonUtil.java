package com.lessly.center.server.common.util;

import org.apache.commons.lang.StringUtils;

import java.util.UUID;

/**
 * @Author:debug (SteadyJack)
 * @Date: 2019/12/2 22:23
 */
public class CommonUtil {

    public static String randomUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    /**
     * 分隔后拼接成用于sql查询的字符串- 'a','b','c'  "a,b,c"
     * @param param
     * @param separatorChars
     * @return
     */
    public static String concatStrToChar(String param, String separatorChars){
        StringBuilder sb=new StringBuilder();
        String[] arr= StringUtils.split(param,separatorChars);
        int i=0;
        for (;i<arr.length;i++){
            if (arr.length-1 != i){
                sb.append("'").append(arr[i]).append("'").append(",");
            }else{
                sb.append("'").append(arr[i]).append("'");
            }
        }
        return sb.toString();
    }

    /**
     * 分隔后拼接成用于sql查询的字符串- a,b,c
     * @param param
     * @param separatorChars
     * @return
     */
    public static String concatStrToInt(String param, String separatorChars){
        StringBuilder sb=new StringBuilder();
        String[] arr= StringUtils.split(param,separatorChars);
        int i=0;
        for (;i<arr.length;i++){
            if (arr.length-1 != i){
                sb.append(arr[i]).append(",");
            }else{
                sb.append(arr[i]);
            }
        }
        return sb.toString();
    }

//    public static void main(String[] args) {
//        Double[] arr=new Double[]{12.2,2.4,1.5,8.8,9.0,10.9,20.5};
//        List<Double> list=Lists.newArrayList(arr);
//        //Collections.sort(list);
//        list.sort(Comparator.reverseOrder());
//
//        System.out.println(list);
//    }
}






















