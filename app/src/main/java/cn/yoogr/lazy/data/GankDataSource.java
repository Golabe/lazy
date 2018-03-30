package cn.yoogr.lazy.data;

import android.arch.paging.ItemKeyedDataSource;
import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.List;

import cn.yoogr.lazy.net.ApiService;
import cn.yoogr.lazy.net.RetrofitFactory;
import retrofit2.Response;


public class GankDataSource extends ItemKeyedDataSource<Long, GankData> {
    private  int page = 1;

    public GankDataSource() {
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<GankData> callback) {

        try {
            Response<BaseResponse<List<GankData>>> response = RetrofitFactory
                    .getInstance()
                    .createRetrofitClient(ApiService.class)
                    .getWelfare(1, params.requestedLoadSize)
                    .execute();
            List<GankData> results = response.body().getResults();
            callback.onResult(results);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull LoadCallback<GankData> callback) {
        try {
            Response<BaseResponse<List<GankData>>> response = RetrofitFactory
                    .getInstance()
                    .createRetrofitClient(ApiService.class)
                    .getWelfare(++page, params.requestedLoadSize)
                    .execute();
            List<GankData> results = response.body().getResults();
            callback.onResult(results);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<GankData> callback) {

    }

    @NonNull
    @Override
    public Long getKey(@NonNull GankData item) {
        return null;
    }
}
