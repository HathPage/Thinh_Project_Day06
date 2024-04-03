package com.example.thinh_project_day06.presenter;

import com.example.thinh_project_day06.interfaces.IAuthentication;
import com.example.thinh_project_day06.interfaces.IAuthenticationView;
import com.example.thinh_project_day06.models.UserDatabase;
import com.example.thinh_project_day06.models.UserModel;

public class Authentication implements IAuthentication {
    private IAuthenticationView iAuthenticationView;
    private UserDatabase userDatabase;

    public Authentication(IAuthenticationView iAuthenticationView){
        this.iAuthenticationView = iAuthenticationView;
        userDatabase = UserDatabase.getInstances();
        userDatabase.setIAuthentication(this);
    }

    public void login(String userName, String password){
        userDatabase.checkAuthentication(userName, password);
    }
    @Override
    public void addUser(UserModel model) {
        userDatabase.register(model);
    }

    @Override
    public boolean checkAuthentication(String userName, String password) {
        return false;
    }

    @Override
    public void onLoginSuccess(String key, String value) {
        iAuthenticationView.onLoginSuccess(key, value);
    }

    @Override
    public void onLoginError(String error) {
        iAuthenticationView.onLoginError(error);
    }

    @Override
    public void onRegisterSuccess(UserModel model) {
        iAuthenticationView.onRegisterSuccess(model);
    }

    @Override
    public void onRegisterError(String error) {
        iAuthenticationView.onRegisterError(error);
    }
}
