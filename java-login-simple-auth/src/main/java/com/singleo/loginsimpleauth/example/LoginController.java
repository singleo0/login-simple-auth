package com.singleo.loginsimpleauth.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

import static com.singleo.loginsimpleauth.util.MyWebConfig.SESSION_KEY;


@RestController
public class LoginController {

//    @Autowired
//    private LoginServiceImpl loginService;

//    @PostMapping("/login")
//    public ApiResult login(@RequestBody Login login, HttpSession session) {
//
//        Integer username = login.getUsername();
//        String password = login.getPassword();
//        Admin adminRes = loginService.adminLogin(username, password);
//
//        if (adminRes != null) {
//            LoginMsg loginMsg = new LoginMsg();
//            loginMsg.setUserId(adminRes.getAdminId());
//            loginMsg.setUserName(adminRes.getAdminName());
//            loginMsg.setUserRole(adminRes.getRole());
//            String token = adminRes.getAdminId()+" "+adminRes.getAdminName()+" "+TokenProcessor.getInstance().makeToken();
//            session.setAttribute(SESSION_KEY, token);
//            loginMsg.setUserToken(token);
//            return ApiResultHandler.buildApiResult(200, "请求成功", loginMsg);
//        }
//
//        return ApiResultHandler.buildApiResult(400, "请求失败", null);
//    }
}
