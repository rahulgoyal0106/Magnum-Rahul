package com.example.magnumrahul;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText userName;
    String name;
    Button btn;
    public void search(View view) {
        userName = (EditText) findViewById(R.id.editText);
        name = userName.getText().toString();
        if(checkConnection()) {
            if (name.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Field is Empty", Toast.LENGTH_SHORT).show();
                Log.i("Text is ", name);
            } else {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                intent.putExtra("key", name);
                startActivity(intent);
            }
        }
        else {
            Toast.makeText(this,"Internet Connection Required",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.search);
    }

    public boolean checkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo == null)
            return false;
        else
            return true;
    }
}