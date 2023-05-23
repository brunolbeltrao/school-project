package com.example.schooloauth.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.FixedAuthoritiesExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.FixedPrincipalExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class UserInfoTokenServicesCustom implements ResourceServerTokenServices {


    private String clientId;

    private AuthoritiesExtractor authoritiesExtractor = new FixedAuthoritiesExtractor();

    private PrincipalExtractor principalExtractor = new FixedPrincipalExtractor();


    public UserInfoTokenServicesCustom(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public OAuth2Authentication loadAuthentication(String accessToken) throws AuthenticationException, InvalidTokenException {
        Map<String, Object> map = new HashMap<>();

        return extractAuthentication(map);
    }

    private OAuth2Authentication extractAuthentication(Map<String, Object> map) {
        Object principal = getPrincipal(map);
        List<GrantedAuthority> authorities = this.authoritiesExtractor.extractAuthorities(map);
        OAuth2Request request = new OAuth2Request(null, this.clientId, null, true, null, null, null, null, null);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(principal, "N/A",
                authorities);
        token.setDetails(map);
        return new OAuth2Authentication(request, token);
    }

    protected Object getPrincipal(Map<String, Object> map) {
        Object principal = this.principalExtractor.extractPrincipal(map);
        return (principal == null ? "unknown" : principal);
    }

    @Override
    public OAuth2AccessToken readAccessToken(String s) {
        throw new UnsupportedOperationException("Not supported: read access token");
    }
}
