package im.fehead;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class StringTest {
	@Test
	public void test() {
		
		StringBuilder	sb = new StringBuilder("ab")
				.append(1)
				.append(true)
				.append(false)
				.append(10.1);
		assertThat(sb.toString().equals("ab1truefalse10.1")).isTrue();
		
	}

}
