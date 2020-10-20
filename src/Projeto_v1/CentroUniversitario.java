/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto_v1;

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
    private ArrayList<Estudante> estudantes;
    private ArrayList<Disciplina> disciplinas;

    public String getNome() {
        return nome;
    }

    public ArrayList<Estudante> getEstudantes() {
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
        // reader para ler arquivos de estudantes
        BufferedReader bufferEstudante = new BufferedReader(new FileReader(arqEstudantes));
        
        String linhaEstudante[];
        String linhaMatricula[];
        String linhaDisciplina[];
        String linha;
        
        // LEITURA DE ESTUDANTES
        try {
        // se r!= null significa que r está guardando referência do arquivo físico
            if(bufferEstudante != null) {
                linha = bufferEstudante.readLine();
                while(linha != null) {
                    linhaEstudante = linha.split(":");
                    
                    // cria novo objeto de disciplina baseado na leitura do arquivo
                    Estudante novoEstudante = new Estudante(Long.parseLong(linhaEstudante[0]), linhaEstudante[1], linhaEstudante[2]);
                    // adiciona o novo objeto ao array de estudantes da classe
                    estudantes.add(novoEstudante);
                    
                    // lê a próxima linha
                    linha = bufferEstudante.readLine();
                }
                
            }
            // encerra comunicação entre arquivo lógico e físico, não encerra arquivo físico
            bufferEstudante.close();
            
        } catch(Exception e) {
            System.exit(-1);
        }    
        
        // LEITURA DE DISCIPLINAS
        try {
            // leitura de arquivos por Scanner
            File farqDisciplinas = new File(arqDisciplinas);
            Scanner scDisciplinas = new Scanner(farqDisciplinas);
            Disciplina novaDisciplina;
           
            while(scDisciplinas.hasNextLine()){
                
                 linha = scDisciplinas.nextLine();
                 
                 linhaDisciplina = linha.split(":");
                 
                 // cria novo objeto de disciplina baseado na leitura do arquivo
                 novaDisciplina = new Disciplina(linhaDisciplina[0], Integer.parseInt(linhaDisciplina[1]));
                 // adiciona o novo objeto ao array de disciplina da classe CentroUniversitario
                 this.disciplinas.add(novaDisciplina);
                 
                 
            }
        } catch(FileNotFoundException e){
            System.out.println("Erro na abertura de arquivo de disciplinas");
            e.printStackTrace();
        }
        
        // LEITURA DE MATRÍCULAS POR SCANNER -> 
        try{
            File farqMatriculas = new File(arqMatriculas);
            Scanner scMatriculas = new Scanner(farqMatriculas);
           
            
            // enquanto houver linha seguinte
            while(scMatriculas.hasNextLine()){
                // le a linha
                linha = scMatriculas.nextLine();
                // divide a linha por : e coloca dentro do vetor
                linhaMatricula = linha.split(":");
                long idEstudante = Long.parseLong(linhaMatricula[0]);
                String codDisciplina = linhaMatricula[1];
               
                
                Estudante estudante = null;
                Disciplina disciplina = null;
                
                for(Estudante e : estudantes) {
                    // se o estudante estiver na lista
                    if(e.getId() == idEstudante){                         
                        estudante = e;
                        
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
