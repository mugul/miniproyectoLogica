package com.howtodoinjava.entity;
// Generated Mar 20, 2017 12:50:11 PM by Hibernate Tools 3.2.1.GA

import com.howtodoinjava.lambdacalculo.PasoInferencia;
import com.howtodoinjava.lambdacalculo.Term;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import org.springframework.util.SerializationUtils;

/**
 * Solucion generated by hbm2java
 */
public class Solucion implements java.io.Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "solucion_id_seq")
    @SequenceGenerator(name = "solucion_id_seq", sequenceName = "solucion_id_seq")
    private int id;
    private byte[] arregloSerializado;
    private List<PasoInferencia> arregloInferencias = new ArrayList<PasoInferencia>();
    private Resuelve resuelve;

    public Solucion() {
    }

    public Solucion(PasoInferencia paso) {
        this.arregloInferencias.add(paso);
//        this.arregloSerializado = SerializationUtils.serialize(this.arregloInferencias);
        this.serialize();
        this.deserialize();

    }

    public Solucion(Resuelve resuelve, PasoInferencia paso) {
        this.resuelve = resuelve;
        this.arregloInferencias.add(paso);
//        this.arregloSerializado = SerializationUtils.serialize(this.arregloInferencias);
        this.serialize();
        this.deserialize();

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setArregloSerializado(byte[] arregloSerializado) {
        this.arregloSerializado = arregloSerializado;
    }

    public void setArregloInferencias(List<PasoInferencia> arregloInferencias) {
        this.arregloInferencias = arregloInferencias;
//        this.arregloSerializado = SerializationUtils.serialize(arregloInferencias);
        this.serialize();
        this.deserialize();
    }

    public void setResuelve(Resuelve resuelve) {
        this.resuelve = resuelve;
    }

    public int getId() {
        return id;
    }

    public byte[] getArregloSerializado() {
        return arregloSerializado;
    }

    public List<PasoInferencia> getArregloInferencias() {
        return arregloInferencias;
    }

    public Resuelve getResuelve() {
        return resuelve;
    }

    public void addArregloInferencias(PasoInferencia paso) {
        this.deserialize();
        List<PasoInferencia> newArray = new ArrayList<PasoInferencia>();
        newArray = getArregloInferencias();
        System.out.println("El largo del arreglo es: "+getArregloInferencias().size());
        newArray.add(paso);
        System.out.println("El largo del arreglo con un nuevo elemento es: "+newArray.size());
        setArregloInferencias(newArray);
        System.out.println("Se guardo el largo del arreglo con un nuevo elemento en: "+getArregloInferencias().size());
    }

    public void serialize() {
//        this.arregloInferencias.add(paso);
//        this.arregloSerializado = SerializationUtils.serialize(this.arregloInferencias);
        System.out.println("INICIADO PROCESO DE SERIALIZACION");
        List<byte[]> newArray = new ArrayList<byte[]>();
        for (PasoInferencia x : this.getArregloInferencias()) {
            System.out.print("El teorema a resolver: ");
            System.out.println(x.getExpresion().toStringInf());
            newArray.add(SerializationUtils.serialize(x.getExpresion()));

            System.out.print("El lado izq del teo es: ");
            System.out.println(x.getTeoIzq().toStringInf());
            newArray.add(SerializationUtils.serialize(x.getTeoIzq()));

            System.out.print("El lado der del teo es: ");
            System.out.println(x.getTeoDer().toStringInf());
            newArray.add(SerializationUtils.serialize(x.getTeoDer()));

            System.out.print("El leibniz es: ");
            System.out.println(x.getLeibniz().toStringInf());
            newArray.add(SerializationUtils.serialize(x.getLeibniz()));

            System.out.print("Finalmente, instanciacion es: ");
            System.out.println(x.getInstancia().toString());
            newArray.add(SerializationUtils.serialize(x.getInstancia()));

        }

        this.arregloSerializado = SerializationUtils.serialize(newArray);
        System.out.println("Serializo toda vaina");

    }

    public void deserialize() {
        System.out.println("INICIADO PROCESO DE DESERIALIZACION");
        List<byte[]> theArray = new ArrayList<byte[]>();
        theArray = (List<byte[]>) SerializationUtils.deserialize(this.arregloSerializado);
        List<PasoInferencia> newArray = new ArrayList<PasoInferencia>();
        
        Iterator<byte[]> solIter = theArray.iterator();
        while (solIter.hasNext())  {
            PasoInferencia paso = new PasoInferencia();
            paso.setExpresion((Term) SerializationUtils.deserialize(solIter.next()));
            paso.setTeoIzq((Term) SerializationUtils.deserialize(solIter.next()));
            paso.setTeoDer((Term) SerializationUtils.deserialize(solIter.next()));
            paso.setLeibniz((Term) SerializationUtils.deserialize(solIter.next()));
            paso.setInstancia((String) SerializationUtils.deserialize(solIter.next()));
            System.out.println("Valores del paso");
            System.out.println(paso.getExpresion());
            System.out.println(paso.getInstancia());
            System.out.println(paso.getLeibniz());
            System.out.println(paso.getTeoDer());
            System.out.println(paso.getTeoIzq());
            newArray.add(paso);
        }
        this.arregloInferencias = newArray;

    }
}
