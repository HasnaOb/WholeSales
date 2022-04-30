package com.company.wholesales.service;

import java.util.List;
import com.jal.wholesales.model.Empresa;
import com.jal.wholesales.service.EmpresaCriteria;
import com.wholesales.exception.DataException;
import com.wholesales.exception.InstanceException;
import com.wholesales.exception.InstanceNotFoundException;
import com.wholesales.exception.InvalidUserOrPasswordException;
import com.wholesales.exception.MailException;
import com.wholesales.exception.ServiceException;

public interface EmpresaService {

	public Empresa signUp(Empresa e) throws InstanceException, MailException, ServiceException;

	public Empresa login(String email, String password) throws InvalidUserOrPasswordException, ServiceException;

	Empresa findById(long id) throws DataException, ServiceException;

	Empresa findByEmail(String email) throws DataException, ServiceException;

	void update(Empresa e) throws ServiceException;

	List<Empresa> findByNombre(String nombre) throws DataException, ServiceException;

	public List<Empresa> findByCriteria(EmpresaCriteria ec) throws DataException;

	public void deleteById(Long id) throws DataException, InstanceNotFoundException;

	/*
	 * public void delete(Empresa e) throws
	 * InstanceNotFoundException,ServiceException;
	 */

}
