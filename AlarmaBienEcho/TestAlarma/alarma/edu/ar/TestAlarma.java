package alarma.edu.ar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.TreeSet;

import org.junit.Test;

public class TestAlarma {

	@Test
	public void queSePuedaRegistrarUnaAlarmaEnLaCentral() {
		CentralAlarma centralAlarma= new CentralAlarma();
		
		Integer idAlarma=1;
		String codigoActivacion="ff";
		String codigoConfig="aa";
		String nombre="alarmaFran";
		Alarma alarma= new Alarma(idAlarma, codigoActivacion, codigoConfig, nombre);
		assertTrue(centralAlarma.agregarAlarma(alarma));
	}
	
	@Test
	public void queSePuedaRegistarUnUsuarioConfiguradorYActivadorEnLaCentral() {
		CentralAlarma centralAlarma= new CentralAlarma();
		
		Integer dni=4644;
		String nombre="franco";
		Usuario usuarioConfigurador = new Configurador(dni, nombre);
		Integer dni2=4645;
		Usuario usuarioActivador= new Activador(dni2, nombre);
		
		assertTrue(centralAlarma.agregarUsuario(usuarioActivador));
		assertTrue(centralAlarma.agregarUsuario(usuarioConfigurador));
	}
	/*
	@Test
	public void queSePuedaAgregarUnUsuarioConfiguradorAUnaAlarma()  {
	CentralAlarma centralAlarma= new CentralAlarma();
		
		Integer idAlarma=1;
		String codigoActivacion="ff";
		String codigoConfig="aa";
		String nombre="alarmaFran";
		Alarma alarma= new Alarma(idAlarma, codigoActivacion, codigoConfig, nombre);
		centralAlarma.agregarAlarma(alarma);
		
		Integer dni=4644;
		String nombree="franco";
		Usuario usuarioConfigurador = new Configurador(dni, nombree);
		centralAlarma.agregarUsuario(usuarioConfigurador);
		
		Integer idAccion=1;
		Alarma alarmaEncontrada=centralAlarma.buscarAlarmaPorId(idAlarma);
		Usuario usuarioEncontrado=centralAlarma.buscarUsuarioPorDni(dni);
		Integer fecha=11062024;
		TipoOperacion tipoOperacion=TipoOperacion.configuracion;
		Accion accion= new Accion(idAccion, alarmaEncontrada, usuarioEncontrado, fecha, tipoOperacion);
		
		centralAlarma.agregarUsuarioConfiguradorAUnaAlarma(accion);
		
		ArrayList<Usuario>cantidaddeUsuarios=centralAlarma.obtenerCantidadDeUsuariosDeUnaAlarma(idAlarma); 
		
		assertEquals(1, cantidaddeUsuarios.size());
}
	*/
	
	@Test
	public void queSePuedaAgregarUnUsuarioConfiguradorAUnaAlarmaa() throws Exception {
	CentralAlarma centralAlarma= new CentralAlarma();
		
		Integer idAlarma=1;
		String codigoActivacion="ff";
		String codigoConfig="aa";
		String nombre="alarmaFran";
		Alarma alarma= new Alarma(idAlarma, codigoActivacion, codigoConfig, nombre);
		centralAlarma.agregarAlarma(alarma);
		
		Integer dni=4644;
		String nombree="franco";
		Usuario usuarioConfigurador = new Configurador(dni, nombree);
		centralAlarma.agregarUsuario(usuarioConfigurador);
		
		Integer dniActivador=4645;
		String nombreActivador="Andres";
		Usuario usuarioActivador = new Activador(dniActivador, nombreActivador);
		centralAlarma.agregarUsuario(usuarioActivador);
		
		alarma.getUsuarioValidos().add(usuarioConfigurador);
		
		centralAlarma.AgregarConfiguradorAUnaAlarmaaa(dni, idAlarma, dniActivador, codigoConfig);
		
	Usuario agregado=centralAlarma.buscarUsuarioDeUnaAlarma(alarma,dniActivador);
	
	assertNotNull(agregado);
	}
	
	@Test (expected = Exception.class)
	
	public void queSeLanzeUnaExcepcionSiSeIngresaElCodigoConfigErroneo() throws Exception {
CentralAlarma centralAlarma= new CentralAlarma();
		
		Integer idAlarma=1;
		String codigoActivacion="ff";
		String codigoConfig="aa";
		String nombre="alarmaFran";
		Alarma alarma= new Alarma(idAlarma, codigoActivacion, codigoConfig, nombre);
		centralAlarma.agregarAlarma(alarma);
		
		Integer dni=4644;
		String nombree="franco";
		Usuario usuarioConfigurador = new Configurador(dni, nombree);
		centralAlarma.agregarUsuario(usuarioConfigurador);
		
		Integer dniActivador=4645;
		String nombreActivador="Andres";
		Usuario usuarioActivador = new Activador(dniActivador, nombreActivador);
		centralAlarma.agregarUsuario(usuarioActivador);
		
		alarma.getUsuarioValidos().add(usuarioConfigurador);
		
		centralAlarma.AgregarConfiguradorAUnaAlarmaaa(dni, idAlarma, dniActivador, "c");
	}
	
