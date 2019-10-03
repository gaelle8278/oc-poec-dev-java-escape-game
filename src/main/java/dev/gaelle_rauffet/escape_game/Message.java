package dev.gaelle_rauffet.escape_game;

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
	public void printInfo(String msg) {
		System.out.println(msg);
		
	}

	public void logError(String msg, Exception e) {
		logger.error(msg, e);
	}
	
	public void logError(String msg) {
		logger.error(msg);
	}
	
	

}
