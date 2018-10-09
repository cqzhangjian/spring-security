package org.zj.springboot.springsecurity.demo.springbootspringsecurityhello.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.zj.springboot.springsecurity.demo.springbootspringsecurityhello.service.CustomUserDetailsService;

@EnableWebSecurity
@EnableJpaRepositories(basePackages= {"org.zj.springboot.springsecurity.demo.springbootspringsecurityhello.repository"})
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	
	@Autowired
	CustomUserDetailsService userDetailsService;
	
	/**
	 * 自定义 url 访问规则
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/css/**","/js/**","/fonts/**","/index")
					.permitAll() 
				.antMatchers("/user/**").hasAnyRole("ADMIN")
				.and()
			.formLogin()  //基于表单登录
				.loginPage("/login")
				.defaultSuccessUrl("/index")
				.loginProcessingUrl("/authentication/login")
				.usernameParameter("username")
				.passwordParameter("password")
				.failureUrl("/login-error")
				.permitAll(); //自定义登陆页面
				
		http.csrf().disable();
	}
	
	/**
	 * 定义认证信息管理方式
	 */
	@Autowired
	protected void configGlobleSecurity(AuthenticationManagerBuilder auth) throws Exception {
		/*auth
			.inMemoryAuthentication() // 认证信息储存内存
				.withUser("zhangjian").password("{noop}1").roles("ADMIN");*/
		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(getBCryptPasswordEncoder());
	}

	@Bean
	public  PasswordEncoder getBCryptPasswordEncoder() {
		
		  return new BCryptPasswordEncoder();
	}
}
