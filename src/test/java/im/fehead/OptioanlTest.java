package im.fehead;

import java.util.Optional;

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
	
}
