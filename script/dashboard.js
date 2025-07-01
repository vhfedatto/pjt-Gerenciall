let tarefas = [
{
    titulo: "Estudar Java",
    descricao: "Estudo intensivo para backend com Spring Boot.",
    prioridade: "alta",
    status: "pendente",
    data: "2025-06-04"
},
{
    titulo: "Apresentação StepUp",
    descricao: "Preparar slides e ensaiar fala.",
    prioridade: "media",
    status: "feita",
    data: "2025-06-04"
},
{
    titulo: "Criar Tela Criar-Tarefa",
    descricao: "Programar o frontend da nova tela.",
    prioridade: "baixa",
    status: "pendente",
    data: "2025-06-06"
}
];

function criarTarefaCard(tarefa, index) {
const cores = {
    alta: "#DC2626",
    media: "#FACC15",
    baixa: "#93C5FD"
};

const statusCores = {
    pendente: "#9CA3AF",
    feita: "#10B981"
};

const linha = document.createElement("div");
linha.className = "caixa-tarefa";

const botoes = document.createElement("div");
botoes.className = "botoes-card";

const btnEditar = document.createElement("button");
btnEditar.className = "icon-button";
btnEditar.innerHTML = "<img src='../images/editar_icon.png' alt='icone de lapis' class='icone_editar_excluir icone_editar'>";

const btnExcluir = document.createElement("button");
btnExcluir.className = "icon-button";
btnExcluir.innerHTML = "<img src='../images/lixeira_icon.png' alt='icone de lixeira' class='icone_editar_excluir'>";
btnExcluir.onclick = () => {
    tarefas.splice(index, 1);
    carregarTarefas();
};


botoes.appendChild(btnEditar);
botoes.appendChild(btnExcluir);

const card = document.createElement("div");
card.className = "card";
card.style.borderLeft = `12px solid ${cores[tarefa.prioridade]}`;

const statusSpan = document.createElement("span");
statusSpan.className = "status";
statusSpan.style.backgroundColor = statusCores[tarefa.status];
statusSpan.textContent = tarefa.status.charAt(0).toUpperCase() + tarefa.status.slice(1);

const btnFeito = document.createElement("button");
btnFeito.className = tarefa.status === 'feita' ? 'btn-feito' : 'btn-normal';
btnFeito.textContent = tarefa.status === 'feita' ? 'Feito' : 'Marcar como feito';
btnFeito.onclick = () => {
    tarefa.status = 'feita';
    carregarTarefas();
};

card.innerHTML = `
    <h3>${tarefa.titulo}</h3>
    <p>${tarefa.descricao}</p>
`;

const actions = document.createElement("div");
actions.className = "card-actions";
actions.appendChild(statusSpan);
actions.appendChild(btnFeito);
card.appendChild(actions);

linha.appendChild(card);
linha.appendChild(botoes);

return { linha, data: tarefa.data };
}

function carregarTarefas() {
    const lista = document.getElementById("lista-tarefas");
    lista.innerHTML = "";

    const agrupadas = {};
    tarefas.forEach((tarefa, index) => {
        if (!agrupadas[tarefa.data]) agrupadas[tarefa.data] = [];
        agrupadas[tarefa.data].push({ tarefa, index });
    });

    Object.keys(agrupadas).sort().forEach(data => {
        const dataTitle = document.createElement("div");
        dataTitle.className = "data-header";
        const [ano, mes, dia] = data.split("-");
        const dataFormatada = new Date(`${ano}-${mes}-${dia}T00:00:00`);
        dataTitle.textContent = dataFormatada.toLocaleDateString("pt-BR", {
            weekday: "long",
            year: "numeric",
            month: "long",
            day: "numeric"
        });
        lista.appendChild(dataTitle);

        agrupadas[data].forEach(({ tarefa, index }) => {
            const { linha } = criarTarefaCard(tarefa, index);
            lista.appendChild(linha);
        });
    });

    atualizarIconesEditar(); // 🔧 Isso garante que as imagens sejam atualizadas com o tema atual
}
carregarTarefas();