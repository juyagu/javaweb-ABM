
package ar.com.codigolibre.servlet;

import ar.com.codigolibre.entities.Producto;
import ar.com.codigolibre.repositories.ProductoRepository;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@WebServlet(name="ps",urlPatterns="/action/*")
public class Controller extends HttpServlet{
    private DataSource datasource;
    
    @Override
    public final void init() throws ServletException {
	try{
	    datasource = createDataSource();
	}catch (PropertyVetoException ex) {
            throw new RuntimeException("error base de datos", ex);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductoRepository pr = null;
	try{
	    pr = new ProductoRepository(datasource.getConnection());
	}catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
	
	List<String> mensajes = new ArrayList<String>();
	String action = action(req);
	if(action.equals("delete")){
	    Long id = Long.parseLong(req.getParameter("id"));
	    pr.delete(id);
	    mensajes.add("Borrado con éxito id " + id);
	} else if(action.equals("save")){
	    String presentacion = req.getParameter("presentacion");
	    String descripcion = req.getParameter("descripcion");
	    
	    Float precio = 0.0f;
	    try{
		precio = Float.parseFloat(req.getParameter("precio"));
	    }catch (NumberFormatException ex) {
                mensajes.add("El precio no es numerico");
            }
	    
	    Integer cantidad = 0;
	    try{
		cantidad = Integer.parseInt(req.getParameter("cantidad"));
	    }catch (NumberFormatException ex) {
                mensajes.add("La cantidad ingresada no es numerica");
            }
	    
	    if(mensajes.isEmpty()){
		Producto p = new Producto(presentacion,cantidad,precio,descripcion);
		pr.saves(p);
		mensajes.add("Guardado exitoso");
	    }
	}
	
	List<Producto> productos = pr.getAll();
        req.getSession().setAttribute("prods", productos);
        req.getSession().setAttribute("msgs", mensajes);

        resp.sendRedirect("/ABM/index.jsp");

    }
    
    
    public String action(HttpServletRequest req) {
        String[] urlParts = req.getRequestURL().toString().split("/");
        String actionUrl = urlParts[urlParts.length - 1];
        return actionUrl;
    }
    
    private ComboPooledDataSource createDataSource() throws PropertyVetoException {
	ComboPooledDataSource cpds = new ComboPooledDataSource();
	cpds.setDriverClass("org.gjt.mm.mysql.Driver");
	cpds.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/abm");
	cpds.setUser("root");
	cpds.setPassword("");
	cpds.setInitialPoolSize(5);
	cpds.setMinPoolSize(5);
	cpds.setAcquireIncrement(5);
	cpds.setMaxPoolSize(20);
	
	return cpds;
    }
    
}
