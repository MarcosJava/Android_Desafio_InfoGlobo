<h1> Desafio Info Globo </h1>

Desafio para a infoGlobo, um app em android que carrega uma lista de JSON da API TheMovies. <br>
Para a requisição foi usado metodos assincronos para imagem e metodos sincronos para dados.

<h3>O que contém no app</h3>
Na lista de imagem, pullDown (movimento de swipe para baixo) que atualiza os dados.<br>
Na lista de imagem, tambem pode atualizar os dados utilizando um menu setting. <br>

Para realizar a lista foi usado RecyclerView ao inves de ListView. <br>
Tem um loading quando carrega os dados da API.<br>
Tem loading assincrono na imagem.<br>
Usando framework GSON , para deserializar String Json em Objeto.

<h3>TESTE </h3>
Tem uma cobertura de 100% de metodos e classes, e 90% de linhas (a outras 10% são para erros inexperados/não tratados).<br>
Cobertura dos testes foram em cima da Classe Manager(Classe de Regra de Negocio).<br>
Teste de view garantindo que as view estão funcionando.<br>

<h3>Arquitetura </h3>
<b>Models -- </b> Modelos do projeto, so existe Filme.<br>
<b>DTOs -- </b> Modelos de dados apenas para trafegar informações.<br>
<b>Manager -- </b> Regras de Negocios de acordo ao Modelo.<br>
<b>Service -- </b> Toda requisição que utilize Servicos como WebService(SOAP) ou Rest.<br>
<b>Default -- </b> Activities, controles das interfaces<br>

<h3>Observações do Desafio </h3>
<ul>
  <li>Não foi usado nenhum framework para captura de dados JSON, mas tenho noção de como utiliza um framework. </li>
  <li>Para requisições tenho experiencia com o framework Spring Template Rest Android </li>
  <li>Para garantir que sei adicionar framework, usei GSON apenas para deserializar String Json em Objeto.</li>
  <li>O desafio esta implementado em MVC Designer, mas também tenho experiencia em MVVM utilizando binding, reactiveX e outros (RxAndroid).</li>
  <li>Garantia de cobertura em cima da regra de negocio (100% coberto)</li>
  <li>ServiceMock utilizado nos testes para não ficar utilizando requisição em testes unitarios</li>
  <li>Interface está usando padrões de content e todo Material Designer da Google.</li>
</ul>

