
package javaapplication13;

import java.awt.Image;
import java.io.File;
import static java.lang.Thread.sleep;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class arbolAVL {
    private boolean pausado = false;
    private Adivinador pantalla;
    public static nodo raiz;
    
    public arbolAVL(Adivinador pantalla) {
        this.pantalla = pantalla;
        
        new Thread(() -> {
            
            while (true) {
                if (!pausado) { // verifica si el hilo está pausado
                    juegoInicial();
                }
            }
        }).start();
    }
    
    // método para pausar o reanudar el hilo
    public void setPausado(boolean pausado) {
        this.pausado = pausado;
    }
    


    public void juegoInicial(){
        if(raiz==null){
            arbolInicio(pantalla.getTextoPersonaje());
        }
        else{
            this.busqueda();
        }
        
    }
    
    
    public nodo nodoInicial(){
        nodo nodo=raiz;
        return nodo;
    }
    
     
     public void arbolInicio(String personaje){         
         nodo nodo=raiz;
         if(raiz==null){
            raiz=new nodo(personaje);
            nodo=raiz;
             
         
         }
     }
     
         public boolean respuesta() {
        boolean res = false;
        try {
            res = pantalla.getRespuesta();
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
        return res;
    }
     
      public void busqueda(){
          do{
      nodo nodo=raiz;

      while(nodo!=null){
          pantalla.mostrarEnPantalla();
          if(nodo.hijoIzq!=null){ //pregunta
              pantalla.setlblPregunta(nodo.dato);
              nodo=(respuesta()? nodo.getHijoIzq():nodo.getHijoDer());
          }
          else{//respuesta
              if(nodo.hijoDer==null){
                  pantalla.noMostrar();
              }
              int res=3;
              boolean personajeNOEscogido=true;
              while(personajeNOEscogido){
              res=JOptionPane.showConfirmDialog(pantalla, "El personaje es: "+ nodo.dato,"Adivino" ,JOptionPane.YES_NO_OPTION);
                  System.out.println(res);
                  if(res==0||res==1){
                      personajeNOEscogido=false;
                  }
              }
              if(res==JOptionPane.YES_OPTION){
                  JOptionPane.showMessageDialog(pantalla, "adivine");
                  pantalla.setTextoPersonaje("");
              } else{
                  personajeNuevo(nodo);
              }
              nodo=null;
              
            }
          
      }
            
            }while(jugarNuevo());
      
    }

      public boolean jugarNuevo(){
          int res=3;
          while(res==3){
          res=JOptionPane.showConfirmDialog(pantalla,"¿Deseas volver a jugar?", "Adivino",JOptionPane.YES_NO_OPTION);    
          }
          if (res==JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(pantalla, "Gracias por Jugar");
            System.exit(0);

            return false;
        }
          
        return true;
      }
      
      public void insertar(nodo node, String nuevoPersonaje, String pregunta){
        
        String personajeNode = node.dato;
       // ImageIcon imgPersonaje=nodo.imgPersonaje;

        nodo nodoizq=new nodo(personajeNode);
        nodo nododer= new nodo(nuevoPersonaje);

        node.dato=""+pregunta+"?";
        node.setHijoIzq(nododer);
        node.setHijoDer(nodoizq);
        
        raiz=raiz.equilibrarArbol(raiz);
            
      }
      
   public void personajeNuevo(nodo node){
    
    String personaje=pantalla.getPersonaje();
    String pregunta = pantalla.getDescripcion();
    
    
//    ImageIcon personajeImagen=null;
//    JFileChooser chooser=new JFileChooser();
//        int result= chooser.showOpenDialog(null);
//        
//        if(result==JFileChooser.APPROVE_OPTION){
//            File imgSel =chooser.getSelectedFile();
//            personajeImagen = new ImageIcon(imgSel.getPath());
//            Image img=personajeImagen.getImage().getScaledInstance(300,300, Image.SCALE_SMOOTH);
//            personajeImagen=new ImageIcon(img);
//        }
//  //  personajeImagen=pantalla.escogerImg();
//  
//    if(personajeImagen==null){
//        pantalla.sinImagen();
//    }
    
    insertar(node, personaje, pregunta);
}
   

   

        
}


