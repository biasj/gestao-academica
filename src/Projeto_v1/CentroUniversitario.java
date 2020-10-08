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

    public void carregarDados(String arqDisciplinas, String arqEstudantes, String arqMatriculas) {
        
        
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
