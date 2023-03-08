
package javaapplication13;

import javax.swing.ImageIcon;

public class nodo {
    String dato;
    nodo hijoIzq;
    nodo hijoDer;
    int factorEquilibrio;
    static ImageIcon imgPersonaje;
    
    
    public nodo(){
        this.dato = null;
        this.hijoIzq = null;
        this.hijoDer = null;
        this.factorEquilibrio=0;
        this.imgPersonaje=null;
    }
    
      public nodo(String dato){
        this.dato=dato;
        this.factorEquilibrio=0;
        this.hijoDer=null;
        this.hijoIzq=null;
        this.imgPersonaje=imgPersonaje;
    }

    public ImageIcon getImgPersonaje() {
        return imgPersonaje;
    }

    public void setImgPersonaje(ImageIcon imgPersonaje) {
        this.imgPersonaje = imgPersonaje;
    }
      
      
    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public nodo getHijoIzq() {
        return hijoIzq;
    }

    public void setHijoIzq(nodo hijoIzq) {
        this.hijoIzq = hijoIzq;
    }

    public nodo getHijoDer() {
        return hijoDer;
    }

    public void setHijoDer(nodo hijoDer) {
        this.hijoDer = hijoDer;
    }

    public int getFactorEquilibrio() {
        return factorEquilibrio;
    }

    public void setFactorEquilibrio(int factorEquilibrio) {
        this.factorEquilibrio = factorEquilibrio;
    }
    
    public nodo equilibrarArbol(nodo node){
        if(node==null){
            return null;
        }
        int balance=obtenerFE(node);
         if (balance > 1) {
            if (obtenerFE(node.getHijoIzq()) < 0) {
                node.setHijoIzq(rotacionIzquierda(node.getHijoIzq()));
            }
            node = rotacionDerecha(node);
        } else if (balance < -1) {
            if (obtenerFE(node.getHijoDer()) > 0) {
                node.setHijoDer(rotacionDerecha(node.getHijoDer()));
            }
            node = rotacionIzquierda(node);
        }

        node.setHijoIzq(equilibrarArbol(node.getHijoIzq()));
        node.setHijoDer(equilibrarArbol(node.getHijoDer()));

        return node;
        
    }
    
    public int obtenerFE(nodo node) {
        if(node == null) return 0;
        
        return altura(node.getHijoIzq()) - altura(node.getHijoDer());


    }
    
    public int altura(nodo node){
        if(node == null)
            return 0;
    return 1 + Math.max(altura(node.getHijoIzq()), altura(node.getHijoDer()));

        
    }

    //rotaciones
    private nodo rotacionIzquierda(nodo node) {
        nodo nuevo = node.getHijoDer();
        node.setHijoDer(nuevo.getHijoIzq());
        nuevo.setHijoIzq(node);
        return nuevo;
    }

    private nodo rotacionDerecha(nodo node) {
        nodo nuevo = node.getHijoIzq();
        node.setHijoIzq(nuevo.getHijoDer());
        nuevo.setHijoDer(node);
        return nuevo;
    }
       	
   
}
