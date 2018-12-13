
package ar.com.codigolibre.repositories;

import ar.com.codigolibre.entities.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepository {
    private Connection conexion;

    public ProductoRepository(Connection conexion) {
	this.conexion = conexion;
    }
    
    public Integer saves(Producto p){
	int inserted = 0;
	try{
	    PreparedStatement stmt = conexion.prepareStatement("insert into productos (presentacion,cantidad,precio,descripcion) values (?,?,?,?)");
	    stmt.setString(1, p.getPresentacion());
	    stmt.setInt(2, p.getCantidad());
	    stmt.setFloat(3, p.getPrecio());
	    stmt.setString(4, p.getDescripcion());
	    inserted = stmt.executeUpdate();
	}catch(SQLException ex){
	    throw new RuntimeException("Error consultando con la base de datos", ex);
	}
	return inserted;
    }
    
    public Integer update (Producto p) {
        int inserted = 0;
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement("update productos set presentacion = ? ,cantidad = ? ,precio = ? ,descripcion = ? where id = ?");
            preparedStatement.setString(1, p.getPresentacion());
            preparedStatement.setInt(2, p.getCantidad());
            preparedStatement.setFloat(3, p.getPrecio());
            preparedStatement.setString(4, p.getDescripcion());
            preparedStatement.setLong(5, p.getId());
            inserted = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("error consultando la base de datos", ex);
        }
        return inserted;
    }
    
    public Integer delete (Long id) {
        int inserted = 0;
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement("delete from productos where id = ?");
            preparedStatement.setLong(1,id);
            inserted = preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            throw new RuntimeException("error consultando la base de datos", ex);
        }
        return inserted;
    }
    
     public Producto getById (long id) {
        Producto producto = null;
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement("select * from productos where id = ?");
            preparedStatement.setLong(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                String presentacion = rs.getString("presentacion");
                Integer cantidad = rs.getInt("cantidad");
                Float precio = rs.getFloat("precio");
                String nota = rs.getString("descripcion");
                producto = new Producto(id,presentacion, cantidad, precio, nota);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("error consultando la base de datos", ex);
        }
        return producto;
    }
     
     public List<Producto> getAll() {
        List<Producto> productos = new ArrayList<>();
        try {
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery("select * from productos");
            while(rs.next()){
                Long id = rs.getLong("id");
                String presentacion = rs.getString("presentacion");
                Integer cantidad = rs.getInt("cantidad");
                Float precio = rs.getFloat("precio");
                String nota = rs.getString("descripcion");
                Producto p = new Producto(id, presentacion, cantidad, precio, nota);
                productos.add(p);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("error consultando la base de datos", ex);
        }
        return productos;
    }
     
     public List<Producto> searchByDescripcion(String descripcion) {
        List<Producto> productos = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement("select * from productos p where p.descripcion like ?");
            preparedStatement.setString(1,"%"+descripcion+"%");
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Long id = rs.getLong("id");
                String presentacion = rs.getString("presentacion");
                Integer cantidad = rs.getInt("cantidad");
                Float precio = rs.getFloat("precio");
                String nota = rs.getString("descripcion");
                Producto p = new Producto(id, presentacion, cantidad, precio, nota);
                productos.add(p);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("error consultando la base de datos", ex);
        }
        return productos;
    }
}
