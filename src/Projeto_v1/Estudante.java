/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto_v1;

import java.util.ArrayList;

/**
 *
 * @author Gabriel
 */
public class Estudante {
    private long id;
    private String nome;
    private String email;
    private ArrayList<Matricula> matriculas;
    
    public Estudante(long id, String nome, String email){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.matriculas = new ArrayList<>();
    }

    public void setMatriculas(ArrayList<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<Matricula> getMatriculas() {
        return matriculas;
    }
    
    public void addMatricula(Matricula matricula){
        this.matriculas.add(matricula);
    }
    
    public ArrayList<Disciplina> getDisciplinasMatriculadas(){
        
        ArrayList<Disciplina> disciplinas;
        
        disciplinas = new ArrayList<>();
        
        for (int i = 0 ; i < matriculas.size(); i++) {
            disciplinas.add(matriculas.get(i).getDisciplina());
        }
        
        return disciplinas;
    }
    
    
    public void imprimeId(){
        System.out.print(" Matrícula n. " + this.getId());
    }
    
    public void imprimeAlunos(){
        System.out.print(" | Nome: " + this.getNome());
    }
    
    @Override
    public String toString() {
        return "Estudante{" + "id=" + id + ", nome=" + nome + ", email=" + email + "matriculas=" + matriculas + '}';
    }
    
}
