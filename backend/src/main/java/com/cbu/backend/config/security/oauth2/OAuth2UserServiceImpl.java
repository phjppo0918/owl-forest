package com.cbu.backend.config.security.oauth2;

import com.cbu.backend.config.security.oauth2.attributemapper.AttributeMapper;
import com.cbu.backend.authaccount.entity.AuthAccount;
import com.cbu.backend.authaccount.entity.AuthProvider;
import com.cbu.backend.authaccount.mapper.AuthAccountMapper;
import com.cbu.backend.authaccount.service.AuthAccountService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * User 인증 후 계정이 있으면 그대로 불러오고, 없으면 신규 생성하여 유저를 불러옴
 *
 * @author Hyeonjun Park
 */
@Service
@RequiredArgsConstructor
public class OAuth2UserServiceImpl extends DefaultOAuth2UserService {
    private final AttributeMapper attributeMapper;
    private final AuthAccountService authAccountService;
    private final AuthAccountMapper authAccountMapper;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        AuthProvider authProvider =
                AuthProvider.valueOf(
                        userRequest.getClientRegistration().getClientName().toUpperCase());
        OAuth2User oAuth2User = super.loadUser(userRequest);
        OAuth2Request oAuth2Request =
                attributeMapper.mapToUser(authProvider, oAuth2User.getAttributes());
        authAccountService.createIfFirst(oAuth2Request);
        AuthAccount user = authAccountService.findByAccountId(oAuth2Request.getAccountId());
        Map<String, Object> userAttributes = authAccountMapper.mapToAttributeMap(user);

        return new DefaultOAuth2User(user.getRole(), userAttributes, "id");
    }
}
