const cardPreview = document.querySelector(".card");
const tituloInput = document.getElementById("tituloTask");
const descInput = document.getElementById("descTask");
const statusSelect = document.getElementById("selectStatus");
const dataInput = document.getElementById("dataTask");
const radios = document.querySelectorAll('input[name="radioPr"]');

function atualizarCard() {
    let titulo = tituloInput.value || "Título da Tarefa";
    let descricao = descInput.value || "Descrição da tarefa";
    let status = statusSelect.value;
    let data = dataInput.value ? new Date(dataInput.value).toLocaleDateString("pt-BR") : "dd/mm/yyyy";

    let cor = "var(--border-color)"; // padrão
    const prioridadeSelecionada = document.querySelector('input[name="radioPr"]:checked');
    if (prioridadeSelecionada) {
        switch (prioridadeSelecionada.value) {
            case "baixo": cor = "#3b82f6"; break;
            case "media": cor = "#facc15"; break;
            case "urgente": cor = "#ef4444"; break;
        }
    }

    cardPreview.innerHTML = `
        <h3 class='txtCard' id='tituloCard'>${titulo}</h3>
        <p class='txtCard' id='descricaoCard'>${descricao}</p>
        <p class='txtCard' id='statusCard'><strong>Status:</strong> ${status.charAt(0).toUpperCase() + status.slice(1)}</p>
        <p class='txtCard' id='dataCard'><strong>Data:</strong> ${data}</p>
    `;
    cardPreview.style.borderLeft = `20px solid ${cor}`;
    cardPreview.style.borderRight = `20px solid ${cor}`;
}

[tituloInput, descInput, statusSelect, dataInput].forEach(el => {
    el.addEventListener("input", atualizarCard);
});

radios.forEach(radio => {
    radio.addEventListener("change", atualizarCard);
});

// Inicializa o card vazio
atualizarCard();
