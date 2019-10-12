package im.fehead;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LiternalTest {
	@Test
	public void integerTest() {
		Integer a = 10;
		Integer b = new Integer(10);
		Integer c = 10;
		int s = b;

		log.info("a : {}, b {}, c {}", a, b, c);
		log.info("a.equals(b) {}", a.equals(b));
		log.info("a.equals(b) {}", a.equals(b));
		log.info("a == c {}", a == c);
		log.info("a == b {}", a == b);

		log.info("&a {}, &b {}, &c {}, &s {}", System.identityHashCode(a), System.identityHashCode(b),
				System.identityHashCode(c), System.identityHashCode(s));

		assertThat(a.equals(b)).isTrue();
		assertThat(a == c).isTrue();
		assertThat(a == b).isFalse();
	}

	@Test
	public void integerTest2() {
		Integer b = new Integer(10);
		assertThat(10 == b).isTrue();
	}

	
	@Test
	public void integerTest3() {
		int a = 11;
		Integer b = new Integer(11);
		assertThat(a == b).isTrue();
	}
	
	@Test
	public void integerTest4() {
		Integer a = 11;
		Integer b = new Integer(a);
		assertThat(a == b).isFalse();
		assertThat(a.intValue() == b).isTrue();
	}

}
