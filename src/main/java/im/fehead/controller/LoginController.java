package im.fehead.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import im.fehead.domain.User;
import im.fehead.domain.enums.SocialType;

@Controller
public class LoginController {
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	public String loginComplete(HttpSession session) {
		OAuth2Authentication authentication = (OAuth2Authentication)
				SecurityContextHolder.getContext().getAuthentication();
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (HashMap<String, String>)
				authentication.getUserAuthentication().getDetails();
		
		session.setAttribute("user", User.builder()
				.name(map.get("name"))
				.email(map.get("email"))
				.principal(map.get("id"))
				.socialType(SocialType.FACEBOOK)
				.createdDate(LocalDateTime.now())
				.build()
				);
		return "redirect:/board/list";
	}
}
