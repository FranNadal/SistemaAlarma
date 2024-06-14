package alarma.edu.ar;

import java.util.HashSet;
import java.util.Objects;

import javax.swing.tree.AbstractLayoutCache;

public abstract  class Usuario {
Integer dni;
String nombre;

public Usuario(Integer dni, String nombre) {
	super();
	this.dni = dni;
	this.nombre = nombre;
}
public Integer getDni() {
	return dni;
}
public void setDni(Integer dni) {
	this.dni = dni;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
@Override
public int hashCode() {
	return Objects.hash(dni);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Usuario other = (Usuario) obj;
	return Objects.equals(dni, other.dni);
}


}
