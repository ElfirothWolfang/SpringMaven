package com.carlossoft.MiPrimerMaven;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        String url = "jdbc:mysql://192.168.202.12:3306/biblioteca";
        String user = "biblioteca";
        String pass = "1234";
        
        try (var con = DriverManager.getConnection(url, user, pass)){
        	System.out.println("Conexión establecida");
        } catch (SQLException e) {
			System.out.println("Error de conexión");
			e.printStackTrace();
		}
       
    }
}
