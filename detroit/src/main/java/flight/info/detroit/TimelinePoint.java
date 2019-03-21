package flight.info.detroit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimelinePoint implements Comparable<TimelinePoint> {

	private String description;
	private LocalDateTime time;
	private boolean completed;
	
	
	public TimelinePoint() {
		super();
	}

	public TimelinePoint(String description, LocalDateTime time, boolean completed) {
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

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	
	public String getTimeAsString() {
		return time.format((DateTimeFormatter.ofPattern("hh:mm a")));
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	@Override
	public int compareTo(TimelinePoint object) {
		return this.time.compareTo(object.time);
		
	}
	
	
	
	
	
	
	
}
