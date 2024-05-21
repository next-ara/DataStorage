package com.next.module.datastorage;

import android.app.Application;
import android.text.TextUtils;

import java.util.HashMap;

/**
 * ClassName:数据存储工厂类
 *
 * @author Afton
 * @time 2023/7/21
 * @auditor
 */
public class StorageFactory {

    private static StorageFactory storageFactory;

    //数据存储对象队列
    private HashMap<String, DataStorageBase> dataStorageMap = new HashMap();

    //应用对象
    private Application application;

    public static StorageFactory getInstance() {
        if (storageFactory == null) {
            storageFactory = new StorageFactory();
        }

        return storageFactory;
    }

    /**
     * 初始化
     *
     * @param application 应用对象
     * @return 数据存储工厂对象
     */
    public StorageFactory init(Application application) {
        this.application = application;
        return this;
    }

    /**
     * 获取数据对象
     *
     * @param flag 存储标识
     * @param <T>
     * @return 存储数据对象
     */
    public <T extends DataStorageBase> T getDataObj(String flag) {
        if (!TextUtils.isEmpty(flag)) {
            if (this.dataStorageMap.containsKey(flag)) {
                return (T) this.dataStorageMap.get(flag);
            }
        }

        return null;
    }

    /**
     * 注册
     *
     * @param dataStorageBase 存储对象
     * @return 数据存储工厂对象
     */
    public StorageFactory register(DataStorageBase dataStorageBase) {
        //创建数据
        dataStorageBase.creatData();
        this.dataStorageMap.put(dataStorageBase.getStorageFlag(), dataStorageBase);
        return this;
    }

    public Application getApplication() {
        return application;
    }
}