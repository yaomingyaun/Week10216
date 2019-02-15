package com.bw.ymy.zy1.refit;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Retfit {

    private static  final String BASEURL="http://172.17.8.100/small/";
    private OkHttpClient httpClient;
    private static Retfit instance;

    public  static  synchronized  Retfit getInstance()
    {
        if(instance==null)
        {
            instance=new Retfit();
        }
        return  instance;
    }
    private BaseApi baseApi;

    public  Retfit()
    {
        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

       OkHttpClient.Builder builder=new OkHttpClient.Builder();
       builder.connectTimeout(15,TimeUnit.SECONDS);
       builder.readTimeout(15,TimeUnit.SECONDS);
       builder.addInterceptor(httpLoggingInterceptor);
       builder.writeTimeout(15,TimeUnit.SECONDS);
       httpClient=builder.build();


        Retrofit retrofit=new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASEURL)
                .client(httpClient)
                .build();
        baseApi=retrofit.create(BaseApi.class);
    }

    //post
    public Retfit post(String url, Map<String,String> map, HttpListener listener)
    {
        if(map==null)
        {
            map=new HashMap<>();
        }
        baseApi.post(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getobserver(listener));

      return  instance;
    }

    //get
public  void  get(String url,HttpListener listener)
{
    baseApi.get(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getobserver(listener));
}

    public Observer getobserver(final HttpListener listener)
    {
        Observer Observer= new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                if(listener!=null)
                {
                    listener.onFail(e.getMessage());
                }

            }

            @Override
            public void onNext(ResponseBody responseBody) {

                try {
                    String string=responseBody.string();
                    if(listener!=null)
                    {
                        listener.onsuccess(string);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    if(listener!=null)
                    {
                        listener.onFail(e.getMessage());
                    }
                }

            }
        };
        return  Observer;
    }


    public  HttpListener listener;

    public  void  setHttpli(HttpListener httpli)
    {
        this.listener=httpli;
    }
    public  interface  HttpListener {
        void  onsuccess(String data);
        void  onFail(String error);
    }
}
