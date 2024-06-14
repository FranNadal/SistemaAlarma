package alarma.edu.ar;

import java.util.HashSet;
import java.util.Objects;

public class Accion implements Comparable<Accion>{
Integer id;
//no va alarma
Alarma alarma;
Usuario usuario;
Integer fecha;
TipoOperacion tipoOperacion;
private String descripcion;



public Accion(Integer id,  Usuario usuario, Integer fecha, TipoOperacion tipoOperacion,String descripcion) {
	super();
	this.id = id;
	this.descripcion=descripcion;
	this.usuario = usuario;
	this.fecha = fecha;
	this.tipoOperacion = tipoOperacion;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Alarma getAlarma() {
	return alarma;
}
public void setAlarma(Alarma alarma) {
	this.alarma = alarma;
}
public Usuario getUsuario() {
	return usuario;
}
public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
}
public Integer getFecha() {
	return fecha;
}
public void setFecha(Integer fecha) {
	this.fecha = fecha;
}
public TipoOperacion getTipoOperacion() {
	return tipoOperacion;
}
public void setTipoOperacion(TipoOperacion tipoOperacion) {
	this.tipoOperacion = tipoOperacion;
}
@Override
public int compareTo(Accion o) {
	
	return this.id.compareTo(o.getId());
}

}
