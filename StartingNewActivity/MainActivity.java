package com.prady.learning;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.jar.Manifest;


/**
 * To use any widget you first need it's reference . Use View.findViewById(Widget id ).
 * To specify the function invoked on clicking of a widget(like button, TextView, etc.) onClickListener Interface is used;
 * setOnClickListener(Takes in anonymous inner class) for better specification of the methd to be invoked.
 * Anonymous inner classes is a good practice as it helps in memory saving, And code specification is also clear.
 * To start a new Activity from one activity, Intents are used.
 * Intents make calls to android os and then Activitymanager that is ahndled by os starts new Activity.
 * To use Intents , Intent class is used.
 *      Intent intent = new Intent(packageContext, NewActivity.class)->packageContext refers to your application's context;
 *      Intents have Extras that are used to pass messages and arguments to new activity.
 *      Extras work in key:value pair where key can be used to get the value from intents.
 *      finally Activity class has method startActiviy(intent), intent contains the intent which has your new activity's reference.
 *      finally new activity is started.
 *  The most important point: Your New Activit must be declared in your AndroidManifest.xml file which contains meta-data about your app.
 *      to decare your activity in Manifest file: add <activity android:name=".NewActivity"></activity>
 */

public class MainActivity extends AppCompatActivity {

    public static String Main_TAG = "MainActivity";

    private Button mButtonNewActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonNewActivity = findViewById(R.id.button);
        mButtonNewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewActivity();
            }
        });
    }

    private void startNewActivity()
    {
        Intent intent = new Intent(this,NewActivity.class);
        intent.putExtra(Main_TAG,"Hey "+ Main_TAG+" called this activity");
        startActivity(intent);
    }
}
