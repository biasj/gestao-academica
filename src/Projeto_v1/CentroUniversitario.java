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
    
    public Disciplina getDisciplina(String codigo) {
        
        // loop que vai localizar e atribuir o objeto à variável
        for (int i=0; i < this.disciplinas.size(); i++){
            if (disciplinas.get(i).getCodigo() == codigo) {
                return disciplinas.get(i);
            }
        }
        
        return null;
    }

    public void carregarDados(String arqDisciplinas, String arqEstudantes, String arqMatriculas) throws FileNotFoundException {
        // reader para ler arquivos de estudantes
        BufferedReader bufferEstudante = new BufferedReader(new FileReader(arqEstudantes));
        
        String linhaEstudante[];
        String linhaMatricula[];
        String linhaDisciplina[];
        String linha;
        
        // LEITURA DE DISCIPLINAS
        try {
            
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
                 
                 System.out.println("codigo: " + linhaDisciplina[0] + " credito: " + linhaDisciplina[1]);
                 
            }
        } catch(FileNotFoundException e){
            System.out.println("Erro na abertura de arquivo de disciplinas");
            e.printStackTrace();
        }
        
        // LEITURA DE MATRÍCULAS POR SCANNER -> falta relacionar com os estudantes e com as disciplinas
        try{
            File farqMatriculas = new File(arqMatriculas);
            Scanner scMatriculas = new Scanner(farqMatriculas);
            
            Matricula novaMatricula;
            Disciplina objDisciplina;
            
            // enquanto houver linha seguinte
            while(scMatriculas.hasNextLine()){
                // le a linha
                linha = scMatriculas.nextLine();
                // divide a linha por : e coloca dentro do vetor
                linhaMatricula = linha.split(":");
                
                // não sei se entendi (VERIFICAR)
                novaMatricula = new Matricula(null, this.getDisciplina(linhaMatricula[1]));
                // falta criar a disciplina (VERIFICAR) -> atribuir pra disciplina, atribuir pra 
                
                
                // imprime para verificar se está tudo certo (RETIRAR)
                System.out.println("codigo: " + linhaMatricula[0] + " credito: " + linhaMatricula[1]);
                
            }
        } catch(FileNotFoundException e){
            System.out.println("Erro na abertura de arquivo de Matriculas.");
            e.printStackTrace();
        }
        
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
                    
                    // imprime para verificar se está tudo certo (RETIRAR)
                    System.out.println("id: " + linhaEstudante[0] + " nome: " + linhaEstudante[1] + " email: " + linhaEstudante[2]);
                    
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
    
}
