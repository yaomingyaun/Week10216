package com.bw.ymy.zy1.presenter;

import java.util.Map;

public interface IPresenter {
    void  getpost(String url, Map<String, String> map, Class clazz);
    void  get(String url,  Class clazz);
}
