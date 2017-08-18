package club.iandroid.canvasdemo;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import club.iandroid.canvasdemo.fragments.CanvasArcFragment;
import club.iandroid.canvasdemo.fragments.CanvasCircleFragment;
import club.iandroid.canvasdemo.fragments.CanvasDrawPathFragment;
import club.iandroid.canvasdemo.fragments.CanvasLineFragment;
import club.iandroid.canvasdemo.fragments.CanvasOvalFragment;
import club.iandroid.canvasdemo.fragments.CanvasPathFillTypeFragment;
import club.iandroid.canvasdemo.fragments.CanvasPointFragment;
import club.iandroid.canvasdemo.fragments.CanvasRectFragment;
import club.iandroid.canvasdemo.fragments.CanvasRoundRectFragment;
import club.iandroid.canvasdemo.paints.PaintColorFragment;

public class PainBasicActivity extends AppCompatActivity {


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
        setContentView(R.layout.activity_pain_basic);
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
        mTitles.add("paint 设置颜色");

        //画圆形
        fragmentList.add(new PaintColorFragment());
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
