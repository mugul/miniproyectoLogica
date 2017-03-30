/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.howtodoinjava.dao;

import com.howtodoinjava.entity.Resuelve;
import com.howtodoinjava.entity.Solucion;
import com.howtodoinjava.entity.SolucionId;
import java.util.List;

/**
 *
 * @author miguel
 */
public interface SolucionDAO {
    
    public void addSolucion(Solucion solucion);
    
    public void deleteSolucion(SolucionId id);
    
    public Solucion getSolucion(SolucionId id);
    
    public List<Solucion> getAllSolucionesByResuelve(int resuelveId);
    
}




