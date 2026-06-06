package codearena.model.question;

import java.util.*;

/**
 * Banco de perguntas do jogo.
 * Armazena e divide perguntas por dificuldade.
 */
public class QuestionBank {

    private List<Question> questions;
    private Random random;

    public QuestionBank() {
        this.questions = new ArrayList<>();
        this.random = new Random();
        loadQuestions();
    }

    private void loadQuestions() {
        // ===== FÁCIL =====
        // Múltipla Escolha - Fácil
        questions.add(new MultipleChoiceQuestion(
            "Qual é a capital do Brasil?",
            new String[]{"São Paulo", "Brasília", "Rio de Janeiro", "Salvador"},
            "B", Question.Difficulty.FACIL, "Geografia"
        ));
        questions.add(new MultipleChoiceQuestion(
            "Quantos lados tem um triângulo?",
            new String[]{"2", "3", "4", "5"},
            "B", Question.Difficulty.FACIL, "Matemática"
        ));
        questions.add(new MultipleChoiceQuestion(
            "Qual animal é conhecido como o rei da selva?",
            new String[]{"Elefante", "Tigre", "Leão", "Gorila"},
            "C", Question.Difficulty.FACIL, "Natureza"
        ));
        questions.add(new MultipleChoiceQuestion(
            "Qual é a cor do céu em um dia limpo?",
            new String[]{"Verde", "Vermelho", "Amarelo", "Azul"},
            "D", Question.Difficulty.FACIL, "Ciências"
        ));
        questions.add(new MultipleChoiceQuestion(
            "Qual é o maior oceano do mundo?",
            new String[]{"Atlântico", "Índico", "Pacífico", "Ártico"},
            "C", Question.Difficulty.FACIL, "Geografia"
        ));
        questions.add(new MultipleChoiceQuestion(
            "Quantos meses tem um ano?",
            new String[]{"10", "11", "12", "13"},
            "C", Question.Difficulty.FACIL, "Geral"
        ));
        questions.add(new MultipleChoiceQuestion(
            "Qual é o resultado de 5 × 5?",
            new String[]{"20", "25", "30", "35"},
            "B", Question.Difficulty.FACIL, "Matemática"
        ));
        questions.add(new MultipleChoiceQuestion(
            "Em qual continente fica o Brasil?",
            new String[]{"Europa", "África", "América do Sul", "Ásia"},
            "C", Question.Difficulty.FACIL, "Geografia"
        ));

        // Verdadeiro/Falso - Fácil
        questions.add(new TrueFalseQuestion(
            "A Terra é o terceiro planeta do Sistema Solar.",
            true, Question.Difficulty.FACIL, "Ciências"
        ));
        questions.add(new TrueFalseQuestion(
            "O Brasil tem 26 estados e o Distrito Federal.",
            true, Question.Difficulty.FACIL, "Geografia"
        ));
        questions.add(new TrueFalseQuestion(
            "O sol é uma estrela.",
            true, Question.Difficulty.FACIL, "Ciências"
        ));
        questions.add(new TrueFalseQuestion(
            "O pinguim é uma ave que consegue voar.",
            false, Question.Difficulty.FACIL, "Natureza"
        ));
        questions.add(new TrueFalseQuestion(
            "A água ferve a 100°C ao nível do mar.",
            true, Question.Difficulty.FACIL, "Ciências"
        ));

        // ===== MÉDIO =====
        questions.add(new MultipleChoiceQuestion(
            "Qual é o elemento químico com símbolo 'O'?",
            new String[]{"Ouro", "Osmio", "Oxigênio", "Óleum"},
            "C", Question.Difficulty.MEDIO, "Química"
        ));
        questions.add(new MultipleChoiceQuestion(
            "Quem escreveu 'Dom Casmurro'?",
            new String[]{"José de Alencar", "Machado de Assis", "Clarice Lispector", "Jorge Amado"},
            "B", Question.Difficulty.MEDIO, "Literatura"
        ));
        questions.add(new MultipleChoiceQuestion(
            "Qual é a fórmula química da água?",
            new String[]{"CO2", "NaCl", "H2O", "O2"},
            "C", Question.Difficulty.MEDIO, "Química"
        ));
        questions.add(new MultipleChoiceQuestion(
            "Em que ano o Brasil se tornou independente de Portugal?",
            new String[]{"1800", "1822", "1850", "1889"},
            "B", Question.Difficulty.MEDIO, "História"
        ));
        questions.add(new MultipleChoiceQuestion(
            "Qual é o maior país do mundo em extensão territorial?",
            new String[]{"China", "EUA", "Brasil", "Rússia"},
            "D", Question.Difficulty.MEDIO, "Geografia"
        ));
        questions.add(new MultipleChoiceQuestion(
            "Quantos ossos tem o corpo humano adulto?",
            new String[]{"186", "206", "226", "256"},
            "B", Question.Difficulty.MEDIO, "Biologia"
        ));
        questions.add(new MultipleChoiceQuestion(
            "Qual planeta é conhecido como o 'Planeta Vermelho'?",
            new String[]{"Júpiter", "Saturno", "Marte", "Vênus"},
            "C", Question.Difficulty.MEDIO, "Astronomia"
        ));
        questions.add(new MultipleChoiceQuestion(
            "Quem pintou a Mona Lisa?",
            new String[]{"Michelangelo", "Rafael", "Leonardo da Vinci", "Botticelli"},
            "C", Question.Difficulty.MEDIO, "Arte"
        ));

        // Verdadeiro/Falso - Médio
        questions.add(new TrueFalseQuestion(
            "A velocidade da luz é aproximadamente 300.000 km/s.",
            true, Question.Difficulty.MEDIO, "Física"
        ));
        questions.add(new TrueFalseQuestion(
            "O DNA humano é formado por apenas 2 bases nitrogenadas.",
            false, Question.Difficulty.MEDIO, "Biologia"
        ));
        questions.add(new TrueFalseQuestion(
            "A Segunda Guerra Mundial terminou em 1945.",
            true, Question.Difficulty.MEDIO, "História"
        ));
        questions.add(new TrueFalseQuestion(
            "O coração humano tem 3 câmaras.",
            false, Question.Difficulty.MEDIO, "Biologia"
        ));
        questions.add(new TrueFalseQuestion(
            "O Python é uma linguagem de programação interpretada.",
            true, Question.Difficulty.MEDIO, "Tecnologia"
        ));

        // ===== DIFÍCIL =====
        questions.add(new MultipleChoiceQuestion(
            "Qual é o número de Avogadro?",
            new String[]{"6,02 × 10²³", "3,14 × 10²³", "1,38 × 10²³", "9,11 × 10²³"},
            "A", Question.Difficulty.DIFICIL, "Química"
        ));
        questions.add(new MultipleChoiceQuestion(
            "Qual é a teoria que descreve a origem do universo?",
            new String[]{"Teoria da Evolução", "Big Bang", "Teoria Quântica", "Relatividade Geral"},
            "B", Question.Difficulty.DIFICIL, "Física"
        ));
        questions.add(new MultipleChoiceQuestion(
            "Em que linguagem foi desenvolvido o sistema operacional Unix originalmente?",
            new String[]{"Python", "Java", "C", "Assembly"},
            "C", Question.Difficulty.DIFICIL, "Tecnologia"
        ));
        questions.add(new MultipleChoiceQuestion(
            "Qual é o menor país do mundo?",
            new String[]{"Monaco", "San Marino", "Vaticano", "Liechtenstein"},
            "C", Question.Difficulty.DIFICIL, "Geografia"
        ));
        questions.add(new MultipleChoiceQuestion(
            "Quem formulou a Teoria da Relatividade?",
            new String[]{"Newton", "Tesla", "Einstein", "Bohr"},
            "C", Question.Difficulty.DIFICIL, "Física"
        ));
        questions.add(new MultipleChoiceQuestion(
            "Qual é o nome do processo pelo qual as plantas produzem alimento?",
            new String[]{"Respiração Celular", "Fotossíntese", "Fermentação", "Osmose"},
            "B", Question.Difficulty.DIFICIL, "Biologia"
        ));
        questions.add(new MultipleChoiceQuestion(
            "Qual é a estrutura de dados que segue o princípio LIFO?",
            new String[]{"Fila", "Lista Ligada", "Pilha", "Árvore"},
            "C", Question.Difficulty.DIFICIL, "Computação"
        ));
        questions.add(new MultipleChoiceQuestion(
            "Quem foi o primeiro ser humano a pisar na Lua?",
            new String[]{"Yuri Gagarin", "Buzz Aldrin", "John Glenn", "Neil Armstrong"},
            "D", Question.Difficulty.DIFICIL, "História"
        ));

        // Verdadeiro/Falso - Difícil
        questions.add(new TrueFalseQuestion(
            "A mecânica quântica foi desenvolvida por Einstein.",
            false, Question.Difficulty.DIFICIL, "Física"
        ));
        questions.add(new TrueFalseQuestion(
            "O sistema binário usa apenas os dígitos 0 e 1.",
            true, Question.Difficulty.DIFICIL, "Computação"
        ));
        questions.add(new TrueFalseQuestion(
            "O algoritmo de Dijkstra resolve o problema do caminho mais curto em grafos.",
            true, Question.Difficulty.DIFICIL, "Computação"
        ));
        questions.add(new TrueFalseQuestion(
            "A mitocôndria é chamada de 'usina de energia da célula'.",
            true, Question.Difficulty.DIFICIL, "Biologia"
        ));
        // ===== COMPLETAR LACUNA - FACIL =====
        questions.add(new FillBlankQuestion(
            "A ___ é o planeta mais proximo do Sol.",
            "mercurio",
            "Nome de um deus romano",
            Question.Difficulty.FACIL, "Astronomia"
        ));
        questions.add(new FillBlankQuestion(
            "A ___ é a capital da Franca.",
            "paris",
            "Cidade da Torre Eiffel",
            Question.Difficulty.FACIL, "Geografia"
        ));
        questions.add(new FillBlankQuestion(
            "A ___ é o liquido essencial para a vida.",
            "agua",
            "Formula H2O",
            Question.Difficulty.FACIL, "Ciencias"
        ));

        // ===== COMPLETAR LACUNA - MEDIO =====
        questions.add(new FillBlankQuestion(
            "O ___ foi o cientista que descobriu a gravidade ao ver uma maca cair.",
            "newton",
            "Sobrenome de Isaac ___",
            Question.Difficulty.MEDIO, "Fisica"
        ));
        questions.add(new FillBlankQuestion(
            "A ___ é a celula responsavel pelo transporte de oxigenio no sangue.",
            "hemacia",
            "Tambem chamada de globulo vermelho",
            Question.Difficulty.MEDIO, "Biologia"
        ));
        questions.add(new FillBlankQuestion(
            "O ___ é o maior planeta do Sistema Solar.",
            "jupiter",
            "Nome de um deus romano rei dos deuses",
            Question.Difficulty.MEDIO, "Astronomia"
        ));

        // ===== COMPLETAR LACUNA - DIFICIL =====
        questions.add(new FillBlankQuestion(
            "O ___ é o principio que diz que nao podemos conhecer posicao e velocidade de uma particula simultaneamente.",
            "incerteza",
            "Principio de Heisenberg",
            Question.Difficulty.DIFICIL, "Fisica"
        ));
        questions.add(new FillBlankQuestion(
            "Em Java, o ___ é o modificador que impede uma classe de ser herdada.",
            "final",
            "Palavra reservada que tambem impede sobrescrita de metodos",
            Question.Difficulty.DIFICIL, "Computacao"
        ));
        questions.add(new FillBlankQuestion(
            "O ___ é o algoritmo de ordenacao mais eficiente para listas grandes em media.",
            "quicksort",
            "Divide a lista em particoes recursivamente",
            Question.Difficulty.DIFICIL, "Computacao"
        ));
    }

    /**
     * Retorna perguntas filtradas por dificuldade em ordem aleatória.
     */
    public List<Question> getQuestionsByDifficulty(Question.Difficulty difficulty, int count) {
        List<Question> filtered = questions.stream()
            .filter(q -> q.getDifficulty() == difficulty)
            .collect(java.util.stream.Collectors.toList());
        Collections.shuffle(filtered, random);
        return filtered.subList(0, Math.min(count, filtered.size()));
    }

    public int getTotalQuestions() { return questions.size(); }
}