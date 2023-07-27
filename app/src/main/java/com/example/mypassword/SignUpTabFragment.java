package com.example.mypassword;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.widget.AppCompatButton;

import com.example.mypassword.database.Database;
import com.example.mypassword.database.User;
import com.example.mypassword.validation.EnteredFieldsChecking;

public class SignUpTabFragment extends Fragment {

    private EditText username, email, password, rPassword;
    private Button registerNewUser;
    private Database database;
    private User user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_sign_up_tab, container, false);

        username = root.findViewById(R.id.sUsername);
        email = root.findViewById(R.id.sEmail);
        password = root.findViewById(R.id.sPassword);
        rPassword = root.findViewById(R.id.sRepeatPassword);
        registerNewUser = root.findViewById(R.id.sSignUpButton);

        database = new Database(getContext());
        user = new User();

        registerNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postDataToSQLite();
            }
        });

        return root;
    }

    public void postDataToSQLite() {
        if (!checkAllEditTextNotEmpty()) {
            return;
        }

        String emailValue = email.getText().toString().trim();
        String passwordValue = password.getText().toString().trim();
        String repeatPasswordValue = rPassword.getText().toString().trim();
        String usernameValue = username.getText().toString().trim();

        if (!EnteredFieldsChecking.emailIsValid(emailValue)) {
            setError(email, getString(R.string.email_err_msg));
            return;
        }

        if (!passwordValue.equals(repeatPasswordValue)) {
            setError(password, getString(R.string.passwordNotMatch_err_msg));
            setError(rPassword, getString(R.string.passwordNotMatch_err_msg));
            return;
        }

        if (database.checkUserName(usernameValue)) {
            setError(username, getString(R.string.usernameExists_err_msg));
            return;
        }

        if (database.checkUserEmail(emailValue)) {
            setError(email, getString(R.string.emailExists_err_msg));
            return;
        }

        // All validations passed, proceed with adding the user
        user.setUsername(usernameValue);
        user.setEmail(emailValue);
        user.setPassword(passwordValue);
        database.addUser(user);
    }

    private boolean checkAllEditTextNotEmpty() {
        boolean check = true;
        if (isEmpty(username)) {
            setError(username, getString(R.string.empty_err_msg));
            check = false;
        }
        if (isEmpty(email)) {
            setError(email, getString(R.string.empty_err_msg));
            check = false;
        }
        if (isEmpty(password)) {
            setError(password, getString(R.string.empty_err_msg));
            check = false;
        }
        if (isEmpty(rPassword)) {
            setError(rPassword, getString(R.string.empty_err_msg));
            check = false;
        }
        return check;
    }

    private boolean isEmpty(EditText editText) {
        return editText.getText().toString().trim().isEmpty();
    }

    private void setError(EditText editText, String errorMessage) {
        editText.setError(errorMessage);
    }
}
