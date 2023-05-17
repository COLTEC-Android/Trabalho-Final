# Trabalho Final: Aplicativo Mobile

**Professor João Eduardo Montandon**

**Núm de integrantes: 2**

## Visão Geral

O objetivo deste trabalho será desenvolver um aplicativo utilizando todos os conceitos aprendidos nas disciplinas de Programação Android e Tópicos em Desenvolvimento Android.
Em linhas gerais, seu aplicativo deverá exibir uma lista de informações (lugares, livros, filmes, etc---vai depender do tema do seu app).
Essas informações poderão ser consultadas em detalhes em uma outra tela, caso o usuário selecione o item da lista.
Ainda, o usuário poderá exercer pelo menos um tipo de ação para cada item da lista, como por exemplo compartilhar, favoritar, excluir, etc.

## Requisitos

Mais Especificamente, seu aplicativo deverá cumprir os seguintes requisitos:

### Login e Cadastro de Usuários

As funcionalidades do aplicativo estarão disponíveis apenas perante login/cadastro de usuário. 
O login deverá ser feito por meio de um username (ex: login, e-mail, etc) e senha, composta de caracteres numéricos apenas.

Já o cadastro deverá incluir os seguintes dados:

1. Dados pessoais
    * Nome, data de nascimento
2. Dados de contato
    * e-mail, telefone, endereço
3. Dados de login
    * username e senha

Além disso, seu sistema deverá salvar os dados do usuário logado, para que não seja necessário fazer a autenticação na próxima vez que ele abrir o app.

### Listagem dos dados

Seu aplicativo deverá possuir telas que permitam:
* Listar os dados gerais do aplicativo
* Exibir em detalhes um item selecionado pelo usuário na lista
* A possibilidade de realizar uma ação sobre cada item da lista

<!-- É importante ressaltar que o procedimento para carregamento dos dados deverá acontecer em uma thread diferente da principal. -->

### Acessibilidade & Usabilidade

Sua aplicação deverá suportar pelo menos duas línguas distintas.

Além disso, você deverá dar a possibilidade do usuário escolhar um tema de interface da aplicação (Ex.: Tema claro ou Tema escuro).

Seu grupo deverá implementar a tela da listagem visando dois dispositivos: Telefone e Tablet.
No layout de Tablet, a listagem deverá aparecer ao lado de um painel de detalhes; já no layout de telefone, o painel de detalhes deverá tomar todo espaço da tela. Veja essa [imagem](https://developer.android.com/images/guide/fragments/fragment-screen-sizes.png) para mais detalhes.

### Dados

Você poderá escolher entre recuperar os dados a partir de um serviço REST existente, ou de um banco de dados local. 
**Não precisa suportar as duas fontes de dados!**
Caso opte pelo armazenamento local, um schema de banco de dados deverá ser constrúido, e suas tabelas previamente preenchidas com dados para teste.

Além disso, você precisará criar classes de modelo que representem os dados que seu aplicativo irá exibir (ex.: classe `Livro` para uma lista de livros, classe `Filme` para uma lista de filmes, classe `Usuario` para representar o usuário logado, etc).

Por fim, as classes desenvolvidas neste módulo de dados deverão estar, majoritariamente, presentes em um módulo externo de seu projeto.

### Versões do aplicativo

Você deverá disponibilizar duas versões distintas para seu aplicativo: demo e completa. A versão demo deverá impedir o acesso a pelo menos um recurso presente no aplicativo.

<!-- ## Integração com o trabalho de PDS

Opcionalmente, você poderá intergrar esse trabalho com o trabalho final da disciplina de Princípios de Desenvolvimento de Software.
Para que essa integração seja válida, você deverá:

* Implementar testes unitários para verificar o funcionamento/comportamento das classes, métodos e funções auxiliares utilizadas para desenvolver o aplicativo
<!-- * Implemente um [proxy](https://engsoftmoderna.info/cap6.html) para manter um cache dos itens da listagem, válido enquanto o aplicativo estiver aberto. -->
<!-- * Implemente um [singleton](https://engsoftmoderna.info/cap6.html) que irá controlar a conexão com o serviço ou o banco de dados -->
<!-- * Organize a arquitetura da sua aplicação utilizando o padrão de camadas: Uma camada de interface (Activities), uma camada de negócios (classes intermediárias), e uma camada de modelo (classes de modelo presentes no módulo externo) -->

## Entrega & Apresentação

O trabalho deverá ser **entregue até o dia 07/07/2023**, no formato de pull request a este repositório.

A apresentação do trabalho **acontecerá no dia 07/07/2023**, e terá duração de 25 minutos.
O trabalho deverá ser apresentado no formato de pitch, contemplando os seguintes pontos:

- Problema abordado;
- Proposta de solução para o problema;
- Como funciona o aplicativo;
- Principais dificuldades encontradas;
- O que faltou fazer ou o que se pretende fazer para o futuro;

Além do cumprimento aos requisitos, serão avaliados a participação dos integrantes no desenvolvimento e apresentação do trabalho.
**O trabalho é grande, então distribuam as tarefas!!**