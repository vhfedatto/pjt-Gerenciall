function alternarTema() {
    const isDark = document.body.classList.toggle("dark");
    localStorage.setItem("tema", isDark ? "escuro" : "claro");

    const toggle = document.getElementById("remember-toggle");
    if (toggle) toggle.checked = isDark;
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

    // Segurança extra: se o DOM e o localStorage estiverem fora de sincronia
    if (toggle) {
        const domIsDark = document.body.classList.contains("dark");
        if (toggle.checked !== domIsDark) {
            toggle.checked = domIsDark;
        }
    }
});