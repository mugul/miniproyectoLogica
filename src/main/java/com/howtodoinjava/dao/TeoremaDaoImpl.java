/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.howtodoinjava.dao;

import com.howtodoinjava.entity.Teorema;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author miguel
 */


public class TeoremaDaoImpl implements TeoremaDAO{
        
    
        @Autowired
        private SessionFactory sessionFactory;
	
	@Override
	public void addTeorema(Teorema teorema) {
		this.sessionFactory.getCurrentSession().save(teorema);
	}
        
        
	@Override
	public void deleteTeorema(int id) {
            Teorema teorema = (Teorema) sessionFactory.getCurrentSession().load(
                                Teorema.class, id);
            if (null != teorema) {
                    this.sessionFactory.getCurrentSession().delete(teorema);
            }
	}
        
        @Override
	public Teorema getTeorema(int id){
            return (Teorema)this.sessionFactory.getCurrentSession().get(Teorema.class,id);
        }
        
        
	@Override
	public List<Teorema> getAllTeoremas() {
//            Aqui hay q colocar un select distinct
            return this.sessionFactory.getCurrentSession().createQuery("FROM Teorema").list();
	}
        
        
//	@Override
//	public List<Termino> getAllTeoremasByUser(String username)
//        {
//		return this.sessionFactory.getCurrentSession().createQuery("FROM Termino WHERE usuario.login = :username").setParameter("username",username).list();
//	}

//	@Override
//        public List<Teorema> getTeoremasByCategoria(Categoria categoria)
//        {
//                String publico="publico";
//                return this.sessionFactory.getCurrentSession().createQuery("select teorema FROM Termino ter, Publicacion publ WHERE publ.id.login = :username AND publ.id.alias = ter.id.alias AND ter.usuario.login = :publico").setParameter("username",username).setParameter("publico",publico).list();
//        }
}
