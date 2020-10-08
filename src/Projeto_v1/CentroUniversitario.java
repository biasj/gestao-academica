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

    public void carregarDados(String arqDisciplinas, String arqEstudantes, String arqMatriculas) {
        try {
            
            File farqDisciplinas = new File(arqDisciplinas);
            Scanner scDisciplinas = new Scanner(farqDisciplinas);
            String sDisciplina;
            String[] sDisciplinaPartes;
            Disciplina objDisciplina;
            
            
            while(scDisciplinas.hasNextLine()){
                
                 sDisciplina = scDisciplinas.nextLine();
                 
                 sDisciplinaPartes = sDisciplina.split(":");
                 
                 objDisciplina = new Disciplina(sDisciplinaPartes[0], Integer.parseInt(sDisciplinaPartes[1]));
                 
                 this.disciplinas.add(objDisciplina);
                 
                 System.out.println("codigo: " + sDisciplinaPartes[0] + " credito: " + sDisciplinaPartes[1]);
                 
            }
        }catch(FileNotFoundException e){
            System.out.println("Erro na abertura de arquivo de disciplinas");
            e.printStackTrace();
        }
        
        try{
            File farqMatriculas = new File(arqMatriculas);
            Scanner scMatriculas = new Scanner(farqMatriculas);
            String sMatricula;
            String[] sMatriculaPartes;
            Matricula objMatricula;
            Disciplina objDisciplina;
            
            while(scMatriculas.hasNextLine()){
                
                sMatricula = scMatriculas.nextLine();
                
                sMatriculaPartes = sMatricula.split(":");
                
                objMatricula = new Matricula(null, this.getDisciplina(sMatriculaPartes[1]));
                
            }
        }catch(FileNotFoundException e){
            System.out.println("Erro na abertura de arquivo de Matriculas.");
            e.printStackTrace();
        }
        
    }
    
    public void carregarDadosEstudantes(String arqEstudantes) throws FileNotFoundException, IOException {
        BufferedReader r = new BufferedReader(new FileReader(arqEstudantes));
        String v[];
        String linha;
        
        try {
        // se r!= null significa que r está guardando referência do arquivo físico
            if(r!=null) {
                linha=r.readLine();
                while(linha != null) {
                    v = linha.split(":");
                    // atribui id, nome e e-mail pro estudante
                    Estudante novoEstudante1 = new Estudante(Long.parseLong(v[0]), v[1], v[2]);
                    estudantes.add(novoEstudante1);
                    linha=r.readLine();
                }
                
            }
            // encerra comunicação entre arquivo lógico e físico, não encerra arquivo físico
            r.close();
            
        } catch(Exception e) {
            System.exit(-1);
        }
    }
    
}
