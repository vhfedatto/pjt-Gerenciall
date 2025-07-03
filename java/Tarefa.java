import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Tarefa {
    private String titulo;
    private String descricao;
    private LocalDate dataValidade;
    private String status;

    private static final DateTimeFormatter FORMATADOR = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public Tarefa(String titulo, String descricao, String dataTexto, int statusCode) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataValidade = converterData(dataTexto);
        this.status = converterStatus(statusCode);
    }

    // Converte status numérico em texto
    private String converterStatus(int statusCode) {
        return switch (statusCode) {
            case 1 -> "A fazer";
            case 2 -> "Fazendo";
            case 3 -> "Feito";
            default -> "Desconhecido";
        };
    }

    // Converte texto para LocalDate com validação
    private LocalDate converterData(String dataTexto) {
        try {
            return LocalDate.parse(dataTexto, FORMATADOR);
        } catch (DateTimeParseException e) {
            System.out.println("::   Data inválida! Use o formato dd-MM-yyyy. Exemplo: 25-12-2025   ::");
            return LocalDate.now(); // fallback seguro para evitar null
        }
    }

    // Getters
    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    // Setters
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setData(String dataTexto) {
        this.dataValidade = converterData(dataTexto);
    }

    public void setStatus(int statusCode) {
        this.status = converterStatus(statusCode);
    }

    @Override
    public String toString() {
        return "Tarefa: " + titulo +
               "\nDescrição: " + descricao +
               "\nData de validade: " + (dataValidade != null ? dataValidade.format(FORMATADOR) : "Inválida") +
               "\nStatus: " + status;
    }
}