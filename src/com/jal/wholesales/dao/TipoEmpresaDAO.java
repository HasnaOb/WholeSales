package com.jal.wholesales.dao;

import java.sql.Connection;

import com.jal.wholesales.model.TipoEmpresa;

public interface TipoEmpresaDAO {
	
	 
	TipoEmpresa findById(Connection c, long id);
}
