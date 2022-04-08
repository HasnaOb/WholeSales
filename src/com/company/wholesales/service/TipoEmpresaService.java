package com.company.wholesales.service;

import com.jal.wholesales.model.TipoEmpresa;
import com.wholesales.exception.DataException;
import com.wholesales.exception.ServiceException;

public interface TipoEmpresaService {

	TipoEmpresa findById(long id) throws DataException, ServiceException;

}
