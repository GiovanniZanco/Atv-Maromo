package Model;
import java.time.LocalDate;

public class ControleEmprestimo {
    // Método para registrar o empréstimo
    public String registraEmprestimo(Usuario usuario, Livro livro) {
        if (livro.isDisponivel()) {
            livro.setDisponivel(false); // Marcando o livro como indisponível
            LocalDate dataEmprestimo = LocalDate.now();
            LocalDate dataDevolucao = dataEmprestimo.plusWeeks(1); // Data de devolução 1 semana após o empréstimo
            RegistroEmprestimo emprestimo = new RegistroEmprestimo(1, usuario, livro, dataEmprestimo);
            emprestimo.setDataDevolucao(dataDevolucao);

            return "Empréstimo registrado para: " + usuario.getNome() +
                    "\nLivro: " + livro.getTitulo() +
                    "\nData do Empréstimo: " + dataEmprestimo +
                    "\nData da Devolução: " + dataDevolucao;
        } else {
            return "Livro não está disponível.";
        }
    }

    // Método para registrar a devolução
    public String registraDevolucao(Usuario usuario, Livro livro) {
        if (!livro.isDisponivel()) {
            livro.setDisponivel(true); // Marcando o livro como disponível novamente
            LocalDate dataDevolucao = LocalDate.now();
            return "Devolução registrada para: " + usuario.getNome() +
                    "\nLivro: " + livro.getTitulo() +
                    "\nData da Devolução: " + dataDevolucao;
        } else {
            return "Livro já está disponível, não é necessário devolver.";
        }
    }
}
