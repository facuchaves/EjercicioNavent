package com.navent.examenTenico.dao;

import com.navent.examenTenico.model.Pedido;
/**
 * Dao particular para los pedidos
 * 
 * @author facundo.lopez
 *
 */
public class PedidoDAO implements BumexDAO<Pedido>{

	public PedidoDAO() {
		super();
	}
	
	/**
	 * 
	 * Inserta un nuevo pedido en la base de datos o modifica un pedido existente 
	 * (en cado de crear uno nuevo, el pedido pasado como parámetro se completa con el nuevo id).
	 * 
	 * @param pedido
	 */
	public void insertOrUpdate(Pedido pedido){
	}
	
	/**
	 * 
	 * Elimina el pedido que corresponde al id recibido.
	 * 
	 * @param pedido
	 */
	public void delete(Pedido pedido){
		
	}
	
	/**
	 * Busca un pedido por id.
	 * @param idPedido
	 * @return
	 */
	public Pedido select(Integer idPedido){
		return null;
	}

}