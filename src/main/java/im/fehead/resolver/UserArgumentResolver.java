package im.fehead.resolver;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
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

public class UserArgumentResolver implements HandlerMethodArgumentResolver {

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
		Map<String, String> map = (HashMap<String, String>)
				authentication.getUserAuthentication().getDetails();
		User convertUser = convertUser(String.valueOf(authentication.
				getAuthorities().toArray()[0]), map);
		return null;
	}

}
