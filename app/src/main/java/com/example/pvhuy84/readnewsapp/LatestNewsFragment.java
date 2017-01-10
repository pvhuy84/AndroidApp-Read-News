package com.example.pvhuy84.readnewsapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class LatestNewsFragment extends Fragment implements NewsAdapter.MyOnClick  {

    private static final String TAG = LatestNewsFragment.class.getSimpleName();
    private Context context;
    private MyOnClick myOnClick;
    private ArrayList<News> listNews;
    private NewsAdapter newsAdapter;

    public LatestNewsFragment() {
    }

    public void setMyOnClick(LatestNewsFragment.MyOnClick myOnClick) {
        this.myOnClick = myOnClick;
    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        listNews = new ArrayList<>();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_latest_news_list_view, container, false);
        RecyclerView rvListNews = (RecyclerView) v.findViewById(R.id.rv_list_news);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);

        if (getArguments() != null) {
            ResultForRequestNews resultForRequestNews = getArguments().getParcelable("ResultForRequestNews");
            if (resultForRequestNews != null) {
                listNews = resultForRequestNews.getArticles();
            }

            Log.d(TAG, "onCreateView: " + listNews.size());
        }
        if (listNews == null) {
            listNews = new ArrayList<>();
        }

        newsAdapter = new NewsAdapter(context, listNews, R.layout.list_view_news_item);
        newsAdapter.setMyOnClick(this);

        rvListNews.setAdapter(newsAdapter);
        rvListNews.setLayoutManager(linearLayoutManager);

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onClick(int position) {
        if (myOnClick != null) {
            myOnClick.onClick(listNews.get(position));
        }
    }

    public void filter(CharSequence charSequence) {
        if (newsAdapter != null) {
            newsAdapter.getFilter().filter(charSequence.toString());
        }
    }

    interface MyOnClick {
        void onClick(News news);
    }
}
