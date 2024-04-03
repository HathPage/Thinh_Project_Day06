package com.example.thinh_project_day06.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thinh_project_day06.R;
import com.example.thinh_project_day06.constant.Constant;
import com.example.thinh_project_day06.interfaces.IAuthentication;
import com.example.thinh_project_day06.interfaces.IAuthenticationView;
import com.example.thinh_project_day06.models.UserModel;
import com.example.thinh_project_day06.presenter.Authentication;
import com.example.thinh_project_day06.utils.PrefManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, IAuthenticationView {

    EditText edtUserName;
    EditText edtPassword;
    Button btnLogin;
    TextView btnRegister;

    private Authentication authentication;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        authentication = new Authentication(this);
    }

    private void initView(){
        edtUserName = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin= findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        edtUserName.setText(getSavedUserName());
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                String userName = edtUserName.getText().toString();
                String password = edtPassword.getText().toString();
                saveUserName(userName);
                login(userName, password);
                break;
            case R.id.btn_register:
                String userName2 = edtUserName.getText().toString();
                String password2 = edtPassword.getText().toString();
                UserModel userModel = new UserModel();
                userModel.setUsername(userName2);
                userModel.setPassword(password2);
                userModel.setAddress("HN");
                userModel.setAge(22);
                register(userModel);
                break;
        }
    }

    private void saveUserName(String userName) {
//        SharedPreferences sharedPreferences = getSharedPreferences("thinh_project_day06", MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("userName", userName);
//        editor.apply();

        PrefManager.saveString(Constant.USER_NAME_KEY, userName);
    }
    private void saveAccount(String userName, String password) {
        PrefManager.saveAccount(userName, password);
    }
    private String getSavedUserName(){
        return PrefManager.getString(Constant.USER_NAME_KEY);
    }

    @Override
    public void register(UserModel model) {
        authentication.addUser(model);
    }

    @Override
    public void login(String userName, String password) {
        authentication.login(userName,password);
    }

    public void onLoginSuccess(String key, String value){
        Toast.makeText(this, "Hello "+key, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MainActivity.this, HomeActivity.class));
    }

    @Override
    public void onLoginError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRegisterSuccess(UserModel model) {
        Toast.makeText(this, "Hello "+model.getUsername(), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MainActivity.this, HomeActivity.class));
    }

    @Override
    public void onRegisterError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}