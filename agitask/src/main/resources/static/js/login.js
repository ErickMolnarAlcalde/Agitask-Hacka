document.addEventListener('DOMContentLoaded', () => {
    // Agora selecionamos o formulário pelo ID 'loginForm'
    const form = document.getElementById('loginForm');

    // Agora selecionamos os inputs pelos IDs 'email' e 'senha'
    const emailInput = document.getElementById('email');
    const passwordInput = document.getElementById('senha');

    // **Ajuste o endpoint se necessário**
    const loginUrl = 'http://localhost:8080/usuarios/login';

    form.addEventListener('submit', async (e) => {
        e.preventDefault();

        const email = emailInput.value;
        const senha = passwordInput.value;

        // Estrutura de dados para enviar ao backend
        const userData = {
            email: email,
            senha: senha
        };

        try {
            const response = await fetch(loginUrl, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(userData)
            });

            if (!response.ok) {
                alert('Credenciais inválidas. Verifique seu e-mail e senha.');
                return;
            }

            const user = await response.json();
            const cargo = user.cargo;

            // --- Lógica de Redirecionamento (4 Perfis, 4 Telas) ---
            switch (cargo) {
                case 'ADMIN':
                    window.location.href = '/pages/Projetos/Cadastro/admin.html';
                    break;
                case 'GESTOR':
                    window.location.href = '/pages/Projetos/Cadastro/gestor.html';
                    break;
                case 'SUPERVISOR':
                    window.location.href = '/pages/Projetos/supervisor.html';
                    break;
                case 'COLABORADOR':
                    window.location.href = '/pages/Projetos/colaborador.html';
                    break;
                default:
                    alert('Perfil não reconhecido.');
                    window.location.href = '/index.html';
            }

        } catch (error) {
            console.error('Erro de conexão ou processamento:', error);
            alert('Não foi possível conectar ao servidor.');
        }
    });
});