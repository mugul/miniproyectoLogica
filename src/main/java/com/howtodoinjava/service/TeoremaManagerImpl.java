/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.howtodoinjava.service;

import com.howtodoinjava.dao.TeoremaDAO;
import com.howtodoinjava.entity.Teorema;
import com.howtodoinjava.entity.Termino;
import com.howtodoinjava.lambdacalculo.Term;
import java.util.List;
import org.hibernate.type.SerializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.SerializationUtils;

/**
 *
 * @author miguel
 */
@Service
public class TeoremaManagerImpl implements TeoremaManager {
       
    @Autowired
    private TeoremaDAO teoremaDAO;
    
    @Override
    @Transactional
    public void addTeorema(Teorema teorema){
//        if (teorema != null) {
//            teorema.setTeoserializadoizq((byte[])SerializationUtils.serialize(teorema.getTeoserializadoizq()));
//            teorema.setTeoserializadoder((byte[])SerializationUtils.serialize(teorema.getTeoserializadoder()));
//        }
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
        Teorema teo = teoremaDAO.getTeorema(id);
        if (teo != null) {
            System.out.println(teo);
            byte[] serIzq = (byte[])SerializationUtils.deserialize(teo.getTeoserializadoizq());
            teo.setTeoserializadoizq(serIzq);
            byte[] serDer = (byte[])SerializationUtils.deserialize(teo.getTeoserializadoder());
            teo.setTeoserializadoder(serDer);
            System.out.println(serDer);
        }
        return teo;
    }
    
    @Override
    @Transactional
    public List<Teorema> getAllTeoremas(){
        List<Teorema> teoList = teoremaDAO.getAllTeoremas();
        try{
            for(Teorema teo: teoList)
            {
                //ter.setTermObject((Term)ToString.fromString(ter.getSerializado()));
                teo.setTeoserializadoizq((byte[])SerializationUtils.deserialize(teo.getTeoserializadoizq()));
                teo.setTeoserializadoder((byte[])SerializationUtils.deserialize(teo.getTeoserializadoder()));
            }
        }
        catch(Exception e){e.printStackTrace();}
        return teoList;
    }
    
    @Override
    @Transactional
    public Teorema getTeoremaByEnunciados(String enunciadoizq,String enunciadoder){
        Teorema teo = teoremaDAO.getTeoremaByEnunciados(enunciadoizq,enunciadoder);
        if (teo != null) {
            teo.setTeoserializadoizq((byte[])SerializationUtils.deserialize(teo.getTeoserializadoizq()));
            teo.setTeoserializadoder((byte[])SerializationUtils.deserialize(teo.getTeoserializadoder()));
        }
        return teo;
    }
    
}
