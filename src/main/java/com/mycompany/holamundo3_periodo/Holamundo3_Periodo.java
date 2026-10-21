/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.holamundo3_periodo;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
/**
 *
 * @author User
 */
public class Holamundo3_Periodo {

    public static void main(String[] args) throws SQLException {
        System.out.print("==Ejecutando operaciones en la tabla Alumnos==");
        // obtener la conexion 
            Connection cn = ConexionDB.getConexion();
            if(cn == null){
                  System.out.print("No se puede establecer la conexion ");
                  return;
            }
            
            // Declarar los objetivos de SQL para nuestra base
             PreparedStatement psInsertar = null;
             PreparedStatement psSeleccionar = null;
              PreparedStatement psActualizar = null;
               PreparedStatement psEliminar = null;
             ResultSet rs = null;
           
            try{
                          // insertar 
                     System.out.println("\n 1. Intentando insertar nuevo Alumno..");
                     String  psInsert = "INSERT INTO Alumnos (nombre,correo) VALUES (?,?)";
                      psInsertar = cn.prepareStatement(psInsert);
                      
                      // asignamos los valoresa nuestros parametros 
                       
                           
                             psInsertar.setString(1, "Neymar Pele");
                           psInsertar.setString(2, "neymar.pele@uth.hn");
                           
                          // ejecutar la inserción
                              int filasAfectadas = psInsertar.executeUpdate();
                                  if(filasAfectadas > 0){
                            System.out.println("!Alumno insertado con exito!");
                   }
                                  // Parte B Seleccionar y visualizar datos en pantalla
                                  System.out.println("\n 2.Consulta de lista de Alumnos en la base de datos ");
                                  String sqlSelect = "SELECT id, nombre, correo FROM Alumnos";
                                  
                                  psSeleccionar = cn.prepareStatement(sqlSelect);
                                  rs = psSeleccionar.executeQuery();
                                  
                                     System.out.println("\n ===================================================");
                                    System.out.println("ID    | Nombre          |Correo ");
                                     System.out.println("\n ===================================================");
                                     while(rs.next()){
                                         int id = rs.getInt("id");
                                         String nombre = rs.getString("nombre");
                                         String correo = rs.getString("correo");
                                         // Imprimimos la tabla virtual
                                         System.out.printf("%-3d | %-20s |%s\n  ", id, nombre, correo);
                                         
                                     }
                                      System.out.println("\n ===================================================");
                                      
                                      // ACTUALIZAR
                                       System.out.println("\n 3. Actualizando datos de un Alumno");
                                       String sqlActualizar  = "UPDATE Alumnos SET nombre = ?, correo = ? WHERE id =?";
                                       psActualizar  = cn.prepareStatement(sqlActualizar );
                                        psActualizar.setString(1,"Taty Izaguirre");
                                         psActualizar.setString(2, "lionel.messi@ut.hn");
                                         psActualizar.setInt(3, 16);
                                         int filasActualizadas = psActualizar.executeUpdate();
                                         if (filasActualizadas > 0) {
                                             
                                             System.out.println("** Alumno actualizado con exito ***");
                                        
                                         }else {
                                             System.out.println("** No se encontro el alumno con ese ID.**");
                                         }
                                         
                                         
                                         
                                         //ELIMINAR 
                                         System.out.println("\n 4 Eliminado  un Alumno");
                                         String sqlEliminar = "DELETE FROM Alumnos WHERE id = ?";
                                         psEliminar = cn.prepareStatement(sqlEliminar );
                                             psEliminar.setInt(1, 17);
                                             int filasEliminadas =  psEliminar.executeUpdate();
                                             if (filasEliminadas > 0) {
                                                  System.out.println("** Alumno eliminado con exito ***");
                                                  
                                                    }else {
                                             System.out.println("** No se encontro el alumno con ese ID.**");
                                         
                                                 
                                             }
                                
                                      
                                  
                        }          
                      catch(SQLException e){
                      System.err.println("Ocurrio un error con la base de datos"  + e.getMessage());
                      
                      }finally{
                              //cerrar la conexion de manera obligatoria 
                              if(rs != null){
                              rs.close();
                              
                              }
                              
                              if( psInsertar != null) psInsertar.close();
                              
                               if( psSeleccionar != null) psSeleccionar.close();
                                 if( psActualizar != null) psActualizar.close();
                                   if( psEliminar != null) psEliminar.close();
                              ConexionDB.cerrarConexion();
                       }
}
            
}
    
