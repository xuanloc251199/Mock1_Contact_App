package com.xuanloc.mock1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;
import com.xuanloc.mock1.adapter.ViewPagerAdapter;
import com.xuanloc.mock1.ui.ContactFragment;
import com.xuanloc.mock1.ui.CallLogFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static final int REQUEST_CODE = 1;

    private static String[] STORAGE_PERMISSION = {
            Manifest.permission.READ_CALL_LOG,
            Manifest.permission.READ_CONTACTS
    };

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        verityPermission();

        initId();

        setupViewPager();
        mTabLayout.setupWithViewPager(mViewPager);

    }

    private void verityPermission() {

        Log.d(TAG, "verityPermission: Checking Permission");

        int permissionReadContact = ActivityCompat
                .checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS);
        int permissionReadCallLog = ActivityCompat
                .checkSelfPermission(MainActivity.this, Manifest.permission.READ_CALL_LOG);

        if (permissionReadContact != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                    MainActivity.this,
                    STORAGE_PERMISSION,
                    REQUEST_CODE
            );
        }

        if (permissionReadCallLog != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                    MainActivity.this,
                    STORAGE_PERMISSION,
                    REQUEST_CODE
            );
        }

    }

    private void initId() {
        mTabLayout = findViewById(R.id.tabCategory);
        mViewPager = findViewById(R.id.viewContact);
    }

    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addFragment(new ContactFragment(), "Danh bạ");
        adapter.addFragment(new CallLogFragment(), "Nhật ký");
        mViewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}