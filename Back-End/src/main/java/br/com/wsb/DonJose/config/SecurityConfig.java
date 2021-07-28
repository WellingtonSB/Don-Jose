package br.com.wsb.DonJose.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.wsb.DonJose.security.JWTUtil;



@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private Environment env;
	
	@Autowired
	private JWTUtil jwtUtil;
	

	@Autowired
	private UserDetailsService userDetailsService; 
	
	
	private static final String[] PUBLIC_MATCHERS = {
			"/h2-console/**"
	};

	private static final String[] PUBLIC_MATCHERS_GET = {
			"/produtos/**",
			"/categorias/**"
	};

	private static final String[] PUBLIC_MATCHERS_POST = {
			"/clientes/**",
			"/auth/forgot/**"
	};
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//se eu estiver com o perfil de testes libera o acesso ao h2
		if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
            http.headers().frameOptions().disable();
        }
		
		//desabilita protecao csrf, nao usa stateless
		http.cors().and().csrf().disable();
		//autorizar toda requisição que venha no metodo Public_Matchers. O restante solicita autorização
	http.authorizeRequests().antMatchers(HttpMethod.GET,PUBLIC_MATCHERS_GET).permitAll().anyRequest().authenticated();
	
	//garante que o back nao crirá uma sessao de usuario
	http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Bean
	CorsConfigurationSource CorsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		//permitir acesso de outras fontes usando configurações basicas
		source.registerCorsConfiguration("/**",new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}
	
	//responsavel por incripitar a senha
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
