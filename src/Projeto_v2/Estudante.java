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
public abstract class Estudante {
    protected long id;
    protected String nome;
    protected String email;
    protected ArrayList<Matricula> matriculas;
    
    public Estudante(long id, String nome, String email){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.matriculas = new ArrayList<>();
    }
    
    public abstract int getTotalCreditos();

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
        ArrayList<Disciplina> disciplinas = new ArrayList<>();
        
        for (int i = 0 ; i < matriculas.size(); i++) {
            disciplinas.add(matriculas.get(i).getDisciplina());
        }
        
        return disciplinas;
    }
    
    public int getCreditosMatriculas() {
        int total = 0;
        for(Matricula matricula : matriculas) {
            total += matricula.getDisciplina().getCreditos();
        }
        return total;
    }

}
