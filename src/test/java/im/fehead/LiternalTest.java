package im.fehead;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LiternalTest {
	@Test
	public void integerTest() {
		Integer	a = 10;
		Integer b = new Integer(10);
		Integer c = 10;
		
		assertThat(a.equals(b)).isTrue();
		assertThat(a == c).isTrue();
		assertThat(a == b).isFalse();
	}

}
