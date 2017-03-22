/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.howtodoinjava.service;

import com.howtodoinjava.entity.Teorema;
import java.util.List;

/**
 *
 * @author miguel
 */
public interface TeoremaManager {
    
    public void addTeorema(Teorema teorema);
    
    public void deleteTeorema(int id);
    
    public Teorema getTeorema(int id);
    
    public List<Teorema> getAllTeoremas();
    
    public Teorema getTeoremaByEnunciados(String enunciadoizq,String enunciadoder);
    
    
}




