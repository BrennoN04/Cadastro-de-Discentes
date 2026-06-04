package br.com.webacademy;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner console = new Scanner(System.in);

        int opcao;
        do {
            exibirMenu();
            opcao = Integer.parseInt(console.nextLine());
            switch (opcao) {
                case 0 -> cadastrarDiscente();
                case 1 -> buscarTodosDiscentes();
                case 2 -> buscarDiscentePorId();
                case 3 -> atualizarDiscente();
                case 4 -> excluirDiscente();
                case 5 -> System.exit(0);
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    public static void exibirMenu() {
        System.out.println("\n=========== MENU ===========");
        System.out.println("0 - Cadastrar Discente");
        System.out.println("1 - Buscar Todos os Discentes");
        System.out.println("2 - Buscar Discente por ID");
        System.out.println("3 - Atualizar Discente");
        System.out.println("4 - Excluir Discente");
        System.out.println("5 - Sair");
        System.out.print("Escolha uma opção: ");
        
    }

    public static void cadastrarDiscente() throws Exception {

        Scanner console = new Scanner(System.in);

        System.out.println("\n--- Criar Novo Discente ---");
        System.out.print("Digite o nome do discente: ");
        String nome = console.nextLine();
        System.out.print("Digite a matricula do discente: ");
        Long matricula = Long.parseLong(console.nextLine());
        System.out.print("Digite o curso do discente: ");
        String curso = console.nextLine();
        System.out.print("Digite o período atual do discente: ");
        int periodo_atual = Integer.parseInt(console.nextLine());

        Discente discente = new Discente(nome, matricula, curso, periodo_atual);
        DiscenteDAO.salvar(discente);
        try {
            DiscenteDAO.salvar(discente);
            System.out.println("Discente salvo com sucesso!");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void buscarTodosDiscentes() {
        System.out.println("\n--- Lista de Discentes ---");
        DiscenteDAO discenteDAO = new DiscenteDAO();
        try {
            List<Discente> discentes = discenteDAO.buscarTodos();
            if (discentes != null) {
                System.out.println("Lista de discentes:");
                for (Discente discente : discentes) {
                    System.out.println("Nome:" + discente.nome());
                }
            } else {
                System.out.println("Nenhum discente encontrado.");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

        public static void buscarDiscentePorId() {

            Scanner console = new Scanner(System.in);

            System.out.println("\n--- Buscar Discente por ID ---");
            System.out.print("Digite o ID do discente: ");
            long id = Long.parseLong(console.nextLine());
            DiscenteDAO discenteDAO = new DiscenteDAO();
            try {
                Discente discente = discenteDAO.buscarPorId(id);
                if (discente != null) {
                    System.out.println("Discente encontrado:");
                    System.out.println(discente.nome());
                } else {
                    System.out.println("Discente não encontrado.");
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    
    public static void atualizarDiscente() {

        Scanner console = new Scanner(System.in);

        System.out.println("\n--- Atualizar Discente ---");
        System.out.print("Digite o ID do discente a ser atualizado: ");
        long id = Long.parseLong(console.nextLine());
        DiscenteDAO discenteDAO = new DiscenteDAO();
        try {
            Discente discente = discenteDAO.buscarPorId(id);
            if (discente != null) {
                System.out.print("Novo nome (atual: " + discente.nome() + "): ");
                String nome = console.nextLine();
                System.out.println("Nova matricula (atual: " + discente.matricula() + "): ");
                Long matricula = Long.parseLong(console.nextLine());
                System.out.println("Novo curso (atual: " + discente.curso() + "): ");
                String curso = console.nextLine();
                System.out.println("Novo período atual (atual: " + discente.periodo_atual() + "): ");
                int periodo_atual = Integer.parseInt(console.nextLine());
                Discente discenteAtualizado = new Discente(id, nome, matricula, curso, periodo_atual);
                try {
                    discenteDAO.atualizar(discenteAtualizado);
                    System.out.println("Discente atualizado com sucesso!");
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            } else {
                System.out.println("Discente não encontrado.");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void excluirDiscente() {

        Scanner console = new Scanner(System.in);

        System.out.println("\n--- Excluir Discente ---");
        System.out.print("Digite o ID do discente a ser excluído: ");
        long id = Long.parseLong(console.nextLine());
        DiscenteDAO discenteDAO = new DiscenteDAO();
        try {
            Discente discenteExistente = discenteDAO.buscarPorId(id);
            if (discenteExistente != null) {
                discenteDAO.excluir(discenteExistente.id());
                System.out.println("Discente excluído com sucesso!");
            } else {
                System.out.println("Discente não encontrado.");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}   