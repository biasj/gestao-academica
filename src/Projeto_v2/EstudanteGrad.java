/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto_v2;

import java.util.ArrayList;

/**
 *
 * @author beatrizsato
 */
public class EstudanteGrad extends Estudante {
    private int horasAtividades;
    
    public EstudanteGrad(long id, String nome, String email, int horasAtividades) {
        super(id, nome, email);
        this.horasAtividades = horasAtividades;
    }
    
    public int getHorasAtividades() {
        return horasAtividades;
    }

    public void setHorasAtividades(int horasAtividades) {
        this.horasAtividades = horasAtividades;
    }
        
    @Override
    public int getTotalCreditos() {
        return horasAtividades + super.getCreditosMatriculas();
    }
    
    @Override
    public String toString() {
        return "\nGraduação\n" + "Nome: " + nome + "\nID: " + id + "\nE-mail: " + email + "\nCréditos: " 
                + getTotalCreditos() + "\nAtiv. Complementares: " + getHorasAtividades();
    }
    
}
