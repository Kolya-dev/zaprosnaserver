/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.postzapros;


import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author krivosheevnikolaj
 */
public class Postzapros {

    public static void main(String[] args) {
        System.out.println("Start program!");
        String server = "https://android-for-students.ru";
        String serverPath = "/coursework/login.php";
        HashMap<String, String> map = new HashMap();
        map.put("lgn", "Student86758");
        map.put("pwd", "L+SvUJC");
        map.put("g", "RIBO-04-21");
        HTTPRunnable httpRunnable = new HTTPRunnable(server + serverPath, map);
        Thread th = new Thread(httpRunnable);
        th.start();
        try{
            th.join();
        }catch(InterruptedException ex){
            
        }finally{
            JSONObject jSONObject = new JSONObject(httpRunnable.getResponseBody());
            int result = jSONObject.getInt("result_code");
            System.out.println("Result: " + result);
            System.out.println("Variant: " + jSONObject.getInt("variant"));
            System.out.println("Title: " + jSONObject.getString("title"));
            System.out.println("Task: " + jSONObject.getString("task"));
            switch(result){
                case 1:
                    JSONArray jSONArray = jSONObject.getJSONArray("data");
                    System.out.println("Data:");
                    for(int i = 0; i < jSONArray.length();i++){
                        System.out.println((i + 1) + ")" + jSONArray.get(i));
                    }
                    break;
                case 0:
                    break;
                default:
                    break;
            }
        }
    }
}
