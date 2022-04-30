package com.company.wholesales.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.company.wholesales.service.TipoEmpresaService;
import com.jal.wholesales.dao.TipoEmpresaDAO;
import com.jal.wholesales.dao.TipoEmpresaDAOImpl;
import com.jal.wholesales.dao.util.ConnectionManager;
import com.jal.wholesales.dao.util.JDBCUtils;
import com.jal.wholesales.model.TipoEmpresa;
import com.wholesales.exception.DataException;
import com.wholesales.exception.ServiceException;

public class TipoEmpresaServiceImpl implements TipoEmpresaService {
	private Logger logger = LogManager.getLogger(TipoEmpresaServiceImpl.class);
	private TipoEmpresaDAO tipoEmpresaDAO = null;

	public TipoEmpresaServiceImpl() {

		tipoEmpresaDAO = new TipoEmpresaDAOImpl();
	}

	@Override
	public TipoEmpresa findById(long id) throws DataException, ServiceException {
		if (logger.isDebugEnabled())
			logger.debug("id: {}", id);
		Connection c = null;
		TipoEmpresa tipoEmpresa = null;
		boolean commitOrRollback = false;
		try {
			c = ConnectionManager.getConnection();

			c.setAutoCommit(false);

			tipoEmpresa = tipoEmpresaDAO.findById(c, id);

			commitOrRollback = true;

		} catch (SQLException sqle) {
			logger.error("findById: " + id + ": " + sqle.getMessage(), sqle);
			throw new DataException(id + "", sqle);

		} finally {
			JDBCUtils.closeConnection(c, commitOrRollback);
		}
		return tipoEmpresa;
	}

}
