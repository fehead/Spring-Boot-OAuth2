package im.fehead.oauth;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

import im.fehead.domain.enums.SocialType;

public class UserTokenService implements UserInfoTokenServices {

	public UserTokenService(ClientResources client, SocialType socialType) {
		super(client.getResource().getUserInfoUri(),
				client.getClient().getClientId());
		
	}

	@Override
	public OAuth2Authentication loadAuthentication(String accessToken)
			throws AuthenticationException, InvalidTokenException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OAuth2AccessToken readAccessToken(String accessToken) {
		// TODO Auto-generated method stub
		return null;
	}

}
