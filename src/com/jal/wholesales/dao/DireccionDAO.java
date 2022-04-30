package com.jal.wholesales.dao;

import java.sql.Connection;
import java.util.List;

import com.jal.wholesales.model.Direccion;

import com.wholesales.exception.DataException;
import com.wholesales.exception.InstanceNotFoundException;

public interface DireccionDAO {

	Direccion findById(Connection c, long id) throws InstanceNotFoundException, DataException;

	List<Direccion> findByEmpresa(Connection c, Long idEmpresa) throws InstanceNotFoundException, DataException;

	public Direccion create(Connection c, Direccion d) throws DataException;

	public void update(Connection c, Direccion d) throws InstanceNotFoundException, DataException;

	/*
	 * void deleteById(Connection c, Long id) throws InstanceNotFoundException,
	 * DataException;
	 */

}
