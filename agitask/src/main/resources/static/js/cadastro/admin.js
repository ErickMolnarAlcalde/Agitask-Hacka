document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('cadastroUsuarioForm');
    const mensagemStatus = document.getElementById('mensagemStatus');

    // URL da API. Ajuste para o endereço correto da sua API.
    // Usando o endpoint '/admin' conforme a sua estrutura de Controller
    const API_URL = 'http://localhost:8080/usuarios/admin';

    // Simulação do email do usuário logado (Admin/Gestor) que está fazendo o cadastro.
    // ISTO DEVE SER SUBSTITUÍDO PELO EMAIL REAL DO USUÁRIO AUTENTICADO EM PRODUÇÃO.
    const EMAIL_DO_USUARIO_LOGADO = "usuario_logado@agitask.com";

    // Função auxiliar para atualizar o feedback visual
    function updateStatus(message, type) {
        mensagemStatus.textContent = message;
        mensagemStatus.className = ''; // Limpa classes
        mensagemStatus.classList.add(`status-${type}`);
    }

    form.addEventListener('submit', async (event) => {
        event.preventDefault(); // Impede o envio tradicional

        // 1. Coleta dos dados
        const dadosCadastro = {
            nome: document.getElementById('nome').value,
            email: document.getElementById('email').value,
            senha: document.getElementById('senha').value,
            cargo: document.getElementById('cargo').value,
            equipe: document.getElementById('equipe').value,
            // Seu DTO espera 'emailCargo'
            emailCargo: EMAIL_DO_USUARIO_LOGADO
        };

        // 2. Feedback de carregamento
        updateStatus('Cadastrando usuário...', 'loading');

        // 3. Requisição Fetch
        try {
            const resposta = await fetch(API_URL, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    // **ADICIONE AQUI O TOKEN JWT SE USAR AUTENTICAÇÃO:**
                    // 'Authorization': 'Bearer ' + seuToken
                },
                body: JSON.stringify(dadosCadastro)
            });

            // 4. Lida com a Resposta
            const resultado = await resposta.json();

            if (resposta.ok) {
                // Sucesso (Status 201 Created)
                updateStatus(`✅ Usuário '${resultado.nome}' cadastrado com sucesso!`, 'success');
                form.reset(); // Limpa o formulário
            } else {
                // Erro do servidor (Ex: Email já cadastrado)
                const erroMsg = resultado.message || `Erro de Servidor. Status: ${resposta.status}`;
                updateStatus(`❌ Falha no cadastro: ${erroMsg}`, 'error');
                console.error('Detalhes do Erro do Servidor:', resultado);
            }
        } catch (error) {
            // Erro de rede ou erro de código
            console.error('Erro na requisição de cadastro:', error);
            updateStatus('🚨 Erro de conexão com o servidor. Verifique se a API está rodando.', 'error');
        }
    });
});
