/**
 * FileName: SecurityConfig
 * Author:   jason
 * Date:     2021/3/10 17:01
 * Description:
 */
package org.zhangxujie.konfig.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.zhangxujie.konfig.dto.AdminUserDetails;
import org.zhangxujie.konfig.filter.JwtAuthenticationTokenFilter;
import org.zhangxujie.konfig.model.Account;
import org.zhangxujie.konfig.model.Permission;
import org.zhangxujie.konfig.service.AccountService;
import org.zhangxujie.konfig.service.GroupUserService;
import org.zhangxujie.konfig.service.PermissionService;

import javax.annotation.Resource;
import java.util.List;

//Spring Security 配置
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    AccountService accountService;

    @Resource
    PermissionService permissionService;

    @Resource
    GroupUserService groupUserService;


    @Resource
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    @Resource
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()//不需要csrf
                .sessionManagement()//基於token，不需要session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .rememberMe()
                .and()
                .authorizeRequests()
                .antMatchers(
                        HttpMethod.GET,// 允许对于网站静态资源的无授权访问
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/swagger-resources/**",
                        "/v2/api-docs/**"
                ).permitAll()//允許訪問
                .antMatchers("/admin/login", "/admin/register").permitAll()// 对登录注册要允许匿名访问
                .antMatchers("/**").permitAll()//測試時候允許所有訪問
                .anyRequest().authenticated();//除了上面的请求，其他全部需要认证


        // 禁用缓存
        httpSecurity.headers().cacheControl();
        // 添加JWT filter
        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        //添加自定义未授权和未登录结果返回
        httpSecurity.exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthenticationEntryPoint);

    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return username -> {
            Account account = accountService.getAdminByUsername(username);
            if (account != null) {
                List<Permission> permissionList = null;

                //TODO:先查出用户所在的Group，再同时查询用户单独的权限和Group的权限
                //1、对于组权限的确定：查出当前用户的所有所在组的id，然后查所有拥有的权限
                List<Integer> groupIds = groupUserService.getGroupIdsByAccountId(account.getId());//

                //2、对于用户个人权限的确定：查出当前用户单独分配的权限
                permissionList = permissionService.getPermissionList(account.getId(), groupIds);
                return new AdminUserDetails(account, permissionList);
            }
            throw new UsernameNotFoundException("用户名或密码错误");
        };
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
