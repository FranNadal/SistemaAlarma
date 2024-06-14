package alarma.edu.ar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

public class CentralAlarma {

	
	HashSet<Alarma>alarmas;
	HashSet<Usuario>usuarios;
	
	public CentralAlarma() {
		this.alarmas= new HashSet<>();
		this.usuarios= new HashSet<>();
		
	}

	public boolean agregarAlarma(Alarma alarma) {
	
		return alarmas.add(alarma);
	}

	public boolean agregarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return usuarios.add(usuario);
	}
	
	public Usuario buscarUsuarioPorDni(Integer dni) {
		for (Usuario usuario : usuarios) {
			if(usuario.getDni().equals(dni)) {
				return usuario;
			}
		}
		return null;
	}
	
	public Alarma buscarAlarmaPorId(Integer idAlarma) {
		for (Alarma alarma : alarmas) {
			if(alarma.getId().equals(idAlarma)) {
				return alarma;
			}
		}
		return null;
	}

public void AgregarConfiguradorAUnaAlarmaaa(Integer idUsuario,Integer idAlarma,Integer idUsuarioAagregar,String codigoConfiguracion) throws Exception {
	Alarma alarmaEncontrada=buscarAlarmaPorId(idAlarma);
	Usuario usuarioAAgregar=buscarUsuarioPorDni(idUsuarioAagregar);
	
	alarmaEncontrada.agregarUsuarioValido(idUsuario,usuarioAAgregar,codigoConfiguracion);
}


public Usuario buscarUsuarioDeUnaAlarma(Alarma alarma, Integer dniActivador) throws Exception{
	
	return alarma.buscarUsuario(dniActivador);
}


public void agregarSensorAunaAlarma(Integer idAlarma, String codigoConfig, Sensor sensor,Integer idUsuario) throws Exception {
Alarma alarmaAtratar = buscarAlarmaPorId(idAlarma);

alarmaAtratar.agregarSensor(codigoConfig,sensor,idUsuario);
	
}


public Sensor buscarSensor(Alarma alarma, Integer idSensor) throws Exception {
	if(alarmas.contains(alarma)) {
	
		return alarma.buscarSensor(idSensor);
	}
	return null;
}


public void ActivarSensor(Integer idSensor, Integer idAlarma, String codigoConfig, Integer dni) throws Exception {
Alarma alarmaAtratar=buscarAlarmaPorId(idAlarma);

alarmaAtratar.activarSensor(idSensor,codigoConfig,dni);
	
}

public void activarAlarma(Integer idAlarma, Integer dniActivador, String codigoActivacion, Integer idSensor) throws Exception {
	Alarma alarmaAtrata=buscarAlarmaPorId(idAlarma);
	
	alarmaAtrata.ActivarAlarma(dniActivador,codigoActivacion,idSensor);
	
}

public TreeSet<Accion> obtenerAccionesOrdenadasPorId(Integer idAlarma) {
	TreeSet<Accion>accionesordenadas= new TreeSet<>();
	Alarma alarmaAtratar=buscarAlarmaPorId(idAlarma);
	accionesordenadas.addAll(alarmaAtratar.getAccionesRealizadas()) ;  
	
	return accionesordenadas;
}







}
