package com.bw.ymy.zy1.callback;

public interface MCallBack<T> {
    void  onsuccess(T data);
    void  onFail(String error);

}
