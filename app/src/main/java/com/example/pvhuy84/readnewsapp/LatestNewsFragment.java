package com.example.pvhuy84.readnewsapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class LatestNewsFragment extends Fragment {

    private Context context;

    public LatestNewsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_latest_news_list_view, container, false);
//        RecyclerView rvListNews = (RecyclerView) v.findViewById(R.id.rv_list_news);
//
//        ArrayList<News> listNews = new ArrayList<>();
//        if (getArguments() != null) {
//            listNews = (ArrayList<News>) getArguments().getParcelable("listNews");
//        }
//        NewsAdapter newsAdapter = new NewsAdapter(context, listNews, R.layout.list_view_news_item);
//        rvListNews.setAdapter(newsAdapter);

        return v;
    }
}
