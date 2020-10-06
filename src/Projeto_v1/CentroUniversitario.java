/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto_v1;

import java.io.File;
import java.io.FileNotFoundException;
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

    public CentroUniversitario(String nome) {

        this.nome = nome;
        this.estudantes = new ArrayList<>();
        this.disciplinas = new ArrayList<>();
    }

    public void carregarDados(String arqDisciplinas, String arqEstudantes, String arqMatriculas) {
        
        
    }
}
