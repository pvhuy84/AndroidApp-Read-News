package com.example.pvhuy84.readnewsapp;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopNewsFragment extends Fragment {

    private static final String TAG = TopNewsFragment.class.getSimpleName();
    private Context context;

    public TopNewsFragment() {
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
        View v = inflater.inflate(R.layout.fragment_top_news_list_view, container, false);
        RecyclerView rvListNews = (RecyclerView) v.findViewById(R.id.rv_list_news);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);

        ArrayList<News> listNews = new ArrayList<>();
        if (getArguments() != null) {
            ResultForRequestNews resultForRequestNews = getArguments().getParcelable("ResultForRequestNews");
            listNews = resultForRequestNews.getArticles();
            Log.d(TAG, "onCreateView: " + listNews.size());
        }
        if (listNews == null) {
           listNews = new ArrayList<>();
        }
        NewsAdapter newsAdapter = new NewsAdapter(context, listNews, R.layout.list_view_news_item);
        rvListNews.setAdapter(newsAdapter);
        rvListNews.setLayoutManager(linearLayoutManager);

        return v;
    }

}
