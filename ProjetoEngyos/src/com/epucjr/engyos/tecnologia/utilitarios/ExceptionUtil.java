package com.epucjr.engyos.tecnologia.utilitarios;

import java.io.PrintWriter;
import java.io.StringWriter;


public class ExceptionUtil {

	public static String stackTraceToString (Exception e){
		
		StringWriter stringException = new StringWriter ();
		PrintWriter printer = new PrintWriter(stringException);
		e.printStackTrace(printer);
		
		return stringException.toString();
	}
	
}
