package com.company.wholesales.service;

import com.jal.wholesales.model.TipoEstado;
import com.wholesales.exception.DataException;
import com.wholesales.exception.ServiceException;

public interface TipoEstadoService {

	TipoEstado findById(long id) throws DataException, ServiceException;

	TipoEstado create(TipoEstado te) throws DataException, ServiceException;

	void update(TipoEstado te) throws ServiceException;

}
