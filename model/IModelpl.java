package com.bw.ymy.zy1.model;


import com.bw.ymy.zy1.callback.MCallBack;
import com.bw.ymy.zy1.refit.Retfit;
import com.google.gson.Gson;

import java.util.Map;

public class IModelpl implements IModel {
    @Override
    public void getpost(String url, Map<String, String> map, final Class clazz, final MCallBack mCallBack) {

        Retfit.getInstance().post(url, map, new Retfit.HttpListener() {
            @Override
            public void onsuccess(String data) {
               try {
                   Object o=new Gson().fromJson(data,clazz);
                   if(mCallBack!=null)
                   {
                       mCallBack.onsuccess(o);
                   }

               }catch (Exception e)
               {
                   e.printStackTrace();
                   if(mCallBack!=null)
                   {
                       mCallBack.onFail(e.getMessage());
                   }
               }
            }

            @Override
            public void onFail(String error) {
                if(mCallBack!=null)
                {
                    mCallBack.onFail(error);
                }

            }
        });

    }

    @Override
    public void get(String url, final Class clazz, final MCallBack mCallBack) {
        Retfit.getInstance().get(url, new Retfit.HttpListener() {
            @Override
            public void onsuccess(String data) {
                Object o=new Gson().fromJson(data,clazz);
                if(mCallBack!=null)
                {
                    mCallBack.onsuccess(o);
                }
            }

            @Override
            public void onFail(String error) {
        if(mCallBack!=null)
        {
            mCallBack.onFail(error);
        }
            }
        });
    }


}
