//package com.example.rideswebsocket.config;
//
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.*;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.apache.shiro.util.ByteSource;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//
//import java.util.HashSet;
//import java.util.Set;
//
//
//public class CustomRealm extends AuthorizingRealm {
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        String username = (String) SecurityUtils.getSubject().getPrincipal();
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        Set<String> stringSet = new HashSet<>();
//        stringSet.add("user:show");
//        stringSet.add("user:admin");
//        // 添加角色
////        info.addRoles(null);
//        //添加权限
//        info.setStringPermissions(stringSet);
//        return info;
//    }
//
//    /**
//     * 这里可以注入userService,为了方便演示，我就写死了帐号了密码
//     * private UserService userService;
//     * <p>
//     * 获取即将需要认证的信息
//     */
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//        System.out.println("-------身份认证方法--------");
//        String username = String.valueOf(token.getPrincipal());
//        String password = new String((char[]) token.getCredentials());
//        System.out.println(username+"-------------"+password);
//        boolean isAuthented = true;
//        if (isAuthented) {
//            return new SimpleAuthenticationInfo(
//                    username, password, getName());
//        } else {
//            throw new AuthenticationException("1");
//        }
//    }
//}
