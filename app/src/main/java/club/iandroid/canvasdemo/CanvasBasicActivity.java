package club.iandroid.canvasdemo;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import club.iandroid.canvasdemo.fragments.CanvasCircleFragment;
import club.iandroid.canvasdemo.fragments.CanvasLineFragment;
import club.iandroid.canvasdemo.fragments.CanvasOvalFragment;
import club.iandroid.canvasdemo.fragments.CanvasPointFragment;
import club.iandroid.canvasdemo.fragments.CanvasRectFragment;
import club.iandroid.canvasdemo.fragments.CanvasRoundRectFragment;

public class CanvasBasicActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private TabLayout tabLayout;

    private List<String> mTitles = new ArrayList<>();
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_basic);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        initDatas();

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout = (TabLayout)findViewById(R.id.mTab);
        tabLayout.setupWithViewPager(mViewPager);
        addEvent();
    }

    private void initDatas(){
        mTitles.clear();
        mTitles.add("drawCircle");
        mTitles.add("drawRect");
        mTitles.add("drawLine");
        mTitles.add("drawPoint");

        mTitles.add("drawRoundRect");
        mTitles.add("drawOval");

        //画圆形
        fragmentList.add(new CanvasCircleFragment());
        //画矩形
        fragmentList.add(new CanvasRectFragment());
        //画线
        fragmentList.add(new CanvasLineFragment());
        //画圆点
        fragmentList.add(new CanvasPointFragment());

        //画圆角矩形
        fragmentList.add(new CanvasRoundRectFragment());
        //画椭圆
        fragmentList.add(new CanvasOvalFragment());
    }

    private void addEvent(){
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //选中tab
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //未选中tab
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //再次选中tab
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_canvas_basic, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return mTitles.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles.get(position);
        }
    }
}
