package com.CR.examples.android.bhopaldarshan.Activity;

import android.os.Bundle;
import android.widget.Toast;

import com.CR.examples.android.bhopaldarshan.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.CR.examples.android.bhopaldarshan.Adapter.TabAdapter;
import com.CR.examples.android.bhopaldarshan.Fragment.EventFragment;
import com.CR.examples.android.bhopaldarshan.Fragment.HotelFragment;
import com.CR.examples.android.bhopaldarshan.Fragment.PlaceFragment;
import com.CR.examples.android.bhopaldarshan.Fragment.RestaurantFragment;
import com.CR.examples.android.bhopaldarshan.Fragment.ShopFragment;
import com.CR.examples.android.bhopaldarshan.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser user;


    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() !=null) {
            user = mAuth.getCurrentUser();
        }
        else{
            Toast.makeText(this, "Please login first!!", Toast.LENGTH_SHORT).show();
            finish();
        }
        // Fetching view IDs from resource
        ViewPager viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabs);

        // Registering TabLayout with FragmentManager
        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager());

        // Adding Fragment with help of TabLayout adapter
        tabAdapter.addFragment(new HotelFragment(), getString(R.string.hotels));
        tabAdapter.addFragment(new PlaceFragment(), getString(R.string.places));
        tabAdapter.addFragment(new RestaurantFragment(), getString(R.string.restaurants));
        tabAdapter.addFragment(new ShopFragment(), getString(R.string.shoppings));
        tabAdapter.addFragment(new EventFragment(), getString(R.string.events));

        toolbar = findViewById(R.id.toolbar);



        DatabaseReference datareference= FirebaseDatabase.getInstance().getReference("Registered Users/"+user.getUid());
        datareference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user1 = snapshot.getValue(User.class);
                toolbar.setTitle("Welcome to Bhopal Darshan "+user1.getName());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //Setting the Fragments with ViewPager
        viewPager.setAdapter(tabAdapter);

        //Setting up TabLayout with ViewPager
        tabLayout.setupWithViewPager(viewPager);
    }

    public static class User {

        public String name;
        public String email;

        public User() {

        }

        public User(String email, String name) {
            this.name = name;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }
    }
}