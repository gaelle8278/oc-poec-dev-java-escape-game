package dev.gaellerauffet.escapegame.message;

public interface Message {
	public void info(String Message);
	
	public void infoLine(String Message);
	
	public void errorLine(String Message, Exception e);
	
	public void errorLine(String Message);
	
}
