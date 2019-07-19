package com.sh.shdemo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsUtils;

import java.io.PrintWriter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {//WebSecurityEnablerConfiguration
    @Bean
    UserDetailsService customUserService() {
        return new CustomUserService();
    }
    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService()).passwordEncoder(bCryptPasswordEncoder());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry
                = http.authorizeRequests();
        registry.requestMatchers(CorsUtils::isPreFlightRequest).permitAll();*/
        http.httpBasic()//表单验证，为了简单就不搞token验证了
                //.loginProcessingUrl("/login")//处理登录请求的api
                //因为表单验证方式默认是跳转页面，而我们前后分离不需要后端处理跳转
                //所以自定义一个登录成功处理器，它只需要告诉我们登录结果就可以了
                //.successHandler(successHandler())//登录成功处理器
                //.failureHandler(failureHandler())//登录失败处理器
                //.usernameParameter("username")
                //.passwordParameter("password")
                //.permitAll()  //表单登录，permitAll()表示这个不需要验证 登录页面，登录失败页面
                .and()
                .logout()
                .logoutSuccessHandler(logoutSuccessHandler())//登出处理器
                .logoutUrl("/logout")//登出api
                .permitAll()
                .and()
                .authorizeRequests()//接下来进行鉴权拦截，有相应权限的才可以从接口中得到数据
                .antMatchers("/").access("hasRole('USER')")
                .antMatchers("/user/**").access("hasRole('USER')")
                .antMatchers("/AdminPage/**").access("hasRole('ADMIN')")
                .and()
                .cors()//跨域设置
                .and()
                .csrf()
                .disable()/*csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())*/; //关掉csrf防御 最后的disable
    }
    private AuthenticationSuccessHandler successHandler() {
        return (request, response, authentication) -> {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write("登录success");
            out.write("<a href='/'>"+ "首页" +"</a>");
            out.flush();
            out.close();
        };
    }
    /**
     *自定义登录失败处理器
     */
    private AuthenticationFailureHandler failureHandler() {
        return (request, response, authentication) -> {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write("登录失败啦！！！！！！！！！！！！！！！！！！！！！");
            out.flush();
            out.close();
        };
    }
    /**
     *自定义登出成功处理器
     */
    private LogoutSuccessHandler logoutSuccessHandler(){
        return (request, response, authentication) -> {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null){
                new SecurityContextLogoutHandler().logout(request, response, auth);//清除登录认证信息
            }
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write("注销成功");
            out.flush();
            out.close();
        };
    }
}
