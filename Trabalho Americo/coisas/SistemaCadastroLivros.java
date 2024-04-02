package coisas;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SistemaCadastroLivros {
    private static SistemaCadastroLivros instance;
    private static ArrayList<Livro> livros = new ArrayList<>();
    private static ArrayList<Usuario> usuarios = new ArrayList<>();
    private static ArrayList<Funcionario> funcionarios = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static Usuario usuarioLogado = null;
    private SistemaCadastroLivros() {
    }
    public static SistemaCadastroLivros getInstance() {
        if (instance == null) {
            instance = new SistemaCadastroLivros();
        }
        return instance;
    }
    public void cadastrarUsuario() {
        System.out.println("\n=== Cadastro de Usuário ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        while (!validarEmail(email)) {
            System.out.print("Email inválido! Digite um email válido: ");
            email = scanner.nextLine();
        }
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        System.out.print("Confirmar Senha: ");
        String confirmarSenha = scanner.nextLine();
        while (!senha.equals(confirmarSenha)) {
            System.out.println("As senhas não coincidem! Digite novamente.");
            System.out.print("Senha: ");
            senha = scanner.nextLine();
            System.out.print("Confirmar Senha: ");
            confirmarSenha = scanner.nextLine();
        }
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        while (!validarTelefone(telefone)) {
            System.out.print("Telefone inválido! Digite um telefone válido: ");
            telefone = scanner.nextLine();
        }
        Usuario usuario = new Usuario(nome, email, senha, telefone);
        usuarios.add(usuario);
        System.out.println("Usuário cadastrado com sucesso!\n");
    }
    public void cadastrarFuncionario() {
        System.out.println("\n=== Cadastro de Funcionário ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        while (!validarEmail(email)) {
            System.out.print("Email inválido! Digite um email válido: ");
            email = scanner.nextLine();
        }
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        System.out.print("Confirmar Senha: ");
        String confirmarSenha = scanner.nextLine();
        while (!senha.equals(confirmarSenha)) {
            System.out.println("As senhas não coincidem! Digite novamente.");
            System.out.print("Senha: ");
            senha = scanner.nextLine();
            System.out.print("Confirmar Senha: ");
            confirmarSenha = scanner.nextLine();
        }
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        while (!validarTelefone(telefone)) {
            System.out.print("Telefone inválido! Digite um telefone válido: ");
            telefone = scanner.nextLine();
        }
        Funcionario funcionario = new Funcionario(nome, email, senha, telefone);
        funcionarios.add(funcionario);
        usuarios.add(funcionario);
        System.out.println("Funcionário cadastrado com sucesso!\n");
    }
    public void cadastrarLivro() {
        if (!(usuarioLogado instanceof Funcionario)) {
            System.out.println("Apenas funcionários podem cadastrar livros.\n");
            return;
        }
        System.out.println("\n=== Cadastro de Livro ===");
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Ano de Publicação: ");
        int anoPublicacao = scanner.nextInt();
        System.out.print("Preço: ");
        double preco = scanner.nextDouble();
        scanner.nextLine(); 

        Livro livro = new Livro(titulo, autor, anoPublicacao, preco);
        livros.add(livro);
        System.out.println("Livro cadastrado com sucesso!\n");
    }
    private static boolean validarEmail(String email) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    private static boolean validarTelefone(String telefone) {
        return telefone.matches("\\d{9}");
    }
    public static void main(String[] args) {
        SistemaCadastroLivros sistema = SistemaCadastroLivros.getInstance();
        boolean continuar = true;
        while (continuar) {
            if (usuarioLogado == null) {
                System.out.println("=== Menu ===");
                System.out.println("1. Cadastrar novo usuário");
                System.out.println("2. Fazer login");
                System.out.println("3. Cadastrar novo funcionário");
                System.out.println("4. Entrar como funcionário");
                System.out.println("5. Sair");
                System.out.print("Escolha uma opção: ");
                int escolha = scanner.nextInt();
                scanner.nextLine(); 

                switch (escolha) {
                    case 1:
                        sistema.cadastrarUsuario();
                        break;
                    case 2:
                        sistema.fazerLogin();
                        break;
                    case 3:
                        sistema.cadastrarFuncionario();
                        break;
                    case 4:
                        sistema.entrarComoFuncionario();
                        break;
                    case 5:
                        continuar = false;
                        System.out.println("Saindo do sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } else {
                if (usuarioLogado instanceof Funcionario) {
                    System.out.println("\n=== Menu Funcionário ===");
                    System.out.println("1. Cadastrar novo livro");
                    System.out.println("2. Listar todos os livros");
                    System.out.println("3. Listar todos os Usuarios");
                    System.out.println("4. Logout");
                    System.out.print("Escolha uma opção: ");
                    int escolha = scanner.nextInt();
                    scanner.nextLine(); 
                    switch (escolha) {
                        case 1:
                            sistema.cadastrarLivro();
                            break;
                        case 2:
                            sistema.listarLivros();
                            break;
                        case 3:
                            sistema.listarUsuario();
                            break;    
                        case 4:
                            usuarioLogado = null;
                            System.out.println("Logout realizado com sucesso!");
                            break;
                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                    }
                } else {
                    System.out.println("\n=== Menu Usuário ===");
                    System.out.println("1. Listar todos os livros");
                    System.out.println("2. Comprar livro");
                    System.out.println("3. Logout");
                    System.out.print("Escolha uma opção: ");
                    int escolha = scanner.nextInt();
                    scanner.nextLine(); 

                    switch (escolha) {
                        case 1:
                            sistema.listarLivros();
                            break;
                        case 2:
                            sistema.comprarLivro();
                            break;
                        case 3:
                            usuarioLogado = null;
                            System.out.println("Logout realizado com sucesso!");
                            break;
                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                    }
                }
            }
        }
    }
    private void fazerLogin() {
        System.out.println("\n=== Login ===");
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                usuarioLogado = usuario;
                System.out.println("Login realizado com sucesso!\n");
                return;
            }
        }
        System.out.println("Email ou senha incorretos. Tente novamente.\n");
    }
    private void entrarComoFuncionario() {
        System.out.println("\n=== Entrar como Funcionário ===");
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getEmail().equals(email) && funcionario.getSenha().equals(senha)) {
                usuarioLogado = funcionario;
                System.out.println("Login como funcionário realizado com sucesso!\n");
                return;
            }
        }
        System.out.println("Email ou senha de funcionário incorretos. Tente novamente.\n");
    }
    private void listarLivros() {
        System.out.println("\n=== Lista de Livros ===");
        for (Livro livro : livros) {
            System.out.println(livro);
        }
        System.out.println();
    }
    private void listarUsuario() {
        System.out.println("\n=== Lista de Usuarios ===");
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }
        System.out.println();
    }
    private void comprarLivro() {
        if (usuarioLogado == null) {
            System.out.println("Você precisa fazer login primeiro para comprar um livro.\n");
            return;
        }
        System.out.println("\n=== Comprar Livro ===");
        if (livros.isEmpty()) {
            System.out.println("Desculpe, não há livros disponíveis para venda.");
            return;
        }
        listarLivros();
        System.out.print("Digite o índice do livro que deseja comprar: ");
        int indice = scanner.nextInt();
        scanner.nextLine(); 
        if (indice >= 0 && indice < livros.size()) {
            Livro livroComprado = livros.remove(indice);
            System.out.println("Você comprou o livro: " + livroComprado.getTitulo());
            System.out.println("Compra realizada com sucesso!");
        } else {
            System.out.println("Índice inválido. Tente novamente.");
 }
}
}