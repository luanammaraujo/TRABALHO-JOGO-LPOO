Quem Sabe Mais no Reino? — Batalha do Conhecimento

Quem Sabe Mais no Reino é um jogo educacional de perguntas e respostas com batalhas por turnos, desenvolvido para a disciplina de Linguagem de Programação Orientada a Objetos (LPOO). O jogador escolhe um personagem de universo Disney e enfrenta uma sequência de vilões respondendo perguntas de conhecimento geral. Cada resposta correta causa dano ao inimigo, e cada resposta incorreta causa dano ao próprio jogador. Além disso, cada personagem possui uma habilidade especial única que pode ser usada uma vez por fase para virar o jogo a seu favor.

Como executar

O projeto não depende de nenhuma biblioteca externa, apenas Java puro (recomenda-se JDK 17 ou superior). Existem duas versões prontas para rodar: uma pelo console e outra com interface gráfica feita em Swing. As duas compartilham exatamente a mesma lógica de jogo — personagens, perguntas e regras de batalha são idênticos — e diferem apenas na forma como a tela é exibida e a resposta é coletada.

Para rodar a versão console, entre na pasta src/main/java do projeto, compile todos os arquivos com o comando javac -encoding UTF-8 -d . codearena/model/ability/*.java codearena/model/character/*.java codearena/model/question/*.java codearena/model/*.java codearena/battle/*.java codearena/ui/*.java codearena/game/*.java e depois execute com java codearena.game.Main.

Para rodar a versão gráfica, o processo de compilação é o mesmo, mudando apenas o comando de execução para java codearena.game.MainGUI. Isso abre uma janela com botões, barras de vida e campos de texto, ao invés do terminal.

Personagens jogáveis

O jogador pode escolher entre três personagens, cada um com atributos e uma habilidade especial diferentes.

Alladin é o personagem equilibrado, com 100 de HP e 10 de defesa. Sua habilidade, chamada Tapete Mágico, dá uma dica sobre a pergunta atual: em perguntas de múltipla escolha ela elimina duas alternativas erradas, em perguntas de verdadeiro ou falso ela revela qual opção está incorreta, e em perguntas de completar lacuna ela mostra a dica da pergunta.

Merida é a personagem com menos resistência, com 85 de HP e apenas 5 de defesa. Sua habilidade, Flecha Certeira, faz a próxima resposta correta causar dano em dobro.

Rapunzel é a personagem mais defensiva, com 120 de HP e 15 de defesa. Sua habilidade, Cabelos Mágicos, bloqueia a penalidade do próximo erro cometido, ou seja, anula o dano que o jogador receberia ao responder errado.

Cada personagem só pode usar sua habilidade uma única vez por fase.

Inimigos e progressão das fases

O jogo é dividido em três fases com dificuldade crescente, cada uma com cinco rodadas (perguntas). O jogador vence a fase ao reduzir o HP do inimigo a zero antes que as cinco rodadas se esgotem.

Na primeira fase, de dificuldade fácil, o jogador enfrenta a Rainha Má, que tem 80 de HP e 5 de defesa. Na segunda fase, de dificuldade média, o oponente é o Scar, com 80 de HP e 8 de defesa. Na terceira e última fase, de dificuldade difícil, o jogador enfrenta a Malévola, com 80 de HP e 12 de defesa.

Tipos de pergunta

O jogo possui três tipos de pergunta. As perguntas de múltipla escolha apresentam quatro alternativas (A, B, C e D). As perguntas de verdadeiro ou falso aceitam diferentes formas de resposta, como V, F, Verdadeiro, Falso, True ou False. Já as perguntas de completar lacuna pedem que o jogador digite a resposta livremente.

O dano causado ao acertar e a penalidade sofrida ao errar variam conforme a dificuldade da pergunta, e não conforme o personagem escolhido. Perguntas fáceis causam 20 de dano ao acertar e 10 de penalidade ao errar. Perguntas médias causam 25 de dano e 18 de penalidade. Perguntas difíceis causam 40 de dano e 28 de penalidade. Esse valor é o mesmo independentemente de qual personagem o jogador está controlando.

Sistema de habilidades

As habilidades especiais são representadas pela interface Ability, implementada pelas classes HintAbility, DoubleDamageAbility e ShieldAbility. Cada personagem instancia sua habilidade uma única vez, dentro do próprio construtor, evitando criar um novo objeto a cada rodada em que a habilidade é usada. O jogo sempre manipula essas habilidades pelo tipo da interface, nunca pela implementação concreta, o que é um exemplo direto de polimorfismo.

Estrutura do projeto

O código está organizado em pacotes por responsabilidade dentro de src/main/java/codearena.

O pacote game contém o Main, ponto de entrada da versão console, o MainGUI, ponto de entrada da versão gráfica, e o Game, que controla o fluxo geral da partida na versão console.

O pacote battle contém o Round, que representa uma rodada de batalha, e o BattleManager, que controla a sequência de rodadas de uma fase na versão console.

O pacote model guarda o Player, responsável pelo estado do jogador durante a partida, como pontuação, personagem escolhido e habilidades ativas. Dentro dele, o subpacote character contém a classe abstrata Character, os personagens jogáveis Alladin, Merida e Rapunzel, e a classe Enemy, que representa os inimigos controlados pelo sistema. O subpacote question contém a classe abstrata Question, suas três subclasses concretas (múltipla escolha, verdadeiro ou falso e completar lacuna), o QuestionBank, que armazena e filtra as perguntas por dificuldade, e o AnswerEvaluator, responsável por avaliar respostas e calcular dano ou penalidade. O subpacote ability contém a interface Ability, a classe AbilityResult, que representa o resultado da ativação de uma habilidade, e as três implementações concretas das habilidades especiais.

Por fim, o pacote ui contém o ConsoleUI, responsável pela interface em texto, e o GameGUI, responsável pela interface gráfica em Swing.

Conceitos de orientação a objetos aplicados

O projeto aplica herança nas classes Aladdin, Merida, Rapunzel e Enemy, que herdam de Character, e nas classes MultipleChoiceQuestion, TrueFalseQuestion e FillBlankQuestion, que herdam de Question. O polimorfismo aparece nos métodos checkAnswer e formatForDisplay, que se comportam de forma diferente em cada subclasse de pergunta, e também no modo como o jogo manipula personagens e habilidades sempre pelo tipo base, nunca pela classe concreta. A interface Ability define o contrato comum para todas as habilidades especiais, permitindo adicionar novos personagens com novas habilidades sem alterar o código já existente. As classes Character e Question são abstratas, obrigando suas subclasses a implementar comportamentos essenciais. Por fim, o encapsulamento aparece em todas as classes do projeto, com atributos privados acessados apenas por meio de métodos próprios.

Decisões de design

Uma decisão importante foi fazer cada personagem instanciar sua habilidade especial uma única vez, no próprio construtor, em vez de criar um novo objeto de habilidade a cada vez que ela é usada. Isso evita alocação repetida de memória sem necessidade.

Outra decisão foi manter a lógica de jogo completamente separada da interface. As classes Round, BattleManager, Player, Question e Character não sabem nada sobre como a tela é desenhada. Essa separação foi o que permitiu construir a versão gráfica reaproveitando inteiramente essas classes, sem precisar alterar nenhuma linha delas.

Vale destacar também que a versão gráfica funciona de um jeito diferente da versão console por uma razão técnica: o console pode parar e esperar o jogador digitar algo, enquanto uma interface gráfica reage a cliques de botão. Por isso, o GameGUI organiza o fluxo da partida chamando diretamente os mesmos métodos de Round e Player que o BattleManager usa no console, mas de forma orientada a eventos.

Por fim, as classes Main e Game foram agrupadas no mesmo pacote game porque ambas tratam da inicialização e do controle geral da partida. Essa é uma escolha de organização por responsabilidade, e não uma regra obrigatória da linguagem — outras formas de organizar essas classes também seriam válidas.

Inicialmente, os personagens e inimigos possuíam também um atributo de ataque, mas esse valor nunca era de fato usado nos cálculos de dano da batalha — o dano sempre dependeu exclusivamente da dificuldade da pergunta, definida na classe Question. Por esse motivo, o atributo de ataque foi removido de Character e de todas as suas subclasses, evitando manter no código um dado que dava a falsa impressão de influenciar o resultado da batalha. Hoje, o que diferencia os personagens entre si é apenas o HP, a defesa e a habilidade especial de cada um.
