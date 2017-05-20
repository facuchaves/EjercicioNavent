/**
 * 
 */
package com.navent.examenTenico.service;

import org.apache.log4j.Logger;

import com.navent.examenTenico.dao.BumexMemcached;
import com.navent.examenTenico.dao.PedidoDAO;
import com.navent.examenTenico.model.Pedido;

/**
 * Dao particular para los pedidos
 * 
 * @author facundo.lopez
 *
 */
public class PedidoService implements BumexService<Pedido>{
	
	PedidoDAO pedidoDAO;//Esto se manejaria con IOC en un ambiente real
	BumexMemcached bumexMemcached;//Esto se manejaria con IOC en un ambiente real
	Logger logger = Logger.getRootLogger();
	
	public PedidoService() {
		super();
	}
	
	public void insertOrUpdate(Pedido pedido){
		logger.info("Buscando el pedido : " + pedido.getId() + " para insertar/actualizar.");
		Pedido pedidoEncontrado = null;
		
		if( pedido.getId() != null ){
			//Busco por id para saber si creo o actulizo
			pedidoEncontrado = select( pedido.getId().intValue() );
		}
		
		if( pedidoEncontrado == null ){
			logger.info("Insertando pedido.");
			//Aca deberia setear el id incremental, no lo hago porque decidi usar hibernate y el ya maneja automaticamente el id.
			pedidoDAO.insertOrUpdate(pedido);
			
			logger.info("Actualizando cache pedido " + pedido.getId());
			bumexMemcached.set(pedido.getId().toString(), pedido);
		} else {
			logger.info("Actualizando pedido con id " + pedido.getId() );
			mapearPedido( pedidoEncontrado , pedido );
			pedidoDAO.insertOrUpdate(pedido);
			
			logger.info("Actualizando cache pedido " + pedido.getId());
			bumexMemcached.set(pedido.getId().toString(), pedido);
		}
		
	}
	
	/**
	 * Mapea los atributos de un pedido en otro.
	 * Esto podria usarse con generic.
	 * 
	 * @param pedidoEncontrado
	 * @param pedido
	 */
	private void mapearPedido(Pedido pedidoEncontrado, Pedido pedido) {
		logger.info("Mapeando pedido");
		
		pedidoEncontrado.setNombre( pedido.getNombre() );
		pedidoEncontrado.setMonto( pedido.getMonto() );
		pedidoEncontrado.setDescuento( pedido.getDescuento() );
	}

	public void delete(Pedido pedido){
		logger.info("Eliminando pedido " + pedido.getId());
		pedidoDAO.delete(pedido);
		
		logger.info("Quitando pedido de la cache");
		bumexMemcached.delete( pedido.getId().toString() );
		
		logger.info("Pedido " + pedido.getId() + " eliminado correctamente.");
	}
	
	public Pedido select(Integer idPedido){
		logger.info("Consultando pedido " + idPedido);
		
		logger.info("Buscando pedido" + idPedido + " en la cache");
		Pedido pedido = (Pedido) bumexMemcached.get( idPedido.toString() );
		
		if ( pedido != null ){
			logger.debug("Pedido encontrado en la cache");
			return pedido;
		}
		
		logger.info("Buscando pedido " + idPedido + " en la BBDD");
		pedido = pedidoDAO.select(idPedido);
		
		if( pedido == null ){
			logger.debug("Pedido " + idPedido + " no encontrado.");
		}
		return pedido;
	}

	public PedidoDAO getPedidoDAO() {
		return pedidoDAO;
	}

	public void setPedidoDAO(PedidoDAO pedidoDAO) {
		this.pedidoDAO = pedidoDAO;
	}

	public BumexMemcached getBumexMemcached() {
		return bumexMemcached;
	}

	public void setBumexMemcached(BumexMemcached bumexMemcached) {
		this.bumexMemcached = bumexMemcached;
	}
	
}
