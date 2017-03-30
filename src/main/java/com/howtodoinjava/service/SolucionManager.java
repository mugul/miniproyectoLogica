/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.howtodoinjava.service;

import com.howtodoinjava.entity.Predicado;
import com.howtodoinjava.entity.Solucion;
import com.howtodoinjava.entity.SolucionId;
import java.util.List;

/**
 *
 * @author miguel
 */
public interface SolucionManager {
        
    public void addSolucion(Solucion solucion);
    
    public void deleteSolucion(SolucionId id);
    
    public Solucion getSolucion(SolucionId id);
    
    public List<Solucion> getAllSolucionesByResuelve(int resuelveId);
    
}