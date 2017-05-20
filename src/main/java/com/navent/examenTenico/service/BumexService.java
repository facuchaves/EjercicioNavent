/**
 * 
 */
package com.navent.examenTenico.service;

/**
 * Dao de bumeran
 * 
 * @author facundo.lopez
 *
 */
public interface BumexService<T> {
	
	/**
	 * 
	 * Inserta un pedido o lo actualiza si existe.
	 * Luego de insertarlo o actualizarlo, lo pone en cache.
	 * 
	 * @param object
	 */
	public void insertOrUpdate(T object);
	
	/**
	 * Elimina el objeto que corresponde al id recibido.
	 * Lo elimina tanto de la BBDD como de la cache.
	 * 
	 * @param object
	 */
	public void delete(T object);
	
	/**
	 * Busca un objeto por id.
	 * Primero lo busca en la cache y si no esta lo busca en la BBDD.
	 * 
	 * @param id
	 * @return
	 */
	public T select(Integer id);
	
}
