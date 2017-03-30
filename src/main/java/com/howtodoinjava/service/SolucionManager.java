/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.howtodoinjava.service;

import com.howtodoinjava.entity.Solucion;
import java.util.List;

/**
 *
 * @author miguel
 */
public interface SolucionManager {
        
    public void addSolucion(Solucion solucion);
    
    public void deleteSolucion(int id);
    
    public Solucion getSolucion(int id);
    
    public List<Solucion> getAllSolucionesByResuelve(int resuelveId);
    
}