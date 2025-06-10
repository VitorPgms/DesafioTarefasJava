import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

public class Main {
    private static List<Tarefas> tarefas = new ArrayList<>();
    private static int proximoCodigo = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean executando = true;

        while (executando) {
            System.out.println("\n==== MENU ====");
            System.out.println("1. Incluir nova tarefa");
            System.out.println("2. Listar todas as tarefas");
            System.out.println("3. Atualizar uma tarefa existente");
            System.out.println("4. Deletar uma tarefa");
            System.out.println("5. Sair e salvar");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    incluirTarefa(scanner);
                    break;
                case "2":
                    listarTarefas();
                    break;
                case "3":
                    atualizarTarefa(scanner);
                    break;
                case "4":
                    deletarTarefa(scanner);
                    break;
                case "5":
                    salvarTarefasEmArquivo();
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }

        scanner.close();
    }

    private static void incluirTarefa(Scanner scanner) {
        System.out.print("Digite o nome da tarefa: ");
        String nome = scanner.nextLine();

        System.out.print("Digite a situação da tarefa (Pendente/Concluida): ");
        String situacao = scanner.nextLine();

        Tarefas nova = new Tarefas(proximoCodigo++, nome, situacao);
        tarefas.add(nova);
        System.out.println("Tarefa adicionada com sucesso.");
    }

    private static void listarTarefas() {
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada.");
        } else {
            for (Tarefas t : tarefas) {
                System.out.println(t);
            }
        }
    }

    private static void atualizarTarefa(Scanner scanner) {
        System.out.print("Digite o código da tarefa que deseja atualizar: ");
        int codigo = Integer.parseInt(scanner.nextLine());

        for (Tarefas t : tarefas) {
            if (t.getCodigo() == codigo) {
                System.out.print("Novo nome da tarefa: ");
                String novoNome = scanner.nextLine();

                System.out.print("Nova situação (Pendente/Concluida): ");
                String novaSituacao = scanner.nextLine();

                t.setNome(novoNome);
                t.setSituacao(novaSituacao);
                System.out.println("Tarefa atualizada.");
                return;
            }
        }

        System.out.println("Tarefa com esse código não encontrada.");
    }

    private static void deletarTarefa(Scanner scanner) {
        System.out.print("Digite o código da tarefa que deseja deletar: ");
        int codigo = Integer.parseInt(scanner.nextLine());

        Iterator<Tarefas> iterator = tarefas.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getCodigo() == codigo) {
                iterator.remove();
                System.out.println("Tarefa deletada.");
                return;
            }
        }

        System.out.println("Tarefa com esse código não encontrada.");
    }

    private static void salvarTarefasEmArquivo() {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String nomeArquivo = "tarefas-" + timestamp + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (Tarefas t : tarefas) {
                writer.write(t.toString());
                writer.newLine();
            }
            System.out.println("Tarefas salvas em: " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }
}
