package com.margaret.gudfud;


import android.net.Uri;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements CookMenuFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //temporary, delete the whole orders table when the app runs
        OrdersDbHelper ordersDbHelper = new OrdersDbHelper(this);
        final SQLiteDatabase db = ordersDbHelper.getReadableDatabase();

        Fragment defaultFragment = new OrdersFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragmentcontainer, defaultFragment);
        fragmentTransaction.commit();

    }

    public void onCookMenuFragmentInteraction(Uri uri){

    }
}
