/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.howtodoinjava.dao;

import com.howtodoinjava.entity.Predicado;
import com.howtodoinjava.entity.PredicadoId;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author miguel
 */
@Repository
public class PredicadoDAOImpl implements PredicadoDAO{
    
    @Autowired
    private SessionFactory sessionFactory;
    
    
    @Override   
    public void addPredicado(Predicado predicado){
        this.sessionFactory.getCurrentSession().save(predicado);
    }
    
    @Override
    public void deletePredicado(PredicadoId id){
        Predicado predicado = (Predicado) sessionFactory.getCurrentSession().load(
				Predicado.class, id);
        if (null != predicado) {
        	this.sessionFactory.getCurrentSession().delete(predicado);
        }
    }
    
    @Override
    public Predicado getPredicado(PredicadoId id){
        return (Predicado)this.sessionFactory.getCurrentSession().get(Predicado.class,id);
    }
    
    @Override
    public List<Predicado> getAllPredicadosByUser(String userLogin){
        return this.sessionFactory.getCurrentSession().createQuery("FROM Predicado WHERE usuario.login = :userLogin").setParameter("userLogin",userLogin).list();
    }
    
    
}
