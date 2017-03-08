/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.howtodoinjava.service;

import com.howtodoinjava.dao.TeoremaDAO;
import com.howtodoinjava.entity.Teorema;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author miguel
 */
public class TeoremaManagerImpl implements TeoremaManager {
       
    @Autowired
    private TeoremaDAO teoremaDAO;
    
    @Override
    @Transactional
    public void addTeorema(Teorema teorema){
        teoremaDAO.addTeorema(teorema);
    }
    
    @Override
    @Transactional
    public void deleteTeorema(int id){
        teoremaDAO.deleteTeorema(id);
    }
    
    @Override
    @Transactional
    public Teorema getTeorema(int id){
        return teoremaDAO.getTeorema(id);
    }
    
    @Override
    @Transactional
    public List<Teorema> getAllTeoremas(){
        return teoremaDAO.getAllTeoremas();
    }
}
