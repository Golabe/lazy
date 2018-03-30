package cn.yoogr.lazy.adapter;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import cn.yoogr.lazy.R;
import cn.yoogr.lazy.data.GankData;

public class GankPagedAdapter extends PagedListAdapter<GankData, RecyclerView.ViewHolder> {
    private Context mContext;

    public GankPagedAdapter(@NonNull DiffUtil.ItemCallback<GankData> diffCallback) {
        super(diffCallback);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        return new GankPagedViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_gank, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        GankPagedViewHolder itemView = (GankPagedViewHolder) holder;
        GankData item = getItem(position);
        itemView.tv.setText(item.getCreatedAt());
        Glide.with(mContext).load(item.getUrl()).into(itemView.iv);
    }


    class GankPagedViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;
        private ImageView iv;

        public GankPagedViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
            iv = itemView.findViewById(R.id.iv);
        }
    }



}
