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
        
        do {
            indice = menuInicial();
            if (indice == 1) {

            } else {
                if (indice == 2) {
                    
                } else {
                    if (indice == 3) {
                        
                    } else {
                        if (indice != 0) {
                            reportaErro();
                        }
                    }
                }
            }
            
        } while (indice != 0);
        if (indice == 0) {
            termino();
        }
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
    
    //método de impressão do menu inicial e obtenção da opção selecionada pelo usuário    
    public static int menuInicial(){
        int indice;
        Scanner reader = new Scanner(System.in);
        System.out.println("Informe a opção desejada:");
        System.out.println("[1] Consultar a lista de estudantes;");
        System.out.println("[2] Listar alunos matriculados a partir de uma disciplina;");
        System.out.println("[3] Listas disciplinas a partir de código de aluno;");
        System.out.println("[0] Sair do sistema;");
        indice = reader.nextInt();
        return indice;
    }
    
    //método de impressão de mensagem de término    
    public static void termino(){
        System.out.println("Sessão encerrada.");
    }
    
    //método de impressão de mensagem de erro    
    public static void reportaErro(){
        System.out.println("Opção inválida. Tente novamente.");
    }
    
}
