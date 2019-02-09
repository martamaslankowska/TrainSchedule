package mma.trainschedule.common;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.Toast;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Common {
    public static String API_LINK = "http://rozklad-pkp.pl/";


    public static String request(String direction) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yy");
        String date = df.format(c.getTime());

        Date dt = new Date();
        int hours = dt.getHours();
        int minutes = dt.getMinutes();
        String time = hours + "%3A" + minutes;

//        Toast.makeText(context, date + "\n" + (hours + ":" + minutes) + "\n" + time, Toast.LENGTH_LONG).show();

        String url = "Something went wrong...";
        if (direction.equals("to"))
            url = "http://rozklad-pkp.pl/pl/tp?queryPageDisplayed=yes&REQ0JourneyStopsS0A=1&REQ0JourneyStopsS0G=5104140&REQ0JourneyStopsS0ID=&REQ0JourneyStops1.0G=&REQ0JourneyStopover1=&REQ0JourneyStops2.0G=&REQ0JourneyStopover2=&REQ0JourneyStopsZ0A=1&REQ0JourneyStopsZ0G=5104130&REQ0JourneyStopsZ0ID=&date=08.02.19&dateStart=08.02.19&dateEnd=08.02.19&REQ0JourneyDate=08.02.19&time=17%3A08&REQ0JourneyTime=17%3A08&REQ0HafasSearchForw=1&existBikeEverywhere=yes&existHafasAttrInc=yes&existHafasAttrInc=yes&REQ0JourneyProduct_prod_section_0_0=1&REQ0JourneyProduct_prod_section_1_0=1&REQ0JourneyProduct_prod_section_2_0=1&REQ0JourneyProduct_prod_section_3_0=1&REQ0JourneyProduct_prod_section_0_1=1&REQ0JourneyProduct_prod_section_1_1=1&REQ0JourneyProduct_prod_section_2_1=1&REQ0JourneyProduct_prod_section_3_1=1&REQ0JourneyProduct_prod_section_0_2=1&REQ0JourneyProduct_prod_section_1_2=1&REQ0JourneyProduct_prod_section_2_2=1&REQ0JourneyProduct_prod_section_3_2=1&REQ0JourneyProduct_prod_section_0_3=1&REQ0JourneyProduct_prod_section_1_3=1&REQ0JourneyProduct_prod_section_2_3=1&REQ0JourneyProduct_prod_section_3_3=1&REQ0JourneyProduct_opt_section_0_list=0%3A000000&existOptimizePrice=1&existHafasAttrExc=yes&REQ0HafasChangeTime=0%3A1&existSkipLongChanges=0&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&existHafasAttrInc=yes&existHafasAttrExc=yes&wDayExt0=Pn%7CWt%7C%C5%9Ar%7CCz%7CPt%7CSo%7CNd&start=start&existUnsharpSearch=yes&came_from_form=1#focus";
        if (direction.equals("from"))
            url = "https://rozklad-pkp.pl/pl/tp?queryPageDisplayed=yes&REQ0JourneyStopsS0A=1&REQ0JourneyStopsS0G=5104130&REQ0JourneyStopsS0ID=&REQ0JourneyStops1.0G=&REQ0JourneyStopover1=&REQ0JourneyStops2.0G=&REQ0JourneyStopover2=&REQ0JourneyStopsZ0A=1&REQ0JourneyStopsZ0G=5104140&REQ0JourneyStopsZ0ID=&date=08.02.19&dateStart=08.02.19&dateEnd=08.02.19&REQ0JourneyDate=08.02.19&time=17%3A08&REQ0JourneyTime=17%3A08&REQ0HafasSearchForw=1&existBikeEverywhere=yes&existHafasAttrInc=yes&existHafasAttrInc=yes&REQ0JourneyProduct_prod_section_0_0=1&REQ0JourneyProduct_prod_section_1_0=1&REQ0JourneyProduct_prod_section_2_0=1&REQ0JourneyProduct_prod_section_3_0=1&REQ0JourneyProduct_prod_section_0_1=1&REQ0JourneyProduct_prod_section_1_1=1&REQ0JourneyProduct_prod_section_2_1=1&REQ0JourneyProduct_prod_section_3_1=1&REQ0JourneyProduct_prod_section_0_2=1&REQ0JourneyProduct_prod_section_1_2=1&REQ0JourneyProduct_prod_section_2_2=1&REQ0JourneyProduct_prod_section_3_2=1&REQ0JourneyProduct_prod_section_0_3=1&REQ0JourneyProduct_prod_section_1_3=1&REQ0JourneyProduct_prod_section_2_3=1&REQ0JourneyProduct_prod_section_3_3=1&REQ0JourneyProduct_opt_section_0_list=0%3A000000&existOptimizePrice=1&existHafasAttrExc=yes&REQ0HafasChangeTime=0%3A1&existSkipLongChanges=0&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&existHafasAttrInc=yes&existHafasAttrExc=yes&wDayExt0=Pn%7CWt%7C%C5%9Ar%7CCz%7CPt%7CSo%7CNd&start=start&existUnsharpSearch=yes&came_from_form=1#focus\n";

        url = url.replaceAll("08.02.19", date);  // 09.02.19
        url = url.replaceAll("17%3A08", time);  // 19%3A24

        return url;
    }

    public static boolean isNetworkAvailable(Activity activity) {
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

}

