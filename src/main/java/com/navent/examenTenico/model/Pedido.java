/**
 * 
 */
package com.navent.examenTenico.model;

/**
 * @author facundo
 *
 */
//@Entity
//@Table(name = "pedido", schema = "public" )
//@Cacheable(true)
//@Cache(usage=CacheConcurrencyStrategy.TRANSACTIONAL)
public class Pedido{
	
	private Long id;
	private int version;
	private String nombre;
	private Double monto;
	private Double descuento;
	
//	@SequenceGenerator(name = "generator", sequenceName = "pedido_id_seq")
//	@Id
//	@GeneratedValue(strategy = SEQUENCE, generator = "generator")
//	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
//	@Version
//	@Column(name = "version", nullable = false)		
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
//	@Column(name = "nombre")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
//	@Column(name = "monto")
	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}
	
//	@Column(name = "descuento")
	public Double getDescuento() {
		return descuento;
	}

	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

}
