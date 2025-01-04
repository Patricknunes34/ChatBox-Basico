import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ChatBox extends JFrame {
    private JPanel mainPanel;
    private JTextField inputField;
    private JButton sendButton;
    private JPanel messagePanel;
    private JLabel recognitionLabel;
    private static final String BOT_NAME = "Bot";

    public ChatBox() {
        // Configurações principais da janela
        setTitle("ChatBox Moderno");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Cabeçalho
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.setBackground(new Color(0, 0, 0));
        headerPanel.setPreferredSize(new Dimension(400, 50));

        // Redimensionar e adicionar imagem ao cabeçalho
        ImageIcon botImage = resizeImage("Imagem de boot.jpg", 40, 40);
        JLabel imageLabel = new JLabel(botImage);
        headerPanel.add(imageLabel);

        JLabel headerLabel = new JLabel("Bot: Ativo");
        headerLabel.setFont(new Font("Roboto", Font.BOLD, 16));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Painel de mensagens
        messagePanel = new JPanel();
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
        messagePanel.setBackground(Color.WHITE);
        JScrollPane chatScrollPane = new JScrollPane(messagePanel);
        chatScrollPane.setBorder(BorderFactory.createEmptyBorder());
        mainPanel.add(chatScrollPane, BorderLayout.CENTER);

        // Painel de reconhecimento
        recognitionLabel = new JLabel("Digite algo para começar...");
        recognitionLabel.setFont(new Font("Roboto", Font.ITALIC, 12));
        recognitionLabel.setForeground(Color.GRAY);
        recognitionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        recognitionLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        mainPanel.add(recognitionLabel, BorderLayout.SOUTH);

        // Painel inferior
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBackground(Color.WHITE);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputField = new JTextField();
        inputField.setFont(new Font("Roboto", Font.PLAIN, 14));
        inputField.setBackground(new Color(240, 240, 240));
        inputField.setForeground(Color.BLACK);
        inputField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        inputPanel.add(inputField, BorderLayout.CENTER);

        sendButton = new JButton("Enviar");
        sendButton.setFont(new Font("Roboto", Font.BOLD, 14));
        sendButton.setBackground(Color.WHITE);
        sendButton.setForeground(Color.BLACK);
        sendButton.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
        sendButton.setFocusPainted(false);
        inputPanel.add(sendButton, BorderLayout.EAST);

        mainPanel.add(inputPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // Ações do botão "Enviar"
        sendButton.addActionListener(e -> sendMessage());
        inputField.addActionListener(e -> sendMessage());
    }

    private ImageIcon resizeImage(String imagePath, int width, int height) {

        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image originalImage = originalIcon.getImage();


        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedImage.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.drawImage(originalImage, 0, 0, width, height, null);
        g2d.dispose();

        return new ImageIcon(resizedImage);
    }

    private void sendMessage() {
        String userMessage = inputField.getText().trim();
        if (!userMessage.isEmpty()) {
            recognitionLabel.setText("Você disse: " + userMessage);

            JPanel userMessagePanel = createMessagePanel(userMessage, true);
            messagePanel.add(userMessagePanel);

            String botResponse = getBotResponse(userMessage.toLowerCase());
            JPanel botMessagePanel = createMessagePanel(botResponse, false);
            messagePanel.add(botMessagePanel);

            messagePanel.revalidate();
            messagePanel.repaint();
            inputField.setText("");
        }
    }

    private JPanel createMessagePanel(String message, boolean isUser) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(isUser ? FlowLayout.LEFT : FlowLayout.RIGHT));
        panel.setBackground(Color.WHITE);

        JLabel messageLabel = new JLabel("<html><p style='width: 200px;'>" + message + "</p></html>");
        messageLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        messageLabel.setOpaque(true);
        messageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        messageLabel.setBackground(isUser ? new Color(200, 255, 200) : new Color(220, 220, 220));
        messageLabel.setForeground(Color.BLACK);
        messageLabel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));

        panel.add(messageLabel);
        return panel;
    }


    private String getBotResponse(String message) {
        if (message.contains("oi") || message.contains("olá") || message.contains("bom dia")) {
            return "Olá! Como posso te ajudar?";
        } else if (message.contains("boa tarde") || message.contains("boa noite")) {
            return "Boa tarde/noite! Como posso ajudar?";
        } else if (message.contains("ajuda")) {
            return "Claro! Em que você precisa de ajuda?";
        } else if (message.contains("horário")) {
            return "Estamos disponíveis das 8h às 18h de segunda a sexta.";
        } else if (message.contains("contato")) {
            return "Você pode nos contatar pelo e-mail suporte@exemplo.com.";
        } else if (message.contains("endereço")) {
            return "Nosso endereço é Rua Exemplo, 123, Centro, Cidade.";
        } else if (message.contains("preço")) {
            return "Nossos preços variam de acordo com o serviço. Pode detalhar o que precisa?";
        } else if (message.contains("pagamento")) {
            return "Aceitamos cartões, boletos e transferências bancárias.";
        } else if (message.contains("reclamação")) {
            return "Sinto muito! Por favor, detalhe sua reclamação para que possamos ajudar.";
        } else if (message.contains("elogio")) {
            return "Obrigado! Ficamos felizes em saber que está satisfeito!";
        } else if (message.contains("suporte")) {
            return "Nosso suporte está disponível pelo chat ou pelo telefone 0800-123-456.";
        } else if (message.contains("login")) {
            return "Você pode fazer login no canto superior direito do site.";
        } else if (message.contains("cadastro")) {
            return "Clique em 'Registrar-se' para criar uma conta.";
        } else if (message.contains("senha")) {
            return "Você pode redefinir sua senha clicando em 'Esqueci minha senha'.";
        } else if (message.contains("cancelar")) {
            return "Você quer cancelar qual serviço? Podemos ajudar.";
        } else if (message.contains("assinatura")) {
            return "Nosso plano mensal custa R$49,90. Quer saber mais?";
        } else if (message.contains("planos")) {
            return "Oferecemos planos mensais e anuais. Qual deles interessa?";
        } else if (message.contains("projeto")) {
            return "Que tipo de projeto você está planejando? Podemos ajudar!";
        } else if (message.contains("dúvida")) {
            return "Qual a sua dúvida? Estou aqui para ajudar.";
        } else if (message.contains("tchau") || message.contains("até logo")) {
            return "Tchau! Espero falar com você em breve.";
        }
        return "Desculpe, não entendi sua mensagem.";
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChatBox chatBox = new ChatBox();
            chatBox.setVisible(true);
        });
    }
}
