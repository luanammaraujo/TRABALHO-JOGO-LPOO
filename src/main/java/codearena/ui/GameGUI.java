package codearena.ui;

import codearena.model.Player;
import codearena.model.character.Character;
import codearena.model.character.Aladdin;
import codearena.model.character.Merida;
import codearena.model.character.Rapunzel;
import codearena.model.character.Enemy;
import codearena.model.question.Question;
import codearena.model.question.QuestionBank;
import codearena.model.question.MultipleChoiceQuestion;
import codearena.model.question.TrueFalseQuestion;
import codearena.model.question.FillBlankQuestion;
import codearena.model.ability.AbilityResult;
import codearena.battle.Round;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GameGUI extends JFrame {

    private static final Color BG_COLOR = new Color(30, 28, 45);
    private static final Color PANEL_COLOR = new Color(45, 42, 65);
    private static final Color ACCENT_COLOR = new Color(255, 200, 87);
    private static final Color HP_PLAYER_COLOR = new Color(98, 196, 121);
    private static final Color HP_ENEMY_COLOR = new Color(214, 80, 80);
    private static final Color TEXT_COLOR = new Color(240, 240, 240);
    private static final Font TITLE_FONT = new Font("SansSerif", Font.BOLD, 24);
    private static final Font HEADER_FONT = new Font("SansSerif", Font.BOLD, 17);
    private static final Font BODY_FONT = new Font("SansSerif", Font.PLAIN, 15);
    private static final Font SMALL_FONT = new Font("SansSerif", Font.PLAIN, 13);

    private final QuestionBank questionBank = new QuestionBank();
    private Player player;
    private Enemy currentEnemy;
    private List<Question> currentQuestions;
    private int currentQuestionIndex;
    private int currentPhaseNumber;
    private Round currentRound;
    private boolean abilityUsedThisRound;

    private final CardLayout cardLayout = new CardLayout();
    private final JPanel rootPanel = new JPanel();

    private JLabel roundLabel;
    private JLabel playerNameLabel, enemyNameLabel;
    private JProgressBar playerHpBar, enemyHpBar;
    private JLabel playerHpLabel, enemyHpLabel;
    private JLabel questionTypeLabel;
    private JLabel statementLabel;
    private JPanel optionsPanel;
    private JTextField answerField;
    private JButton answerSubmitButton;
    private JButton abilityButton;
    private JTextArea feedbackArea;
    private JButton continueButton;

    public GameGUI() {
        super("Quem Sabe Mais no Reino?");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(820, 680);
        setMinimumSize(new Dimension(700, 600));
        setLocationRelativeTo(null);
        getContentPane().setBackground(BG_COLOR);

        rootPanel.setLayout(cardLayout);
        rootPanel.setBackground(BG_COLOR);
        setContentPane(rootPanel);

        buildWelcomeScreen();
        buildBattleScreen();

        setVisible(true);
        cardLayout.show(rootPanel, "welcome");
    }

    private void buildWelcomeScreen() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(BG_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel title = new JLabel("Quem Sabe Mais no Reino?");
        title.setFont(TITLE_FONT);
        title.setForeground(ACCENT_COLOR);
        gbc.gridy = 0;
        panel.add(title, gbc);

        JLabel subtitle = new JLabel("Batalha do Conhecimento");
        subtitle.setFont(BODY_FONT);
        subtitle.setForeground(TEXT_COLOR);
        gbc.gridy = 1;
        panel.add(subtitle, gbc);

        JLabel prompt = new JLabel("Digite seu nome:");
        prompt.setFont(BODY_FONT);
        prompt.setForeground(TEXT_COLOR);
        gbc.gridy = 2;
        gbc.insets = new Insets(30, 10, 5, 10);
        panel.add(prompt, gbc);

        JTextField nameField = new JTextField(18);
        nameField.setFont(BODY_FONT);
        gbc.gridy = 3;
        gbc.insets = new Insets(5, 10, 10, 10);
        panel.add(nameField, gbc);

        JButton startButton = createStyledButton("Continuar");
        gbc.gridy = 4;
        gbc.insets = new Insets(20, 10, 10, 10);
        panel.add(startButton, gbc);

        Runnable confirmName = () -> {
            String name = nameField.getText().trim();
            if (name.isEmpty()) name = "Jogador";
            buildCharacterScreen(name);
            cardLayout.show(rootPanel, "character");
        };
        startButton.addActionListener(e -> confirmName.run());
        nameField.addActionListener(e -> confirmName.run());

        rootPanel.add(panel, "welcome");
    }

    private void buildCharacterScreen(String playerName) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(BG_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(8, 10, 8, 10);

        JLabel title = new JLabel("Escolha seu personagem, " + playerName);
        title.setFont(HEADER_FONT);
        title.setForeground(ACCENT_COLOR);
        gbc.gridy = 0;
        panel.add(title, gbc);

        String[][] info = {
            {"Aladdin", "Equilibrado | Habilidade: da uma dica sobre a pergunta"},
            {"Merida", "Alto ataque | Habilidade: dano duplo no proximo acerto"},
            {"Rapunzel", "Alta defesa | Habilidade: bloqueia penalidade do proximo erro"}
        };

        for (int i = 0; i < 3; i++) {
            final int choice = i + 1;
            JButton charButton = createStyledButton(info[i][0] + " - " + info[i][1]);
            charButton.setFont(SMALL_FONT);
            gbc.gridy = i + 1;
            panel.add(charButton, gbc);
            charButton.addActionListener(e -> startGame(playerName, choice));
        }

        for (Component c : rootPanel.getComponents()) {
            if ("character".equals(c.getName())) rootPanel.remove(c);
        }
        panel.setName("character");
        rootPanel.add(panel, "character");
    }

    private Character createCharacter(int choice) {
        return switch (choice) {
            case 1 -> new Aladdin();
            case 2 -> new Merida();
            case 3 -> new Rapunzel();
            default -> new Aladdin();
        };
    }

    private void startGame(String playerName, int characterChoice) {
        Character character = createCharacter(characterChoice);
        player = new Player(playerName, character);
        currentPhaseNumber = 1;
        startPhase();
    }

    private void startPhase() {
        switch (currentPhaseNumber) {
            case 1 -> {
                currentEnemy = Enemy.createRainhaMa();
                currentQuestions = questionBank.getQuestionsByDifficulty(Question.Difficulty.FACIL, 5);
                showPhaseIntro("Fase 1 - O Espelho Magico");
            }
            case 2 -> {
                currentEnemy = Enemy.createScar();
                currentQuestions = questionBank.getQuestionsByDifficulty(Question.Difficulty.MEDIO, 5);
                showPhaseIntro("Fase 2 - As Terras do Orgulhoso");
            }
            case 3 -> {
                currentEnemy = Enemy.createMalévola();
                currentQuestions = questionBank.getQuestionsByDifficulty(Question.Difficulty.DIFICIL, 5);
                showPhaseIntro("Fase 3 - A Maldicao das Fadas");
            }
            default -> showFinalVictory();
        }
    }

    private void showPhaseIntro(String phaseName) {
        player.getCharacter().resetHp();
        currentEnemy.resetHp();
        player.resetAbilityForPhase();
        currentQuestionIndex = 0;

        cardLayout.show(rootPanel, "battle");
        playerNameLabel.setText(player.getCharacter().getName());
        enemyNameLabel.setText(currentEnemy.getName());
        updateHpBar(playerHpBar, playerHpLabel, player.getCharacter().getCurrentHp(), player.getCharacter().getMaxHp());
        updateHpBar(enemyHpBar, enemyHpLabel, currentEnemy.getCurrentHp(), currentEnemy.getMaxHp());

        feedbackArea.setText(phaseName.toUpperCase() + "\n" +
            currentEnemy.getName() + " apareceu! \"" + currentEnemy.getTaunt() + "\"");

        showContinueStep(this::startNextRound);
    }

    private void buildBattleScreen() {
        JPanel battlePanel = new JPanel(new BorderLayout(10, 10));
        battlePanel.setBackground(BG_COLOR);
        battlePanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JPanel statusPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        statusPanel.setBackground(BG_COLOR);
        statusPanel.add(buildStatusPanel(true));
        statusPanel.add(buildStatusPanel(false));

        roundLabel = new JLabel("Rodada 1/5", SwingConstants.CENTER);
        roundLabel.setFont(HEADER_FONT);
        roundLabel.setForeground(ACCENT_COLOR);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(BG_COLOR);
        topPanel.add(roundLabel, BorderLayout.NORTH);
        topPanel.add(statusPanel, BorderLayout.CENTER);

        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
        questionPanel.setBackground(PANEL_COLOR);
        questionPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        questionPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        questionTypeLabel = new JLabel(" ");
        questionTypeLabel.setFont(SMALL_FONT);
        questionTypeLabel.setForeground(ACCENT_COLOR);
        questionTypeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        statementLabel = new JLabel(" ");
        statementLabel.setFont(BODY_FONT);
        statementLabel.setForeground(TEXT_COLOR);
        statementLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        optionsPanel.setBackground(PANEL_COLOR);
        optionsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        answerField = new JTextField();
        answerField.setFont(BODY_FONT);
        answerField.setMaximumSize(new Dimension(400, 36));
        answerField.setAlignmentX(Component.LEFT_ALIGNMENT);
        answerField.setVisible(false);

        questionPanel.add(questionTypeLabel);
        questionPanel.add(Box.createVerticalStrut(8));
        questionPanel.add(statementLabel);
        questionPanel.add(Box.createVerticalStrut(12));
        questionPanel.add(optionsPanel);
        questionPanel.add(answerField);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setBackground(BG_COLOR);

        JPanel actionRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        actionRow.setBackground(BG_COLOR);
        abilityButton = createStyledButton("Usar habilidade");
        answerSubmitButton = createStyledButton("Responder");
        continueButton = createStyledButton("Continuar");
        continueButton.setVisible(false);
        actionRow.add(abilityButton);
        actionRow.add(answerSubmitButton);
        actionRow.add(continueButton);

        feedbackArea = new JTextArea(4, 40);
        feedbackArea.setFont(SMALL_FONT);
        feedbackArea.setForeground(TEXT_COLOR);
        feedbackArea.setBackground(PANEL_COLOR);
        feedbackArea.setEditable(false);
        feedbackArea.setLineWrap(true);
        feedbackArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(feedbackArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        bottomPanel.add(actionRow);
        bottomPanel.add(scrollPane);

        battlePanel.add(topPanel, BorderLayout.NORTH);
        battlePanel.add(questionPanel, BorderLayout.CENTER);
        battlePanel.add(bottomPanel, BorderLayout.SOUTH);

        rootPanel.add(battlePanel, "battle");
    }

    private JPanel buildStatusPanel(boolean isPlayer) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(PANEL_COLOR);
        panel.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));

        JLabel nameLabel = new JLabel(isPlayer ? "Jogador" : "Inimigo");
        nameLabel.setFont(HEADER_FONT);
        nameLabel.setForeground(TEXT_COLOR);

        JProgressBar hpBar = new JProgressBar(0, 100);
        hpBar.setValue(100);
        hpBar.setForeground(isPlayer ? HP_PLAYER_COLOR : HP_ENEMY_COLOR);
        hpBar.setStringPainted(true);

        JLabel hpLabel = new JLabel("HP: 0/0");
        hpLabel.setFont(SMALL_FONT);
        hpLabel.setForeground(TEXT_COLOR);

        panel.add(nameLabel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(hpBar);
        panel.add(Box.createVerticalStrut(3));
        panel.add(hpLabel);

        if (isPlayer) {
            playerNameLabel = nameLabel;
            playerHpBar = hpBar;
            playerHpLabel = hpLabel;
        } else {
            enemyNameLabel = nameLabel;
            enemyHpBar = hpBar;
            enemyHpLabel = hpLabel;
        }
        return panel;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(BODY_FONT);
        button.setBackground(ACCENT_COLOR);
        button.setForeground(new Color(40, 30, 10));
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(true);
        button.setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 16));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }

    private void updateHpBar(JProgressBar bar, JLabel label, int current, int max) {
        int pct = max == 0 ? 0 : (int) ((double) current / max * 100);
        bar.setValue(pct);
        label.setText("HP: " + current + "/" + max);
    }
    private void startNextRound() {
        if (currentQuestionIndex >= currentQuestions.size()) {
            if (currentEnemy.isAlive()) {
                feedbackArea.setText("Rodadas esgotadas! O inimigo sobreviveu...");
                showContinueStep(this::showDefeatScreen);
            }
            return;
        }

        Question question = currentQuestions.get(currentQuestionIndex);
        currentRound = new Round(currentQuestionIndex + 1, question, player, currentEnemy);
        abilityUsedThisRound = false;

        roundLabel.setText("Rodada " + (currentQuestionIndex + 1) + "/" + currentQuestions.size());
        updateHpBar(playerHpBar, playerHpLabel, player.getCharacter().getCurrentHp(), player.getCharacter().getMaxHp());
        updateHpBar(enemyHpBar, enemyHpLabel, currentEnemy.getCurrentHp(), currentEnemy.getMaxHp());
        feedbackArea.setText("");

        displayQuestion(question);

        clearListeners(abilityButton);
        abilityButton.setEnabled(player.isAbilityAvailable());
        abilityButton.setText(player.isAbilityAvailable()
            ? "Usar: " + player.getCharacter().getSpecialAbility().getName()
            : "Habilidade usada");
        abilityButton.addActionListener(e -> useAbilityForRound(question));
    }

    private void useAbilityForRound(Question question) {
        if (abilityUsedThisRound || !player.isAbilityAvailable()) return;
        AbilityResult result = currentRound.useAbility();
        abilityUsedThisRound = true;
        abilityButton.setEnabled(false);
        feedbackArea.append(result.getMessage() + "\n");

        if (result.getType() == AbilityResult.EffectType.HINT) {
            if (question instanceof MultipleChoiceQuestion mcq) {
                showEliminatedOptions(mcq);
            } else if (question instanceof TrueFalseQuestion tfq) {
                String label = tfq.getWrongOption().equals("V") ? "Verdadeiro" : "Falso";
                feedbackArea.append("A opcao incorreta e: " + tfq.getWrongOption() + " (" + label + ")\n");
            } else if (question instanceof FillBlankQuestion fbq) {
                feedbackArea.append("Dica: " + fbq.getHint() + "\n");
            }
        }
    }

    private void showEliminatedOptions(MultipleChoiceQuestion mcq) {
        String[] eliminable = mcq.getEliminableOptions();
        int count = 0;
        StringBuilder sb = new StringBuilder("Eliminadas: ");
        for (String letter : eliminable) {
            if (count >= 2) break;
            sb.append(letter).append(" ");
            for (Component c : optionsPanel.getComponents()) {
                if (c instanceof JButton b && b.getText().startsWith(letter + ")")) {
                    b.setEnabled(false);
                }
            }
            count++;
        }
        feedbackArea.append(sb + "\n");
    }

    private void displayQuestion(Question question) {
        optionsPanel.removeAll();
        answerField.setVisible(false);
        answerField.setText("");
        clearListeners(answerSubmitButton);

        statementLabel.setText("<html><body style='width: 480px'>" + question.getStatement() + "</body></html>");

        if (question instanceof MultipleChoiceQuestion mcq) {
            questionTypeLabel.setText("MULTIPLA ESCOLHA - " + question.getDifficultyLabel());
            String[] letters = {"A", "B", "C", "D"};
            String[] options = mcq.getOptions();
            for (int i = 0; i < options.length; i++) {
                JButton optButton = new JButton(letters[i] + ") " + options[i]);
                optButton.setFont(BODY_FONT);
                optButton.setBackground(Color.WHITE);
                optButton.setForeground(new Color(30, 28, 45));
                optButton.setOpaque(true);
                optButton.setBorderPainted(true);
                optButton.setFocusPainted(false);
                optButton.setContentAreaFilled(true);
                optButton.setHorizontalAlignment(SwingConstants.LEFT);
                optButton.setAlignmentX(Component.LEFT_ALIGNMENT);
                optButton.setMaximumSize(new Dimension(620, 38));
                final String letter = letters[i];
                optButton.addActionListener(e -> processAnswer(letter));
                optionsPanel.add(optButton);
                optionsPanel.add(Box.createVerticalStrut(5));
            }
            answerSubmitButton.setVisible(false);
        } else if (question instanceof TrueFalseQuestion) {
            questionTypeLabel.setText("VERDADEIRO OU FALSO - " + question.getDifficultyLabel());
            JButton trueButton = new JButton("Verdadeiro");
            JButton falseButton = new JButton("Falso");
            for (JButton b : new JButton[]{trueButton, falseButton}) {
                b.setFont(BODY_FONT);
                b.setBackground(Color.WHITE);
                b.setForeground(new Color(30, 28, 45));
                b.setOpaque(true);
                b.setBorderPainted(true);
                b.setFocusPainted(false);
                b.setContentAreaFilled(true);
                b.setHorizontalAlignment(SwingConstants.LEFT);
                b.setAlignmentX(Component.LEFT_ALIGNMENT);
                b.setMaximumSize(new Dimension(620, 38));
            }
            trueButton.addActionListener(e -> processAnswer("V"));
            falseButton.addActionListener(e -> processAnswer("F"));
            optionsPanel.add(trueButton);
            optionsPanel.add(Box.createVerticalStrut(5));
            optionsPanel.add(falseButton);
            answerSubmitButton.setVisible(false);
        } else if (question instanceof FillBlankQuestion) {
            questionTypeLabel.setText("COMPLETAR LACUNA - " + question.getDifficultyLabel());
            answerField.setVisible(true);
            answerSubmitButton.setVisible(true);
            answerSubmitButton.setEnabled(true);
            answerSubmitButton.setText("Responder");
            answerSubmitButton.addActionListener(e -> processAnswer(answerField.getText()));
            answerField.addActionListener(e -> processAnswer(answerField.getText()));
        }

        optionsPanel.revalidate();
        optionsPanel.repaint();
    }

    private void processAnswer(String answer) {
        disableAllAnswerInputs();
        abilityButton.setEnabled(false);

        Round.RoundResult result = currentRound.processAnswer(answer);
        feedbackArea.append(result.getOutcome().getFeedback() + "\n");
        updateHpBar(playerHpBar, playerHpLabel, player.getCharacter().getCurrentHp(), player.getCharacter().getMaxHp());
        updateHpBar(enemyHpBar, enemyHpLabel, currentEnemy.getCurrentHp(), currentEnemy.getMaxHp());

        currentQuestionIndex++;

        if (!result.isPlayerAlive()) {
            showContinueStep(this::showDefeatScreen);
            return;
        }
        if (!result.isEnemyAlive()) {
            feedbackArea.append("\nVITORIA! Voce derrotou " + currentEnemy.getName() + "!\n");
            showContinueStep(() -> {
                currentPhaseNumber++;
                startPhase();
            });
            return;
        }

        showContinueStep(this::startNextRound);
    }

    private void disableAllAnswerInputs() {
        for (Component c : optionsPanel.getComponents()) {
            if (c instanceof JButton b) b.setEnabled(false);
        }
        answerSubmitButton.setEnabled(false);
    }

    private void clearListeners(JButton button) {
        for (var listener : button.getActionListeners()) {
            button.removeActionListener(listener);
        }
    }

    private void showContinueStep(Runnable nextStep) {
        continueButton.setVisible(true);
        clearListeners(continueButton);
        continueButton.addActionListener(e -> {
            continueButton.setVisible(false);
            nextStep.run();
        });
    }

    private void showDefeatScreen() {
        showEndScreen(false);
    }

    private void showFinalVictory() {
        showEndScreen(true);
    }

    private void showEndScreen(boolean victory) {
        JPanel endPanel = new JPanel(new GridBagLayout());
        endPanel.setBackground(BG_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel resultLabel = new JLabel(victory
            ? "VITORIA! Voce salvou o reino!"
            : "DERROTA! O reino precisa de outro heroi...");
        resultLabel.setFont(TITLE_FONT);
        resultLabel.setForeground(victory ? HP_PLAYER_COLOR : HP_ENEMY_COLOR);
        gbc.gridy = 0;
        endPanel.add(resultLabel, gbc);

        String[] stats = {
            "Jogador: " + player.getName(),
            "Personagem: " + player.getCharacter().getName(),
            "Pontuacao final: " + player.getScore(),
            "Acertos: " + player.getCorrectAnswers() + " | Erros: " + player.getWrongAnswers(),
            "Habilidades usadas: " + player.getAbilitiesUsed()
        };
        int row = 1;
        for (String stat : stats) {
            JLabel statLabel = new JLabel(stat);
            statLabel.setFont(BODY_FONT);
            statLabel.setForeground(TEXT_COLOR);
            gbc.gridy = row++;
            endPanel.add(statLabel, gbc);
        }

        JButton exitButton = createStyledButton("Sair do jogo");
        exitButton.addActionListener(e -> System.exit(0));
        gbc.gridy = row;
        gbc.insets = new Insets(25, 10, 10, 10);
        endPanel.add(exitButton, gbc);

        String cardName = "end-" + System.nanoTime();
        rootPanel.add(endPanel, cardName);
        cardLayout.show(rootPanel, cardName);
    }
}
    