package com.example.animeimages;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    ListView listView;
    ImageView imageView;
    DownloadData data;
    String execute = "https://wall.alphacoders.com/by_category.php?id=3&name=Anime+Wallpapers";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.htmlListView);
        imageView = (ImageView) findViewById(R.id.imageView);

        data = new DownloadData();

        if (execute != "") {
            data.execute(execute);
        } else {
            Toast.makeText(this, "Please Search something to see! :)", Toast.LENGTH_LONG).show();
        }
    }

    class DownloadData extends AsyncTask<String, Void, String> {
        private static final String TAG = "DownloadData";

        ArrayList<Photo> arrayList;
        Photo pic;

        public ArrayList<Photo> getList() {
            return arrayList;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Pattern p = Pattern.compile("src=\"(.*?)\"");
            Matcher m = p.matcher(s);
            arrayList = new ArrayList<>();

            while (m.find()) {
                pic = new Photo();
                pic.setPhoto(m.group(1));
                arrayList.add(pic);
            }

            /* TODO I have made the reference here Photo is a reference type so, when you make it's object you only get one of them.
                In the loop you change pic, then addd it to the list but it's the same pic i.e., reference each time, so at the end you only have the last one in the arraylist.
                So, to fix it I had to made the reference into the while loop! */

//        Log.i(TAG, "onPostExecute: List " + data.getList());

            final CustomAdapter customAdapter = new CustomAdapter(
                    MainActivity.this, R.layout.view_images, data.getList());
            listView.setAdapter(customAdapter);
        }

        @Override
        protected String doInBackground(String... s) {
            String htmlResult = downloadHTML(s[0]);

            if (htmlResult == null) {
                Log.e(TAG, "doInBackground: Error Downloading!");
            }
            return htmlResult;
        }

        public String downloadHTML(String htmlData) {
            String result = "";
            Document doc;

            try {
                doc = Jsoup.connect(htmlData).get();
                Elements links = doc.getElementsByClass("boxgrid");
                result = String.valueOf(links);

//                Log.i(TAG, "downloadHTML: " + result);

                return result;

            } catch (Exception e) {
                e.printStackTrace();
                return "Nothing Happened!";
            }
        }
    }
}