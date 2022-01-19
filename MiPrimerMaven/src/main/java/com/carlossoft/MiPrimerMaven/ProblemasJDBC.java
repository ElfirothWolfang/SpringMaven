/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.carlossoft.MiPrimerMaven;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author vicente
 */
public class ProblemasJDBC {

    protected ArrayList<LibroJDBC> libros;

    public ProblemasJDBC() {
        libros = new ArrayList<>();
        recuperarLibros();
        imprimirLibros();
    }
    

    protected void recuperarLibros() {
        
        //Mientras estamos construyendo esta parte de código, es posible que
        //la BBDD no esté accesible aún por algún motivo (no está construída,
        //no hay acceso al servidor por problemas de red o lo que sea).
        //Para seguir construyendo y comprobando que el resto de nuestro programa
        //funciona sin problemas, lo que se suele hacer es crear datos dummy.
        //Es decir, en lugar de recuperar los datos de la BBDD, los generamos
        //a pelo temporalmente en código hasta que podamos usar la BBDD.
        

        //libros.add(new LibroJDBC(1, "7812387AB", "El libro de la selva", "Anónimo", "Disney"));
        //libros.add(new LibroJDBC(2, "878787GVA", "Phineas y Ferb", "Pachuly", "Disney"));
        //...
        
        //Cuando tengamos acceso a la BBDD, sustituimos el código de prueba anterior
        //por el que se encarga de recuperar datos de BBDD y rellenar el array.
        
        String url = "jdbc:mysql://192.168.202.12:3306/biblioteca";
        String user = "biblioteca";
        String pass = "1234";
        
        try (var con = DriverManager.getConnection(url, user, pass)) {
            System.out.println("Conexión establecida con éxito para la recuperación de libros.");
            String queryLibros = "SELECT * FROM libros";
            try(var stmLibros = con.prepareStatement(queryLibros);
                var rsLibros = stmLibros.executeQuery()) {
                while(rsLibros.next()){
                    libros.add(new LibroJDBC(rsLibros.getInt(1),      //Primera columna: ID
                                             rsLibros.getString(2),   //Segunda columna: ISBN
                                             rsLibros.getString(3),   //Tercera columna: Título
                                             rsLibros.getString(4),   //Cuarta columna: Autor
                                             rsLibros.getString(5))); //Quinta columna: Editorial
                }
            }     
        } catch (SQLException e) {
            System.err.println("Error al establecer la conexión: " + e.toString());
        }
    }
    
    
    protected void imprimirLibros() {
        
        //Pintamos la cabecera respetando anchos fijos para cada campo
        System.out.format("%-8s", "ID");
        System.out.format("%-10s", "ISBN");
        System.out.format("%-50s", "Título");
        System.out.format("%-25s", "Autor");
        System.out.format("%-15s\n", "Editorial");
        
        //Iteramos sobre los distintos libros
        for(LibroJDBC libro:libros) {
            System.out.format("%-8s", libro.getId());
            System.out.format("%-10s", libro.getIsbn());
            System.out.format("%-50s", libro.getTitulo());
            System.out.format("%-25s", libro.getAutor());
            System.out.format("%-15s\n", libro.getEditorial());
            
        }
    }
    
    public static void main(String[] args) {
      new ProblemasJDBC();    
    }
    
}
