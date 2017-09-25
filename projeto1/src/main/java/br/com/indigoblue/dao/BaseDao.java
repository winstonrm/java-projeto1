package br.com.indigoblue.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class BaseDao<T> {
  
	@PersistenceContext(unitName = "OraclePU")
	private EntityManager em;
	
	public Class<T> tipo;
	
	public BaseDao(Class<T> tipo) {
		this.tipo = tipo;
	}
	
	public void salvar(T tipo) {
		em.persist(tipo);
	}
	
	public T atualizar(T tipo) {
		return em.merge(tipo);
	}
	
	public List<T> listarTodos(){
		StringBuilder sql = new StringBuilder();
		sql.append("from ").append(tipo.getSimpleName());
		Query q = em.createQuery(sql.toString());
		
		return q.getResultList();
	}
}
