package Model;
public class Aluno extends Usuario {
    private String matricula;
    private String curso;

    public Aluno(int idUsuario, String nome, String email, String matricula, String curso) {
        super(idUsuario, nome, email);
        this.matricula = matricula;
        this.curso = curso;
    }

    // Getters e Setters
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}

