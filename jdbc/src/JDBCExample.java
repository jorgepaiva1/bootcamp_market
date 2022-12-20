/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author jorge
 */
public class JDBCExample {
    static final String DB_URL = "jdbc:mysql://localhost/TUTORIALSPOINT";
   static final String USER = "guest";
   static final String PASS = "guest123";
   static final String QUERY = "SELECT *FROM cliente";
   
   public static Connection conectar(){
        Connection database = null;
        try{
            database = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/bootcamp_market",
            "postgres", "postgres");
            return database;
        }catch (Exception e){
            System.out.println("Ha ocurrido un error al conectarse");
            return null;
        }
    }
   
   public static void ej1() throws SQLException{
       Connection database = conectar();
       Statement stmt= database.createStatement();
       ResultSet rs = stmt.executeQuery("Select nombre,apellido,count(*) from factura fact inner join cliente as cli on fact.cliente_id=cli.id " +
"group by nombre,apellido having count(*)>0 order by count(*) desc");
       try{ 
       while(rs.next()){
            System.out.println("----------------------------------------------");
            System.out.print("Nombre " + rs.getString("nombre"));
            System.out.println("-Apellido " + rs.getString("apellido"));
            System.out.println("Facturas " + rs.getString("count"));
         }
      } catch (SQLException e) {
         e.printStackTrace();
        }
       stmt.close();
        rs.close();
        database.close();
   }
   
   public static void ej2()throws SQLException{
       Connection database = conectar();
       Statement stmt= database.createStatement();
       ResultSet rs = stmt.executeQuery("Select cliente.nombre, cliente.apellido, cliente.nro_cedula, sum(prod.precio * fact_det.cantidad) as gasto from \n" +
"( (cliente inner join factura as fact on cliente.id=fact.cliente_id) \n" +
" inner join factura_detalle as fact_det on fact.id=fact_det.factura_id)\n" +
"inner join producto as prod on fact_det.producto_id= prod.id\n" +
"group by cliente.nombre,cliente.apellido,cliente.nro_cedula, (prod.precio * fact_det.cantidad)\n" +
"order by gasto desc;");
       try{ 
       while(rs.next()){
            System.out.println("----------------------------------------------");
            System.out.println("Nombre " + rs.getString("nombre"));
            System.out.println("Apellido " + rs.getString("apellido"));
            System.out.println("Cedula " + rs.getString("nro_cedula"));
            System.out.println("Gasto " + rs.getString("costo"));
         }
      } catch (SQLException e) {
         e.printStackTrace();
        }
       stmt.close();
        rs.close();
        database.close();
   }
   
    public static void ej3()throws SQLException{
       Connection database = conectar();
       Statement stmt= database.createStatement();
       ResultSet rs = stmt.executeQuery("select mon.nombre, count(*) AS Uso from factura fact inner join moneda mon on fact.moneda_id=mon.id\n" +
"group by mon.nombre order by count(*) desc;");
       try{ 
       while(rs.next()){
            System.out.println("----------------------------------------------");
            System.out.println("Nombre " + rs.getString("nombre"));
            System.out.println("Uso " + rs.getString("uso"));
         }
      } catch (SQLException e) {
         e.printStackTrace();
        }
       stmt.close();
        rs.close();
        database.close();
   }
    public static void ej4()throws SQLException{
       Connection database = conectar();
       Statement stmt= database.createStatement();
       ResultSet rs = stmt.executeQuery("Select prov.nombre, count(prov.id) as productos from producto as prod inner join proveedor as prov on prod.proveedor_id=prov.id\n" +
"group by prov.nombre order by count(prov.id) desc; ");
       try{ 
       while(rs.next()){
            System.out.println("----------------------------------------------");
            System.out.println("Nombre_proveedor " + rs.getString("nombre"));
            System.out.println("cant_Productos " + rs.getString("productos"));
         }
      } catch (SQLException e) {
         e.printStackTrace();
        }
       stmt.close();
        rs.close();
        database.close();
   }
    
    public static void ej5()throws SQLException{
       Connection database = conectar();
       Statement stmt= database.createStatement();
       ResultSet rs = stmt.executeQuery("Select prod.nombre,sum(prod.id*fact_det.cantidad) as cant_vendida from factura_detalle as fact_det inner join producto as prod\n" +
"on fact_det.producto_id=prod.id group by prod.nombre order by sum(prod.id*fact_det.cantidad) desc;");
       try{ 
       while(rs.next()){
            System.out.println("----------------------------------------------");
            System.out.println("Nombre_prod " + rs.getString("nombre"));
            System.out.println("cant_vendida " + rs.getString("cant_vendida"));
         }
      } catch (SQLException e) {
         e.printStackTrace();
        }
       stmt.close();
        rs.close();
        database.close();
   }
    
    public static void ej6()throws SQLException{
       Connection database = conectar();
       Statement stmt= database.createStatement();
       ResultSet rs = stmt.executeQuery("Select prod.nombre,sum(prod.id * fact_det.cantidad) as cant_vendida from factura_detalle as fact_det inner join producto as prod\n" +
"on fact_det.producto_id=prod.id group by prod.nombre order by sum(prod.id*fact_det.cantidad) asc;");
       try{ 
       while(rs.next()){
            System.out.println("----------------------------------------------");
            System.out.println("Nombre_prod " + rs.getString("nombre"));
            System.out.println("cant_vendida " + rs.getString("cant_vendida"));
         }
      } catch (SQLException e) {
         e.printStackTrace();
        }
       stmt.close();
        rs.close();
        database.close();
   }
    public static void ej7()throws SQLException{
       Connection database = conectar();
       Statement stmt= database.createStatement();
       ResultSet rs = stmt.executeQuery("Select fact.fecha_emision,client.nombre,client.apellido,prod.nombre as nombre_producto,fact_det.cantidad from \n" +
"((cliente as client inner join factura as fact on client.id=fact.cliente_id) \n" +
" inner join factura_detalle as fact_det on fact.id=fact_det.factura_id)\n" +
" inner join producto as prod on fact_det.producto_id=prod.id\n" +
"group by fact.fecha_emision,client.nombre,client.apellido,prod.nombre,fact_det.cantidad\n" +
"order by fact.fecha_emision desc;");
       try{ 
       while(rs.next()){
            System.out.println("----------------------------------------------");
            System.out.println("fecha de emision " + rs.getDate("fecha_emision"));
            System.out.println("nombre " + rs.getString("nombre"));
            System.out.println("apellido " + rs.getString("apellido"));
            System.out.println("producto " + rs.getString("nombre_producto"));
            System.out.println("cantidad " + rs.getString("cantidad"));
            
         }
      } catch (SQLException e) {
         e.printStackTrace();
        }
       stmt.close();
        rs.close();
        database.close();
   }
   public static void main(String[] args) throws SQLException{
       //ej1();
       //ej2();
       //ej3();
       //ej4();
       //ej4();
       //ej6();
       ej7();
   }
      // Open a connection
      /*try(Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bootcamp_market", "postgres", "postgres");
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(QUERY);
      ) {		      
         while(rs.next()){
            //Display values
//            System.out.print("ID: " + rs.getInt("id"));
            System.out.print("Nombre " + rs.getString("nombre"));
            System.out.println("Apellido " + rs.getString("apellido"));
            System.out.println("Cedula " + rs.getString("nro_cedula"));
            System.out.println("Numero " + rs.getString("telefono"));
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }*/ 
       
   
}
