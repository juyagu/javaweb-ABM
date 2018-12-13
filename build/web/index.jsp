

<%@page import="ar.com.codigolibre.entities.Producto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ABM Productos</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    </head>
    <body>
	<div class="container">
	    <h1>ABM Productos</h1>
	    <form action="action/save" method="post">
		<div class="row">
		    <div class="form-group col-6">
			<label>Presentación:</label>
			<input type="text" class="form-control" name="presentacion" />
		    </div>
		    <div class="form-group col-6">
			<label>Descripción:</label>
			<input type="text" class="form-control" name="descripcion" />
		    </div>
		</div>
		<div class="row">
		    <div class="form-group col-6">
			<label>Precio</label>
			<input type="text" class="form-control" name="precio" />
		    </div>
		    <div class="form-group col-6">
			<label>Cantidad</label>
			<input type="text" class="form-control" name="cantidad" />
		    </div>
		</div>
		<button type="submit" class="btn btn-info">Enviar</button>
	    </form>
	    <br />
	    <%List<String> mensajes = (List<String>) session.getAttribute("msgs"); %>
	    <% if(mensajes != null){%>
		<% for(String m : mensajes){ %>
		   <div class="alert alert-danger" role="alert">
			<%=m%>
			<% session.removeAttribute("msgs"); %>
		    </div>
		 <%}%>
	    <%}%> 
	    <hr>
	    <%List<Producto> productos = (List<Producto>) session.getAttribute("prods");%>
	    <table class="table table-bordered">
		<thead>
		    <tr>
			<th>Id</th>
			<th>Descripción</th>
			<th>Presentación</th>
			<th>Cantidad</th>
			<th>Precio</th>
			<th>Borrar</th>
		    </tr>
		</thead>
		<tbody>
		    <%if(productos != null){%>
			<% for(Producto p : productos){ %>
			<tr>
			    <td><%= p.getId() %></td>
			    <td><%= p.getDescripcion()%></td>
			    <td><%= p.getPresentacion()%></td>
			    <td><%= p.getCantidad()%></td>
			    <td>$<%= p.getPrecio()%></td>
			    <td>
				<form method="post" action="/ABM/action/delete">
				    <input type="hidden" name="id" value="<%= p.getId() %>">
				    <button type="submit" class="btn btn-danger">Eliminar</button>
				</form>
			    </td>
			</tr>
			<% } %>
			<% session.removeAttribute("prods"); %>
		    <% } %>
		</tbody>
	    </table>
	</div>
    </body>
</html>
