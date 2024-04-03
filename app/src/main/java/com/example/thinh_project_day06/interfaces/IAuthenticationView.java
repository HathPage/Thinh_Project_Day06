package com.example.thinh_project_day06.interfaces;

import com.example.thinh_project_day06.models.UserModel;

public interface IAuthenticationView{
        void register(UserModel model);
        void login(String userName, String password);
        void onLoginSuccess(String key, String value);
        void onLoginError(String error);
        void onRegisterSuccess(UserModel model);
        void onRegisterError(String error);
}
