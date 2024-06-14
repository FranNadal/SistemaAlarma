package alarma.edu.ar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class Alarma {
	Integer id;
	String codigoActDes;
	String codigoConfig;
	String nombre;
	HashSet<Usuario> usuarioValidos;
	ArrayList<Accion> accionesRealizadas;
	HashSet<Sensor> sensores;
	Boolean EstadoAlarma;

	public Alarma(Integer id, String codigoActDes, String codigoConfig, String nombre) {
		super();
		this.id = id;
		this.codigoActDes = codigoActDes;
		this.codigoConfig = codigoConfig;
		this.nombre = nombre;
		this.usuarioValidos = new HashSet<>();
		this.accionesRealizadas = new ArrayList<>();
		this.sensores = new HashSet<>();
		this.EstadoAlarma=false;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigoActDes() {
		return codigoActDes;
	}

	public void setCodigoActDes(String codigoActDes) {
		this.codigoActDes = codigoActDes;
	}

	public String getCodigoConfig() {
		return codigoConfig;
	}

	public void setCodigoConfig(String codigoConfig) {
		this.codigoConfig = codigoConfig;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public HashSet<Usuario> getUsuarioValidos() {
		return usuarioValidos;
	}

	public void setUsuarioValidos(HashSet<Usuario> usuarioValidos) {
		this.usuarioValidos = usuarioValidos;
	}

	public Boolean getEstadoAlarma() {
		return EstadoAlarma;
	}

	public void setEstadoAlarma(Boolean estadoAlarma) {
		EstadoAlarma = estadoAlarma;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public ArrayList<Accion> getAccionesRealizadas() {
		return accionesRealizadas;
	}

	public void setAccionesRealizadas(ArrayList<Accion> accionesRealizadas) {
		this.accionesRealizadas = accionesRealizadas;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alarma other = (Alarma) obj;
		return Objects.equals(id, other.id);
	}

	public void agregarUsuarioValido(Configurador usuario) {
		usuarioValidos.add(usuario);
	}

	public void registrarAccion(Accion accion) {
		accionesRealizadas.add(accion);
	}

	public void agregarUsuarioValido(Integer idUsuario, Usuario usuarioAAgregar, String codigoConfig) throws Exception {
		//el admin
		Usuario usuarioConfigurador = buscarUsuarioPorId(idUsuario);// usuario configurador que ya esta dentro de usuarios validos 
		
		verificarQueUnUsuarioSeaConfigurador(usuarioConfigurador);
		
		verificarQueElCodigoConfigSeaElCorrecto(codigoConfig);

		this.usuarioValidos.add(usuarioAAgregar);
		registrarAccion(usuarioConfigurador, TipoOperacion.configuracion, "se agrego el usuario a la alarma");
	}

	private void registrarAccion(Usuario usuarioConfigurador, TipoOperacion configuracion, String string) {
		Accion accion = new Accion(this.accionesRealizadas.size() + 1, usuarioConfigurador, 12062024, configuracion,
				string);
		accionesRealizadas.add(accion);

	}

	private void verificarQueElCodigoConfigSeaElCorrecto(String codigoConfig2) throws Exception {
		if (!(this.codigoConfig.equals(codigoConfig2))) {
			throw new Exception("Codigo Incorrecto");
		}

	}

	private void verificarQueUnUsuarioSeaConfigurador(Usuario usuarioEncontrado) throws Exception {
		if (!(usuarioEncontrado instanceof Configurador)) {
			throw new Exception("el Usuario no es configurador");
		}

	}

	private Usuario buscarUsuarioPorId(Integer idUsuario) throws Exception {
		for (Usuario usuario : usuarioValidos) {
			if (usuario.getDni().equals(idUsuario)) {
				return usuario;
			}
		}
		throw new Exception("usuario no econtrado");
	}

	public Usuario buscarUsuario(Integer dniActivador) throws Exception {
		for (Usuario usuario : usuarioValidos) {
			if (usuario.getDni().equals(dniActivador)) {
				return usuario;
			}
		}
		throw new Exception("el usuario no Existe dentro de Alarma");
	}

	public void agregarSensor(String codigoConfig2, Sensor sensor, Integer idUsuario) throws Exception {
	verificarQueElCodigoConfigSeaElCorrecto(codigoConfig2);
	verificarQueNoExistaEnLaAlarmaElMismoSensor(sensor);
	Usuario configurador = buscarUsuarioPorId(idUsuario);
	verificarQueUnUsuarioSeaConfigurador(configurador);
	this.sensores.add(sensor);
	registrarAccion(configurador, TipoOperacion.configuracion, "se agrego el sensor a la alarma");
	}
	
	public void verificarQueNoExistaEnLaAlarmaElMismoSensor(Sensor sensor) throws Exception {
	if(sensores.contains(sensor)) {
		throw new Exception("El sensor ya Existe");
	}
	}

	public Sensor buscarSensor(Integer idSensor) throws Exception {
		for (Sensor sensor : sensores) {
			if(sensor.getId().equals(idSensor)) {
				return sensor;
			}
		}
		throw new Exception("Sensor no Encontrado");
	}

	public void activarSensor(Integer idSensor, String codigoConfig2, Integer dni) throws Exception {
		Sensor sensor = buscarSensor(idSensor);
		verificarQueElCodigoConfigSeaElCorrecto(codigoConfig2);
		Usuario configurador= buscarUsuarioPorId(dni);
		verificarQueUnUsuarioSeaConfigurador(configurador);
		verificarQueElSensorExista(sensor);
		sensor.setEstado(true);
		registrarAccion(configurador, TipoOperacion.configuracion, "se activo el sensor en la alarma");
		
	}

	private void verificarQueElSensorExista(Sensor sensor) throws Exception {
		if(!(sensores.contains(sensor))) {
			throw new Exception("El sensor no existe");
		}
		
	}

	public void ActivarAlarma(Integer dniActivador, String codigoActivacion, Integer idSensor) throws Exception {
		Usuario activador=buscarUsuario(dniActivador);
		Sensor sensor=buscarSensor(idSensor);
		verificarQueElUsuarioSeaActivador(activador);
		verificarQueElCodigoDeActivacionSeaCorrecto(codigoActivacion);
		verificarQueElSensorEsteActivado(sensor);
		setEstadoAlarma(true);
		registrarAccion(activador, TipoOperacion.activacion, "se activo el sensor a la alarma");
		
	}

	private void verificarQueElSensorEsteActivado(Sensor sensor) throws Exception {
		if((sensor.getEstado()==false)) {
			throw new Exception("el sensor esta desactivado");
		}
		
	}

	private void verificarQueElCodigoDeActivacionSeaCorrecto(String codigoActivacion) throws Exception {
		if(!(this.codigoActDes.equals(codigoActivacion))) {
			throw new Exception("Codigo De Activacion/Desactivacion incorrecto");
		}
		
	}

	private void verificarQueElUsuarioSeaActivador(Usuario activador) throws Exception {
		if(!(activador instanceof Activador )) {
			throw new Exception("No es Un Activador");
		}
		
	}

}
