package cn.yoogr.lazy.model;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import cn.yoogr.lazy.data.GankData;
import cn.yoogr.lazy.data.GankDataSourceFactory;


public class GankViewModel extends ViewModel {
    public LiveData<PagedList<GankData>> mListLiveData;
    private static final int PAGE = 5;
    private GankDataSourceFactory mGankDataSourceFactory;


    public GankViewModel() {
        mGankDataSourceFactory = new GankDataSourceFactory();
        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(PAGE)
                .setInitialLoadSizeHint(PAGE)
                .setEnablePlaceholders(false)
                .build();

        mListLiveData = new LivePagedListBuilder<>(mGankDataSourceFactory, config).build();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}

