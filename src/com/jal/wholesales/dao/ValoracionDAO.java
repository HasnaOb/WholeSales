package com.jal.wholesales.dao;

import java.sql.Connection;

import com.jal.wholesales.model.Valoracion;
import com.wholesales.exception.DataException;
import com.wholesales.exception.InstanceNotFoundException;

public interface ValoracionDAO {

	Valoracion findById(Connection c, long id) throws InstanceNotFoundException, DataException;

	public void update(Connection c, Valoracion valoracion) throws InstanceNotFoundException, DataException;

	public Valoracion create(Connection c, Valoracion valoracion) throws DataException;

	void deleteById(Connection c, Long id) throws DataException, InstanceNotFoundException;

}
	