/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.howtodoinjava.service;

import com.howtodoinjava.dao.DisponeDAO;
import com.howtodoinjava.entity.Dispone;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author miguel
 */
@Service
public class DisponeManagerImpl implements DisponeManager {
       
    @Autowired
    private DisponeDAO disponeDAO;
    
    @Override
    @Transactional
    public void addDispone(Dispone dispone){
        disponeDAO.addDispone(dispone);
    }
    
    @Override
    @Transactional
    public void deleteDispone(int id){
        disponeDAO.deleteDispone(id);
    }
    
    @Override
    @Transactional
    public Dispone getDispone(int id){
        return disponeDAO.getDispone(id);
    }
    
    @Override
    @Transactional
    public List<Dispone> getAllDispone(){
        return disponeDAO.getAllDispone();
    }
    
    @Override
    @Transactional
    public List<Dispone> getAllDisponeByUser(String userLogin){
        return disponeDAO.getAllDisponeByUser(userLogin);
    }
    
    @Override
    @Transactional
    public List<Dispone> getDisponeByMetateorema(int metateoremaID){
        return disponeDAO.getDisponeByMetateorema(metateoremaID);
    }
}
