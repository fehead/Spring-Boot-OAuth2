package im.fehead;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import im.fehead.domain.User;
import im.fehead.resolver.UserArgumentResolver;

@SpringBootApplication
public class SpringBootOAuth2Application extends WebMvcConfigurerAdapter{

	public static void main(String[] args) {
		SpringApplication.run(SpringBootOAuth2Application.class, args);
	}
	
	@Autowired
	private	UserArgumentResolver	userArgumentResolver;
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(userArgumentResolver);
	}
	
	@Bean
	public CommandLineRunner runner(UserRepository userRepository,
			BoardRepository boardRepository) {
		return (args) -> {
			userRepository.save(User.builder()
				.name("havi")
				.email("test")
				.principal("havi@gmail.com")
				.createdDate(LocalDateTime.now())
				.build()
			);
		}
	}
	
}
