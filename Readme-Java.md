A estrutura Principal é a classe ChatBox estende JFrame, sendo a janela principal do aplicativo, incluindo:

- JPanel: Organiza os elementos da interface.
- JTextField inputField: Onde o usuário digita suas mensagens.
- JButton sendButton: Botão para enviar as mensagens.
- JPanel messagePanel: Onde as mensagens do usuário e do bot são exibidas.
- JLabel recognitionLabel: Exibe o que o usuário digitou ou uma mensagem inicial.

•	Funcionalidades e Métodos Chave

- ChatBox() (Construtor):

1.	Configura a janela principal (JFrame), incluindo título, tamanho, operação de fechamento e posicionamento.

2.	Cria e organiza todos os componentes da interface usando BorderLayout e FlowLayout e BoxLayout.

3.	Adiciona um cabeçalho com uma imagem de "bot" (redimensionada) e um rótulo "Bot: Ativo".

4.	Cria uma área de rolagem (JScrollPane) para as mensagens (messagePanel).

5.	Define o estilo visual dos componentes (cores, fontes, bordas).

6.	Associa as ações de envio de mensagem ao botão "Enviar" e à tecla Enter no campo de entrada (sendMessage()).

•	resizeImage(String imagePath, int width, int height):

1.	Carrega uma imagem de um caminho especificado.

2.	Redimensiona a imagem para as dimensões (width, height) fornecidas, mantendo a qualidade.

3.	Retorna um ImageIcon com a imagem redimensionada.

•	sendMessage():

1.	É acionado quando o usuário envia uma mensagem.

2.	Pega o texto do inputField.

3.	Cria um painel para a mensagem do usuário (createMessagePanel) e o adiciona ao messagePanel.

4.	Chama getBotResponse() para obter a resposta do bot.

5.	Cria um painel para a mensagem do bot e o adiciona ao messagePanel.
Atualiza a exibição das mensagens (revalidate, repaint) e limpa o inputField.

•	createMessagePanel(String message, boolean isUser):

1.	Cria um JPanel que contém uma JLabel com a mensagem.

2.	Formata a mensagem em HTML para quebrar linhas e se ajustar ao tamanho.

3.	Define a cor de fundo e o alinhamento da mensagem com base se ela é do usuário (verde claro) ou do bot (cinza claro).

•	getBotResponse(String message):

É a "inteligência" do bot.
Recebe a mensagem do usuário (em minúsculas).
Usa uma série de condições (if-else if) para verificar palavras-chave na mensagem do usuário (ex: "oi", "ajuda", "horário", "preço").
Retorna uma resposta predefinida com base nas palavras-chave encontradas.
Se nenhuma palavra-chave for reconhecida, retorna uma mensagem padrão como "Desculpe, não entendi sua mensagem."

main(String[] args):

É o ponto de entrada da aplicação.
Cria e exibe uma nova instância da ChatBox em uma thread segura para GUIs (SwingUtilities.invokeLater).
