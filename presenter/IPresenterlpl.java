package com.bw.ymy.zy1.presenter;



import com.bw.ymy.zy1.callback.MCallBack;
import com.bw.ymy.zy1.model.IModelpl;
import com.bw.ymy.zy1.view.IView;

import java.util.Map;

public class IPresenterlpl implements IPresenter {

    private IView iView;
    private IModelpl iModelpl;

    public IPresenterlpl(IView iView) {
        this.iView = iView;
        iModelpl=new IModelpl();
    }

    @Override
    public void getpost(String url, Map<String, String> map, Class clazz) {

        iModelpl.getpost(url, map, clazz, new MCallBack() {
            @Override
            public void onsuccess(Object data) {
                iView.onSuccess(data);
            }

            @Override
            public void onFail(String error) {
            iView.onSuccess(error);
            }
        });

    }

    @Override
    public void get(String url, Class clazz) {
        iModelpl.get(url, clazz, new MCallBack() {
            @Override
            public void onsuccess(Object data) {
                iView.onSuccess(data);
            }

            @Override
            public void onFail(String error) {
                iView.onSuccess(error);
            }
        });
    }


}
