function alternarTema() {
    const isDark = document.body.classList.toggle("dark");
    localStorage.setItem("tema", isDark ? "escuro" : "claro");

    const toggle = document.getElementById("remember-toggle");
    if (toggle) toggle.checked = isDark;

    atualizarIconesEditar(); // <- aqui
}

window.addEventListener("DOMContentLoaded", () => {
    const temaSalvo = localStorage.getItem("tema");
    const toggle = document.getElementById("remember-toggle");

    if (temaSalvo === "escuro") {
        document.body.classList.add("dark");
        if (toggle) toggle.checked = true;
    } else {
        document.body.classList.remove("dark");
        if (toggle) toggle.checked = false;
    }

    // Sincronização extra
    if (toggle) {
        const domIsDark = document.body.classList.contains("dark");
        if (toggle.checked !== domIsDark) {
            toggle.checked = domIsDark;
        }
    }

    atualizarIconesEditar(); // <- aqui também
});

function atualizarIconesEditar() {
    const isDark = document.body.classList.contains("dark");
    const icones = document.querySelectorAll(".icone_editar");

    icones.forEach(img => {
        img.src = isDark
            ? "../images/editar_icon-branco.png"
            : "../images/editar_icon.png";
    });
}