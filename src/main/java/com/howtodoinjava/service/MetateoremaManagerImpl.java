/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.howtodoinjava.service;

import com.howtodoinjava.dao.MetateoremaDAO;
import com.howtodoinjava.entity.Metateorema;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author miguel
 */
@Service
public class MetateoremaManagerImpl implements MetateoremaManager {
       
    @Autowired
    private MetateoremaDAO metateoremaDAO;
    
    @Override
    @Transactional
    public void addMetateorema(Metateorema metateorema){
        metateoremaDAO.addMetateorema(metateorema);
    }
    
    @Override
    @Transactional
    public void deleteMetateorema(int id){
        metateoremaDAO.deleteMetateorema(id);
    }
    
    @Override
    @Transactional
    public Metateorema getMetateorema(int id){
        return metateoremaDAO.getMetateorema(id);
    }
    
    @Override
    @Transactional
    public List<Metateorema> getAllMetateoremas(){
        return metateoremaDAO.getAllMetateoremas();
    }
    
    @Override
    @Transactional
    public Metateorema getMetateoremaByEnunciados(String enunciadoizq,String enunciadoder){
        return metateoremaDAO.getMetateoremaByEnunciados(enunciadoizq,enunciadoder);
    }

}
