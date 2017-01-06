package com.example.pvhuy84.readnewsapp;

import android.content.Intent;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements TopNewsFragment.MyOnClick{

    private static final String TAG = MainActivity.class.getSimpleName();
    private ActionBar actionBar;
    private ViewPager vpListNews;
    private TabLayout tlNewsTab;
    private NewsViewPagerAdapter newsViewPagerAdapter;
    private TopNewsFragment topNewsFragment;
    private LatestNewsFragment latestNewsFragment;
    private PopularNewsFragment popularNewsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();
        actionBar.setTitle("News");

        vpListNews = (ViewPager) findViewById(R.id.vp_list_news);
        tlNewsTab = (TabLayout) findViewById(R.id.tl_news_tab);

        newsViewPagerAdapter = new NewsViewPagerAdapter(getSupportFragmentManager());
        initializeNewsViewPagerAdapter();

        vpListNews.setAdapter(newsViewPagerAdapter);
        tlNewsTab.setupWithViewPager(vpListNews);
    }

    // Initialize NewsViewPagerAdapter
    private void initializeNewsViewPagerAdapter() {
        Bundle bundle = new Bundle();
        ResultForRequestNews resultForRequestNews;
        try {
            String url = "https://newsapi.org/v1/articles?source=cnn&sortBy=top&apiKey=56e8a396457f4becb018c72c66645e91";
            resultForRequestNews = new GetNewsTask().execute(url).get();
            Log.d(TAG, "initializeNewsViewPagerAdapter: " + resultForRequestNews);
        } catch (InterruptedException e) {
            e.printStackTrace();
            resultForRequestNews = new ResultForRequestNews();
        } catch (ExecutionException e) {
            e.printStackTrace();
            resultForRequestNews = new ResultForRequestNews();
        }
        bundle.putParcelable("ResultForRequestNews", resultForRequestNews);

        topNewsFragment = new TopNewsFragment();
        topNewsFragment.setArguments(bundle);
        topNewsFragment.setMyOnClick(this);

        latestNewsFragment = new LatestNewsFragment();
        popularNewsFragment = new PopularNewsFragment();

        newsViewPagerAdapter.addFragment(topNewsFragment, "Top");
        newsViewPagerAdapter.addFragment(latestNewsFragment, "Latest");
        newsViewPagerAdapter.addFragment(popularNewsFragment, "Popular");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_pages_saved:
                break;
            case R.id.action_settings:
                break;
            case R.id.action_about:
                break;
            case R.id.action_exit:
                finish();
                System.exit(0);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(News news) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("news", news);
        Intent intent = new Intent(MainActivity.this, NewsContentActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
