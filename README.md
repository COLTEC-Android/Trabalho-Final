# Trabalho Final: Aplicativo Mobile

**Professor João Eduardo Montandon**

**Núm de integrantes: 2**

## Visão Geral

O objetivo deste trabalho será desenvolver um aplicativo utilizando todos os conceitos aprendidos nas disciplinas de Programação Android e Tópicos em Desenvolvimento Android.
Em linhas gerais, seu aplicativo deverá exibir uma lista de informações (lugares, livros, filmes, etc---vai depender do tema do seu app).
Essas informações poderão ser consultadas em detalhes em uma outra tela, caso o usuário selecione o item da lista.
Além disso, o usuário poderá favoritar os itens da lista que julgar mais interessantes; esses itens favoritos deverão ser exibidos em uma listagem específica.
Por fim, o usuário poderá compartilhar qualquer item de ambas as listas com outros usuários.

## Requisitos

Mais Especificamente, seu aplicativo deverá cumprir os seguintes requisitos:

### Listagem dos dados

Seu aplicativo deverá possuir telas que permitam:
* Listar os dados gerais do aplicativo
* Exibir em detalhes um item selecionado pelo usuário na lista
* A possibilidade de favoritar itens da lista

É importante ressaltar que o procedimento para carregamento dos dados deverá acontecer em uma thread diferente da principal.

### Acessibilidade & Usabilidade

Sua aplicação deverá suportar pelo menos duas línguas distintas.

Além disso, você deverá dar a possibilidade do usuário escolhar um tema de interface da aplicação (Ex.: Tema claro ou Tema escuro).

Por fim, você deverá implementar a tela da listagem visando dois dispositivos: Telefone e Tablet.
No layout de Tablet, a listagem deverá aparecer ao lado de um painel de detalhes; já no layout de telefone, o painel de detalhes deverá tomar todo espaço da tela. Veja essa [imagem](https://developer.android.com/images/guide/fragments/fragment-screen-sizes.png) para mais detalhes.

### Dados

Você poderá escolher entre recuperar os dados a partir de um serviço REST existente, ou de um banco de dados local. **Você não precisa suportar as duas fontes de dados!**
Caso opte pelo armazenamento local, um schema de banco de dados deverá ser constrúido, e suas tabelas previamente preenchidas com dados para teste.

Além disso, você precisará criar classes de modelo que representem os dados que seu aplicativo irá exibir (ex.: classe `Livro` para uma lista de livros, classe `Filme` para uma lista de filmes, etc).

Por fim, as classes desenvolvidas neste módulo de dados deverão estar, majoritariamente, presentes em um módulo externo de seu projeto.

### Versões do aplicativo

Você deverá disponibilizar duas versões distintas para seu aplicativo:
* Demo: não permitirá favoritar os itens da lista, nem seu compartilhamento
* Completa: todas as funcionalidades estão disponíveis


## Integração com o trabalho de PDS

Opcionalmente, você poderá intergrar esse trabalho com o trabalho final da disciplina de Princípios de Desenvolvimento de Software.
Para que essa integração seja válida, você deverá:

* Implementar testes unitários para verificar o funcionamento/comportamento das classes, métodos e funções auxiliares utilizadas para desenvolver o aplicativo
* Implemente um [proxy](https://engsoftmoderna.info/cap6.html) para manter um cache dos itens da listagem, válido enquanto o aplicativo estiver aberto.
* Implemente um [singleton](https://engsoftmoderna.info/cap6.html) que irá controlar a conexão com o serviço ou o banco de dados
* Organize a arquitetura da sua aplicação utilizando o padrão de camadas: Uma camada de interface (Activities), uma camada de negócios (classes intermediárias), e uma camada de modelo (classes de modelo presentes no módulo externo)

## Entrega & Apresentação

O trabalho deverá ser **entregue até o dia 29/08/2021**, no formato de pull request a este repositório.

A apresentação do trabalho **acontecerá no dia 30/08/2021, a partir das 18hrs**, e terá duração de 30 minutos; nesse tempo os alunos irão fazer uma demonstração de funcionamento do aplicativo e explicar as principais decisões de implementação tomadas no desenvolvimento do aplicativo.
Além disso, o professor irá poderá fazer perguntas a respeito dos requisitos implementados.

A ordem de apresentação será definida por um sorteio prévio, realizado pelo professor.