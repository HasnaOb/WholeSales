package com.jal.wholesales.dao;

import java.sql.Connection;

import com.jal.wholesales.model.Pais;
import com.wholesales.exception.DataException;

public interface PaisDAO {
	 

	Pais findById(Connection c, long id) throws DataException;
	public Pais create(Connection c ,Pais p) throws DataException;
	
	
	public void update(Connection c,Pais p) throws DataException;
}
