package dev.gaellerauffet.escapegame.message.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Store message in logging system
 * @author gaelle
 *
 */
public class LogMessage {
	private static final Logger logger = LogManager.getLogger(DisplayMessage.class);

	/**
	 * Add an error with stack trace in log system info
	 * @param msg
	 * @param e
	 */
	public void errorLine(String msg, Exception e) {
		logger.error(msg, e);
	}
	
	/**
	 * Add an error in log system info
	 * @param msg
	 */
	public void errorLine(String msg) {
		logger.error(msg);
	}
	
	/**
	 * Add a message in log system with level INFO
	 * @param string
	 */
	public void info(String msg) {
		logger.info(msg);
		
	}
	
	/**
	 * Add a message in log system with level INFO
	 * @param string
	 */
	public void infoLine(String msg) {
		logger.info(msg);
		
	}
}
