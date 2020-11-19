/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto_v2;

import java.util.ArrayList;

/**
 *
 * @author Gabriel
 */
public class Disciplina {
    private String codigo;
    private int creditos;
    private ArrayList<Matricula> matriculas;

    public Disciplina(String codigo, int creditos){
        this.codigo = codigo;
        this.creditos = creditos;
        this.matriculas = new ArrayList<>();
    }
    
    public String getCodigo() {
        return codigo;
    }

    public int getCreditos() {
        return creditos;
    }

    public ArrayList<Matricula> getMatriculas() {
        return matriculas;
    }

    public void addMatricula(Matricula matricula){
        this.matriculas.add(matricula);
    }
    
    public ArrayList<Estudante> getEstudantesMatriculados(){
        
        ArrayList<Estudante> estudantes = new ArrayList<>();
        
        for(int i = 0; i < matriculas.size(); i++){
            estudantes.add((Estudante) matriculas.get(i).getEstudante());
        }
        
        return estudantes;
    }

    public void imprimeCodigo(){
        System.out.println(" Disciplina n. " + this.getCodigo());
    }
    
    @Override
    public String toString() {
        return "\nDisciplina \n" + "Código: " + codigo + "\nCréditos: " + creditos +"\n";
    }
    
    
}
