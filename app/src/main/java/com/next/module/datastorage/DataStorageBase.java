package com.next.module.datastorage;

import android.text.TextUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * ClassName:数据存储基类
 *
 * @author Afton
 * @time 2023/7/21
 * @auditor
 */
abstract public class DataStorageBase {

    //存储标识
    private String storageFlag;

    public DataStorageBase(String storageFlag) {
        this.storageFlag = storageFlag;
    }

    /**
     * 保存数据
     */
    public void save() {
        if (TextUtils.isEmpty(this.storageFlag)) {
            return;
        }

        //获取所有注解属性
        ArrayList<Field> fieldAnnotationList = this.getAllAnnotationFields();
        if (fieldAnnotationList.isEmpty()) {
            return;
        }

        //设置标记
        DataStorageTool.getInstance().setFlag(this.storageFlag);

        for (Field field : fieldAnnotationList) {
            Data data = field.getAnnotation(Data.class);
            //获取属性值
            Object obj = this.getFieldValue(field);

            if (obj != null) {
                DataStorageTool.getInstance().put(data.key(), JsonTool.getInstance().toJson(obj));
            }
        }

        //保存
        DataStorageTool.getInstance().save();
    }

    /**
     * 创建数据
     */
    protected void creatData() {
        DataStorageTool.getInstance().setFlag(this.storageFlag);

        //获取所有注解属性
        ArrayList<Field> fieldAnnotationList = this.getAllAnnotationFields();
        if (fieldAnnotationList.isEmpty()) {
            return;
        }

        for (Field field : fieldAnnotationList) {
            Data data = field.getAnnotation(Data.class);
            String key = data.key();
            String json = DataStorageTool.getInstance().get(key, "");

            //如果json为空，并且属性值不为空，则跳过
            if (TextUtils.isEmpty(json) && this.getFieldValue(field) != null) {
                continue;
            }

            Object object = JsonTool.getInstance().toObject(json, field.getType());

            //设置属性值
            this.setFieldValue(field, object);
        }
    }

    /**
     * 获取所有注解属性列表
     *
     * @return 注解属性列表
     */
    private ArrayList<Field> getAllAnnotationFields() {
        Class c = this.getClass();
        ArrayList<Field> fieldAnnotationList = new ArrayList<>();
        for (Field field : c.getDeclaredFields()) {
            boolean isAnnotationPresent = field.isAnnotationPresent(Data.class);
            if (isAnnotationPresent) {
                fieldAnnotationList.add(field);
            }
        }

        return fieldAnnotationList;
    }

    /**
     * 获取属性值
     *
     * @param field 属性
     * @return 属性值
     */
    private Object getFieldValue(Field field) {
        field.setAccessible(true);

        try {
            return field.get(this);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 设置属性值
     *
     * @param field 属性
     * @param value 属性值
     */
    private void setFieldValue(Field field, Object value) {
        field.setAccessible(true);

        try {
            field.set(this, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取存储标识
     *
     * @return 存储标识
     */
    public String getStorageFlag() {
        return this.storageFlag;
    }
}