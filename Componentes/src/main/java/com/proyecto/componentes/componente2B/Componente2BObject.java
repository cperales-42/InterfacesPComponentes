/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.componentes.componente2B;

import java.util.EventObject;

/**
 *
 * @author Meu
 */
public class Componente2BObject extends EventObject{
    private final String codigo;
    private final String texto;
    private final int indice;
    private final boolean tieneMensaje;

    public Componente2BObject(Object source, String codigo, String texto, int indice, boolean tieneMensaje) {
        super(source);
        this.codigo = codigo;
        this.texto = texto;
        this.indice = indice;
        this.tieneMensaje = tieneMensaje;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTexto() {
        return texto;
    }

    public int getIndice() {
        return indice;
    }

    public boolean isTieneMensaje() {
        return tieneMensaje;
    }
}
