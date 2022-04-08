package com.jal.wholesales.dao;

import java.sql.Connection;
import java.util.List;

import com.jal.wholesales.model.Categoria;
import com.jal.wholesales.service.CategoriaCriteria;
import com.wholesales.exception.DataException;
import com.wholesales.exception.InstanceNotFoundException;

public interface CategoriaDAO {

	 
	public Categoria findById (Connection c, long id)
			throws  InstanceNotFoundException, DataException;

		 
		 
		/*public Boolean exists(Connection connection, int  id) 
	    		throws DataException;*/
		
		public Categoria create (Connection c, Categoria C)
			throws   DataException;
		
		public void update (Connection c, Categoria C)
			throws  InstanceNotFoundException, DataException;



		public List<Categoria> findByCriteria(Connection c, CategoriaCriteria categoria) throws DataException;



		public List<Categoria> findByNombre(Connection c, String nombre) throws DataException;
		
	 


		 
	 
}

