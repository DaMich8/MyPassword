package com.example.mypassword;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


public class LoginTabFragment extends Fragment {
    EditText username, password;
    TextView fPassword;
    AppCompatButton login;
    float alphaConstant=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_login_tab, container, false);

        username = root.findViewById(R.id.lUsername);
        password = root.findViewById(R.id.lPassword);
        fPassword = root.findViewById(R.id.lFPassword);
        login = root.findViewById(R.id.lLoginButton);

        username.setTranslationX(800);
        password.setTranslationX(800);
        fPassword.setTranslationX(800);
        login.setTranslationX(800);

        username.setAlpha(alphaConstant);
        password.setAlpha(alphaConstant);
        fPassword.setAlpha(alphaConstant);
        login.setAlpha(alphaConstant);

        username.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        password.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        fPassword.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();

        return root;
    }


}