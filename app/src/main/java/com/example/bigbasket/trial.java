package com.example.bigbasket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class trial extends AppCompatActivity {
private BottomNavigationView bottomNavigationView;
private  HomeFragment homeFragment;
private  SearchFragment searchFragment;
private  CartFragment cartFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_trial );
        bottomNavigationView=findViewById( R.id.bottombar );
        homeFragment=new HomeFragment();
        searchFragment=new SearchFragment();
        cartFragment=new CartFragment();



      bottomNavigationView.setOnNavigationItemSelectedListener( new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId())
                {
                    case  R.id.home:
                        setFragment( homeFragment );
                        return true;

                    case R.id.search:
setFragment( searchFragment );
                        return true;

                    case R.id.cart:
setFragment( cartFragment );
                        return true;

                        default:
                            return false;
                }

            }
        } );
    }
  /*  public  void  setFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace( R.id.mainframe ,fragment);
        fragmentTransaction.commit();
    }
*/

        void setFragment(Fragment fragment) {
            // Create transactionns
            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

            // Hide all of the fragments
            for (Fragment fragments : getSupportFragmentManager().getFragments()) {
                transaction.hide(fragments);
            }

            if (fragment.isAdded()) {
                // When fragment was previously added - show it
                transaction.show(fragment);
            } else {
                // When fragment is adding first time - add it
                transaction.add(R.id.mainframe, fragment);
            }

            transaction.commit();
        }
}
