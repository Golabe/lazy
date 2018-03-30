package cn.yoogr.lazy.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.AsyncPagedListDiffer;
import android.arch.paging.PagedList;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.yoogr.lazy.R;
import cn.yoogr.lazy.adapter.GankAdapter;
import cn.yoogr.lazy.adapter.GankPagedAdapter;
import cn.yoogr.lazy.data.BaseResponse;
import cn.yoogr.lazy.data.GankData;
import cn.yoogr.lazy.model.GankViewModel;
import cn.yoogr.lazy.net.ApiService;
import cn.yoogr.lazy.net.RetrofitFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "AFragment";
    private RecyclerView mRecyclerView;
    private GankAdapter mAdapter;
    private SwipeRefreshLayout mRefreshLayout;
    private List<GankData> mList = new ArrayList<>();
    private GankViewModel gankViewModel;
    private GankPagedAdapter gankPagedAdapter;

    @Override
    protected void initView() {
        mRecyclerView = $(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRefreshLayout = $(R.id.refresh_layout);
        mRefreshLayout.setOnRefreshListener(this);
        gankViewModel = ViewModelProviders.of(getActivity()).get(GankViewModel.class);


    }

    @Override
    public void fetchData() {

        gankPagedAdapter=new GankPagedAdapter(new DiffUtil.ItemCallback<GankData>() {
            @Override
            public boolean areItemsTheSame(GankData oldItem, GankData newItem) {
                return oldItem.get_id().equals(newItem.get_id());
            }

            @Override
            public boolean areContentsTheSame(GankData oldItem, GankData newItem) {
                return oldItem.getUrl().equals(newItem.getUrl());
            }
        });

        gankViewModel.mListLiveData.observe(getActivity(), new Observer<PagedList<GankData>>() {
            @Override
            public void onChanged(@Nullable PagedList<GankData> gankData) {
                gankPagedAdapter.submitList(gankData);
            }
        });
     mRecyclerView.setAdapter(gankPagedAdapter);
//        RetrofitFactory.getInstance().createRetrofitClient(ApiService.class).getWelfare(1L, 10).enqueue(new Callback<BaseResponse<List<GankData>>>() {
//            @Override
//            public void onResponse(Call<BaseResponse<List<GankData>>> call, Response<BaseResponse<List<GankData>>> response) {
//                Log.d(TAG, "onResponse: " + response.body().getResults());
//                List<GankData> results = response.body().getResults();
//                if (results!=null){
//                    mList.addAll(results);
//                    mRecyclerView.setAdapter(mAdapter);
//                    mAdapter.notifyDataSetChanged();
//                }
//                mRefreshLayout.setRefreshing(false);
//            }
//
//            @Override
//            public void onFailure(Call<BaseResponse<List<GankData>>> call, Throwable t) {
//                Log.d(TAG, "onFailure: " + t.getMessage());
//                mRefreshLayout.setRefreshing(false);
//            }
//        });


    }


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_a;
    }

    @Override
    public void onRefresh() {

    }
}
