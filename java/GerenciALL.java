import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class GerenciALL {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Tarefa> listaTarefas = new ArrayList<>();
        
        String data;
        boolean rodando = true;
        int escolha = 0;
        int status = 0;

        while (rodando) {
            System.out.println("=================================================================");
            System.out.println("  _______                             __ _______ ___     ___     ");
            System.out.println(" |   _   .-----.----.-----.-----.----|__|   _   |   |   |   |    ");
            System.out.println(" |.  |___|  -__|   _|  -__|     |  __|  |.  1   |.  |   |.  |    ");
            System.out.println(" |.  |   |_____|__| |_____|__|__|____|__|.  _   |.  |___|.  |___ ");
            System.out.println(" |:  1   |                              |:  |   |:  1   |:  1   |");
            System.out.println(" |::.. . |                              |::.|:. |::.. . |::.. . |");
            System.out.println(" `-------'                              `--- ---`-------`-------'");
            System.out.println("                                                                  ");
            System.out.println("=================================================================");
            System.out.println("            GERENCIAMENTO DE TAREFAS  |  TERMINAL v1.0           ");
            System.out.println("=================================================================");
            
            System.out.println("\n[1] Criar Tarefas\n[2] Gerenciar Tarefas\n[3] Fazer Tarefa\n[4] Sair\n");
            System.out.println("=================================================================");
            
            while(true){
                System.out.print(">> Escolha: "); // Criar uma prevenção para erros de digitação de algo que não seja um número INT
                try {
                    escolha = input.nextInt();
                    input.nextLine(); // limpa o buffer
                    break; // se chegou aqui, a entrada foi numérica válida, pode sair do loop
                } catch (Exception e) {
                    System.out.println("::   Entrada inválida! Digite apenas números inteiros   ::");
                    input.nextLine(); // limpa o que foi digitado (evita loop infinito)
                }
            }
            
            switch (escolha) {
                case 1 -> { // Criar tarefas
                    System.out.println("\n========================= Criar Tarefas =========================");
                    System.out.print("NOME TAREFA: ");
                    String titulo = input.nextLine();

                    System.out.print("Descrição: ");
                    String descricao = input.nextLine();

                    while (true) {
                        System.out.print("Data: [dd-mm-yyyy] ");
                        data = input.nextLine();

                        try {
                            LocalDate dataDigitada = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

                            if (dataDigitada.isBefore(LocalDate.now())) {
                                System.out.println("::     A data não pode estar no passado. Digite novamente     ::");
                            } else {
                                break;
                            }

                        } catch (DateTimeParseException e) {
                            System.out.println("::   Data inválida! Use o formato correto e uma data real (ex: 30-07-2025)   ::");
                        }
                    }

                    System.out.println("Status: [1] A fazer [2] Fazendo [3] Feito");
                    System.out.print(">> Escolha: ");
                    
                    while(true){
                        try {
                            status = input.nextInt();
                            while(status > 3 || status < 1){
                                System.out.println("::   Opção inválida   ::");
                                System.out.print(">> Escolha: ");
                                status = input.nextInt();
                            }
                            input.nextLine(); // limpa o buffer
                            break; //funcionou!
                        } catch (Exception e) {
                            System.out.println("::   Entrada inválida! Digite apenas números inteiros   ::");
                            System.out.print(">> Escolha: ");
                            input.nextLine(); // limpa o que foi digitado (evita loop infinito)
                        }
                    }
                    
                    Tarefa nova = new Tarefa(titulo, descricao, data, status);
                    listaTarefas.add(nova);

                    System.out.println("\nTarefa criada com sucesso!");
                }

                case 2 -> { // Gerenciar tarefas --> Filtrar, Pesquisar, Editar e Excluir
                    System.out.println("\n=================================================================");
                    System.out.println("                    GERENCIAMENTO DE TAREFAS                     ");
                    System.out.println("=================================================================");

                    if (listaTarefas.isEmpty()) {
                        System.out.println("\nNenhuma tarefa registrada.\n");
                        System.out.println("=================================================================");
                        System.out.println("Pressione Enter para voltar...");
                        input.nextLine();
                    
                    }else{
                        // Exibir lista de tarefas
                        for (int i = 0; i < listaTarefas.size(); i++) {
                            System.out.println("\n[" + (i + 1) + "]\n" + listaTarefas.get(i));
                        }

                        System.out.println("\n=================================================================");
                        System.out.println("|                       SELECIONE UMA AÇÃO                      |");
                        System.out.println("=================================================================\n");
                        System.out.println("[1] Filtrar tarefas");
                        System.out.println("[2] Editar tarefa");
                        System.out.println("[3] Excluir tarefa");
                        System.out.println("[4] Voltar ao menu");
                        System.out.println("\n=================================================================");
                        System.out.print(">> Escolha: ");
                        String escolha1 = input.nextLine();

                        switch (escolha1) {
                            case "1" -> {
                                System.out.println("\n=================================================================");
                                System.out.println("|                      FILTROS DISPONÍVEIS                      |");
                                System.out.println("=================================================================\n");
                                System.out.println("[1] Por data de validade");
                                System.out.println("[2] Por status");
                                System.out.println("[3] Por ordem alfabética");
                                System.out.println("\n=================================================================");
                                System.out.print(">> Escolha o filtro: ");
                                String filtro = input.nextLine(); //Já tem tratamento de erro

                                while(!filtro.equals("1") && !filtro.equals("2") && !filtro.equals("3")){
                                    System.out.println("::   Opção inválida   ::");
                                    System.out.print(">> Escolha: ");
                                    filtro = input.nextLine();
                                }
    
                                switch (filtro) {
                                    case "1" -> { //pela data
                                        listaTarefas.sort(Comparator.comparing(Tarefa::getDataValidade));
                                        System.out.println("\n##  Tarefas ordenadas por data de validade.  ##");
                                    }
                                    case "2" -> { //status
                                        listaTarefas.sort(Comparator.comparing(Tarefa::getStatus));
                                        System.out.println("\n##  Tarefas ordenadas por status.  ##");
                                    }
                                    case "3" -> { //ordem alfabetica
                                        listaTarefas.sort(Comparator.comparing(Tarefa::getTitulo, String.CASE_INSENSITIVE_ORDER));
                                        System.out.println("\n##  Tarefas ordenadas por ordem alfabética.  ##");
                                    }
                                    default -> System.out.println("::   Filtro inválido.   ::");
                                }
                                System.out.println("Pressione Enter para continuar...");
                                input.nextLine();
                            }
                                
                            case "2" -> { //editar tarefas
                                if (listaTarefas.isEmpty()) {
                                    System.out.println("\n::   Nenhuma tarefa registrada para editar.   ::");
                                    System.out.println("Pressione Enter para voltar...");
                                    input.nextLine();
                                } else {
                                    System.out.println("\n=================================================================");
                                    System.out.println("|                      TAREFAS REGISTRADAS                      |");
                                    System.out.println("=================================================================\n");
                                    for (int i = 0; i < listaTarefas.size(); i++) {
                                        System.out.println("[" + (i + 1) + "] " + listaTarefas.get(i).getTitulo());
                                    }

                                    Tarefa tarefaSelecionada = null;
                                    while (tarefaSelecionada == null) {
                                        System.out.print("\nDigite o número da tarefa que deseja editar (ou 'sair'): ");
                                        String entrada = input.nextLine().trim();

                                        if (entrada.equalsIgnoreCase("sair")) return;

                                        try {
                                            int indice = Integer.parseInt(entrada) - 1;
                                            if (indice >= 0 && indice < listaTarefas.size()) {
                                                tarefaSelecionada = listaTarefas.get(indice);
                                            } else {
                                                System.out.println("::    Número inválido.    ::");
                                            }
                                        } catch (NumberFormatException e) {
                                            System.out.println("::    Digite um número válido.    ::");
                                        }
                                    }

                                    boolean editando = true;
                                    while (editando) {
                                        System.out.println("-----------------------------------------------------------------");
                                        System.out.println("|                         O QUE EDITAR?                         |");
                                        System.out.println("-----------------------------------------------------------------");
                                        System.out.println("[1] Título");
                                        System.out.println("[2] Descrição");
                                        System.out.println("[3] Data de validade");
                                        System.out.println("[4] Status");
                                        System.out.println("[5] Cancelar edição");
                                        System.out.print(">> Escolha: ");
                                        String escolha2 = input.nextLine();

                                        switch (escolha2) {
                                            case "1" -> {
                                                System.out.print("Novo título: ");
                                                String novoTitulo = input.nextLine();
                                                tarefaSelecionada.setTitulo(novoTitulo);
                                                System.out.println("##   Título atualizado!   ##");
                                            }
                                            case "2" -> {
                                                System.out.print("Nova descrição: ");
                                                String novaDescricao = input.nextLine();
                                                tarefaSelecionada.setDescricao(novaDescricao);
                                                System.out.println("##   Descrição atualizada!   ##");
                                            }
                                            case "3" -> {
                                                LocalDate novaData = Util.lerDataValida(input);
                                                String dataFormatada = novaData.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                                                tarefaSelecionada.setData(dataFormatada);
                                                System.out.println("##   Data de validade atualizada!   ##");
                                            }
                                            case "4" -> {
                                                while (true) {
                                                    System.out.print("Novo status [1] A fazer, [2] Fazendo, [3] Feito: ");
                                                    String entradaStatus = input.nextLine();
                                                    if (entradaStatus.equals("1") || entradaStatus.equals("2") || entradaStatus.equals("3")) {
                                                        tarefaSelecionada.setStatus(Integer.parseInt(entradaStatus));
                                                        System.out.println("##   Status atualizado!   ##");
                                                        break;
                                                    } else {
                                                        System.out.println("::   Opção inválida.   ::");
                                                    }
                                                }
                                            }
                                            case "5" -> {
                                                System.out.println("|X    Edição cancelada.    X|");
                                                editando = false;
                                            }
                                            default -> System.out.println("::   Opção inválida.   ::");
                                        }

                                        if (editando) {
                                            System.out.print("\nDeseja continuar editando essa tarefa? (s/n): ");
                                            String continuar = input.nextLine().toLowerCase();
                                            if (!continuar.equals("s")) editando = false;
                                        }
                                    }
                                }
                            }

                            case "3" -> { //excluir tarefas
                                if (listaTarefas.isEmpty()) {
                                    System.out.println("\n::   Nenhuma tarefa registrada para excluir.   ::");
                                    System.out.println("Pressione Enter para voltar...");
                                    input.nextLine();

                                } else {
                                    System.out.println("\n=================================================================");
                                    System.out.println("|                      TAREFAS REGISTRADAS                      |");
                                    System.out.println("=================================================================\n");
                                    
                                    for (int i = 0; i < listaTarefas.size(); i++) {
                                        System.out.println("[" + (i + 1) + "] " + listaTarefas.get(i).getTitulo());
                                    }

                                    System.out.print("\nDigite o número da tarefa que deseja excluir: ");
                                    String entrada = input.nextLine();

                                    try {
                                        int indice = Integer.parseInt(entrada) - 1;

                                        if (indice < 0 || indice >= listaTarefas.size()) {
                                            System.out.println("::    Número inválido. Nenhuma tarefa será excluída.    ::");
                                        } else {
                                            Tarefa selecionada = listaTarefas.get(indice);
                                            System.out.println("\nVocê selecionou:");
                                            System.out.println(selecionada);

                                            System.out.print("\nTem certeza que deseja excluir essa tarefa? (s/n): ");
                                            String confirmacao = input.nextLine().toLowerCase();

                                            if (confirmacao.equals("s") || confirmacao.equals("sim")) {
                                                listaTarefas.remove(indice);
                                                System.out.println(">>>  Tarefa excluída com sucesso!  <<<");
                                            } else {
                                                System.out.println(">>>  Exclusão cancelada.  <<<");
                                            }
                                        }

                                    } catch (NumberFormatException e) {
                                        System.out.println("::    Entrada inválida. Por favor, digite um número.    ::");
                                    }

                                    System.out.println("Pressione Enter para continuar...");
                                    input.nextLine();
                                }
                            }


                            case "4" -> { //sair
                                System.out.println(">>>  Voltando para o menu  <<<");
                                // Voltar ao menu principal
                            }

                            default -> {
                                System.out.println("::   Opção inválida.   ::");
                                System.out.println("Pressione Enter para voltar...");
                                input.nextLine();
                            }
                        }
                    }
                } //Fechamento do case 2

                case 3 -> { // Fazer tarefa
                    System.out.println("\n=================================================================");
                    System.out.println("|                         FAZER TAREFAS                         |");
                    System.out.println("=================================================================\n");

                    if (listaTarefas.isEmpty()) {
                        System.out.println("::   Nenhuma tarefa para atualizar.   ::\n");
                    } else {
                        System.out.println("Digite o número da tarefa que deseja marcar como FEITO (ou 'sair'):\n");

                        for (int i = 0; i < listaTarefas.size(); i++) {
                            System.out.println("[" + (i + 1) + "] " + listaTarefas.get(i).getTitulo());
                        }

                        boolean feito = false;
                        while (!feito) {
                            System.out.print("\n>> Escolha: ");
                            String entrada = input.nextLine().trim();

                            if (entrada.equalsIgnoreCase("sair")) {
                                System.out.println("|X     Ação cancelada.     X|");
                                break;
                            }

                            try {
                                int index = Integer.parseInt(entrada) - 1;

                                if (index < 0 || index >= listaTarefas.size()) {
                                    System.out.println("::     Número inválido. Tente novamente.     ::");
                                } else {
                                    Tarefa tarefaSelecionada = listaTarefas.get(index);
                                    System.out.println("\nSelecionada: " + tarefaSelecionada.getTitulo());

                                    System.out.print("Tem certeza que deseja marcar como FEITO? (s/n): ");
                                    String confirmacao = input.nextLine().trim().toLowerCase();

                                    if (confirmacao.equals("s")) {
                                        tarefaSelecionada.setStatus(3); // 3 = Feito
                                        System.out.println("::     Tarefa marcada como FEITA com sucesso!     ::");
                                        feito = true;
                                    } else {
                                        System.out.println("|X     Ação cancelada.     X|");
                                        break;
                                    }
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("::     Entrada inválida. Digite um número ou 'sair'.     ::");
                            }
                        }
                    }
                    System.out.println("\nPressione Enter para continuar...");
                    input.nextLine();
                }


                case 4 -> { //Sair do programa
                    System.out.println("Saindo...");
                    rodando = false;
                }

                default -> System.out.println("Opção inválida."); // Caso não seja um número
            } // Fecha o Switch Case
        }
        input.close();
    }//main
}//import