package com.jal.wholesales.dao;

import java.sql.Connection;

import com.jal.wholesales.model.TipoEmpresa;
import com.wholesales.exception.DataException;

public interface TipoEmpresaDAO {

	TipoEmpresa findById(Connection c, long id) throws DataException;
}
