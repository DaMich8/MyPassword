package com.example.mypassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class PasswordActivity extends AppCompatActivity {

    private GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        TextView nameTextView = findViewById(R.id.pName);
        TextView emailTextView = findViewById(R.id.pEmail);
        Button signOutButton = findViewById(R.id.pSignOut);

        configureGoogleSignIn();

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            String personName = account.getDisplayName();
            String personEmail = account.getEmail();
            nameTextView.setText(personName);
            emailTextView.setText(personEmail);
        }

        signOutButton.setOnClickListener(v -> signOut());
    }

    private void configureGoogleSignIn() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void signOut() {
        googleSignInClient.signOut().addOnCompleteListener(task -> {
            finish();
            startActivity(new Intent(PasswordActivity.this, MainActivity.class));
        });
    }
}