	@Test 
	public void queSePuedaAgregarUnSensorAunaAlarma() throws Exception {
		CentralAlarma centralAlarma= new CentralAlarma();
		Integer idAlarma=1;
		String codigoActivacion="ff";
		String codigoConfig="aa";
		String nombre="alarmaFran";
		Alarma alarma= new Alarma(idAlarma, codigoActivacion, codigoConfig, nombre);
		centralAlarma.agregarAlarma(alarma);
		
		Integer dni=4644;
		String nombree="franco";
		Usuario usuarioConfigurador = new Configurador(dni, nombree);
		centralAlarma.agregarUsuario(usuarioConfigurador);
		
		Integer dniActivador=4645;
		String nombreActivador="Andres";
		Usuario usuarioActivador = new Activador(dniActivador, nombreActivador);
		centralAlarma.agregarUsuario(usuarioActivador);
		
		alarma.getUsuarioValidos().add(usuarioConfigurador);
		
		centralAlarma.AgregarConfiguradorAUnaAlarmaaa(dni, idAlarma, dniActivador, codigoConfig);
		Integer idSensor=1;
		Sensor sensor = new Sensor(idSensor, false);
		centralAlarma.agregarSensorAunaAlarma(idAlarma,codigoConfig,sensor,dni);
		
		Sensor agregado= centralAlarma.buscarSensor(alarma,idSensor);
		
		assertNotNull(agregado);
		
		
	}
	
	@Test (expected = Exception.class)
	
	public void queSeLanceUnaExcepcionSiQuieroAgregarElMismoSensorAUnaAlarma() throws Exception {
		CentralAlarma centralAlarma= new CentralAlarma();
		Integer idAlarma=1;
		String codigoActivacion="ff";
		String codigoConfig="aa";
		String nombre="alarmaFran";
		Alarma alarma= new Alarma(idAlarma, codigoActivacion, codigoConfig, nombre);
		centralAlarma.agregarAlarma(alarma);
		
		Integer dni=4644;
		String nombree="franco";
		Usuario usuarioConfigurador = new Configurador(dni, nombree);
		centralAlarma.agregarUsuario(usuarioConfigurador);
		
		Integer dniActivador=4645;
		String nombreActivador="Andres";
		Usuario usuarioActivador = new Activador(dniActivador, nombreActivador);
		centralAlarma.agregarUsuario(usuarioActivador);
		
		alarma.getUsuarioValidos().add(usuarioConfigurador);
		
		centralAlarma.AgregarConfiguradorAUnaAlarmaaa(dni, idAlarma, dniActivador, codigoConfig);
		Integer idSensor=1;
		Sensor sensor = new Sensor(idSensor, false);
		centralAlarma.agregarSensorAunaAlarma(idAlarma,codigoConfig,sensor,dni);
		centralAlarma.agregarSensorAunaAlarma(idAlarma,codigoConfig,sensor,dni);
	}
	
	@Test
	
	public void queSePuedaActivarElSensorDeUnaAlarma() throws Exception {
		CentralAlarma centralAlarma= new CentralAlarma();
		Integer idAlarma=1;
		String codigoActivacion="ff";
		String codigoConfig="aa";
		String nombre="alarmaFran";
		Alarma alarma= new Alarma(idAlarma, codigoActivacion, codigoConfig, nombre);
		centralAlarma.agregarAlarma(alarma);
		
		Integer dni=4644;
		String nombree="franco";
		Usuario usuarioConfigurador = new Configurador(dni, nombree);
		centralAlarma.agregarUsuario(usuarioConfigurador);
		
		Integer dniActivador=4645;
		String nombreActivador="Andres";
		Usuario usuarioActivador = new Activador(dniActivador, nombreActivador);
		centralAlarma.agregarUsuario(usuarioActivador);
		
		alarma.getUsuarioValidos().add(usuarioConfigurador);
		
		centralAlarma.AgregarConfiguradorAUnaAlarmaaa(dni, idAlarma, dniActivador, codigoConfig);
		Integer idSensor=1;
		Sensor sensor = new Sensor(idSensor, false);
		centralAlarma.agregarSensorAunaAlarma(idAlarma,codigoConfig,sensor,dni);
		
		centralAlarma.ActivarSensor(idSensor,idAlarma,codigoConfig,dni);
		
		assertTrue(sensor.getEstado());
	}
	
	
	@Test
	public void queSePuedaActivarUnaAlarma() throws Exception {
		CentralAlarma centralAlarma= new CentralAlarma();
		Integer idAlarma=1;
		String codigoActivacion="ff";
		String codigoConfig="aa";
		String nombre="alarmaFran";
		Alarma alarma= new Alarma(idAlarma, codigoActivacion, codigoConfig, nombre);
		centralAlarma.agregarAlarma(alarma);
		
		Integer dni=4644;
		String nombree="franco";
		Usuario usuarioConfigurador = new Configurador(dni, nombree);
		centralAlarma.agregarUsuario(usuarioConfigurador);
		
		Integer dniActivador=4645;
		String nombreActivador="Andres";
		Usuario usuarioActivador = new Activador(dniActivador, nombreActivador);
		centralAlarma.agregarUsuario(usuarioActivador);
		
		alarma.getUsuarioValidos().add(usuarioConfigurador);
		
		centralAlarma.AgregarConfiguradorAUnaAlarmaaa(dni, idAlarma, dniActivador, codigoConfig);
		Integer idSensor=1;
		Sensor sensor = new Sensor(idSensor, false);
		centralAlarma.agregarSensorAunaAlarma(idAlarma,codigoConfig,sensor,dni);
		centralAlarma.ActivarSensor(idSensor,idAlarma,codigoConfig,dni);
		
		centralAlarma.activarAlarma(idAlarma,dniActivador,codigoActivacion,idSensor);
		
		
		assertTrue(alarma.getEstadoAlarma());
	}
	
