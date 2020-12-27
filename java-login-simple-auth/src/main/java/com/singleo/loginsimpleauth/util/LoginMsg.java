package com.singleo.loginsimpleauth.util;

import lombok.Data;

@Data
public class LoginMsg {
    private Integer userId;
    private String userName;
    private String userToken;
    private String userRole;

}
