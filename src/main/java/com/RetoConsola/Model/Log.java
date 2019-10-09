package com.RetoConsola.Model;


import java.util.logging.Logger;

public class Log {
	private boolean logToFile;
	private boolean logToConsole;
	private boolean logMessage;
	private boolean logWarning;
	private boolean logError;
	private boolean logToDatabase;
	private boolean initialized;

	
	private static Logger logger;
	
	public Log (boolean logToFileParam, boolean logToConsoleParam, boolean logToDatabaseParam,
			boolean logMessageParam, boolean logWarningParam, boolean logErrorParam) {
		logger = Logger.getLogger("MyLog");  
		logError = logErrorParam;
		logMessage = logMessageParam;
		logWarning = logWarningParam;
		logToDatabase = logToDatabaseParam;
		logToFile = logToFileParam;
		logToConsole = logToConsoleParam;
		
	}

	public boolean isLogToFile() {
		return logToFile;
	}

	public void setLogToFile(boolean logToFile) {
		this.logToFile = logToFile;
	}

	public boolean isLogToConsole() {
		return logToConsole;
	}

	public void setLogToConsole(boolean logToConsole) {
		this.logToConsole = logToConsole;
	}

	public boolean isLogMessage() {
		return logMessage;
	}

	public void setLogMessage(boolean logMessage) {
		this.logMessage = logMessage;
	}

	public boolean isLogWarning() {
		return logWarning;
	}

	public void setLogWarning(boolean logWarning) {
		this.logWarning = logWarning;
	}

	public boolean isLogError() {
		return logError;
	}

	public void setLogError(boolean logError) {
		this.logError = logError;
	}

	public boolean isLogToDatabase() {
		return logToDatabase;
	}

	public void setLogToDatabase(boolean logToDatabase) {
		this.logToDatabase = logToDatabase;
	}

	public boolean isInitialized() {
		return initialized;
	}

	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}



	public  Logger getLogger() {
		return logger;
	}

	public  void setLogger(Logger logger) {
		Log.logger = logger;
	}

	
	

	

	
}
