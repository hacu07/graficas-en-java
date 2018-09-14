/*
    Generar graficos insertando nuevos valores
    Desarrollado por: Harold Cupitra Hernandez
    13/septiembre/2018
 */
package graficas;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.math.plot.Plot2DPanel;
/**
 *  
 * @author hacu
 */
public class Graficas {
    //Datos a tratar en la grafica
    private double[] x = {0.0,0.0};
    private double[] y = {0.0,0.0};
    
    //Objetos de la vista
    private JTextField txtX,txtY;
    private JButton btnGraficar;
    private ArrayList list;
    
    //Objeto que permite graficar
    private Plot2DPanel plot = new Plot2DPanel();
    
    public Graficas(){
        //instancia a los elementos graficos
        txtX = new JTextField(10);
        txtY = new JTextField(10);
        btnGraficar = new JButton("Graficar");
        
        //Agregar datos a la Grafica      
        plot.addScatterPlot("Datos",x, y);
        //graficar en linea
        plot.addLinePlot("Linea",x, y);
        
        //Generar Ventana
        JFrame frame =  new JFrame("Grafica");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,500);
        frame.add(construirPanelPrincipal());
        frame.setVisible(true);
        
        //Evento clic
        btnGraficar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                /* 1. obtener valores x,y ingresados AQUI EN ESTAS VARIABLES AGREGAR LOS NUEVOS VALORES A GRAFICAR*/
                double nuevoX = Double.parseDouble(txtX.getText().toString());
                double nuevoY = Double.parseDouble(txtY.getText().toString());
                

                /* 2. agregarlos a los array*/
                
                //se envia el nuevo valor junto al arreglo actual
                double[] arrayX = agregarValor(x,nuevoX);
                double[] arrayY = agregarValor(y,nuevoY);
                //los arreglos globales obtienen el nuevo arreglo con todos los valores 
                x = null;
                y = null;
                x = arrayX; //asigna los nuevos arreglos a los arreglos globales
                y = arrayY;
                /* 3. graficar nuevamente*/
                plot.removeAllPlots();
                plot.addScatterPlot("Datos",arrayX, arrayY);
                plot.addLinePlot("Linea",arrayX, arrayY);
            }
        });
    }
    
    /* Obtiene como parametros el arreglo actual y el valor a agregar en uno nuevo
        retornando este nuevo arreglo para realizar la grafica
    */
    private double[] agregarValor(double valores[], double nuevoValor){
        double newX[] = new double[valores.length+1];//crea un nuevo arreglo
        for(int i = 0; i<valores.length; i++){
            newX[i]= valores[i]; //asigna los valores del arregloAntiguo al nuevoArreglo
        }
        newX[newX.length-1] = nuevoValor;//agrega el nuevo valor en la ultima posicion
        return newX;//retorna el nuevo arreglo
    }
    
 
    
    //Panel principal que trae los otros paneles para generar la interfaz grafica
    //ordena los paneles
    private JPanel construirPanelPrincipal(){
        JPanel pPrincipal = new JPanel();
        pPrincipal.setLayout(new BorderLayout());
        pPrincipal.add(plot,BorderLayout.CENTER);//Se posiciona la grafica en el centro del panel principal
        pPrincipal.add(contruirPanelSur(),BorderLayout.SOUTH);
        return pPrincipal;
    }
    
    //Panel que se localiza en la parte inferior de la interfaz grafica
    private JPanel contruirPanelSur(){
        JPanel pSur = new JPanel();
        pSur.add(new JLabel("X: "));
        pSur.add(txtX);
        pSur.add(new JLabel("Y: "));
        pSur.add(txtY);
        pSur.add(btnGraficar);
        return pSur;
    }
    
    public static void main(String[] args) {
        Graficas graficas =  new Graficas();
    }
    
}
