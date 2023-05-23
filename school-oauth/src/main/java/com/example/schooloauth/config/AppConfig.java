package com.example.schooloauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableResourceServer
@EnableAuthorizationServer
public class AppConfig extends AuthorizationServerConfigurerAdapter {

    @Value("${oauth.client.name}")
    private String clientName;

    @Value("${oauth.key.secret}")
    private String clientSecret;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration-miliseg}")
    private String jwtExpirationMiliseg;

    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;

    @Autowired
    private JwtTokenStore tokenStore;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        int expirationSeconds = Integer.parseInt(jwtExpirationMiliseg) / 1000;
        clients.inMemory()
                .withClient(clientName)
                .secret(passwordEncoder.encode(clientSecret))
                .scopes("read", "write")
                .authorizedGrantTypes("password")
                .accessTokenValiditySeconds(expirationSeconds);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(List.of(new CustomTokenEnhancer(), accessTokenConverter()));

        endpoints.authenticationManager(authenticationManager)
                .tokenStore(tokenStore)
                .tokenEnhancer(tokenEnhancerChain)
                .accessTokenConverter(accessTokenConverter);
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
        tokenConverter.setSigningKey(jwtSecret);
        return tokenConverter;
    }

    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public ResourceServerTokenServices myUserInfoTokenServices() {
        return new UserInfoTokenServicesCustom(clientName);
    }
}
