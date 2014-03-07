package com.technologicaloddity.departments.dao;

import java.util.*;

import org.hibernate.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import com.technologicaloddity.departments.model.*;

@Repository
@Transactional
public abstract class MappedModelDao<T extends MappedModel> {

	@Autowired
	protected SessionFactory sessionFactory;
	
	public abstract Class<T> getActualClass();
	
	@SuppressWarnings("unchecked")
	public T findById(String id) {
		Session session = sessionFactory.getCurrentSession();
		return (T)session.get(getActualClass(), id);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(getActualClass());
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

	public void saveOrUpdate(T entity) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(entity);
	}

	public void delete(T entity) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(entity);
	}	
	
}
