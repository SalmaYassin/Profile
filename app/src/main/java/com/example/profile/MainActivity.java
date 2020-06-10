package com.example.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/* ProfileFragment headFragment = new ProfileFragment();
       FragmentManager fragmentManager = getSupportFragmentManager();
       fragmentManager.beginTransaction()
               .add(R.id.head_container , headFragment)
               .commit();*/

       HomeFragment fragment = new HomeFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.head_container , fragment)
                .commit();

    }
}
