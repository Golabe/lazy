package cn.yoogr.lazy;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListProvider;
import android.arch.paging.PagedList;
import android.arch.paging.PositionalDataSource;
import android.arch.paging.TiledDataSource;
import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.yoogr.lazy.data.BaseResponse;
import cn.yoogr.lazy.data.GankData;
import cn.yoogr.lazy.net.ApiService;
import cn.yoogr.lazy.net.RetrofitFactory;
import retrofit2.Response;

public class MainData extends AndroidViewModel {

    private static final int NEED_NUMBER = 10;
    private static final int PAGE_FIRST = 1;

    private int mPage = PAGE_FIRST;
    private LiveData<PagedList<GankData>> mDataLiveData;

    public MainData(@NonNull Application application) {
        super(application);
    }

    public LiveData<PagedList<GankData>> getDataLiveData() {

        initPageList();
        return mDataLiveData;
    }

    private void initPageList() {
        PositionalDataSource<GankData> positionalDataSource = new PositionalDataSource<GankData>() {
            @Override
            public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<GankData> callback) {
            }

            @Override
            public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<GankData> callback) {


            }
        };
    }


}
