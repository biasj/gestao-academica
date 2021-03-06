/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto_v2;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Gabriel
 */
public class GestaoAcademicaApp2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        CentroUniversitario senac = new CentroUniversitario("Senac v2");
        senac.carregarDados("disciplinas.txt", "estudantes2.txt", "matriculas.txt");

        interfaceGestao(senac);
    }

    //método de impressão do menu inicial e obtenção da opção selecionada pelo usuário    
    public static int menuInicial() {
        int indice;
        Scanner reader = new Scanner(System.in);
        System.out.println("\nInforme a opção desejada:");
        System.out.println("[1] Consultar a lista de estudantes;");
        System.out.println("[2] Listar todas as disciplinas;");
        System.out.println("[3] Listar alunos matriculados a partir de uma disciplina;");
        System.out.println("[4] Listas disciplinas a partir de código de aluno;");
        System.out.println("[0] Sair do sistema;");
        System.out.print("Escolha: ");
        indice = reader.nextInt();
        System.out.println("");
        return indice;
    }

    public static void interfaceGestao(CentroUniversitario centro) {
        System.out.println("Gestão Acadêmica - Centro Universitário " + centro.getNome());
        String entrada = null;
        long id;
        int indice;
        do {
            indice = menuInicial();
            switch (indice) {
                case 0:
                    termino();
                    break;
                case 1:
                    listaAlunos(centro);
                    break;
                case 2:
                    listaDisciplinas(centro);
                    break;
                case 3:
                    entrada = getNumeroDisciplinas();
                    listarEstudantes(entrada, centro);
                    break;
                case 4:
                    id = getNumeroEstudante();
                    listarDisciplinas(id, centro);
                    break;

                default:
                    reportaErro();
            }
        } while (indice != 0);
    }

    // [1] método para chamar a listagem de alunos e seus ids.
    public static void listaAlunos(CentroUniversitario centro) {

        ArrayList<Estudante> estudantes = centro.getEstudantes();

        int cont = 0; //contador para devolver o total de alunos matriculados.
        for (Estudante e : estudantes) {
            System.out.println(e);
            cont++;
        }
        System.out.println("\nTotal de alunos: " + cont);

    }

    // [2] método para chamar a listagem de disciplinas.
    public static void listaDisciplinas(CentroUniversitario centro) {
        ArrayList<Disciplina> disciplinas = centro.getDisciplinas();
        int cont = 0; //contador para devolver o total de disciplinas.
        for (Disciplina d : disciplinas) {
            d.imprimeCodigo();
            cont++;
        }
        System.out.println("\nTotal de disciplinas: " + cont);
    }

    // [3] método de listagem de estudantes.
    public static void listarEstudantes(String codigoDisciplina, CentroUniversitario senac) {
        ArrayList<Disciplina> disciplinas = senac.getDisciplinas();

        int cont = 0; // contador para listagem do total de alunos matriculados em determinada disciplina.

        for (Disciplina d : disciplinas) {

            if (d.getCodigo().equals(codigoDisciplina)) {
                ArrayList<Estudante> estudantes = d.getEstudantesMatriculados();
                
                for (Estudante e : estudantes) {
                    System.out.println(e);
                    cont++;
                }
            }
            
        }

        System.out.println("\nTotal de alunos: " + cont);
    }

    // método para obter o número da disciplina a ser fornecido pelo usuário.
    public static String getNumeroDisciplinas() {
        String entrada;
        Scanner reader = new Scanner(System.in);
        System.out.print("Digite o código da disciplina: ");
        entrada = reader.next();
        entrada = entrada.toUpperCase();
        return entrada;
    }

    // [4] método de listagem de Disciplinas (item 4 do menu).
    public static void listarDisciplinas(Long codigoAluno, CentroUniversitario senac) {
        ArrayList<Estudante> estudantes = senac.getEstudantes();
        int cont = 0; // contador para listagem do total de crédito de um mesmo aluno.
        for(Estudante e: estudantes) {
            if(e.getId() == codigoAluno) {
                ArrayList<Disciplina> disciplinas = e.getDisciplinasMatriculadas();
                for (Disciplina d: disciplinas) {
                    System.out.println(d);
                    cont = cont + d.getCreditos();
                }
            }
        }
        System.out.println("\nTotal de créditos: " + cont); 
    }

    //método para obter o número do ID do aluno a ser fornecido pelo usuário.
    public static Long getNumeroEstudante() {
        long entrada;
        Scanner reader = new Scanner(System.in);
        System.out.print("Digite o ID do Aluno: ");
        entrada = reader.nextLong();
        return entrada;
    }
    
    //mensagem de término do programa.
    public static void termino() {
        System.out.println("Sessão encerrada.");
    }

    //mensagem de erro caso o usuário digite uma opção inválida.
    public static void reportaErro() {
        System.out.println("Opção inválida. Tente novamente.");
    }

}