	@Test (expected = Exception.class)
	
	public void queArrojeUnaExcepcionSiSeQuiereActivarUnaAlarmaPeroElSensorEstaDesactivado() throws Exception {
		CentralAlarma centralAlarma= new CentralAlarma();
		Integer idAlarma=1;
		String codigoActivacion="ff";
		String codigoConfig="aa";
		String nombre="alarmaFran";
		Alarma alarma= new Alarma(idAlarma, codigoActivacion, codigoConfig, nombre);
		centralAlarma.agregarAlarma(alarma);
		
		Integer dni=4644;
		String nombree="franco";
		Usuario usuarioConfigurador = new Configurador(dni, nombree);
		centralAlarma.agregarUsuario(usuarioConfigurador);
		
		Integer dniActivador=4645;
		String nombreActivador="Andres";
		Usuario usuarioActivador = new Activador(dniActivador, nombreActivador);
		centralAlarma.agregarUsuario(usuarioActivador);
		
		alarma.getUsuarioValidos().add(usuarioConfigurador);
		
		centralAlarma.AgregarConfiguradorAUnaAlarmaaa(dni, idAlarma, dniActivador, codigoConfig);
		Integer idSensor=1;
		Sensor sensor = new Sensor(idSensor, false);
		centralAlarma.agregarSensorAunaAlarma(idAlarma,codigoConfig,sensor,dni);
	
		
		centralAlarma.activarAlarma(idAlarma,dniActivador,codigoActivacion,idSensor);
	}
	
	@Test
	public void queParaUnaAlarmaDeterminadaSePuedaObtenerUnaColeccionOrdenadaDeAcccionesDeTipoConfiguracionOdenadasPorIdDeAccion() throws Exception {
		CentralAlarma centralAlarma= new CentralAlarma();
		Integer idAlarma=1;
		String codigoActivacion="ff";
		String codigoConfig="aa";
		String nombre="alarmaFran";
		Alarma alarma= new Alarma(idAlarma, codigoActivacion, codigoConfig, nombre);
		centralAlarma.agregarAlarma(alarma);
		
		Integer dni=4644;
		String nombree="franco";
		Usuario usuarioConfigurador = new Configurador(dni, nombree);
		centralAlarma.agregarUsuario(usuarioConfigurador);
		
		Integer dniActivador=4645;
		String nombreActivador="Andres";
		Usuario usuarioActivador = new Activador(dniActivador, nombreActivador);
		centralAlarma.agregarUsuario(usuarioActivador);
		
		alarma.getUsuarioValidos().add(usuarioConfigurador);
		
		centralAlarma.AgregarConfiguradorAUnaAlarmaaa(dni, idAlarma, dniActivador, codigoConfig);
		Integer idSensor=1;
		Sensor sensor = new Sensor(idSensor, false);
		centralAlarma.agregarSensorAunaAlarma(idAlarma,codigoConfig,sensor,dni);
		centralAlarma.ActivarSensor(idSensor,idAlarma,codigoConfig,dni);
		
		centralAlarma.activarAlarma(idAlarma,dniActivador,codigoActivacion,idSensor);
		
		TreeSet<Accion>accionesOrdenadas=centralAlarma.obtenerAccionesOrdenadasPorId(idAlarma);
		
		
	    ArrayList<Integer> idDeAccionesEsperadas = new ArrayList<>();
	    idDeAccionesEsperadas.add(1);
	    idDeAccionesEsperadas.add(2);
	    idDeAccionesEsperadas.add(3);
	    idDeAccionesEsperadas.add(4);
	    
	    ArrayList<Integer> idsOrdenados = new ArrayList<>();
	    for (Accion accion : accionesOrdenadas) {
			idsOrdenados.add(accion.getId());
		}
	    
	    
	    
	  assertEquals(idDeAccionesEsperadas, idsOrdenados);
	    
		
		
		
	}
}
