import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Util {

    private static final DateTimeFormatter FORMATADOR = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static LocalDate lerDataValida(Scanner input) {
        LocalDate hoje = LocalDate.now();
        LocalDate limiteFuturo = hoje.plusYears(50);

        while (true) {
            System.out.print("Digite a nova data (dd-mm-yyyy): ");
            String entrada = input.nextLine().trim();

            try {
                LocalDate data = LocalDate.parse(entrada, FORMATADOR);

                if (data.isBefore(hoje)) {
                    System.out.println("::   A data não pode estar no passado.   ::");
                } else if (data.isAfter(limiteFuturo)) {
                    System.out.println("::   A data não pode ultrapassar 50 anos no futuro.   ::");
                } else {
                    return data;
                }

            } catch (DateTimeParseException e) {
                System.out.println("::   Formato inválido! Use o formato dd-mm-yyyy.   ::");
            }
        }
    }
}

