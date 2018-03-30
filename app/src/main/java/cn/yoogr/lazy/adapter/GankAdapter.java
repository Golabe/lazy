package cn.yoogr.lazy.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.yoogr.lazy.R;
import cn.yoogr.lazy.data.GankData;

public class GankAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<GankData> mList;
    private Context mContext;

    public GankAdapter(Context context, List<GankData> mList) {
        this.mContext = context;
        this.mList = mList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GankViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_gank, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        GankViewHolder itemView = (GankViewHolder) holder;
        itemView.tv.setText(mList.get(position).getCreatedAt());
        Glide.with(mContext).load(mList.get(position).getUrl()).into(itemView.iv);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class GankViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv;
        private TextView tv;

        public GankViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv = itemView.findViewById(R.id.tv);

        }
    }
}
