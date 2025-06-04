function atualizarIconesTema() {
    const isDark = document.body.classList.contains("dark");
    const canetas = document.querySelectorAll(".icone_editar_excluir");

    canetas.forEach(img => {
        img.src = isDark
            ? "../images/editar_icon-branco.png"
            : "../images/editar_icon.png";
    });
}