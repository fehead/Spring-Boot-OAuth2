package im.fehead.resolver;

import static im.fehead.domain.enums.SocialType.FACEBOOK;
import static im.fehead.domain.enums.SocialType.GOOGLE;
import static im.fehead.domain.enums.SocialType.KAKAO;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import im.fehead.annotation.SocialUser;
import im.fehead.domain.User;
import im.fehead.domain.enums.SocialType;
import im.fehead.repository.UserRepository;

/**
 * Created by KimYJ on 2017-09-12.
 * @author KimYJ
 */
public class UserArgumentResolver implements HandlerMethodArgumentResolver {
	@Autowired
	private	UserRepository	userRepository;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterAnnotation(SocialUser.class) != null &&
				parameter.getParameterType().equals(User.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.
				currentRequestAttributes()).getRequest().getSession();
		User user = (User) session.getAttribute("user");
		return getUser(user, session);
	}

	private User getUser(User user, HttpSession session) {
		if(user != null)
			return user;
		OAuth2Authentication authentication = (OAuth2Authentication)
				SecurityContextHolder.getContext().getAuthentication();
		Map<String, Object> map = (HashMap<String, Object>)
				authentication.getUserAuthentication().getDetails();
		User convertUser = convertUser(String.valueOf(authentication.
				getAuthorities().toArray()[0]), map);
        user = userRepository.findByEmail(convertUser.getEmail());
        if (user == null) { user = userRepository.save(convertUser); }

        setRoleIfNotSame(user, authentication, map);
        session.setAttribute("user", user);

		return user;
	}
	
	private User convertUser(String authority, Map<String, Object> map) {
		if (FACEBOOK.isEquals(authority))
			return getModernUser(FACEBOOK, map);
		else if (GOOGLE.isEquals(authority))
			return getModernUser(GOOGLE, map);
		else if (KAKAO.isEquals(authority))
			return getKaKaoUser(map);
		return null;
	}
	
    private User getModernUser(SocialType socialType, Map<String, Object> map) {
        return User.builder()
                .name(String.valueOf(map.get("name")))
                .email(String.valueOf(map.get("email")))
                .pincipal(String.valueOf(map.get("id")))
                .socialType(socialType)
                .createdDate(LocalDateTime.now())
                .build();
    }
    
    private User getKaKaoUser(Map<String, Object> map) {
        @SuppressWarnings("unchecked")
		Map<String, String> propertyMap = (HashMap<String, String>) map.get("properties");
        return User.builder()
                .name(propertyMap.get("nickname"))
                .email(String.valueOf(map.get("kaccount_email")))
                .pincipal(String.valueOf(map.get("id")))
                .socialType(KAKAO)
                .createdDate(LocalDateTime.now())
                .build();
    }

    private void setRoleIfNotSame(User user, OAuth2Authentication authentication, Map<String, Object> map) {
        if(!authentication.getAuthorities().contains(new SimpleGrantedAuthority(user.getSocialType().getRoleType()))) {
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(map, "N/A", AuthorityUtils.createAuthorityList(user.getSocialType().getRoleType())));
        }
    }
}
