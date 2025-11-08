document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('loginForm');
    const emailInput = document.getElementById('email');
    const passwordInput = document.getElementById('senha');

    const loginUrl = 'http://localhost:8080/usuarios/login';
    const usuarioUrl = 'http://localhost:8080/usuarios';

    form.addEventListener('submit', async (e) => {
        e.preventDefault();

        const email = emailInput.value.trim();
        const senha = passwordInput.value.trim();

        if (!email || !senha) {
            alert('Por favor, preencha e-mail e senha.');
            return;
        }

        const userData = { email, senha };

        try {
            // --- 1️⃣ Faz o login (validação de credenciais)
            const response = await fetch(loginUrl, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(userData)
            });

            if (!response.ok) {
                alert('Erro no servidor ao tentar fazer login.');
                return;
            }

            const loginValido = await response.json();

            if (!loginValido) {
                alert('Credenciais inválidas. Verifique seu e-mail e senha.');
                return;
            }

            // --- 2️⃣ Busca os dados do usuário
            const userResponse = await fetch(`${usuarioUrl}/${encodeURIComponent(email)}`);
            if (!userResponse.ok) {
                alert('Não foi possível carregar os dados do usuário.');
                return;
            }

            const user = await userResponse.json();
            const cargo = user.cargo?.toUpperCase();

            if (!cargo) {
                alert('Cargo do usuário não encontrado.');
                return;
            }

            // --- 3️⃣ Redireciona conforme o cargo
            switch (cargo) {
                case 'ADMIN':
                    window.location.href = '../Cadastro/admin.html';
                    break;
                case 'GESTOR':
                    window.location.href = '../Cadastro/gestor.html';
                    break;
                case 'SUPERVISOR':
                    window.location.href = '../Projetos/supervisor.html';
                    break;
                case 'COLABORADOR':
                    window.location.href = '../Projetos/colaborador.html';
                    break;
                default:
                    alert('Perfil não reconhecido.');
                    window.location.href = '/login.html';
            }

        } catch (error) {
            console.error('Erro de conexão ou processamento:', error);
            alert('Não foi possível conectar ao servidor.');
        }
    });
});
