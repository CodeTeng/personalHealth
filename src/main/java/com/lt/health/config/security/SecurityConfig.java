package com.lt.health.config.security;

import com.lt.health.config.security.contents.SecurityContents;
import com.lt.health.config.security.filter.JwtAuthenticationFilter;
import com.lt.health.config.security.handler.JwtAccessDeniedHandler;
import com.lt.health.config.security.handler.JwtAuthenticationEntryPoint;
import com.lt.health.config.security.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * @description: security的配置
 * @author: 狂小腾
 * @date: 2022/3/22 19:35
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private JwtAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private JwtAuthenticationFilter authenticationFilter;

    /**
     * 一般用于配置白名单
     * 白名单：可以没有权限也可以访问的资源
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .mvcMatchers(SecurityContents.WHITE_LIST);
    }

    /**
     * Security的核心配置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //1. 使用jwt，首先关闭跨域攻击
        http.csrf().disable();
        //2. 关闭session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //3. 请求都需要进行认证之后才能访问，除白名单以外的资源
        http.authorizeRequests().anyRequest().authenticated();
        //4. 关闭缓存
        http.headers().cacheControl();
        //5. token过滤器，校验token
        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        //6. 没有登录、没有权限访问资源自定义返回结果
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).accessDeniedHandler(accessDeniedHandler);
    }

    /**
     * 自定义登录逻辑的配置
     * 也即是配置到security中进行认证
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    /**
//     * 重写WebSecurityConfigurerAdapter下的userDetailsService
//     * 实现自定义的登录
//     */
//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
//        return username -> {
//            // 1.根据用户名从数据库中查询该用户
//            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//            queryWrapper.like("username", username);
//            User user = userService.getOne(queryWrapper);
//            // 2.设置查询用户的角色
//            LoginUser loginUser = new LoginUser();
//            if (user != null) {
//                // 3.根据用户表和角色表的关联表中根据用户id查询该用户id对应的角色id
//                List<Integer> roleIds = usersRolesService.findRoleIdsByUserId(user.getId());
//                // 4.根据角色id在角色表中查询出所有的角色
//                List<Role> roles = roleService.findRoleByRoleIds(roleIds);
//                // 5.根据角色表和菜单权限表的管理表中根据角色id的集合查询出所有的菜单id集合
//                List<Integer> menuIds = rolesMenusService.findMenuIdsByRoleIds(roleIds);
//                // 6.根据菜单权限id在菜单表中查询出所有的菜单
//                List<Menu> menus = menuService.findMenuByMenuIds(menuIds);
////                TODO 设置子菜单
//                List<Menu> allMenus = menuService.list();
//                List<Menu> menuChildren = new ArrayList<>();
//                for (Menu menu : allMenus) {
//                    Integer id = menu.getId();
//                    // 查找子菜单
//                    menuChildren = menuService.findChildrenMenuByMenuIds(id);
//                }
//                for (Menu menu : menus) {
//                    // 设置子菜单
//                    menu.setChildren(menuChildren);
//                }
////                 同理查询权限
//                List<Integer> permissionIds = rolesPremissionsService.findPremissionIdsByRoleIds(roleIds);
//                List<Permission> permissions = permissionService.findPermissionByPermissionIds(permissionIds);
//                for (Role role : roles) {
//                    role.setMenus(menus);
//                    role.setPermissions(permissions);
//                }
//                // 7.用户设置角色
//                user.setRoles(roles);
//                // 8.实际注用户组合用户
//                loginUser.setUser(user);
//                return loginUser;
//            }
//            throw new UsernameNotFoundException(MessageConstant.LOGIN_USERNAME_PASSWORD_FAIL);
//        };
//    }
}
