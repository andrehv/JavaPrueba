package com.RetoConsola;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.RetoConsola.Model.Log;



@SpringBootApplication
public class RetoConsolaApplication {

	@SuppressWarnings("static-access")
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(RetoConsolaApplication.class, args);
		
		//Simulación de parámetros
		boolean logToFileParam = Boolean.parseBoolean("True"); 
        boolean logToConsoleParam = Boolean.parseBoolean("True"); 
        boolean logToDatabaseParam = Boolean.parseBoolean("True"); 
        boolean logMessageParam = Boolean.parseBoolean("False"); 
        boolean logWarningParam = Boolean.parseBoolean("True"); 
        boolean logErrorParam = Boolean.parseBoolean("True"); 
        
        boolean message = Boolean.parseBoolean("False"); 
        boolean warning = Boolean.parseBoolean("True"); 
        boolean error = Boolean.parseBoolean("False"); 
        String Message= "This is Info Messsage";

		Log sr = new Log(logToFileParam,logToConsoleParam,logToDatabaseParam,logMessageParam,logWarningParam,logErrorParam);
		JobLogger jl = new JobLogger();
		
		jl.LogMessage(Message, message, warning, error, sr);
	}

}
