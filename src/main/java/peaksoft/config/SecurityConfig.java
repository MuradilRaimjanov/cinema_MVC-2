//package peaksoft.config;
//
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        User.UserBuilder user = User.withDefaultPasswordEncoder();
//
//        auth.inMemoryAuthentication()
//                .withUser(user
//                        .username("murad")
//                        .password("murad")
//                        .roles("NEO"))
//                .withUser(user
//                        .username("user")
//                        .password("user")
//                        .roles("USER")
//                );
//
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/").hasAnyRole("NEO", "USER")
//                .antMatchers("/movie/find_all").hasAnyRole("USER","NEO")
//                .antMatchers("/cinema/find_all").hasRole("NEO")
//                .and()
//                .formLogin().permitAll();
//    }
//}
