package com.example.pvhuy84.readnewsapp;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private ActionBar actionBar;
    private ViewPager vpListNews;
    private TabLayout tlNewsTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();
        actionBar.setTitle("News");

        vpListNews = (ViewPager) findViewById(R.id.vp_list_news);
        tlNewsTab = (TabLayout) findViewById(R.id.tl_news_tab);
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
}
