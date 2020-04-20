# Contador de palavras

Projeto de uma aplicação desktop capaz de contar palavras em um arquivo.

Esse projeto foi construído para atender ao **Trabalho II** da disciplina de **Programação II**, ministrada pelo professor Gilvan Justino na 3ª fase do curso de Sistemas de Informação, da Fundação Universidade Regional de Blumenau.

### Alunos responsáveis pelo projeto:

Lucas Samuel Kluser\
lkluser@furb.br

Matheus Leopoldo dos Santos Boing\
mlsboing@furb.br

---
## Sobre o projeto
O projeto foi construído utilizando a arquitetura MVC (Model - View - Controller). Entretanto, para não aumentar a complexidade ou tempo despendido na execução do projeto, não foram empregados todos (ou foram empregados parcialmente) os conceitos dessa arquitetura.

## Diagrama de classes
![Diagrama de classes](https://raw.githubusercontent.com/lukesamk/word-counter/master/diagrama-classe.png)

## Como executar localmente

1. Clone o repositório em sua máquina local\
   `git clone https://github.com/lukesamk/word-counter`
2. Importe o projeto em seu IDE
3. Execute o projeto através do método **main()**, da classe **Main**

### Dicas de uso

1. Ao pressionar `Enter` no campo **Arquivo**, o contador tentará abrir o arquivo no caminho especificado no campo **Arquivo**.
2. Ao pressionar `Enter` no campo **Arquivo**, a janela de seleção de arquivo será aberta se:
   1. O caminho para o arquivo no campo **Arquivo** for referente ao arquivo já aberto no contador;
   2. O caminho no campo **Arquivo** for um diretório.
