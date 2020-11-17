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
        this.creditosTotais = horasAtividades;
    }
    
    
    public int getHorasAtividades() {
        return horasAtividades;
    }

    public void setHorasAtividades(int horasAtividades) {
        this.horasAtividades = horasAtividades;
    }

    @Override
    
    public void addMatricula(Matricula matricula){
        this.matriculas.add(matricula);
        
        this.creditosTotais = this.creditosTotais + matricula.getDisciplina().getCreditos();
        
    }
    
    public int getTotalCreditos() {
        return horasAtividades + super.getCreditosMatriculas();
    }

    public void imprimeTotalCreditos() {
    	System.out.print(" | Total de créditos: " + this.getTotalCreditos());
    }
    
    public void imprimeHorasAtividades() {
    	System.out.print(" | Horas de atividades complementares: " + this.getHorasAtividades());
    }
    
    @Override
    public String toString() {
        return "EstudanteGrad: " + "nome: " + super.getNome() + ", horasAtividades: " + horasAtividades + '}';
    }
    
}
