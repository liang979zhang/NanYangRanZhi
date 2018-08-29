package com.zcf.nanyangranzhi.bean;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zcf.nanyangranzhi.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zhangxutong .
 * Date: 16/08/28
 */

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    private Context mContext;
    private List<CityBean> mDatas;
    private LayoutInflater mInflater;

    public CityAdapter(Context mContext, List<CityBean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_city, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final CityBean cityBean = mDatas.get(position);
        holder.tvCity.setText(cityBean.getCity());
        holder.tvPhone.setText(cityBean.getPhone());
        Glide.with(mContext).load(cityBean.getImg()).placeholder(R.mipmap.avatarsample165).into(holder.ivIcon);
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mContext, "pos:" + position, Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    public void refresh(ArrayList<CityBean> temp) {
        this.mDatas = temp;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_icon)
        CircleImageView ivIcon;
        @BindView(R.id.tvCity)
        TextView tvCity;
        @BindView(R.id.tv_phone)
        TextView tvPhone;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
