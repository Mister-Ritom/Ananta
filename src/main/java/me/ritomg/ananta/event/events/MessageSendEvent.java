package me.ritomg.ananta.event.events;

import me.ritomg.ananta.event.AnantaMainEvent;

public class MessageSendEvent extends AnantaMainEvent{
	
	
	private String message;
	
	public MessageSendEvent(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}
