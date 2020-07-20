package com.example.magnumrahul;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    ArrayList<User> userList;
    ListView lv;
    String nameOfUser;

    public void goBack(View view) {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        userList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.listView);

        Intent intent = getIntent();
        nameOfUser = intent.getStringExtra("key");

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new MainActivity2.ReadJSON().execute("https://api.github.com/search/users?q=saransh&page=2");
            }
        });
    }

    class ReadJSON extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            return readURL(params[0]);
        }

        @Override
        protected void onPostExecute(String content) {
            try {
                JSONObject jsonObject = new JSONObject(content);
                JSONArray jsonArray = jsonObject.getJSONArray("items");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject productObject = jsonArray.getJSONObject(i);
                    if(productObject.getString("login").contains(nameOfUser)) {
                        userList.add(new User(
                                productObject.getString("avatar_url"),
                                productObject.getString("login")
                        ));
                    }
                }
                if (userList.isEmpty()){
                    Toast.makeText(getApplicationContext(),"No Result.\n Try Another Keyword",Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            CustomListAdapter adapter = new CustomListAdapter(
                    getApplicationContext(), R.layout.custom_list_layout, userList
            );
            lv.setAdapter(adapter);
        }
    }


    private static String readURL(String theURL) {
        StringBuilder content = new StringBuilder();
        try {
            URL url = new URL(theURL);
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine())!= null){
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}