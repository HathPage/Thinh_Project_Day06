package com.example.thinh_project_day06.models;

import com.example.thinh_project_day06.interfaces.IAuthentication;
import com.example.thinh_project_day06.interfaces.IAuthenticationView;
import com.example.thinh_project_day06.utils.PrefManager;

import java.util.ArrayList;

public class UserDatabase {
    private static UserDatabase instances;
    private ArrayList<UserModel> mListUser = new ArrayList<>();
    private IAuthentication iAuthentication;

    public void setIAuthentication(IAuthentication iAuthentication) {
        this.iAuthentication = iAuthentication;
    }

    public static UserDatabase getInstances(){
        if(instances == null){
            instances = new UserDatabase();
        }
        return instances;
    }
    public boolean isUserExisted(String userName){
        return PrefManager.getAccount(userName) != null;
    }
    public void addUser (UserModel userModel){

    }

    public void checkAuthentication(String username, String password){
        if(isUserExisted(username)){
                if(PrefManager.getAccount(username).equals(password)) {
                    iAuthentication.onLoginSuccess(username, password);
                }else {
                    iAuthentication.onLoginError("Sai tên đăng nhập hoặc mật khẩu");
                }
        }else{
        iAuthentication.onLoginError("Tên đăng nhập không tồn tại");
    }
    }
    public void register(UserModel model){
        if(isUserExisted(model.getUsername())){
            iAuthentication.onRegisterError("Tài khoản đã tồn tại!");
        }else {
            mListUser.add(model);
            PrefManager.saveAccount(model.getUsername(), model.getPassword());
            iAuthentication.onRegisterSuccess(model);
        }
    }
}
