package com.company.wholesales.service;


import java.util.List;

import com.jal.wholesales.model.Categoria;
import com.jal.wholesales.service.CategoriaCriteria;
import com.wholesales.exception.DataException;
import com.wholesales.exception.InstanceNotFoundException;
import com.wholesales.exception.ServiceException;

public interface CategoriaService {
	
	 
	
 
	
	 

	Categoria findById(long id) throws InstanceNotFoundException, DataException, ServiceException;



	void update(  Categoria ca) throws ServiceException;





	Categoria create(  Categoria ca) throws DataException, ServiceException;



	void deleteById( Long id) throws DataException, InstanceNotFoundException;



	List<Categoria> findByCriteria(CategoriaCriteria cc) throws DataException;



	List<Categoria> findByNombre(String nombre) throws DataException, ServiceException;



	


 
	 
	// ..
	

}
