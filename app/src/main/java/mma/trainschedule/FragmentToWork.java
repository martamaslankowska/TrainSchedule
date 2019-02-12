package mma.trainschedule;

import android.graphics.Color;
import android.opengl.Visibility;
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
import static mma.trainschedule.common.Common.minutesLeft;
import static mma.trainschedule.common.Common.request;

public class FragmentToWork extends Fragment {

    TextView timeTextView, earlierTextView, laterTextView, minutesTextView;
    Document doc;
    String[] departureTimes = {"", "", ""};
    View rootView;

    public FragmentToWork() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_to_work, container, false);
        timeTextView = rootView.findViewById(R.id.timeTextView);
//        earlierTextView = rootView.findViewById(R.id.earlierTimeTextView);
        laterTextView = rootView.findViewById(R.id.laterTimeTextView);
        minutesTextView = rootView.findViewById(R.id.minutesTimeTextView);

        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();

        if (isNetworkAvailable(getActivity())) {
            AsyncTaskRunner runner = new AsyncTaskRunner();
            runner.execute();
        }
        else {
            timeTextView.setText(getString(R.string.no_internet));
            timeTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 48);
            laterTextView.setText("");
        }

    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String[]> {

        @Override
        protected String[] doInBackground(String... strings) {
            try {
                doc = Jsoup.connect(request("to")).get();

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

            int minutesLeft = minutesLeft(results[1]);
            if (minutesLeft <= 10)
                minutesTextView.setTextColor(getResources().getColor(R.color.colorAccent));
            if (minutesLeft == 0)
                minutesTextView.setText(getString(R.string.buhaha));
            else {
                String hours = minutesLeft / 60 > 0 ? String.valueOf(minutesLeft / 60) + " h " : "";
                String minutes = minutesLeft % 60 > 0 ? String.valueOf(minutesLeft % 60) + " min" : "";
                minutesTextView.setText((hours + minutes));
            }
        }


    }



}
