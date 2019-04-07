package com.prady.learning;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * To get messages or arguments from invoking activity, use getIntent() method of Activity class.
 * then you can get the values passed by parent activity using the keys.
 * To use any widget you first need it's reference . Use View.findViewById(Widget id ).
 * The most important point: This Activity must be declared in your AndroidManifest.xml file which contains meta-data about your app.
 *      to decare your activity in Manifest file: add <activity android:name=".NewActivity"></activity>
 * otherwise, your app will crash & so will your motivation.
 *Actionbar is the head of your app. That shows the title of your app by default.
 * You can make it hidden by invoking its hide() method.
 * In AppcompatActivity , Use getSupportActionBar() method to get reference of your ActionBar. For backward compatibility.
 * For displaying short information messages to user ,Use Toast messages.
 *      Toast.makeToast(context, message(String) , Duration).show();
 */

public class NewActivity extends AppCompatActivity {

    private Button mHideActionBarButton, mShowActionBarButton;
    ActionBar actionBar;//for reference of The action bar
    private boolean isActionBarHidden; //shows state of Actionbar whether hidden or not

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        isActionBarHidden = false; //Actionbar not hidden

        Intent intent = getIntent(); //to get reference of intent for getting arguments passe by parent activity
        String messsage = intent.getStringExtra(MainActivity.Main_TAG);  //To get String extra paassed by parent activity
        if(messsage != null)  //If Message is there
        {
            TextView textView = findViewById(R.id.messageTextView); //reference of textview showing message form parent activity
            textView.setText(messsage); //sets text to message
        }

        actionBar = getSupportActionBar(); //gets reference of ActionBar , getSupportActionBar() is used for backward compatibility.
        actionBar.setTitle("Intent app");  //Sets title to Intent app

        mHideActionBarButton = findViewById(R.id.HideButton);  //gets reference of HideButton
        mHideActionBarButton.setOnClickListener(new View.OnClickListener() {
            //Specify code in OnClick() metod to be prformed
            @Override
            public void onClick(View v) {
                hideActionBar();
            }
        });

        mShowActionBarButton = findViewById(R.id.ShowButton);  //gets reference of ShowButton
        mShowActionBarButton.setOnClickListener(new View.OnClickListener() {
            //specify code insode OnClick() method to be performed
            @Override
            public void onClick(View v) {
                showActionBar();
            }
        });
    }

    //this method hides action bar if it's on screen otherwise gives a toast message
    private void hideActionBar()
    {
        if(isActionBarHidden == false) {
            isActionBarHidden = true;
            actionBar.hide();
        }
        else
            Toast.makeText(getApplicationContext(),"Actionbar already hidden",Toast.LENGTH_SHORT).show();
    }

    //this method shows actionbar if it's hidden otherwise gives a toast message
    private void showActionBar()
    {
        if(isActionBarHidden) {
            isActionBarHidden = false;
            actionBar.show();
        }
        else
            Toast.makeText(getApplicationContext(),"Actionbar is already shown",Toast.LENGTH_SHORT).show(); //Toasts are messages tht you want to give to user and automatically fades away
    }
}
