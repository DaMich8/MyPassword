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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login_tab, container, false);

        EditText username = root.findViewById(R.id.lUsername);
        EditText password = root.findViewById(R.id.lPassword);
        TextView fPassword = root.findViewById(R.id.lFPassword);
        AppCompatButton login = root.findViewById(R.id.lLoginButton);

        username.setTranslationX(800);
        password.setTranslationX(800);
        fPassword.setTranslationX(800);
        login.setTranslationX(800);

        float alphaConstant = 0;
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
