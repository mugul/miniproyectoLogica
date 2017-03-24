/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.howtodoinjava.dao;

import com.howtodoinjava.entity.Dispone;
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
public class DisponeDaoImpl implements DisponeDAO {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    
    @Override   
    @Transactional
    public void addDispone(Dispone dispone){
        this.sessionFactory.getCurrentSession().save(dispone);
    }
    
    @Override
    @Transactional
    public void deleteDispone(int id){
        Dispone dispone = (Dispone) sessionFactory.getCurrentSession().load(
				Dispone.class, id);
        if (null != dispone) {
        	this.sessionFactory.getCurrentSession().delete(dispone);
        }
    }
    
    @Override
    @Transactional
    public Dispone getDispone(int id){
//        Dispone r = new Dispone((Dispone)this.sessionFactory.getCurrentSession().get(Dispone.class,id));
//        return r; 
        return (Dispone)this.sessionFactory.getCurrentSession().get(Dispone.class,id);
    }
    
    
    @Override
    @Transactional
    public List<Dispone> getAllDispone(){
        return this.sessionFactory.getCurrentSession().createQuery("FROM Dispone").list();
    }
    
    @Override
    @Transactional
    public List<Dispone> getAllDisponeByUser(String userLogin){
        return this.sessionFactory.getCurrentSession().createQuery("FROM Dispone WHERE usuario.login = :userLogin").setParameter("userLogin",userLogin).list();
    }
    
    @Override
    @Transactional
    public List<Dispone> getDisponeByMetateorema(int metateoremaID){
        return this.sessionFactory.getCurrentSession().createQuery("FROM Dispone WHERE metateorema.id = :metateoremaID").setParameter("metateoremaID",metateoremaID).list();
    }

//    public List<Dispone> getDisponeByUser(String loginusuario);
//
}

