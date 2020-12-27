package com.singleo.loginsimpleauth.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class MyWebConfig implements WebMvcConfigurer {
    /**
     * 登录session key
     */
    public final static String SESSION_KEY = "stoken";

    @Bean
    public SecurityInterceptor getSecurityInterceptor() {
        return new SecurityInterceptor();
    }

    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());
        // 排除配置
        //addInterceptor.excludePathPatterns("/error");
        addInterceptor.excludePathPatterns("/testHello");
        addInterceptor.excludePathPatterns("/login");
        // 拦截配置
        addInterceptor.addPathPatterns("/**");
    }
    //拦截器 内容
    private class SecurityInterceptor extends HandlerInterceptorAdapter {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                throws Exception {
            HttpSession session = request.getSession();
            //登录验证
            System.out.println("前端token: "+request.getHeader("token"));

            String headerToken = request.getHeader("token");
            String attribute = (String)session.getAttribute(SESSION_KEY);
            System.out.println("sessionToken: "+attribute);

            if(headerToken == null ||attribute == null ) {
//                returnJson(response, JSON.toJSONString(ApiResultHandler.buildApiResult(401, "请求失败", "未登录或登录已过期，请先登录"), SerializerFeature.WriteMapNullValue));
                return false;
            }else if(!attribute.equals(headerToken)) {
//                returnJson(response, JSON.toJSONString(ApiResultHandler.buildApiResult(401, "请求失败", "未登录或登录已过期，请先登录"), SerializerFeature.WriteMapNullValue));
                return false;
            }else {
                return true;
            }
        }
        //返回json数据
        private void returnJson(HttpServletResponse response, String json) throws Exception{
            PrintWriter writer = null;
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=utf-8");
            try {
                writer = response.getWriter();
                writer.print(json);

            } catch (IOException e) {
            } finally {
                if (writer != null)
                    writer.close();
            }
        }
    }
}