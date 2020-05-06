package com.example.myapplication2;

import android.os.AsyncTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class fetchData extends AsyncTask<Void, Void, Void> {

    private String data = "";
    private String dataParsed = "";
    private String yes = "";
    private int recent = 0;

    protected Void doInBackground(Void... voids) {

        try {
            URL url = new URL("http://157.245.185.143/stock_services.php");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line = "";

            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }

            String delims = "[}]";
            String[] tokens = data.split(delims);

            for (int x = 0; x < (tokens.length - 1); x++) {
                int test1 = tokens[x].indexOf("Name\":\"");
                if (tokens[x].substring(test1 + 7, test1 + 8).equals("C")) {
                    int left = tokens[x].indexOf("Plant_ID\":\"");
                    int right = tokens[x].indexOf("\",\"Name");

                    String sub = tokens[x].substring(left + 11, right);
                    if (x == 0) {
                        recent = Integer.parseInt(sub);
                    } else {
                        int test = Integer.parseInt(sub);
                        if (test > recent) {
                            recent = test;
                            yes = tokens[x].substring(2);
                        }
                    }
                }
            }

            String delims2 = "[,]";
            String[] no = yes.split(delims2);

            yes = "";
            int n = 0;
            while (n < 7) {
                yes = yes + no[n] + "\n";
                n++;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void avoid) {
        super.onPostExecute(avoid);

        MainActivity.Txt1.setText(yes);


    }
}
