package com.company.wholesales.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.company.wholesales.service.TipoEmpresaService;
import com.jal.wholesales.dao.TipoEmpresaDAO;
import com.jal.wholesales.dao.TipoEmpresaDAOImpl;
import com.jal.wholesales.dao.util.ConnectionManager;
import com.jal.wholesales.dao.util.JDBCUtils;
import com.jal.wholesales.model.TipoEmpresa;
import com.wholesales.exception.DataException;
import com.wholesales.exception.ServiceException;

public class TipoEmpresaServiceImpl implements TipoEmpresaService{
	private TipoEmpresaDAO tipoEmpresaDAO = null;
	
	
	public TipoEmpresaServiceImpl() {
		 
		tipoEmpresaDAO = new TipoEmpresaDAOImpl();
	}
	@Override
	public TipoEmpresa findById (long id) throws DataException, ServiceException {
		 Connection c=null;
		TipoEmpresa tipoEmpresa = null;
		boolean commitOrRollback = false;
		try  {
			c = ConnectionManager.getConnection();					
			
			c.setAutoCommit(false);
			
			tipoEmpresa =tipoEmpresaDAO.findById(c, id);
								
			commitOrRollback = true;
			

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new ServiceException(id+"", sqle);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
			
		} finally {
			JDBCUtils.closeConnection(c, commitOrRollback);
		}
		return tipoEmpresa;	
	}

	 
}
