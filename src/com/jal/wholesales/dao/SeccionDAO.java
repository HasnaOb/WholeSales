package com.jal.wholesales.dao;

import java.sql.Connection;

import com.jal.wholesales.model.Seccion;
import com.wholesales.exception.DataException;

public interface SeccionDAO {

	 

	Seccion findById(Connection c, long id);
	public Seccion create(Connection c, Seccion seccion) throws DataException;
	public void update( Connection c, Seccion seccion) throws DataException;
}

