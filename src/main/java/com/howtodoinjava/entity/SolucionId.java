package com.howtodoinjava.entity;
// Generated Feb 28, 2017 9:07:39 PM by Hibernate Tools 3.2.1.GA



/**
 * SolucionId generated by hbm2java
 */
public class SolucionId  implements java.io.Serializable {


     private String sol;
     private int resuelveid;

    public SolucionId() {
    }

    public SolucionId(String sol, int resuelveid) {
       this.sol = sol;
       this.resuelveid = resuelveid;
    }
   
    public String getSol() {
        return this.sol;
    }
    
    public void setSol(String sol) {
        this.sol = sol;
    }
    public int getResuelveid() {
        return this.resuelveid;
    }
    
    public void setResuelveid(int resuelveid) {
        this.resuelveid = resuelveid;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SolucionId) ) return false;
		 SolucionId castOther = ( SolucionId ) other; 
         
		 return ( (this.getSol()==castOther.getSol()) || ( this.getSol()!=null && castOther.getSol()!=null && this.getSol().equals(castOther.getSol()) ) )
 && (this.getResuelveid()==castOther.getResuelveid());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getSol() == null ? 0 : this.getSol().hashCode() );
         result = 37 * result + this.getResuelveid();
         return result;
   }   


}


