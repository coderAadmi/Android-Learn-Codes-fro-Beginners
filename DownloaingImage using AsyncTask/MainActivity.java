package com.prady.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * AsyncTask class is an abstract class that let's your app to run operations on thread different from UI thread.
 * It takes three parameter: <param1, param2, param3>
 *     param1: It describes the type of argumetns that are to be passed on calling the execute method.
 *     param2: It describes the type of argument onProgressUpdate() will get.
 *     param3: It describes the return type of doInBackground() method.
 *  To download an image from internet, First you should declare <uses-permission: android.permission.INTERNET> </uses-permission:> in your manifest file
 *  To connect to a Http page, you got to make connection with it with the help of URL class.
 *  Check response code of the connection object.
 *  if OKAY: create bitmap image from it's input stream.
 *  If bitmap created successfully-> return image reference
 *  Otherwise, return null.
 */

public class MainActivity extends AppCompatActivity {

    private Button mShowImageButton;
    private ImageView mImageView;
    private ImageDownloader imageDownloader;

    private class ImageDownloader extends AsyncTask<String,Integer, Bitmap>{

        URL imageURL;
        @Override
        protected Bitmap doInBackground(String... strings) {
            try{
                imageURL = new URL(strings[0]);
                HttpsURLConnection imageConnection = (HttpsURLConnection) imageURL.openConnection();
                if(imageConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    imageConnection.connect();
                    Bitmap image = BitmapFactory.decodeStream(imageConnection.getInputStream());
                    return image;
                }
                return null;
            }
            catch (Exception e)
            {
                Toast.makeText(MainActivity.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                return null;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = findViewById(R.id.imageView);
        mShowImageButton = findViewById(R.id.button);
        mShowImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage();
            }
        });
    }

    private void showImage()
    {
        imageDownloader = new ImageDownloader();
        try{
            Bitmap image = imageDownloader.execute("https://static-media.fxx.com/img/FX_Networks_-_FXX/359/435/Simpsons_06_15_P5_640x360_312576067969.jpg").get();
            mImageView.setImageBitmap(image);
        }
        catch (Exception e)
        {
            Toast.makeText(this,"Couldn't downnload",Toast.LENGTH_SHORT).show();
        }
    }
}