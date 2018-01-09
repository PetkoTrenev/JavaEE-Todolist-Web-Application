package webapp.models;

public enum Priority {
	LOW(1),
	MED(2),
	HIGH(3);
	
	private int priority;
	
	Priority(int priority) {
		this.priority = priority;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	
}
