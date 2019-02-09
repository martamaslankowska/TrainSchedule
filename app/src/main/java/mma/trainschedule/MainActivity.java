package mma.trainschedule;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import static mma.trainschedule.common.Common.request;

public class MainActivity extends AppCompatActivity {

//    private WebView webView;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    TextView timeTextView, laterTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

//
//    private void testingWebView() {
//        webView = (WebView) findViewById(R.id.activity_main_webview);
//
//        // Enable Javascript
//        webView.getSettings().setJavaScriptEnabled(true);
//
//        webView.setWebViewClient(new WebViewClient() {
//            public boolean shouldOverrideUrlLoading(WebView view, String url){
//                return false;
//            }
//        });
//
////        webView.loadUrl("http://rozklad-pkp.pl/");
////        webView.loadUrl("http://rozklad-pkp.pl/pl/tp?queryPageDisplayed=yes&REQ0JourneyStopsS0A=1&REQ0JourneyStopsS0G=5104140&REQ0JourneyStopsS0ID=&REQ0JourneyStops1.0G=&REQ0JourneyStopover1=&REQ0JourneyStops2.0G=&REQ0JourneyStopover2=&REQ0JourneyStopsZ0A=1&REQ0JourneyStopsZ0G=5104130&REQ0JourneyStopsZ0ID=&date=08.02.19&dateStart=08.02.19&dateEnd=08.02.19&REQ0JourneyDate=08.02.19&time=13%3A58&REQ0JourneyTime=13%3A58&REQ0HafasSearchForw=1&existBikeEverywhere=yes&existHafasAttrInc=yes&existHafasAttrInc=yes&REQ0JourneyProduct_prod_section_0_0=1&REQ0JourneyProduct_prod_section_1_0=1&REQ0JourneyProduct_prod_section_2_0=1&REQ0JourneyProduct_prod_section_3_0=1&REQ0JourneyProduct_prod_section_0_1=1&REQ0JourneyProduct_prod_section_1_1=1&REQ0JourneyProduct_prod_section_2_1=1&REQ0JourneyProduct_prod_section_3_1=1&REQ0JourneyProduct_prod_section_0_2=1&REQ0JourneyProduct_prod_section_1_2=1&REQ0JourneyProduct_prod_section_2_2=1&REQ0JourneyProduct_prod_section_3_2=1&REQ0JourneyProduct_prod_section_0_3=1&REQ0JourneyProduct_prod_section_1_3=1&REQ0JourneyProduct_prod_section_2_3=1&REQ0JourneyProduct_prod_section_3_3=1&REQ0JourneyProduct_opt_section_0_list=0%3A000000&existOptimizePrice=1&existHafasAttrExc=yes&REQ0HafasChangeTime=0%3A1&existSkipLongChanges=0&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&existHafasAttrInc=yes&existHafasAttrExc=yes&wDayExt0=Pn%7CWt%7C%C5%9Ar%7CCz%7CPt%7CSo%7CNd&start=start&existUnsharpSearch=yes&came_from_form=1#focus");
////        webView.loadUrl("http://rozklad-pkp.pl/pl/tp?queryPageDisplayed=yes&REQ0JourneyStopsS0A=1&REQ0JourneyStopsS0G=5104140&REQ0JourneyStopsS0ID=&REQ0JourneyStops1.0G=&REQ0JourneyStopover1=&REQ0JourneyStops2.0G=&REQ0JourneyStopover2=&REQ0JourneyStopsZ0A=1&REQ0JourneyStopsZ0G=5104130&REQ0JourneyStopsZ0ID=&date=08.02.19&dateStart=08.02.19&dateEnd=08.02.19&REQ0JourneyDate=08.02.19&time=17%3A08&REQ0JourneyTime=17%3A08&REQ0HafasSearchForw=1&existBikeEverywhere=yes&existHafasAttrInc=yes&existHafasAttrInc=yes&REQ0JourneyProduct_prod_section_0_0=1&REQ0JourneyProduct_prod_section_1_0=1&REQ0JourneyProduct_prod_section_2_0=1&REQ0JourneyProduct_prod_section_3_0=1&REQ0JourneyProduct_prod_section_0_1=1&REQ0JourneyProduct_prod_section_1_1=1&REQ0JourneyProduct_prod_section_2_1=1&REQ0JourneyProduct_prod_section_3_1=1&REQ0JourneyProduct_prod_section_0_2=1&REQ0JourneyProduct_prod_section_1_2=1&REQ0JourneyProduct_prod_section_2_2=1&REQ0JourneyProduct_prod_section_3_2=1&REQ0JourneyProduct_prod_section_0_3=1&REQ0JourneyProduct_prod_section_1_3=1&REQ0JourneyProduct_prod_section_2_3=1&REQ0JourneyProduct_prod_section_3_3=1&REQ0JourneyProduct_opt_section_0_list=0%3A000000&existOptimizePrice=1&existHafasAttrExc=yes&REQ0HafasChangeTime=0%3A1&existSkipLongChanges=0&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&existHafasAttrInc=yes&existHafasAttrExc=yes&wDayExt0=Pn%7CWt%7C%C5%9Ar%7CCz%7CPt%7CSo%7CNd&start=start&existUnsharpSearch=yes&came_from_form=1#focus");
//
//
//
////        webView.loadUrl("http://rozklad-pkp.pl/pl/tp?seqnr=1&ld=s38&ident=a0.058938.1549632243&queryPageDisplayed=yes&REQ0JourneyStopsS0A=1&REQ0JourneyStopsS0G=5104140&REQ0JourneyStopsS0ID=&REQ0JourneyStops1.0G=&REQ0JourneyStopover1=&REQ0JourneyStops2.0G=&REQ0JourneyStopover2=&REQ0JourneyStopsZ0A=1&REQ0JourneyStopsZ0K=S-1N1&date=08.02.19&dateStart=08.02.19&dateEnd=08.02.19&REQ0JourneyDate=08.02.19&time=16%3A51&REQ0JourneyTime=14%3A31&REQ0HafasSearchForw=1&existBikeEverywhere=yes&existHafasAttrInc=yes&existHafasAttrInc=yes&REQ0JourneyProduct_prod_section_0_0=1&REQ0JourneyProduct_prod_section_1_0=1&REQ0JourneyProduct_prod_section_2_0=1&REQ0JourneyProduct_prod_section_3_0=1&REQ0JourneyProduct_prod_section_0_1=1&REQ0JourneyProduct_prod_section_1_1=1&REQ0JourneyProduct_prod_section_2_1=1&REQ0JourneyProduct_prod_section_3_1=1&REQ0JourneyProduct_prod_section_0_2=1&REQ0JourneyProduct_prod_section_1_2=1&REQ0JourneyProduct_prod_section_2_2=1&REQ0JourneyProduct_prod_section_3_2=1&REQ0JourneyProduct_prod_section_0_3=1&REQ0JourneyProduct_prod_section_1_3=1&REQ0JourneyProduct_prod_section_2_3=1&REQ0JourneyProduct_prod_section_3_3=1&REQ0JourneyProduct_opt_section_0_list=0%3A000000&existOptimizePrice=1&existHafasAttrExc=yes&REQ0HafasChangeTime=0%3A1&existSkipLongChanges=0&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&existHafasAttrInc=yes&existHafasAttrExc=yes&wDayExt0=Pn%7CWt%7C%C5%9Ar%7CCz%7CPt%7CSo%7CNd&start=start&existUnsharpSearch=yes&came_from_form=1#focus");
////        webView.loadUrl("http://rozklad-pkp.pl/pl/tp?seqnr=1&ld=s38&ident=ad.0122738.1549632295&queryPageDisplayed=yes&REQ0JourneyStopsS0A=1&REQ0JourneyStopsS0K=S-0N1&REQ0JourneyStops1.0G=&REQ0JourneyStopover1=&REQ0JourneyStops2.0G=&REQ0JourneyStopover2=&REQ0JourneyStopsZ0A=1&REQ0JourneyStopsZ0G=5104130&REQ0JourneyStopsZ0ID=&date=08.02.19&dateStart=08.02.19&dateEnd=08.02.19&REQ0JourneyDate=08.02.19&time=14%3A40&REQ0JourneyTime=14%3A40&REQ0HafasSearchForw=1&existBikeEverywhere=yes&existHafasAttrInc=yes&existHafasAttrInc=yes&REQ0JourneyProduct_prod_section_0_0=1&REQ0JourneyProduct_prod_section_1_0=1&REQ0JourneyProduct_prod_section_2_0=1&REQ0JourneyProduct_prod_section_3_0=1&REQ0JourneyProduct_prod_section_0_1=1&REQ0JourneyProduct_prod_section_1_1=1&REQ0JourneyProduct_prod_section_2_1=1&REQ0JourneyProduct_prod_section_3_1=1&REQ0JourneyProduct_prod_section_0_2=1&REQ0JourneyProduct_prod_section_1_2=1&REQ0JourneyProduct_prod_section_2_2=1&REQ0JourneyProduct_prod_section_3_2=1&REQ0JourneyProduct_prod_section_0_3=1&REQ0JourneyProduct_prod_section_1_3=1&REQ0JourneyProduct_prod_section_2_3=1&REQ0JourneyProduct_prod_section_3_3=1&REQ0JourneyProduct_opt_section_0_list=0%3A000000&existOptimizePrice=1&existHafasAttrExc=yes&REQ0HafasChangeTime=0%3A1&existSkipLongChanges=0&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&existHafasAttrInc=yes&existHafasAttrExc=yes&wDayExt0=Pn%7CWt%7C%C5%9Ar%7CCz%7CPt%7CSo%7CNd&start=start&existUnsharpSearch=yes&came_from_form=1#focus");
//
//        String url = request(this);
//        webView.loadUrl(url);
//    }
//


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentToWork(), getString(R.string.fragment_to_work));
        adapter.addFragment(new FragmentFromWork(), getString(R.string.fragment_from_work));
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
