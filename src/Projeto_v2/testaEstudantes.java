/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto_v2;

/**
 *
 * @author beatrizsato
 */
public class testaEstudantes {
    public static void main(String[] args) {
        Estudante estudante = new EstudanteGrad(1, "bia", "bia@gmail", 12);
        estudante.addMatricula(new Matricula(estudante, new Disciplina("123", 4)));
        estudante.addMatricula(new Matricula(estudante, new Disciplina("123", 8)));
        
        System.out.println(estudante.getTotalCreditos());
        System.out.println(estudante);
        
        Estudante pos = new EstudantePos(1, "marcelo", "marcelo@gmail", "leis", "Juiz Joao");
        pos.addMatricula(new Matricula(estudante, new Disciplina("123", 2)));
        pos.addMatricula(new Matricula(estudante, new Disciplina("123", 2)));

        System.out.println(pos.getTotalCreditos());
        System.out.println(pos);
    }
}
