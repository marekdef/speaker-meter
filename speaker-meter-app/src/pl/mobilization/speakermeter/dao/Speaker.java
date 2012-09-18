package pl.mobilization.speakermeter.dao;

import java.io.Serializable;
import java.util.Date;

import com.google.common.base.Objects;
import com.google.common.base.Strings;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table SPEAKER.
 */
public class Speaker implements Serializable, Comparable<Speaker> {

	private Long id;
	/** Not-null value. */
	private String name;
	/** Not-null value. */
	private String presentation;
	/** Not-null value. */
	private String venue;
	/** Not-null value. */
	private String description;
	private int votes_up;
	private int votes_down;
	/** Not-null value. */
	private java.util.Date start_time;
	/** Not-null value. */
	private java.util.Date end_time;
	private boolean visible = true;

	public Speaker() {
	}

	public Speaker(Long id) {
		this.id = id;
	}

	public Speaker(Long id, String name, String presentation, String venue,
			String description, int votes_up, int votes_down,
			java.util.Date start_time, java.util.Date end_time, boolean visible) {
		this.id = id;
		this.name = name;
		this.presentation = presentation;
		this.venue = venue;
		this.description = description;
		this.votes_up = votes_up;
		this.votes_down = votes_down;
		this.start_time = start_time;
		this.end_time = end_time;
		this.visible = visible;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/** Not-null value. */
	public String getName() {
		return Strings.nullToEmpty(name);
	}

	/**
	 * Not-null value; ensure this value is available before it is saved to the
	 * database.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/** Not-null value. */
	public String getPresentation() {
		return Strings.nullToEmpty(presentation);
	}

	/**
	 * Not-null value; ensure this value is available before it is saved to the
	 * database.
	 */
	public void setPresentation(String presentation) {
		this.presentation = presentation;
	}

	/** Not-null value. */
	public String getVenue() {
		return Strings.nullToEmpty(venue);
	}

	/**
	 * Not-null value; ensure this value is available before it is saved to the
	 * database.
	 */
	public void setVenue(String venue) {
		this.venue = venue;
	}

	/** Not-null value. */
	public String getDescription() {
		return Strings.nullToEmpty(description);
	}

	/**
	 * Not-null value; ensure this value is available before it is saved to the
	 * database.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public int getVotes_up() {
		return votes_up;
	}

	public void setVotes_up(int votes_up) {
		this.votes_up = votes_up;
	}

	public int getVotes_down() {
		return votes_down;
	}

	public void setVotes_down(int votes_down) {
		this.votes_down = votes_down;
	}

	/** Not-null value. */
	public java.util.Date getStart_time() {
		return Objects.firstNonNull(start_time, new Date());
	}

	/**
	 * Not-null value; ensure this value is available before it is saved to the
	 * database.
	 */
	public void setStart_time(java.util.Date start_time) {
		this.start_time = start_time;
	}

	/** Not-null value. */
	public java.util.Date getEnd_time() {
		return Objects.firstNonNull(end_time, new Date());
	}

	/**
	 * Not-null value; ensure this value is available before it is saved to the
	 * database.
	 */
	public void setEnd_time(java.util.Date end_time) {
		this.end_time = end_time;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Speaker other = (Speaker) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public int compareTo(Speaker another) {
		if (another == null)
			return -1;
		return this.getName().compareTo(another.getName());
	}
	
	@Override
	public String toString() {
		return String.format("%s - %s", getName(), getPresentation()); 
	}

}