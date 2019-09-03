package im.fehead.domain.enums;

public enum SocialType {
	FACEBOOK("facebook"),
	GOOGLE("google"),
	KAKAO("kakao");
	
	private	final String ROLE_PREFIX = "ROLE_";
	private	String	name;
	
	private SocialType(String name) {
		this.name = name;
	}
	
	public String getRoleType() {
		return ROLE_PREFIX + name.toUpperCase();
	}
	
	public String getValue() {
		return name;
	}
	
	public boolean isEquals(String authority) {
		return getRoleType().equals(authority);
	}
	
}
