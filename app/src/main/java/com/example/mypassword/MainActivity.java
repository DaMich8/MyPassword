package com.example.mypassword;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FloatingActionButton facebook, google;

    private GoogleSignInClient gsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        tabLayout = findViewById(R.id.lTabLayout);
        viewPager = findViewById(R.id.lVIewPager);
        facebook = findViewById(R.id.lFloatingButtonFacebook);
        google = findViewById(R.id.lFloatingButtonGoogle);

        setupViewPager();

        setupAnimation();

        setupTabLayout();

        configureGoogleSignIn();

        google.setOnClickListener(view -> signIn());
    }

    private void setupViewPager() {
        tabLayout.addTab(tabLayout.newTab().setText("Login"));
        tabLayout.addTab(tabLayout.newTab().setText("Sign Up"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final LoginAdapter loginAdapter = new LoginAdapter(getSupportFragmentManager(), this, tabLayout.getTabCount());
        viewPager.setAdapter(loginAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    private void setupAnimation() {
        facebook.setTranslationY(300);
        google.setTranslationY(300);
        tabLayout.setTranslationY(300);

        float alphaConstant = 0;
        facebook.setAlpha(alphaConstant);
        google.setAlpha(alphaConstant);
        tabLayout.setAlpha(alphaConstant);
        viewPager.setAlpha(alphaConstant);

        facebook.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        google.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(600).start();
        tabLayout.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(800).start();
        viewPager.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(1000).start();
    }

    private void setupTabLayout() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void configureGoogleSignIn() {
        // Google signup
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);
    }

    private void signIn() {
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent, 1000);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                navigateToPasswordActivity();
            } catch (ApiException e) {
                Log.e("GoogleSignIn", "Error signing in: " + e.getStatusCode(), e);
                Toast.makeText(MainActivity.this, "Google Sign-In Error: " + e.getStatusCode(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void navigateToPasswordActivity() {
        Intent intent = new Intent(MainActivity.this, PasswordActivity.class);
        startActivity(intent);
        this.finish();
    }
}
