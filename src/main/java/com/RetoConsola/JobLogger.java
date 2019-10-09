package com.RetoConsola;

import java.io.File;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;

import org.springframework.beans.factory.annotation.Autowired;

import com.RetoConsola.Model.Log;




public class JobLogger {

	

	public static Map<String, String> dbParams;
	static {
		dbParams = new HashMap<>();
		dbParams.put("postgresql", "postgresql");
		dbParams.put("servidor", "127.0.0.1:5432/demo");
		dbParams.put("servidor", "127.0.0.1:5432/demo");
		dbParams.put("user", "postgres");
		dbParams.put("password", "123");

	}
	
	static Exception e;

	@Autowired
	public static void LogMessage(String messageText, boolean message, boolean warning, boolean error, Log lg) throws Exception 
	{
		
			
			//Creación File
			String l = null;
			File logFile = new File("logFile.txt");
			if (!logFile.exists()) {
				logFile.createNewFile();
			}

			//Escritura archivo
			FileHandler fh = new FileHandler("logFile.txt");
			ConsoleHandler ch = new ConsoleHandler();
			
			//Conexión BD postgresql
			Connection connection = DriverManager.getConnection("jdbc:" + dbParams.get("postgresql") + 
					"://" + dbParams.get("servidor"), dbParams.get("user"), dbParams.get("password"));
			Statement stmt = connection.createStatement();
		
			
			messageText.trim();
			if (messageText == null || messageText.length() == 0) {
				return;
			}
			if (!lg.isLogToConsole()  && !lg.isLogToFile() && !lg.isLogToDatabase()) {		
				e = new Exception("Invalid configuration");
	            // Mediante el metodo getStackTrace obtenemos el stackTrace de la excepcion en forma de un objecto String
	            String msg=JobLogger.getStackTrace(e);
	            lg.getLogger().addHandler(fh);
	            lg.getLogger().log(Level.SEVERE,  msg);
	            	
			}
			if ((!lg.isLogError() && !lg.isLogMessage() && !lg.isLogWarning()) || (!message && !warning && !error)) {
				e = new Exception("Error or Warning or Message must be specified");
		        // Mediante el metodo getStackTrace obtenemos el stackTrace de la excepcion en forma de un objecto String
				String msg=JobLogger.getStackTrace(e);
				lg.getLogger().addHandler(fh);
				lg.getLogger().log(Level.SEVERE,  msg);
			}
			//Validar el tipo de evento (Message, Error, Warning)
			int t = 0;
			if (message && lg.isLogMessage()) {
				t = 1;
			}
	
			if (error && lg.isLogError()) {
				t = 2;
			}
	
			if (warning && lg.isLogWarning()) {
				t = 3;
			}
			//Habilitación log file
			if(lg.isLogToFile()) 
			{	
				lg.getLogger().addHandler(fh);
				if (error && lg.isLogError()) {
					l = l + "error " + DateFormat.getDateInstance(DateFormat.LONG).format(new Date())+ " " + messageText;
					lg.getLogger().log(Level.SEVERE, l);
				}
		
				if (warning && lg.isLogWarning()) {
					l = l + "warning " +DateFormat.getDateInstance(DateFormat.LONG).format(new Date())+ " " + messageText;
					lg.getLogger().log(Level.WARNING, l);
				}
		
				if (message && lg.isLogMessage()) {
					l = l + "message " +DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + " " + messageText;
					lg.getLogger().log(Level.INFO, l);
				}
			}
			
			//Escribe log de consola
			if(lg.isLogToConsole()) {
				lg.getLogger().addHandler(ch);	
				lg.getLogger().log(Level.INFO, messageText);
			}
			//Habilitación log BD - Insert
			if(lg.isLogToDatabase()) {
				stmt.executeUpdate("INSERT INTO Log_Values(Mensaje, Tipo)"+  
				"VALUES('" + l + "'," + String.valueOf(t) + ")");
			}
			
		
	}
	
	//conversión exception a Strring
	public static String getStackTrace(Exception e) {
        StringWriter sWriter = new StringWriter();
        PrintWriter pWriter = new PrintWriter(sWriter);
        e.printStackTrace(pWriter);
        return sWriter.toString();
    }
}
