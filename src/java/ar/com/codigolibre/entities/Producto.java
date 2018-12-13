
package ar.com.codigolibre.entities;


public class Producto {
    private Long id;
    private String presentacion;
    private Integer cantidad;
    private Float precio;
    private String descripcion;

    public Producto(Long id, String presentacion, Integer cantidad, Float precio, String descripcion) {
	this.id = id;
	this.presentacion = presentacion;
	this.cantidad = cantidad;
	this.precio = precio;
	this.descripcion = descripcion;
    }

    public Producto(String presentacion, Integer cantidad, Float precio, String descripcion) {
	this.presentacion = presentacion;
	this.cantidad = cantidad;
	this.precio = precio;
	this.descripcion = descripcion;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getPresentacion() {
	return presentacion;
    }

    public void setPresentacion(String presentacion) {
	this.presentacion = presentacion;
    }

    public Integer getCantidad() {
	return cantidad;
    }

    public void setCantidad(Integer cantidad) {
	this.cantidad = cantidad;
    }

    public Float getPrecio() {
	return precio;
    }

    public void setPrecio(Float precio) {
	this.precio = precio;
    }

    public String getDescripcion() {
	return descripcion;
    }

    public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
    }

    @Override
    public String toString() {
	return "Producto{" + "id=" + id + ", presentacion=" + presentacion + ", cantidad=" + cantidad + ", precio=" + precio + ", descripcion=" + descripcion + '}';
    }
    
    
}
