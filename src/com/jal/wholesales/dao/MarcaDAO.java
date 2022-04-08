package com.jal.wholesales.dao;

import java.sql.Connection;

import com.jal.wholesales.model.Marca;
import com.wholesales.exception.DataException;
import com.wholesales.exception.InstanceNotFoundException;

public interface MarcaDAO {

	 

	Marca findById(Connection c, long id) throws InstanceNotFoundException, DataException;
	
	public Marca create (Connection c, Marca m)
			throws    DataException;
	       
	       
	      
	    		
		
		public void update (Connection c, Marca m)
			throws  InstanceNotFoundException, DataException;

		/*void deleteById(Connection c, Long id) throws InstanceNotFoundException, DataException;*/

		 
}

