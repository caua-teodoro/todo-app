const API_URL = 'http://localhost:8080';

const registerForm = document.getElementById('register-form');

registerForm.addEventListener('submit', async (event) => {
  event.preventDefault(); // Impede a pagina de recarregar sozinha

  const payload = {
    name: document.getElementById('fullname').value.trim(),
    email: document.getElementById('email').value.trim(),
    password: document.getElementById('new-password').value
  };

  try {
    const response = await fetch(`${API_URL}/users`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(payload)
    });

    const data = await response.json();

    if (!response.ok) {
      // Se veio erro do @Valid (400), junta as mensagens; se veio BusinessException (409), mostra data.error
      const message = data.errors
        ? Object.values(data.errors).join('\n')
        : data.error;
      alert(message || 'Erro ao cadastrar usuário.');
      return;
    }

    alert(`Usuário ${data.name} cadastrado com sucesso!`);
    registerForm.reset();
    switchCard(); // Volta automaticamente para o card de Login
  } catch (error) {
    alert('Não foi possível conectar ao servidor. Verifique se o backend está rodando.');
    console.error(error);
  }
});