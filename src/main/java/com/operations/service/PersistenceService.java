package com.operations.service;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.operations.constant.NamedQuery;

@Service
public class PersistenceService {

	private static final Log log = LogFactory.getLog(PersistenceService.class);

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public <T> T get(Class<?> entity, long id) {
		return (T) sessionFactory.getCurrentSession().get(entity, id);
	}

	@SuppressWarnings("unchecked")
	public <T> T get(Class<?> entity, int id) {
		return (T) sessionFactory.getCurrentSession().get(entity, id);
	}

	@SuppressWarnings("unchecked")
	public <T> T get(Class<?> entity, String id) {
		return (T) sessionFactory.getCurrentSession().get(entity, id);
	}

	public Long save(Object transientInstance) {
		return (Long) sessionFactory.getCurrentSession().save(transientInstance);
	}

	public void update(Object transientInstance) {
		sessionFactory.getCurrentSession().update(transientInstance);
	}

	public void saveOrUpdate(Object transientInstance) {
		sessionFactory.getCurrentSession().saveOrUpdate(transientInstance);
	}

	public Long delete(NamedQuery namedQuery, Map<String, Object> params) {
		System.out.println(":::params:::" + params);
		Query hQuery = sessionFactory.getCurrentSession().getNamedQuery(namedQuery.getnamedQueryId());
		for (Entry<String, Object> param : params.entrySet()) {
			hQuery.setParameter(param.getKey(), param.getValue());
		}
		return (long) hQuery.executeUpdate();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(Object persistentInstance) {
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T getUniqueResult(String namedQuery, Map<String, Object> params) {
		Query q = sessionFactory.getCurrentSession().createQuery(namedQuery);
		for (Entry<String, Object> param : params.entrySet()) {
			q.setParameter(param.getKey(), param.getValue());
		}
		q.setMaxResults(1);
		return (T) q.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public <T> T getUniqueResult(NamedQuery namedQueryEnum, Map<String, Object> params) {
		log.info("Invoking Query by Id : " + namedQueryEnum.getnamedQueryId() + ":::params:::" + params);
		Query hQuery = sessionFactory.getCurrentSession().getNamedQuery(namedQueryEnum.getnamedQueryId());
		for (Entry<String, Object> param : params.entrySet()) {
			System.out.println(param);
			hQuery.setParameter(param.getKey(), param.getValue());
		}
		System.out.println(":::params:::" + params);
		System.out.println(params.size());
		hQuery.setMaxResults(1);
		return (T) hQuery.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Deprecated
	public <T> List<T> getList(String namedQuery, Map<String, Object> params) {
		Query hQuery = sessionFactory.getCurrentSession().createQuery(namedQuery);
		for (Entry<String, Object> param : params.entrySet()) {
			hQuery.setParameter(param.getKey(), param.getValue());
		}
		return (List<T>) hQuery.list();
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getList(NamedQuery namedQueryEnum, Map<String, Object> params) {
		log.info("Invoking Query by Id : " + namedQueryEnum.getnamedQueryId() + ":::params:::" + params);

		Query hQuery = sessionFactory.getCurrentSession().getNamedQuery(namedQueryEnum.getnamedQueryId());
		if (params == null) {
			return (List<T>) hQuery.list();
		}
		for (Entry<String, Object> param : params.entrySet()) {
			hQuery.setParameter(param.getKey(), param.getValue());
		}
		return (List<T>) hQuery.list();
	}

	@SuppressWarnings("unchecked")
	public Long getResultCount(NamedQuery namedQueryEnum, Map<String, Object> params) {
		log.info("Invoking Query by Id : " + namedQueryEnum.getnamedQueryId() + ":::params:::" + params);

		Query hQuery = sessionFactory.getCurrentSession().getNamedQuery(namedQueryEnum.getnamedQueryId());
		if (params == null) {
			return ((Number) hQuery.getSingleResult()).longValue();
		}
		for (Entry<String, Object> param : params.entrySet()) {
			hQuery.setParameter(param.getKey(), param.getValue());
		}
		return ((Number) hQuery.getSingleResult()).longValue();
	}

	@SuppressWarnings("unchecked")
	public Long getResultCountByQuery(String sqlQuery, Map<String, Object> params) {
		log.info("Invoking Query by Id : " + sqlQuery + ":::params:::" + params);

		Query hQuery = sessionFactory.getCurrentSession().createQuery(sqlQuery);
		if (params == null) {
			List results = (List) hQuery.list();
			return Long.valueOf(results.size());
		}

		for (Entry<String, Object> param : params.entrySet()) {
			hQuery.setParameter(param.getKey(), param.getValue());
		}
		List results = (List) hQuery.list();
		return Long.valueOf(results.size());
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getList(NamedQuery namedQueryEnum, Map<String, Object> params, int pageIndex, int pageCount) {
		log.info("Invoking Query by Id : " + namedQueryEnum.getnamedQueryId());
		System.out.println("params: " + params);
		Query hQuery = sessionFactory.getCurrentSession().getNamedQuery(namedQueryEnum.getnamedQueryId());

		if (pageIndex == 0) {
			pageIndex = 1;
		}

		if (pageCount == 0) {
			pageCount = 10;
		}

		pageIndex = (pageCount * (pageIndex - 1));// + 1;

		hQuery.setFirstResult(pageIndex);
		hQuery.setMaxResults(pageCount);
		if (params == null || params.isEmpty()) {
			return (List<T>) hQuery.list();
		}
		for (Entry<String, Object> param : params.entrySet()) {
			hQuery.setParameter(param.getKey(), param.getValue());
		}
		return (List<T>) hQuery.list();
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getListWithInClause(String query, Map<String, Object> params) {

		Query hQuery = sessionFactory.getCurrentSession().createQuery(query);
		if (params == null || params.isEmpty()) {
			return (List<T>) hQuery.list();
		}
		for (Entry<String, Object> param : params.entrySet()) {
			hQuery.setParameterList(param.getKey(), (List) param.getValue());
		}
		return (List<T>) hQuery.list();
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getListWithInClause(String query, Map<String, Object> params, int pageIndex, int pageCount) {

		Query hQuery = sessionFactory.getCurrentSession().createQuery(query);

		hQuery.setFirstResult(pageIndex);
		hQuery.setMaxResults(pageCount);

		if (params == null || params.isEmpty()) {
			return (List<T>) hQuery.list();
		}
		for (Entry<String, Object> param : params.entrySet()) {
			hQuery.setParameterList(param.getKey(), (List) param.getValue());
		}
		return (List<T>) hQuery.list();
	}

	@SuppressWarnings("unchecked")
	public <T> T getUniqueResultWithInClause(String query, Map<String, Object> params) {
		Query q = sessionFactory.getCurrentSession().createQuery(query);
		if (params == null || params.isEmpty()) {
			return (T) q.uniqueResult();
		}
		for (Entry<String, Object> param : params.entrySet()) {
			q.setParameterList(param.getKey().toString(), (List) param.getValue());
		}
		q.setMaxResults(1);
		return (T) q.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getListWithInClause(NamedQuery namedQuery, Map<String, Object> params) {
		log.info("Invoking Query by Id : " + namedQuery.getnamedQueryId() + ":::params:::" + params);
		Query q = sessionFactory.getCurrentSession().createQuery(namedQuery.getnamedQueryId());
		if (params == null || params.isEmpty()) {
			return (List<T>) q.uniqueResult();
		}
		for (Entry<String, Object> param : params.entrySet()) {
			q.setParameterList(param.getKey().toString(), (List) param.getValue());
		}
		return (List<T>) q.list();
	}

	@SuppressWarnings("unchecked")
	public <T> T getUniqueResultWithInClause(NamedQuery namedQuery, Map<String, Object> params) {
		log.info("Invoking Query by Id : " + namedQuery.getnamedQueryId() + ":::params:::" + params);
		Query q = sessionFactory.getCurrentSession().createQuery(namedQuery.getnamedQueryId());
		if (params == null || params.isEmpty()) {
			return (T) q.uniqueResult();
		}
		for (Entry<String, Object> param : params.entrySet()) {
			q.setParameterList(param.getKey().toString(), (List) param.getValue());
		}
		q.setMaxResults(1);
		return (T) q.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public <T> List<T> getListByPage(NamedQuery namedQueryEnum, Map<String, Object> params, int pageIndex,
			int pageCount) {
		log.info("Invoking Query by Id : " + namedQueryEnum.getnamedQueryId());
		Query hQuery = sessionFactory.getCurrentSession().createQuery(namedQueryEnum.getnamedQueryId());
		hQuery.setFirstResult(pageIndex);
		hQuery.setMaxResults(pageCount);
		if (params == null || params.isEmpty()) {
			return (List<T>) hQuery.list();
		}
		for (Entry<String, Object> param : params.entrySet()) {
			hQuery.setParameter(param.getKey(), param.getValue());
		}
		return (List<T>) hQuery.list();
	}

	@SuppressWarnings("unchecked")
	public String dbTest() {
		Query q = sessionFactory.getCurrentSession().createSQLQuery("select 1");

		q.setMaxResults(1);
		Object obj = new Object();

		try {
			obj = q.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj.toString();
	}

}