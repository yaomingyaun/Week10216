package com.bw.ymy.zy1.model;



import com.bw.ymy.zy1.callback.MCallBack;

import java.util.Map;

public interface IModel {

    void  getpost(String url, Map<String, String> map, Class clazz, MCallBack mCallBack);
    void  get(String url,  Class clazz, MCallBack mCallBack);
}
