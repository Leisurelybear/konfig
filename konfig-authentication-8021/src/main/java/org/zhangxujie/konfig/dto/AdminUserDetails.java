/**
 * FileName: AdminUserDetails
 * Author:   jason
 * Date:     2021/3/10 17:16
 * Description:
 */
package org.zhangxujie.konfig.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.zhangxujie.konfig.model.Account;
import org.zhangxujie.konfig.model.Permission;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

//SpringSecurity需要的用户详情
@Data
@AllArgsConstructor//全参构造器
public class AdminUserDetails implements UserDetails {

    private Account account;
    private List<Permission> permissionList;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {


        List authorityList = null;

        if (permissionList != null){
            authorityList = permissionList.stream()
                    .filter(permission -> permission.getPermission() != null)
                    .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                    .collect(Collectors.toList());
        }

        //返回当前用户的权限
        return authorityList;
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return account.getIsDel().equals(0);
    }
}
