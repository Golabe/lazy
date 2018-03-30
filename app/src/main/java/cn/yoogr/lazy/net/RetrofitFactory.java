package cn.yoogr.lazy.net;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    private static long DEFAULT_TIME_SECONDS = 10L;

    private static final class RetrofitHolder {
        private static final RetrofitFactory INSTANCE = new RetrofitFactory();
    }

    private RetrofitFactory() {
    }

    public static RetrofitFactory getInstance() {
        return RetrofitHolder.INSTANCE;
    }

    public <S> S createRetrofitClient(Class<S> sClass) {
       Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/api/")
                .client(createOkHttp())

                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(sClass);
    }

    private OkHttpClient createOkHttp() {


        return new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .readTimeout(DEFAULT_TIME_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIME_SECONDS, TimeUnit.SECONDS)
                .connectTimeout(DEFAULT_TIME_SECONDS, TimeUnit.SECONDS)
                .build();

    }
}