// not working
// http://rozklad-pkp.pl/pl/tp?seqnr=1&ld=s38&ident=el.01410638.1549632900&queryPageDisplayed=yes&REQ0JourneyStopsS0A=1&REQ0JourneyStopsS0G=5104140&REQ0JourneyStopsS0ID=&REQ0JourneyStops1.0G=&REQ0JourneyStopover1=&REQ0JourneyStops2.0G=&REQ0JourneyStopover2=&REQ0JourneyStopsZ0A=1&REQ0JourneyStopsZ0K=S-1N1&date=08.02.19&dateStart=08.02.19&dateEnd=08.02.19&REQ0JourneyDate=08.02.19&time=19%3A24&REQ0JourneyTime=19%3A24&REQ0HafasSearchForw=1&existBikeEverywhere=yes&existHafasAttrInc=yes&existHafasAttrInc=yes&REQ0JourneyProduct_prod_section_0_0=1&REQ0JourneyProduct_prod_section_1_0=1&REQ0JourneyProduct_prod_section_2_0=1&REQ0JourneyProduct_prod_section_3_0=1&REQ0JourneyProduct_prod_section_0_1=1&REQ0JourneyProduct_prod_section_1_1=1&REQ0JourneyProduct_prod_section_2_1=1&REQ0JourneyProduct_prod_section_3_1=1&REQ0JourneyProduct_prod_section_0_2=1&REQ0JourneyProduct_prod_section_1_2=1&REQ0JourneyProduct_prod_section_2_2=1&REQ0JourneyProduct_prod_section_3_2=1&REQ0JourneyProduct_prod_section_0_3=1&REQ0JourneyProduct_prod_section_1_3=1&REQ0JourneyProduct_prod_section_2_3=1&REQ0JourneyProduct_prod_section_3_3=1&REQ0JourneyProduct_opt_section_0_list=0%3A000000&existOptimizePrice=1&existHafasAttrExc=yes&REQ0HafasChangeTime=0%3A1&existSkipLongChanges=0&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&existHafasAttrInc=yes&existHafasAttrExc=yes&wDayExt0=Pn%7CWt%7C%C5%9Ar%7CCz%7CPt%7CSo%7CNd&start=start&existUnsharpSearch=yes&came_from_form=1#focus

// working, used URL
// http://rozklad-pkp.pl/pl/tp?queryPageDisplayed=yes&REQ0JourneyStopsS0A=1&REQ0JourneyStopsS0G=5104140&REQ0JourneyStopsS0ID=&REQ0JourneyStops1.0G=&REQ0JourneyStopover1=&REQ0JourneyStops2.0G=&REQ0JourneyStopover2=&REQ0JourneyStopsZ0A=1&REQ0JourneyStopsZ0G=5104130&REQ0JourneyStopsZ0ID=&date=08.02.19&dateStart=08.02.19&dateEnd=08.02.19&REQ0JourneyDate=08.02.19&time=17%3A08&REQ0JourneyTime=17%3A08&REQ0HafasSearchForw=1&existBikeEverywhere=yes&existHafasAttrInc=yes&existHafasAttrInc=yes&REQ0JourneyProduct_prod_section_0_0=1&REQ0JourneyProduct_prod_section_1_0=1&REQ0JourneyProduct_prod_section_2_0=1&REQ0JourneyProduct_prod_section_3_0=1&REQ0JourneyProduct_prod_section_0_1=1&REQ0JourneyProduct_prod_section_1_1=1&REQ0JourneyProduct_prod_section_2_1=1&REQ0JourneyProduct_prod_section_3_1=1&REQ0JourneyProduct_prod_section_0_2=1&REQ0JourneyProduct_prod_section_1_2=1&REQ0JourneyProduct_prod_section_2_2=1&REQ0JourneyProduct_prod_section_3_2=1&REQ0JourneyProduct_prod_section_0_3=1&REQ0JourneyProduct_prod_section_1_3=1&REQ0JourneyProduct_prod_section_2_3=1&REQ0JourneyProduct_prod_section_3_3=1&REQ0JourneyProduct_opt_section_0_list=0%3A000000&existOptimizePrice=1&existHafasAttrExc=yes&REQ0HafasChangeTime=0%3A1&existSkipLongChanges=0&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&REQ0HafasAttrExc=&existHafasAttrInc=yes&existHafasAttrExc=yes&wDayExt0=Pn%7CWt%7C%C5%9Ar%7CCz%7CPt%7CSo%7CNd&start=start&existUnsharpSearch=yes&came_from_form=1#focus