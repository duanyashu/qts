package com.qts.realm;

import com.qts.dao.UserMapper;
import com.qts.pojo.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description: shiro realm
 * @author: duanyashu
 * @time: 2020-07-02 15:27
 */
public class CostomRelam extends AuthorizingRealm {

    @Autowired
    UserMapper userMapper;

    /**
     * 认证方法
     * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        if (username==null){
            throw  new UnsupportedTokenException("token不能为空");
        }
        //查询用户信息
        User user = userMapper.selectByUsername(username);
        if (user==null){
            return  null;
        }
        //判断账号是否被锁定
        if (user.getLocked()){
            throw new LockedAccountException("账号被锁定");
        }
        AuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword().toCharArray(), ByteSource.Util.bytes("tuoda"),getName());
        return info;
    }

    /**
     * 授权方法
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

}
