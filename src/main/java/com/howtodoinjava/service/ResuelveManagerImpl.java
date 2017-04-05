/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.howtodoinjava.service;

import com.howtodoinjava.dao.ResuelveDAO;
import com.howtodoinjava.entity.Resuelve;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author miguel
 */
@Service
public class ResuelveManagerImpl implements ResuelveManager {
       
    @Autowired
    private ResuelveDAO resuelveDAO;
    
    @Override
    @Transactional
    public Resuelve addResuelve(Resuelve resuelve){
        Resuelve res = this.getResuelveByUserAndTeorema(resuelve.getUsuario().getLogin(),resuelve.getTeorema().getId());
        if (res != null){
            return res;
        }
        resuelveDAO.addResuelve(resuelve);
        return resuelve;
    }
    
    
    @Override   
    @Transactional
    public void updateResuelve(Resuelve resuelve){
        resuelveDAO.updateResuelve(resuelve);
    }
    
    @Override
    @Transactional
    public void deleteResuelve(int id){
        resuelveDAO.deleteResuelve(id);
    }
    
    @Override
    @Transactional
    public Resuelve getResuelve(int id){
        return resuelveDAO.getResuelve(id);
    }
    
    @Override
    @Transactional
    public List<Resuelve> getAllResuelve(){
        return resuelveDAO.getAllResuelve();
    }
    
    @Override
    @Transactional
    public List<Resuelve> getAllResuelveByUser(String userLogin){
        return resuelveDAO.getAllResuelveByUser(userLogin);
    }
    
    @Override
    @Transactional
    public List<Resuelve> getResuelveByTeorema(int teoremaID){
        return resuelveDAO.getResuelveByTeorema(teoremaID);
    }

    @Override
    @Transactional
    public Resuelve getResuelveByUserAndTeorema(String userLogin,int teoremaID){
        return resuelveDAO.getResuelveByUserAndTeorema(userLogin, teoremaID);
    }
}
