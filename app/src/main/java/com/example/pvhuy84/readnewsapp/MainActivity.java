package com.example.pvhuy84.readnewsapp;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements LatestNewsFragment.MyOnClick {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ActionBar actionBar;
    private ViewPager vpListNews;
    private TabLayout tlNewsTab;
    private NewsViewPagerAdapter newsViewPagerAdapter;
    private LatestNewsFragment latestNewsFragment;
    private Toolbar toolbarNews;
    private Boolean isSearchBarOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbarNews = (Toolbar) findViewById(R.id.toolbar_news);
        setSupportActionBar(toolbarNews);
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
        ResultForRequestNews resultForRequestNews;
        Bundle bundleForLatest = new Bundle();
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
        bundleForLatest.putParcelable("ResultForRequestNews", resultForRequestNews);
        latestNewsFragment = new LatestNewsFragment();
        latestNewsFragment.setArguments(bundleForLatest);
        latestNewsFragment.setMyOnClick(this);

        newsViewPagerAdapter.addFragment(latestNewsFragment, "Latest");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                ActionBar actionBar = getSupportActionBar();
                if (isSearchBarOpen) {
                    actionBar.setDisplayShowCustomEnabled(false);
                    actionBar.setDisplayShowTitleEnabled(true);

                    item.setIcon(R.mipmap.ic_search_white);
                    isSearchBarOpen = false;
                } else {
                    actionBar.setDisplayShowTitleEnabled(false);
                    actionBar.setDisplayShowCustomEnabled(true);
                    actionBar.setCustomView(R.layout.search_bar);

                    EditText editText = (EditText) actionBar.getCustomView().findViewById(R.id.edt_search);
                    editText.requestFocus();

                    editText.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                            latestNewsFragment.filter(charSequence);
                            Log.d(TAG, "onTextChanged: " + charSequence.toString());
                        }

                        @Override
                        public void afterTextChanged(Editable editable) {

                        }
                    });

                    item.setIcon(R.mipmap.ic_cancel_on_white);
                    isSearchBarOpen = true;
                }
                break;
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
