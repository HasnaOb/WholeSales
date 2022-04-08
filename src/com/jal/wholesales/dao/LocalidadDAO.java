package com.jal.wholesales.dao;

import java.sql.Connection;

import com.jal.wholesales.model.Localidad;
import com.wholesales.exception.DataException;

	public interface LocalidadDAO {
		 

		Localidad findById(Connection c, long id) throws DataException;
		public Localidad create(Connection c ,Localidad l) throws DataException;
		
		
		public void update(Connection c,Localidad l) throws DataException;
}
