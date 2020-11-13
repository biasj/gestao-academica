/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto_v2;

/**
 *
 * @author beatrizsato
 */
public class EstudantePos extends Estudante {
    private String tema;
    private String orientador;

    public EstudantePos(long id, String nome, String email, String tema, String orientador) {
        super(id, nome, email);
        this.tema = tema;
        this.orientador = orientador;
    }
    
    @Override
    public int getTotalCreditos() {
        return super.getCreditosMatriculas();
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getOrientador() {
        return orientador;
    }

    public void setOrientador(String orientador) {
        this.orientador = orientador;
    }

    @Override
    public String toString() {
        return "EstudantePos: " + "nome: " + super.getNome() + ", tema: " + tema + ", orientador: " + orientador + '}';
    }
    
}
