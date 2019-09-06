package im.fehead.oauth;

import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import im.fehead.domain.enums.SocialType;

public class UserTokenService extends UserInfoTokenServices {

	public UserTokenService(ClientResources client, SocialType socialType) {
		super(client.getResource().getUserInfoUri(),
				client.getClient().getClientId());
		setAuthoritiesExtractor(new OAuth2AuthoritiesExtractor(socialType));
	}

	public static class OAuth2AuthoritiesExtractor implements AuthoritiesExtractor {
		private String socialType;

		public OAuth2AuthoritiesExtractor(SocialType socialType) {
			this.socialType = socialType.getRoleType();
		}

		@Override
		public List<GrantedAuthority> extractAuthorities(Map<String, Object> map) {
			return AuthorityUtils.createAuthorityList(this.socialType);
		}
	}

}
