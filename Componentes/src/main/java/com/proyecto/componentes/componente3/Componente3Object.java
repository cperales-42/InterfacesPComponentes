/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.componentes.componente3;

import java.util.EventObject;

/**
 *
 * @author Meu
 */
public class Componente3Object extends EventObject{
     private final String textoBusqueda;

    public Componente3Object(Object source, String textoBusqueda) {
        super(source);
        this.textoBusqueda = textoBusqueda;
    }

    public String getTextoBusqueda() {
        return textoBusqueda;
    }
}
