package cn.yoogr.lazy.data;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

public class GankDataSourceFactory extends DataSource.Factory<Long, GankData> {
    private MutableLiveData<GankDataSource> mutableLiveData;
    @Override
    public DataSource<Long, GankData> create() {
        mutableLiveData = new MutableLiveData<>();
        GankDataSource gankDataSource = new GankDataSource();
        mutableLiveData.postValue(gankDataSource);
        return gankDataSource;
    }
}
