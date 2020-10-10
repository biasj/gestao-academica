/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto_v1;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 *
 * @author Gabriel
 */
public class GestaoAcademicaApp1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        System.out.println("Gestão Acadêmica - Centro Universitário SENAC\n");
        
        CentroUniversitario senac = new CentroUniversitario("Senac");
        senac.carregarDados("disciplinas.txt", "estudantes.txt", "matriculas.txt");
        
    }
    
    // 3 - consulta de informações de estudantes por disciplina
    public static void listarEstudantes(String codigoDisciplina) {
        // pegar o objeto disciplina através dos dados do CentroUniversitario e usando o getEstudantesMatriculados
        CentroUniversitario senac = new CentroUniversitario("Senac");
        listarEstudantes(codigoDisciplina, senac);
    }
    
    public static void listarEstudantes(String codigoDisciplina, CentroUniversitario senac) {
        ArrayList<Disciplina> disciplinas = senac.getDisciplinas();
        
        for(Disciplina d: disciplinas) {
            if(d.getCodigo().equals(codigoDisciplina)) {
                System.out.println(d.getEstudantesMatriculados()); 
            }
        }
    }
    
}
