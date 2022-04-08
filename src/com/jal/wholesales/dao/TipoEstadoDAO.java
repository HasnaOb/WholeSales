package com.jal.wholesales.dao;

import java.sql.Connection;

import com.jal.wholesales.model.TipoEstado;
import com.wholesales.exception.DataException;

public interface TipoEstadoDAO {

	 

	TipoEstado findById(Connection c, long id);
	public TipoEstado create(Connection c,TipoEstado tp) throws DataException;
	public void update(Connection c,TipoEstado tipoEstado) throws DataException;
}

