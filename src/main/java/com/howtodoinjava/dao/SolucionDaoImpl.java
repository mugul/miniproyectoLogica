/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.howtodoinjava.dao;

import com.howtodoinjava.entity.Predicado;
import com.howtodoinjava.entity.Resuelve;
import com.howtodoinjava.entity.Solucion;
import com.howtodoinjava.entity.SolucionId;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author miguel
 */
@Repository
public class SolucionDaoImpl implements SolucionDAO{
    
    @Autowired
    private SessionFactory sessionFactory;
    
    
    @Override   
    @Transactional
    public void addSolucion(Solucion solucion){
        this.sessionFactory.getCurrentSession().save(solucion);
    }
    
    @Override
    @Transactional
    public void deleteSolucion(SolucionId id){
        Solucion solucion = (Solucion) sessionFactory.getCurrentSession().load(
				Solucion.class, id);
        if (null != solucion) {
        	this.sessionFactory.getCurrentSession().delete(solucion);
        }
    }
    
    @Override
    @Transactional
    public Solucion getSolucion(SolucionId id){
        return (Solucion)this.sessionFactory.getCurrentSession().get(Solucion.class,id);
    }
    
    @Override
    @Transactional
    public List<Solucion> getAllSolucionesByResuelve(int resuelveId){
        return this.sessionFactory.getCurrentSession().createQuery("FROM Solucion WHERE resuelve.id = :resuelveId").setParameter("resuelveId",resuelveId).list();
    }
    
    
}
