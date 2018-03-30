package cn.yoogr.lazy.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.yoogr.lazy.R;
import cn.yoogr.lazy.adapter.GankAdapter;
import cn.yoogr.lazy.data.BaseResponse;
import cn.yoogr.lazy.data.GankData;
import cn.yoogr.lazy.net.ApiService;
import cn.yoogr.lazy.net.RetrofitFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BFragment extends BaseFragment {
    private static final String TAG = "BFragment";
    private RecyclerView mRecyclerView;
    private GankAdapter mAdapter;
    private SwipeRefreshLayout mRefreshLayout;
    private List<GankData> mList = new ArrayList<>();

    @Override
    protected void initView() {
        mRecyclerView = $(R.id.recycler_view);
        mRefreshLayout=$(R.id.refresh_layout);
        mRefreshLayout.setRefreshing(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new GankAdapter(getActivity(), mList);
    }

    @Override
    public void fetchData() {
        RetrofitFactory.getInstance().createRetrofitClient(ApiService.class).getWelfare(1, 10).enqueue(new Callback<BaseResponse<List<GankData>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<GankData>>> call, Response<BaseResponse<List<GankData>>> response) {
                Log.d(TAG, "onResponse: " + response.body().getResults());
                List<GankData> results = response.body().getResults();
                if (results!=null){
                    mList.addAll(results);
                    mRecyclerView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                }
                mRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<BaseResponse<List<GankData>>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                mRefreshLayout.setRefreshing(false);

            }
        });
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_b;
    }
}
