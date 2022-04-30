package com.jal.wholesales.dao;

import java.sql.Connection;
import java.util.List;

import com.jal.wholesales.model.Seccion;
import com.wholesales.exception.DataException;
import com.wholesales.exception.InstanceNotFoundException;

public interface SeccionDAO {

	Seccion findById(Connection c, long id) throws DataException;

	public Seccion create(Connection c, Seccion seccion) throws DataException;

	public void update(Connection c, Seccion seccion) throws DataException;

	List<Seccion> findByAll(Connection c) throws InstanceNotFoundException, DataException;
}
