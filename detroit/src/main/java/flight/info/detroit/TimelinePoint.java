package flight.info.detroit;

public class TimelinePoint {

	private String description;
	private String time;
	private boolean completed;
	
	
	public TimelinePoint() {
		super();
	}

	public TimelinePoint(String description, String time, boolean completed) {
		super();
		this.description = description;
		this.time = time;
		this.completed = completed;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	
	
	
	
	
	
}
