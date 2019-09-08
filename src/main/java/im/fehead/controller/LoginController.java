package im.fehead.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import im.fehead.domain.User;

@Controller
public class LoginController {
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/{facebook|google|kakao}/complete")
	public String loginComplete(@SocialUser User user) {
		return "redirect:/board/list";
	}
}
