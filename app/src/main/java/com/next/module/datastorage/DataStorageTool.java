package com.next.module.datastorage;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.util.Set;

/**
 * ClassName:数据存储工具类
 *
 * @author Afton
 * @time 2023/7/21
 * @auditor
 */
public class DataStorageTool {

    private static DataStorageTool dataStorageTool;

    //SharedPreference对象
    private SharedPreferences sp;

    //编辑对象
    private SharedPreferences.Editor editor;

    public static DataStorageTool getInstance() {
        if (dataStorageTool == null) {
            dataStorageTool = new DataStorageTool();
        }

        return dataStorageTool;
    }

    /**
     * 添加数据
     *
     * @param key   键
     * @param value 值
     * @return 数据存储工具对象
     */
    public DataStorageTool put(String key, Object value) {
        if (this.sp != null) {
            if (value instanceof Integer) {
                this.getEdit().putInt(key, (Integer) value);
            } else if (value instanceof Float) {
                this.getEdit().putFloat(key, (Float) value);
            } else if (value instanceof Long) {
                this.getEdit().putLong(key, (Long) value);
            } else if (value instanceof Boolean) {
                this.getEdit().putBoolean(key, (Boolean) value);
            } else if (value instanceof Set) {
                this.getEdit().putStringSet(key, (Set<String>) value);
            } else if (value instanceof String) {
                this.getEdit().putString(key, (String) value);
            }
        }

        return this;
    }

    /**
     * 获取数据
     *
     * @param key      键
     * @param defValue 默认值
     * @return 值
     */
    public String get(String key, String defValue) {
        if (this.sp == null) {
            return defValue;
        }

        return this.sp.getString(key, defValue);
    }

    /**
     * 获取数据
     *
     * @param key      键
     * @param defValue 默认值
     * @return 值
     */
    public int get(String key, int defValue) {
        if (this.sp == null) {
            return defValue;
        }

        return this.sp.getInt(key, defValue);
    }

    /**
     * 获取数据
     *
     * @param key      键
     * @param defValue 默认值
     * @return 值
     */
    public float get(String key, float defValue) {
        if (this.sp == null) {
            return defValue;
        }

        return this.sp.getFloat(key, defValue);
    }

    /**
     * 获取数据
     *
     * @param key      键
     * @param defValue 默认值
     * @return 值
     */
    public long get(String key, long defValue) {
        if (this.sp == null) {
            return defValue;
        }

        return this.sp.getLong(key, defValue);
    }

    /**
     * 获取数据
     *
     * @param key      键
     * @param defValue 默认值
     * @return 值
     */
    public boolean get(String key, boolean defValue) {
        if (this.sp == null) {
            return defValue;
        }

        return this.sp.getBoolean(key, defValue);
    }

    /**
     * 获取数据
     *
     * @param key      键
     * @param defValue 默认值
     * @return 值
     */
    public Set<String> get(String key, Set<String> defValue) {
        if (this.sp == null) {
            return defValue;
        }

        return this.sp.getStringSet(key, defValue);
    }

    /**
     * 设置存储标识
     *
     * @param flag 存储标识
     * @return 数据存储工具对象
     */
    public DataStorageTool setFlag(String flag) {
        Context context = StorageFactory.getInstance().getApplication();
        if (context != null && !TextUtils.isEmpty(flag)) {
            this.sp = context.getSharedPreferences(flag, MODE_PRIVATE);
            this.editor = this.sp.edit();
        }

        return this;
    }

    /**
     * 保存数据
     */
    public void save() {
        if (this.sp == null) {
            return;
        }

        this.getEdit().apply();
        this.clearFlag();
    }

    /**
     * 清空存储标识
     */
    private void clearFlag() {
        this.sp = null;
    }

    /**
     * 获取写入编辑对象
     *
     * @return 编辑对象
     */
    private SharedPreferences.Editor getEdit() {
        if (this.sp == null) {
            return null;
        }

        return this.editor;
    }
}