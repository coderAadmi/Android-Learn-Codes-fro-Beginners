package com.prady.learning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("MAin","onCreate called");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MAin","onStart called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MAin","onRsume called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MAin","onPause called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MAin","onStop called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MAin","onDestroy called");
    }
}
