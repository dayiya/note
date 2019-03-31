package com.didoumi.www.data.configuration;

import com.didoumi.www.data.entity.Permission;
import com.didoumi.www.data.entity.Role;
import com.didoumi.www.data.entity.User;
import com.didoumi.www.data.service.ShiroService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AuthRealm extends AuthorizingRealm {

    @Resource
    private ShiroService shiroService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        /**
         * 核心思想：
         * 获取当前用户信息
         * 因为user存放的是set<Role>，所以用for循环取出内容
         * 取出的role存放new 出来的List中
         * 利用for循环取出permission放入new出来的List中
         * 将刚才的list放入 new SimpleAuthorizationInfo() 中的role和permission中
         * 返回该对象
         */
        User user = (User)principals.fromRealm(this.getClass().getName()).iterator().next();
        List<String> permissionList = new ArrayList<>();
        List<String> roleNameList = new ArrayList<>();
        Set<Role> roleSet = user.getRoles();
        if (roleSet != null) {
            roleSet.stream().forEach(role -> {
                roleNameList.add(role.getRname());
                Set<Permission> permissionSet = role.getPermissions();
                if (permissionSet != null) {
                    permissionSet.stream().forEach(permission -> permissionList.add(permission.getName()));
                }
            });
        }
        //需要把角色和权限放入到info中
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //权限设定
        info.addStringPermissions(permissionList);
        //角色设定
        info.addRoles(roleNameList);

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        /**
         * 核心思想：
         * token中获取当前用户信息
         * 因为user存放的是set<Role>，所以用for循环取出内容
         * 取出用户名。用户名和密码是byte存放，可以考虑用new String转换。
         * 返回SimpleAuthenticationInfo对象。包括用户实体，密码，该对象名
         */
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)token;
        String username = usernamePasswordToken.getUsername();
        User user = shiroService.findByUsername(username);
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), this.getClass().getName());
        return simpleAuthenticationInfo;
    }
}
