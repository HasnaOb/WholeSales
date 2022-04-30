package com.jal.wholesales.dao;

import java.sql.Connection;
import java.util.List;

import com.jal.wholesales.model.Provincia;
import com.wholesales.exception.DataException;
import com.wholesales.exception.InstanceNotFoundException;

public interface ProvinciaDAO {

	public Provincia findById(Connection c, long id) throws InstanceNotFoundException, DataException;

	public List<Provincia> findByPais(Connection c, long idPais) throws DataException;

	public Provincia create(Connection c, Provincia provincia) throws DataException;

	public void update(Connection c, Provincia provincia) throws DataException;
}
