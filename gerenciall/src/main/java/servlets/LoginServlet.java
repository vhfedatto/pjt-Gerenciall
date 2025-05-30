import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.HashMap;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    // Simulando um banco de dados
    private static final HashMap<String, String> usuarios = new HashMap<>();

    @Override
    public void init() {
        usuarios.put("admin", "123");
        usuarios.put("victor", "senha123");
        // Adicione mais usuários conforme necessário
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usuario = request.getParameter("usuario");
        String senha = request.getParameter("senha");

        if (usuarios.containsKey(usuario) && usuarios.get(usuario).equals(senha)) {
            response.sendRedirect("dashboard.html"); // ou redireciona para JSP
        } else {
            response.sendRedirect("erro.html");
        }
    }
}
