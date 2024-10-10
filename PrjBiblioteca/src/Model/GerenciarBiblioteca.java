package Model;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class GerenciarBiblioteca {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Lista de usuários e livros
        List<Usuario> usuarios = new ArrayList<>();
        List<Livro> livros = new ArrayList<>();

        // Controle de empréstimos
        ControleEmprestimo controle = new ControleEmprestimo();

        // Loop para o menu
        int opcao = 0;
        while (opcao != 5) {
            // Exibir o menu
            System.out.println("\nMenu:");
            System.out.println("1 - Registrar pessoa");
            System.out.println("2 - Registrar livro");
            System.out.println("3 - Emprestar livro");
            System.out.println("4 - Devolver livro");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    // Menu para registrar pessoa (Aluno, Professor, Funcionario)
                    registrarPessoa(scanner, usuarios);
                    break;
                case 2:
                    // Registrar livro
                    registrarLivro(scanner, livros);
                    break;
                case 3:
                    // Emprestar livro
                    emprestarLivro(scanner, usuarios, livros, controle);
                    break;
                case 4:
                    // Devolver livro
                    devolverLivro(scanner, usuarios, livros, controle);
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        }

        scanner.close();
    }

    // Método para registrar uma pessoa
    public static void registrarPessoa(Scanner scanner, List<Usuario> usuarios) {
        System.out.println("\nEscolha o tipo de pessoa para registrar:");
        System.out.println("1 - Aluno");
        System.out.println("2 - Professor");
        System.out.println("3 - Funcionario");
        System.out.print("Escolha uma opção: ");
        int tipoPessoa = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        System.out.print("Digite o ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha
        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o email: ");
        String email = scanner.nextLine();

        switch (tipoPessoa) {
            case 1: // Aluno
                System.out.print("Digite a matrícula: ");
                String matricula = scanner.nextLine();
                System.out.print("Digite o curso: ");
                String curso = scanner.nextLine();
                Aluno aluno = new Aluno(id, nome, email, matricula, curso);
                usuarios.add(aluno);
                System.out.println("Aluno registrado com sucesso!");
                break;
            case 2: // Professor
                System.out.print("Digite o departamento: ");
                String departamento = scanner.nextLine();
                System.out.print("Digite a titulação: ");
                String titulacao = scanner.nextLine();
                Professor professor = new Professor(id, nome, email, departamento, titulacao);
                usuarios.add(professor);
                System.out.println("Professor registrado com sucesso!");
                break;
            case 3: // Funcionario
                System.out.print("Digite o cargo: ");
                String cargo = scanner.nextLine();
                System.out.print("Digite o setor: ");
                String setor = scanner.nextLine();
                Funcionario funcionario = new Funcionario(id, nome, email, cargo, setor);
                usuarios.add(funcionario);
                System.out.println("Funcionario registrado com sucesso!");
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    // Método para registrar um livro
    public static void registrarLivro(Scanner scanner, List<Livro> livros) {
        System.out.print("\nDigite o ID do livro: ");
        int idLivro = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha
        System.out.print("Digite o título do livro: ");
        String titulo = scanner.nextLine();
        System.out.print("Digite o autor do livro: ");
        String autor = scanner.nextLine();

        Livro livro = new Livro(idLivro, titulo, autor, true);
        livros.add(livro);

        System.out.println("Livro registrado com sucesso!");
    }

    // Método para emprestar livro
    public static void emprestarLivro(Scanner scanner, List<Usuario> usuarios, List<Livro> livros, ControleEmprestimo controle) {
        if (usuarios.isEmpty() || livros.isEmpty()) {
            System.out.println("Não há usuários ou livros registrados.");
            return;
        }

        // Exibindo os usuários registrados
        System.out.println("\nUsuários disponíveis:");
        for (Usuario usuario : usuarios) {
            System.out.println("- " + usuario.getNome());
        }

        // Exibindo os livros disponíveis
        System.out.println("\nLivros disponíveis:");
        for (Livro livro : livros) {
            if (livro.isDisponivel()) {
                System.out.println("- " + livro.getTitulo());
            }
        }

        // Solicitando o nome do usuário que deseja emprestar um livro
        System.out.print("\nDigite o nome do aluno que quer emprestar o livro: ");
        String nomeUsuario = scanner.nextLine();

        // Procurando o usuário correspondente
        Usuario usuarioEmprestimo = null;
        for (Usuario usuario : usuarios) {
            if (usuario.getNome().equalsIgnoreCase(nomeUsuario)) {
                usuarioEmprestimo = usuario;
                break;
            }
        }

        if (usuarioEmprestimo == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        // Solicitando o nome do livro que deseja emprestar
        System.out.print("Digite o nome do livro que deseja emprestar: ");
        String nomeLivro = scanner.nextLine();

        // Procurando o livro correspondente
        Livro livroEmprestimo = null;
        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(nomeLivro) && livro.isDisponivel()) {
                livroEmprestimo = livro;
                break;
            }
        }

        if (livroEmprestimo == null) {
            System.out.println("Livro não encontrado ou não disponível.");
            return;
        }

        // Registrando o empréstimo com a data de devolução fixa (1 semana)
        System.out.println("\nRegistrando empréstimo:");
        String mensagemEmprestimo = controle.registraEmprestimo(usuarioEmprestimo, livroEmprestimo);
        System.out.println(mensagemEmprestimo);
    }

    // Método para devolver livro
    public static void devolverLivro(Scanner scanner, List<Usuario> usuarios, List<Livro> livros, ControleEmprestimo controle) {
        if (usuarios.isEmpty() || livros.isEmpty()) {
            System.out.println("Não há usuários ou livros registrados.");
            return;
        }

        // Solicitando o nome do usuário que deseja devolver o livro
        System.out.print("\nDigite o nome da pessoa que deseja devolver o livro: ");
        String nomeUsuario = scanner.nextLine();

        // Procurando o usuário correspondente
        Usuario usuarioDevolucao = null;
        for (Usuario usuario : usuarios) {
            if (usuario.getNome().equalsIgnoreCase(nomeUsuario)) {
                usuarioDevolucao = usuario;
                break;
            }
        }

        if (usuarioDevolucao == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        // Solicitando o nome do livro que deseja devolver
        System.out.print("Digite o nome do livro que deseja devolver: ");
        String nomeLivro = scanner.nextLine();

        // Procurando o livro correspondente
        Livro livroDevolucao = null;
        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(nomeLivro) && !livro.isDisponivel()) {
                livroDevolucao = livro;
                break;
            }
        }

        if (livroDevolucao == null) {
            System.out.println("Livro não encontrado ou já está disponível.");
            return;
        }

        // Registrando a devolução
        System.out.println("\nRegistrando devolução:");
        String mensagemDevolucao = controle.registraDevolucao(usuarioDevolucao, livroDevolucao);
        System.out.println(mensagemDevolucao);
    }
}
