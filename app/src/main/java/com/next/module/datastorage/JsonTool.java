package com.next.module.datastorage;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * ClassName:Json工具类
 *
 * @author Afton
 * @time 2023/7/21
 * @auditor
 */
public class JsonTool {

    private static JsonTool jsonTool;

    //Gson对象
    private Gson gson = new Gson();

    public static JsonTool getInstance() {
        if (jsonTool == null) {
            jsonTool = new JsonTool();
        }

        return jsonTool;
    }

    /**
     * Json转对象
     *
     * @param json Json文本
     * @param type 类型
     * @return 对象
     */
    public Object toObject(String json, Type type) {
        return this.gson.fromJson(json, type);
    }

    /**
     * 对象转Json
     *
     * @param o 对象
     * @return Json
     */
    public String toJson(Object o) {
        return this.gson.toJson(o);
    }
}