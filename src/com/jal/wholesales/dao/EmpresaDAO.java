package com.jal.wholesales.dao;

import java.sql.Connection;
import java.util.List;

import com.jal.wholesales.model.Empresa;
import com.jal.wholesales.service.EmpresaCriteria;
import com.wholesales.exception.DataException;
import com.wholesales.exception.InstanceNotFoundException;

public interface EmpresaDAO {

	public Empresa findById(Connection c, long id) throws InstanceNotFoundException, DataException;

	public Empresa findByEmail(Connection c, String email) throws DataException;

	public List<Empresa> findByNombre(Connection c, String nombre) throws DataException;

	/*
	 * public Boolean exists(Connection connection, String email) throws
	 * DataException;
	 */

	public Empresa create(Connection c, Empresa e) throws DataException;

	public void update(Connection c, Empresa e) throws InstanceNotFoundException, DataException;

	List<Empresa> findByCriteria(Connection c, EmpresaCriteria criteria) throws DataException;

	void deleteById(Connection c, Long id) throws DataException, InstanceNotFoundException;

	/*
	 * void deleteById(Connection c, Long id) throws InstanceNotFoundException,
	 * DataException;
	 */

}
	 



