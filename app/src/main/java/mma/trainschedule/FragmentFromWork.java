package mma.trainschedule;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;

import java.io.IOException;

import static mma.trainschedule.common.Common.isNetworkAvailable;
import static mma.trainschedule.common.Common.request;

public class FragmentFromWork extends Fragment {

    TextView timeTextView, earlierTextView, laterTextView;
    Document doc;
    String[] departureTimes = {"", "", ""};
    View rootView;

    public FragmentFromWork() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_from_work, container, false);
        timeTextView = rootView.findViewById(R.id.timeTextView);
//        earlierTextView = rootView.findViewById(R.id.earlierTimeTextView);
        laterTextView = rootView.findViewById(R.id.laterTimeTextView);

//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                try {
//
//                    doc = Jsoup.connect(request()).get();
//                    departureTime = doc.getElementsByAttributeValueMatching("class", "connection-search-results")
//                            .get(0)
//                            .childNodes().get(3)
//                            .childNodes().get(2)
//                            .childNodes().get(3)
//                            .childNodes().get(0)
//                            .childNodes().get(0)
//                            .childNodes().get(2)
//                            .toString();
////                    departureTime = ((TextNode) htmlTime).text();
////                    departureTime = htmlTime.getClass().getSimpleName();
//
//                    String a = "Hey";
//                    timeTextView.setText(a);
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//
//        Thread worker = new Thread(runnable);
//        worker.start();

        if (isNetworkAvailable(getActivity())) {
            AsyncTaskRunner runner = new AsyncTaskRunner();
            runner.execute();
        }
        else {
            timeTextView.setText(getString(R.string.no_internet));
            timeTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 48);
            laterTextView.setText("");
        }

        return rootView;
    }


    private class AsyncTaskRunner extends AsyncTask<String, String, String[]> {

        @Override
        protected String[] doInBackground(String... strings) {
            try {
                doc = Jsoup.connect(request("from")).get();

                // earlier train
                Node node = doc.getElementsByAttributeValueMatching("class", "connection-search-results")
                        .get(0)
                        .childNodes().get(3);

                departureTimes[0] = node.childNodes().get(1)
                        .childNodes().get(5)
                        .childNodes().get(0)
                        .childNodes().get(0)
                        .childNodes().get(2)
                        .toString();

                // good train
                departureTimes[1] = node.childNodes().get(2)
                        .childNodes().get(3)
                        .childNodes().get(0)
                        .childNodes().get(0)
                        .childNodes().get(2)
                        .toString();

                // later train
                departureTimes[2] = node.childNodes().get(3)
                        .childNodes().get(3)
                        .childNodes().get(0)
                        .childNodes().get(0)
                        .childNodes().get(2)
                        .toString();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return departureTimes;
        }

        @Override
        protected void onPostExecute(String[] results) {
            // execution of result of Long time consuming operation
            for(int i=0; i<results.length; i++) {
                results[i] = (results[i]).replaceAll("<span>", "");
                results[i] = (results[i]).replaceAll("</span>", "");
            }
//            earlierTextView.setText(results[0]);
            timeTextView.setText(results[1]);
            laterTextView.setText(results[2]);
        }


    }



}
