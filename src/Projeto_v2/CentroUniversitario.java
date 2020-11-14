/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto_v2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Gabriel
 */
public class CentroUniversitario {

    private String nome;
    private ArrayList<Object> estudantes;
    private ArrayList<Disciplina> disciplinas;

    public String getNome() {
        return nome;
    }

    public ArrayList<Object> getEstudantes() {
        return estudantes;
    }

    public ArrayList<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public CentroUniversitario(String nome) {

        this.nome = nome;
        this.estudantes = new ArrayList<>();
        this.disciplinas = new ArrayList<>();
    }
    

    public void carregarDados(String arqDisciplinas, String arqEstudantes, String arqMatriculas) throws FileNotFoundException {
        String linha = null;
        String linhaQuebrada[] = null;
        
        this.carregarEstudantes(arqEstudantes, linha, linhaQuebrada);
        this.carregarDisciplinas(arqDisciplinas, linha, linhaQuebrada);
        this.carregarMatriculas(arqMatriculas, linha, linhaQuebrada);
    }
    
    public void carregarEstudantes(String arqEstudantes, String linha, String[] linhaQuebrada) throws FileNotFoundException {
        // reader para ler arquivos de estudantes
        BufferedReader bufferEstudante = new BufferedReader(new FileReader(arqEstudantes));
        // LEITURA DE ESTUDANTES
        try {
        // se r!= null significa que r está guardando referência do arquivo físico
            if(bufferEstudante != null) {
                linha = bufferEstudante.readLine();
                while(linha != null) {
                    linhaQuebrada = linha.split(":");
                    
                    // Se Graduação
                    if(linhaQuebrada[3].equals("GRAD")){
                        EstudanteGrad novoEstudante = new EstudanteGrad(Long.parseLong(linhaQuebrada[0]),linhaQuebrada[1], linhaQuebrada[2], Integer.parseInt(linhaQuebrada[4]));
                        // adiciona o novo objeto ao array de estudantes da classe
                        estudantes.add(novoEstudante);
                    } 
                    // Senão se Pós-Graduação
                    else if(linhaQuebrada[3].equals("POS")){
                        EstudantePos novoEstudante = new EstudantePos(Long.parseLong(linhaQuebrada[0]), linhaQuebrada[1], linhaQuebrada[2], linhaQuebrada[4], linhaQuebrada[5]);
                        // adiciona o novo objeto ao array de estudantes da classe
                        estudantes.add(novoEstudante);
                    }
                    
                    
                    
                    // lê a próxima linha
                    linha = bufferEstudante.readLine();
                }
                
            }
            // encerra comunicação entre arquivo lógico e físico, não encerra arquivo físico
            bufferEstudante.close();
            
        } catch(Exception e) {
            System.exit(-1);
        }    
    }
    
    public void carregarDisciplinas(String arqDisciplinas, String linha, String[] linhaQuebrada) {
        // LEITURA DE DISCIPLINAS
        try {
            // leitura de arquivos por Scanner
            File farqDisciplinas = new File(arqDisciplinas);
            Scanner scDisciplinas = new Scanner(farqDisciplinas);
            Disciplina novaDisciplina;
           
            while(scDisciplinas.hasNextLine()){
                
                 linha = scDisciplinas.nextLine();
                 
                 linhaQuebrada = linha.split(":");
                 
                 // cria novo objeto de disciplina baseado na leitura do arquivo
                 novaDisciplina = new Disciplina(linhaQuebrada[0], Integer.parseInt(linhaQuebrada[1]));
                 // adiciona o novo objeto ao array de disciplina da classe CentroUniversitario
                 this.disciplinas.add(novaDisciplina);
            }
        } catch(FileNotFoundException e){
            System.out.println("Erro na abertura de arquivo de disciplinas");
            e.printStackTrace();
        }
    }
    
    public void carregarMatriculas(String arqMatriculas, String linha, String[] linhaQuebrada) {
        // LEITURA DE MATRÍCULAS POR SCANNER -> 
        try{
            File farqMatriculas = new File(arqMatriculas);
            Scanner scMatriculas = new Scanner(farqMatriculas);
            Estudante estudante, estudanteAux;
            Disciplina disciplina;
         
            // enquanto houver linha seguinte
            while(scMatriculas.hasNextLine()){
                // le a linha
                linha = scMatriculas.nextLine();
                // divide a linha por : e coloca dentro do vetor
                linhaQuebrada = linha.split(":");
                long idEstudante = Long.parseLong(linhaQuebrada[0]);
                String codDisciplina = linhaQuebrada[1];
                
                estudante = null;
                disciplina = null;
                
                for(Object e : estudantes) {
                   
                    estudanteAux = (Estudante) e;
                   
                    // se o estudante estiver na lista
                    if(estudante.getId() == idEstudante){                         
                        estudante = estudanteAux;
                    }
                }
                
                for(Disciplina d: disciplinas) {
                    if(d.getCodigo().equals(codDisciplina)) {
                        disciplina = d;
                    }
                }
                
                // falta criar a disciplina -> addMatricula (estudante), addMatricula (disciplina)  
                if(estudante != null && disciplina != null) {
                    Matricula novaMatricula = new Matricula(estudante, disciplina);
                    estudante.addMatricula(novaMatricula);
                    disciplina.addMatricula(novaMatricula);
                }
  
            }
        } catch(FileNotFoundException e){
            System.out.println("Erro na abertura de arquivo de Matriculas.");
            e.printStackTrace();
        }
    }
    
}
