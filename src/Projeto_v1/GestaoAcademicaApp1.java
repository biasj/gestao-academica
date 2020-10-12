/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto_v1;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

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
        int indice;
        CentroUniversitario senac = new CentroUniversitario("Senac");
        senac.carregarDados("disciplinas.txt", "estudantes.txt", "matriculas.txt");
        
        do {
            indice = menuInicial();
            if (indice == 1) {
                listaAlunos(senac);
            } else {
                if (indice == 2) {
                   listaDisciplinas(); 
                } else {
                    if (indice == 3) {
                        alunosPorDisciplinas();
                    } else {
                        if (indice == 4) {
                            disciplinasPorAlunos();
                        } else {
                            if (indice != 0) {
                                reportaErro();
                            }
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
        System.out.println("[2] Listar todas as disciplinas;");
        System.out.println("[3] Listar alunos matriculados a partir de uma disciplina;");
        System.out.println("[4] Listas disciplinas a partir de código de aluno;");
        System.out.println("[0] Sair do sistema;");
        indice = reader.nextInt();
        return indice;
    }
 
    public static void termino(){
        System.out.println("Sessão encerrada.");
    }
    
    public static void reportaErro(){
        System.out.println("Opção inválida. Tente novamente.");
    }
    //método para chamar a listagem de alunos e seus ids.
    public static void listaAlunos(CentroUniversitario centro){
        ArrayList<Estudante> estudantes = centro.getEstudantes();
        int cont = 0; //contador para devolver o total de alunos matriculados.
        for(Estudante e: estudantes) {
            e.imprimeId();
            e.imprimeAlunos();
            cont++;
        }
        System.out.println("Total de alunos: " + cont);

    }
    // método para chamar a listagem de disciplinas.
    public static void listaDisciplinas(){
        ArrayList<Disciplina> disciplinas = new ArrayList<>();
        int cont = 0; //contador para devolver o total de disciplinas.
        for (Disciplina d : disciplinas) {
            d.imprimeCodigo();
            cont++;
        }
        System.out.println("Total de disciplinas: " + cont);
    }
    
    //ainda não descobri como passar o código da matrícula para imprimir apenas os alunos daquela matrícula.
    public static void alunosPorDisciplinas(){
        Scanner reader = new Scanner (System.in);
        ArrayList<Disciplina> disciplinas = new ArrayList<>();
        ArrayList<Estudante> estudantes = new ArrayList<>();
        int cont = 0;
        
        System.out.println("Digite o código da disciplina: ");
        int entrada = reader.nextInt();
        
        for (Disciplina d : disciplinas) { 
            System.out.println(d.getEstudantesMatriculados());
            
            cont++;
        }
        System.out.println("Total de alunos matriculados: " + cont);
    }
    
    public static void disciplinasPorAlunos(){
        Scanner reader = new Scanner (System.in);
        ArrayList<Disciplina> disciplinas = new ArrayList<>();
        ArrayList<Estudante> estudantes = new ArrayList<>();
        int cont = 0;
        
        System.out.println("Digite o ID do Aluno: ");
        int entrada = reader.nextInt();
        
        for (Estudante e: estudantes) { 
            System.out.println(e.getDisciplinasMatriculadas());
            
            cont++;
        }
        System.out.println("Total de créditos do aluno: " + cont);
    }
    
    
}
