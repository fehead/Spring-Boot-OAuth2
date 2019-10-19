package im.fehead;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.stream.Stream;

import org.junit.Test;

public class OptioanlTest {
	@Test(expected = NullPointerException.class)
	public void optional1() {
		String	test = null;
		Optional.of(test)
			.ifPresent(System.out::println);
	}

	@Test
	public void optional2() {
		String	test = null;
		Optional.ofNullable(test)
			.ifPresent(System.out::println);
	}
	
	@Test
	public void optional3() {
		String	test = Stream.of("abc", null, "010-1111-2222")
				.filter(s -> s != null && s.startsWith("010"))
				.findFirst()
				.orElse("test");
		assertThat(test.equals("010-1111-2222")).isTrue();
	}
}
