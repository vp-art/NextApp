package com.salient.nexttablayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements Application.ActivityLifecycleCallbacks {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout) findViewById(R.id.tablayout_id);
        viewPager = (ViewPager) findViewById(R.id.viewpager_id);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.AddFragment(new FragmentIn(), "In");
        adapter.AddFragment(new FragmentNextactions(), "Next Actions");
        adapter.AddFragment(new FragmentProjects(), "Projects");
        adapter.AddFragment(new FragmentEvents(), "Events");
        adapter.AddFragment(new FragmentWaitingfor(), "Waiting For");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0);
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {

    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        RecyclerView recyclerView = new RecyclerView(this);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter();
        recyclerViewAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {

    }
}