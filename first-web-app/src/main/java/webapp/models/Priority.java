package webapp.models;

public enum Priority {
	LOW(1),
	MED(2),
	HIGH(3);
	
	private final int code;
	
	Priority(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
	
	public static Priority ofCode(int code) {
		for (Priority p : Priority.values()) {
			if (p.getCode() == code) {
				return p;
			}
		}
		
		throw new IllegalArgumentException("Unknown code");
	}
}
