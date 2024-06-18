package ar.edu.unju.fi.model;

import org.springframework.stereotype.Component;

@Component
public class Carrera {
	private Integer codigo;
	private String nombre;
	private Byte cantidadDeAnios;
	private String estado;
	
	public Carrera() {
		// TODO Auto-generated constructor stub
	}

	public Carrera(Integer codigo, String nombre, Byte cantidadDeAnios, String estado) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.cantidadDeAnios = cantidadDeAnios;
		this.estado = estado;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Byte getCantidadDeAnios() {
		return cantidadDeAnios;
	}

	public void setCantidadDeAnios(Byte cantidadDeAnios) {
		this.cantidadDeAnios = cantidadDeAnios;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Carrera [codigo=" + codigo + ", nombre=" + nombre + ", cantidadDeAnios=" + cantidadDeAnios + ", estado="
				+ estado + "]";
	}
	
	
}