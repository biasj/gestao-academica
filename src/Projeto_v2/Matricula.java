/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto_v2;

/**
 *
 * @author Gabriel
 */
public class Matricula {
    private Estudante estudante;
    private Disciplina disciplina;
    
    public Matricula(Estudante estudante, Disciplina disciplina){
        this.estudante = estudante;
        this.disciplina = disciplina;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public Estudante getEstudante() {
        return estudante;
    }

}
