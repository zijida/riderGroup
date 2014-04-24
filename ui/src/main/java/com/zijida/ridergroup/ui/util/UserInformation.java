package com.zijida.ridergroup.ui.util;

/**
 * Created by SHENJUN on 14-4-21.
 * Create in ${PROJECT_NAME}
 */
public class UserInformation {
    public String nickName;
    public String email;
    public String password;
    public String emoteImg;
    public String bikeImg;
    public String gender;
    public String age;

    public UserInformation()
    {
        nickName = "未知";
        gender = "男";
        email = "";
        password ="";
        emoteImg = "";
        bikeImg = "";
        age = "0";
    }

    public UserInformation(UserInformation rp)
    {
        nickName = rp.nickName;
        email = rp.email;
        password = rp.password;
        emoteImg = rp.emoteImg;
        bikeImg = rp.bikeImg;
        gender = rp.gender;
        age = rp.age;
    }
}
