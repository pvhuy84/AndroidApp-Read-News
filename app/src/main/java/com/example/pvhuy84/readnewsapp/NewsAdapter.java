package com.example.pvhuy84.readnewsapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by pvhuy84 on 1/7/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<News> listNews;
    private int idItemLayoutResource;
    private MyOnClick myOnClick;

    public NewsAdapter(Context context, ArrayList<News> listNews, int idItemLayoutResource) {
        this.context = context;
        this.listNews = listNews;
        this.idItemLayoutResource = idItemLayoutResource;
    }

    public void setMyOnClick(MyOnClick myOnClick) {
        this.myOnClick = myOnClick;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(idItemLayoutResource, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        News news = listNews.get(position);
        try {
            holder.imgNewsImage.setImageBitmap((new GetNewsImageTask()).execute(news.getUrlToImage()).get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        holder.tvNewsTitle.setText(news.getTitle());
        holder.tvDatePublish.setText(news.getPublishedAt());
//        holder.tvNewsDescription.setText(news.getDescription());
    }

    @Override
    public int getItemCount() {
        return listNews.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgNewsImage;
        TextView tvNewsTitle;
        TextView tvDatePublish;
//        TextView tvNewsDescription;
        public MyViewHolder(View itemView) {
            super(itemView);
            imgNewsImage = (ImageView) itemView.findViewById(R.id.img_news_image);
            tvNewsTitle = (TextView) itemView.findViewById(R.id.tv_news_title_value);
            tvDatePublish = (TextView) itemView.findViewById(R.id.tv_news_date_publish_value);
//            tvNewsDescription = (TextView) itemView.findViewById(R.id.tv_news_description_value);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (myOnClick != null) {
                        myOnClick.onClick(getLayoutPosition());
                    }
                }
            });
        }
    }

    interface MyOnClick {
        void onClick(int position);
    }
}
