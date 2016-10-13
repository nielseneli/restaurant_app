package com.margaret.gudfud;


import android.net.Uri;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.*;

public class MainActivity extends AppCompatActivity implements CookMenuFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OrdersDbHelper ordersDbHelper = new OrdersDbHelper(this);
        final SQLiteDatabase db = ordersDbHelper.getReadableDatabase();

        Fragment defaultFragment = new OrdersFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragmentcontainer, defaultFragment);
        fragmentTransaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_cookMenu) {
            Fragment cookMenuFragment = new CookMenuFragment();

            fragmentTransaction.replace(R.id.fragmentcontainer, cookMenuFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

        if (id == R.id.action_customerMenu) {
            Fragment customerMenuFragment = new CustomerMenuFragment();

            fragmentTransaction.replace(R.id.fragmentcontainer, customerMenuFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

        if (id == R.id.action_orders) {
            Fragment ordersFragment = new OrdersFragment();

            fragmentTransaction.replace(R.id.fragmentcontainer, ordersFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

        if (id == R.id.action_profile) {
            Fragment profileFragment = new ProfileFragment();

            fragmentTransaction.replace(R.id.fragmentcontainer, profileFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

        return super.onOptionsItemSelected(item);
    }

    public void goToItemFragment(int position){

        ItemFragment itemFragment = new ItemFragment();

        Bundle args = new Bundle();
        args.putInt(ItemFragment.ARG_POSITION, position);
        itemFragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragmentcontainer, itemFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();

    }
}
