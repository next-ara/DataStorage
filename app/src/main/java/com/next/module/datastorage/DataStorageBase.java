package com.next.module.datastorage;

import android.text.TextUtils;

/**
 * ClassName:数据存储基类
 *
 * @author Afton
 * @time 2023/7/21
 * @auditor
 */
abstract public class DataStorageBase {

    //存储文件名
    private static String storageFileName = "obj_json";

    //存储标识
    private String storageFlag = "";

    /**
     * 保存数据
     */
    public void save() {
        if (TextUtils.isEmpty(this.storageFlag)) {
            return;
        }

        String json = JsonTool.getInstance().toJson(this);

        DataStorageTool.getInstance()
                .setFlag(this.storageFileName)
                .put(this.storageFlag, json)
                .save();
    }

    /**
     * 创建对象
     *
     * @param storageFlag 存储标识
     * @param c           类对象
     * @return 数据存储对象
     */
    public static DataStorageBase creatObj(String storageFlag, Class c) {
        String json = DataStorageTool.getInstance()
                .setFlag(storageFileName)
                .get(storageFlag, "{}");

        DataStorageBase dataStorageBase = (DataStorageBase) JsonTool.getInstance().toObject(json, c);
        dataStorageBase.setStorageFlag(storageFlag);
        return dataStorageBase;
    }

    public String getStorageFileName() {
        return storageFileName;
    }

    public void setStorageFileName(String storageFileName) {
        this.storageFileName = storageFileName;
    }

    public String getStorageFlag() {
        return storageFlag;
    }

    public void setStorageFlag(String storageFlag) {
        this.storageFlag = storageFlag;
    }
}