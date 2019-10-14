package dev.gaellerauffet.escapegame.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Message {
	private static final Logger logger = LogManager.getLogger(Message.class);

	/**
	 * Add a message in log system with level INFO
	 * @param string
	 */
	public void logInfo(String msg) {
		logger.info(msg);
		
	}

	/**
	 * Print a message in standard output
	 * @param string
	 */
	public void printLineInfo(String msg) {
		System.out.println(msg);
		
	}

	/**
	 * Add an error with stack trace in log system info
	 * @param msg
	 * @param e
	 */
	public void logError(String msg, Exception e) {
		logger.error(msg, e);
	}
	
	/**
	 * Add an error in log system info
	 * @param msg
	 */
	public void logError(String msg) {
		logger.error(msg);
	}

	public void printInfo(String msg) {
		System.out.print(msg);
		
	}
	
	

}
