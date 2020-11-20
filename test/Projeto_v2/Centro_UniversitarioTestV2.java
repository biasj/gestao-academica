package Projeto_v2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author anacris
 */
public class Centro_UniversitarioTestV2 {
    
    private final CentroUniversitario centroUniversitario;
    private final String nomeCentro_UniversitarioEsperado = "Senac";
    private final int[] matriculasPorEstudante = {2,3,2,2,2,2,2,2,5,3};
    private final int[] matriculasPorDisciplina = {0,1,7,2,1,1,2,4,1,6};
    private List<Matricula> matriculasArray;
    
    public Centro_UniversitarioTestV2() throws FileNotFoundException {

        centroUniversitario = new CentroUniversitario("Senac");
        centroUniversitario.carregarDados("disciplinas.txt", "estudantes2.txt", 
                                                   "matriculas.txt");
        
        matriculasArray = carregarMatriculas("matriculas.txt");
        
    }

 
    public Estudante findEstudanteById(long id, List<Estudante> estudantes) {
        for (Estudante estudante : estudantes) {
            if (id == estudante.getId()) {
                return estudante; //estudante encontrado
            }
        }
        return null; //estudante não encontrado
    }

    public Disciplina findDisciplinaByCodigo(String codigo, List<Disciplina> disciplinas) {
        for (Disciplina disciplina : disciplinas) {
            if (codigo.equals(disciplina.getCodigo())) {
                return disciplina;//disciplina encontrada
            }
        }
        return null;//disciplina não encontrada
    }

    @Test
    public void testNomeCentro_Universitario() {
        String nomeObtido = centroUniversitario.getNome();
        assertEquals(nomeCentro_UniversitarioEsperado, nomeObtido);
    }

    @Test
    public void testEstudantes() {
        List<Estudante> estudantes = centroUniversitario.getEstudantes();
        int numeroEsperado = 10;
        int numeroObtido = estudantes.size();
        Assert.assertEquals(numeroEsperado, numeroObtido);
        for (Estudante e : estudantes) {
            long id = e.getId();
            String nomeEsperado = e.getNome();
            String emailEsperado = e.getEmail();
            Estudante estudanteEncontrado = findEstudanteById(id, estudantes);
            assertNotNull(estudanteEncontrado);
            assertEquals(nomeEsperado, estudanteEncontrado.getNome());
            assertEquals(emailEsperado, estudanteEncontrado.getEmail());
        }
    }
    
    @Test
    public void testTipoEstudante() {
        List<Estudante> estudantes = centroUniversitario.getEstudantes();
        for(Estudante e : estudantes) {
            if(e instanceof EstudanteGrad){
                assertNotNull(((EstudanteGrad) e).getHorasAtividades());
            } else {
                assertNotNull(((EstudantePos) e).getOrientador());
            }
        }
    }

    @Test
    public void testDisciplinas() {
        List<Disciplina> disciplinas = centroUniversitario.getDisciplinas();
        int numeroEsperado = 10;
        int numeroObtido = disciplinas.size();
        assertEquals(numeroEsperado, numeroObtido);
        for (Disciplina disciplina : disciplinas) {
            String codigo = disciplina.getCodigo();
            int creditos = disciplina.getCreditos();
            Disciplina disciplinaEncontrada = findDisciplinaByCodigo(codigo, disciplinas);
            assertNotNull(disciplinaEncontrada);
            assertEquals(creditos, disciplinaEncontrada.getCreditos());
        }
    }

    @Test
    public void testMatriculas() {
        List<Estudante> estudantes = centroUniversitario.getEstudantes();
        List<Disciplina> disciplinas = centroUniversitario.getDisciplinas();
        List<Matricula> matriculas =  null;
        
        int k = 0;
        for (Estudante estudante: estudantes) {
            long id = estudante.getId();
            Estudante estudanteEncontrado = findEstudanteById(id, estudantes);
            assertEquals(matriculasPorEstudante[k], estudanteEncontrado.getMatriculas().size());
            k++;
        }
        
        int i=0;
        for (Disciplina disciplina: disciplinas) {
            String codigo = disciplina.getCodigo();
            Disciplina disciplinaEncontrada = findDisciplinaByCodigo(codigo, disciplinas);
            assertEquals(matriculasPorDisciplina[i], disciplinaEncontrada.getMatriculas().size());
            i++;
        }
        
        for (Matricula matricula: matriculasArray) {
            long idEstudante = matricula.getEstudante().getId();
            String codigoDisciplina = matricula.getDisciplina().getCodigo();
            Estudante estudante = findEstudanteById(idEstudante, estudantes);
            Disciplina disciplina = findDisciplinaByCodigo(codigoDisciplina, disciplinas);
            
            Matricula matriculaEncontradaInEstudante = null;
            matriculas = estudante.getMatriculas();
            for (Matricula matricula1 : matriculas) {
                if (codigoDisciplina.equals(matricula1.getDisciplina().getCodigo()) 
                        && idEstudante == matricula1.getEstudante().getId()) {
                    matriculaEncontradaInEstudante = matricula1;
                    break;
                }
            }
            assertNotNull(matriculaEncontradaInEstudante);
            
            Matricula matriculaEncontradaInDisciplina = null;
            matriculas = disciplina.getMatriculas();
            for (Matricula matricula2 : matriculas) {
                if (codigoDisciplina.equals(matricula2.getDisciplina().getCodigo()) 
                           && idEstudante == matricula2.getEstudante().getId()) {
                    matriculaEncontradaInDisciplina = matricula2;
                    break;
                }
            }
            assertNotNull(matriculaEncontradaInDisciplina);
        }
    }
    
    public List<Matricula> carregarMatriculas(String arqMatricula) throws FileNotFoundException {
        String linha;
        String linhaQuebrada[];
        List<Matricula> matriculas = new ArrayList<>();
        
        // reader para ler arquivos de matricula
        BufferedReader bufferMatricula = new BufferedReader(new FileReader(arqMatricula));
        // LEITURA DE MATRÍCULAS
        try {
            // se r!= null significa que r está guardando referência do arquivo físico
            if (bufferMatricula != null) {
                linha = bufferMatricula.readLine();
                while (linha != null) {
                    linhaQuebrada = linha.split(":");
                    Long idEstudante = Long.parseLong(linhaQuebrada[0]);
                    String codDisciplina = linhaQuebrada[1];

                    Disciplina disciplina = null;
                    Estudante estudante = null;
                    
                    for(Estudante est : centroUniversitario.getEstudantes()) {
                        if(est.getId() == idEstudante) {
                            estudante = est;
                        }
                    }
                    
                    for(Disciplina d : centroUniversitario.getDisciplinas()) {
                        if(d.getCodigo().equals(codDisciplina)) {
                            disciplina = d;
                        }
                    }
                     
                    if(estudante != null && disciplina != null) {
                        Matricula novaMatricula = new Matricula(estudante, disciplina);
                        matriculas.add(novaMatricula);
                    }
                    
                    // lê a próxima linha
                    linha = bufferMatricula.readLine();
                }

            }
            // encerra comunicação entre arquivo lógico e físico, não encerra arquivo físico
            bufferMatricula.close();

        } catch (Exception e) {
            System.exit(-1);
        }
        
        return matriculas;
    }
}
