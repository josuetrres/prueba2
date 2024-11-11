package com.tercertotest.controller.models;

import java.util.HashSet;
import java.util.Set;

import com.tercertotest.controller.tda.Stack;

public class RegistroValidator {
    private Stack<String> pt;  
    private Stack<String> pc;  
    private Set<String> palabrasReservadas;

    public RegistroValidator(){
        pt = new Stack<String>(10);
        pc = new Stack<String>(10); 
        palabrasReservadas = new HashSet<String>(); 

        palabrasReservadas.add("html");
        palabrasReservadas.add("head");
        palabrasReservadas.add("body");
        palabrasReservadas.add("div");
        palabrasReservadas.add("span");
        palabrasReservadas.add("p");
        palabrasReservadas.add("h1");
        palabrasReservadas.add("h2");
    }

    public boolean validar(String html) {
        StringBuilder etiqueta = new StringBuilder();  

        for (int i = 0; i < html.length(); i++) {
            char c = html.charAt(i);

            if (c == '<') {
                etiqueta.setLength(0);
                etiqueta.append(c); 
                i++;

                while (i < html.length() && html.charAt(i) != '>') {
                    etiqueta.append(html.charAt(i));
                    i++;
                }
                etiqueta.append('>'); 

                if (etiqueta.charAt(1) != '/') {
                    String etiquetaStr = etiqueta.toString().substring(1, etiqueta.length() - 1).trim();
                  
                    if (palabrasReservadas.contains(etiquetaStr)) {
                        try {
                            pt.push(etiquetaStr); 
                            pc.push(etiquetaStr); 
                        } catch (Exception e) {
                            return false; 
                        }
                    } else {
                        return false; 
                    }
                }
    
                else if (etiqueta.charAt(1) == '/') {
                    String etiquetaStr = etiqueta.toString().substring(2, etiqueta.length() - 1).trim();
                 
                    if (palabrasReservadas.contains(etiquetaStr)) {
                        try {

                            if (!etiquetaStr.equals(pc.peek())) {
                                return false; 
                            }
                            pc.pop(); 
                            pt.pop();
                        } catch (Exception e) {
                            return false; 
                        }
                    } else {
                        return false; 
                    }
                }
            }
        }

        return pt.isEmpty() && pc.isEmpty(); 
    }
}
