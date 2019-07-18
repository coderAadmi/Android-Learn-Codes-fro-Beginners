package com.example.mylibs;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class DelAdd extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap googleMap;

    private FusedLocationProviderClient mFused;

    private Location mLocation;

    private LinearLayout mMarker;

    private TextView mAddress;

    private float[] myLocn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delivery_address);

        SupportMapFragment map = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        map.getMapAsync(this);

        mMarker = findViewById(R.id.map_center);
        mAddress = findViewById(R.id.address_text);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        Toast.makeText(this, "MAp fetched", Toast.LENGTH_SHORT).show();

        getLocation();

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void getLocation()
    {
        mFused = LocationServices.getFusedLocationProviderClient(DelAdd.this);
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},101);
            return;
        }
        else
            googleMap.setMyLocationEnabled(true);

      mFused.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
          @Override
          public void onComplete(@NonNull Task<Location> task) {
              if(task.isSuccessful())
              {
                  mLocation = task.getResult();
                  LatLng latLng = new LatLng(mLocation.getLatitude(),mLocation.getLongitude());

                  googleMap.addMarker(new MarkerOptions().position(latLng).title("POPP"));

                  googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,16f));

                  //googleMap.getUiSettings().setRotateGesturesEnabled(true);
                  googleMap.getUiSettings().setAllGesturesEnabled(true);
                  googleMap.getUiSettings().setCompassEnabled(true);
                  googleMap.setBuildingsEnabled(true);
              }
          }
      });

        googleMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
                Log.d("CAM_MOV","moved");
            }
        });

        googleMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                Log.d("CAM_IDLE","idle");

                Log.d("CAM_IDLE_LOC","X: "+mMarker.getX()+" Y: "+mMarker.getY());

                int x = (int) mMarker.getX();
                int y = (int) mMarker.getY();

                Projection projection = googleMap.getProjection();

                LatLng centerLATLNG = projection.fromScreenLocation(new Point(x,y));

                //googleMap.addMarker(new MarkerOptions().position(centerLATLNG));

                Log.d("CAM_IDLE_POS",centerLATLNG.toString());

                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        Geocoder coder = new Geocoder(DelAdd.this, Locale.getDefault());
                        List<Address> addressList = null;
                        try {
                            addressList = coder.getFromLocation(centerLATLNG.latitude,centerLATLNG.longitude,1);

                           // Toast.makeText(DelAdd.this, "Address: "+addressList.get(0).getAddressLine(0), Toast.LENGTH_LONG).show();
                            String address = addressList.get(0).getAddressLine(0);
                            mAddress.setText(address);
                            String[] info = address.split(",");
                            Log.d("DEL_ADD", address);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });


    }

    private void updateLocn(LatLng pos)
    {
        googleMap.addMarker(new MarkerOptions().position(pos));

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pos,15.2f));

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 101)
        {
            if(grantResults!=null && grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                getLocation();
            }
        }
    }
}
