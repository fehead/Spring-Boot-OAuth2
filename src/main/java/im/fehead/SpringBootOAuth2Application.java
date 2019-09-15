package im.fehead;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import im.fehead.domain.Board;
import im.fehead.domain.User;
import im.fehead.domain.enums.BoardType;
import im.fehead.repository.BoardRepository;
import im.fehead.repository.UserRepository;
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
			User user = User.builder()
				.name("havi")
				.email("test")
				.pincipal("havi@gmail.com")
				.createdDate(LocalDateTime.now())
				.build();
			userRepository.save(user);
			
			IntStream.rangeClosed(1, 200).forEach(index ->
				boardRepository.save(Board.builder()
					.title("게시글 "+index)
					.subTitle("순서 "+index)
					.content("내용~~~")
					.boardType(BoardType.free)
					.createdDate(LocalDateTime.now())
					.updatedDate(LocalDateTime.now())
					.user(user).build())
			);
		};
	}
	
}
