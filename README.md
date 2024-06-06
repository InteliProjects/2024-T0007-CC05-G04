<table>
<tr>
<td>
<a href= "https://vale.com/pt/"><img src="https://upload.wikimedia.org/wikipedia/pt/c/cc/Logotipo_Vale.svg" alt="Vale" border="0" width="60%"></a>
</td>
<td><a href= "https://www.inteli.edu.br/"><img src="./inteli-logo.png" alt="Inteli - Instituto de Tecnologia e Liderança" border="0" width="40%"></a>
</td>
</tr>
</table>

# Introdução

&emsp;Este é um dos repositórios do projeto de alunos do Inteli em parceria com a Vale no 1º semestre de 2024. Este projeto está sendo desenvolvido por alunos do Módulo 5 do curso de Ciência da Computação. 

# Projeto: Aumento da performance logística na distribuição de minério de ferro

# Grupo: *JaVale*

# Integrantes:

* <a href="https://www.linkedin.com/in/pedro-bannwart/">Ana Luisa Goes Barbosa</a>
* <a href="https://www.linkedin.com/in/jo%C3%A3o-hirata-085456279/">João Cauê Hirata</a>
* <a href="https://www.linkedin.com/in/jo%C3%A3o-paulo-santos-872753264/">João Paulo Santos</a>
* <a href="https://www.linkedin.com/in/jo%C3%A3o-paulo-da-silva-a45229215/">João Paulo Silva</a>
* <a href="https://www.linkedin.com/in/lucas-nogueira-nunes/">Lucas Nogueira Nunes</a>
* <a href="https://www.linkedin.com/in/pedro-bannwart/">Pedro Morita Bannwart</a>

# Descrição

&emsp;O problema em questão diz respeito a otimização do fluxo de minérios nas rotas de transporte da Vale, que atualmente não é tão eficiente e possui um tempo de execução do software muito elevado, levando assim a geração lenta de relatórios e um atraso nos processos da empresa que dependem desses resultados.

&emsp;Com isso, visa se desenvolver uma solução avançada para otimizar o fluxo de minérios na malha sudeste da Vale, envolvendo a criação de algoritmos sofisticados para aprimorar a análise, gestão dos dados e tempo de execução. O projeto também inclui o desenvolvimento de uma plataforma web intuitiva para a visualização e interação eficiente com os resultados otimizados.

# Configuração de desenvolvimento

&emsp;*Procedimento para baixar e executar o código deste projeto.*<br>
&emsp;Primeiramente, serão necessárias as ferramentas abaixo:
* <a href="https://git-scm.com/">Git</a><br>
* <a href="https://code.visualstudio.com/">VsCode</a><br>
* <a href="https://www.anaconda.com/anaconda-navigator">Anaconda Navigator</a><br>

&emsp;Antes de iniciar, converta o XML que deseja processar para o formato JSON.

&emsp;Depois, inicie o GitBash no diretório desejado, e execute o seguinte comando no mesmo: <br>
&emsp;```git clone https://github.com/2024M5T7-Inteli/g4.git```

&emsp;Coloque o arquivo JSON que você gerou na pasta g4/codigo/analise_dados

&emsp;Em seguida, abra o Anaconda Navigator e,utilizando o *VsCode* por ele, acesse o diretório g4/codigo/analise_dados e abra o arquivo processamento.ipynb, e execute esse código

&emsp;Por fim, os csv's serão gerados em uma pasta "csv" no diretório em que o código foi executado

&emsp;Após essas etapas, abra o arquivo tratamento.ipynb e coloque as origens de cada csv de acordo com as rotas para os diretorios desses respectivos csv's, que foram gerados anteriormente

&emsp;Será gerado um novo csv único, que deverá ser colocado em g4/codigo/JaVale/src/main/Repository

&emsp;Também será necessário preparar outros 2 arquivos, um .env criado a partir de um bloco de notas com o seguinte comando contido: <br>
&emsp;```VITE_APP_URL=http://localhost:8080```<br>
&emsp;Este deverá ser colocado em g4/codigo/frontend-vite/javale

&emsp;E o outro arquivo será um .ini nomeado "application.properties", gerado a partir de um bloco de notas com o seguinte comando contido: <br>
&emsp;```cors.urls=http://localhost:5173```<br>
&emsp;Este deverá ser colocado em g4/codigo/JaVale/src/main/resources

&emsp;Abra o terminal do *VsCode* e execute o comando: <br>
&emsp;```npm install```

&emsp;Prosseguindo, para executar o front-end, basta executar o comando no terminal: <br>
&emsp;```npm run dev``` <br>

&emsp;E por fim, dar "run" no VsCode no arquivo JaValeApplication.java

&emsp;Assim, ele já poderá abrir a aplicação em um navegador web e utilizá-la!


# Releases

* SPRINT1: *Documentação de negócios, UX e início do artigo*
* SPRINT2: *Modelagem matemática do problema e criação do repositório*
* SPRINT3: *Desenvolvimento do primeiro algoritmo clássico*
* SPRINT4: *Seleção de algoritmos e desenvolvimento do front-end*
* SPRINT5: *Detalhes finais do front-end e revisão da documentação*

## 📋 Licença/License

<img style="height:22px!important;margin-left:3px;vertical-align:text-bottom;" src="https://mirrors.creativecommons.org/presskit/icons/cc.svg?ref=chooser-v1"><img style="height:22px!important;margin-left:3px;vertical-align:text-bottom;" src="https://mirrors.creativecommons.org/presskit/icons/by.svg?ref=chooser-v1"><p xmlns:cc="http://creativecommons.org/ns#" xmlns:dct="http://purl.org/dc/terms/">

<a property="dct:title" rel="cc:attributionURL">JaVale</a> by <a rel="cc:attributionURL dct:creator" property="cc:attributionName">Inteli, Ana Luisa Goes Barbosa, João Cauê Hirata, João Paulo Santos, João Paulo Silva, Lucas Nogueira Nunes, Pedro Morita Bannwart</a> is licensed under <a href="https://creativecommons.org/licenses/by/4.0/?ref=chooser-v1" rel="license noopener noreferrer" style="display:inline-block;">Attribution 4.0 International</a>.</p>
