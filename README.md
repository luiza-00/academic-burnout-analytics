# Manipulação de Dados com Collections e Serviços

![Static Badge](https://img.shields.io/badge/JAVA-orange)

# 🔍 Índice 

* [Introdução](#introducao)
* [Etapas do Projeto](#etapas-do-projeto)
  * [Etapa 1: Estrutura das Collections](#etapa1)
  * [Etapa 2: Importação de Dados](#etapa2)
  * [Etapa 3: Operações de CRUD](#etapa3)
  * [Etapa 4: Pesquisas](#etapa4)
* [Requisitos Gerais](#requisitos)
* [Tecnologias Utilizadas](#tecnologias-utilizadas)
* [Autores](#autores)

<div id='introducao'/>

# 🚀 Introdução
O projeto presente neste repositório trata-se de uma aplicação para manipulação de dados com collections e serviços com base no dataset "[AI Depndency, Career Anxiety and Student Burnout](https://www.kaggle.com/datasets/sridipbasu/ai-depndency-career-anxiety-and-student-burnout)", extraído da plataforma Kaggle, criado para fins avaliativos da disciplina de Tópicos Avançados em Banco de Dados, pertencente ao curso de Bacharelado em Engenharia de Software da Universidade Católica do Salvador, Salvador-BA.

<div id='etapas-do-projeto'/>

# 📌 Etapas do projeto

<div id='etapas1'/>
 
## Etapa 1: Estrutura das Collections
Selecionamos o dataset "AI Depndency, Career Anxiety and Student Burnout" no site Kaggle e importamos seus dados em 6 (seis) collections, sendo elas: 
  - student_id
  - age
  - gender
  - urban_or_rural
  - ai_dependency_score
  - burnout_score

 <div id='etapas2'/>
  
## Etapa 2: Importação de Dados
Desenvolvemos serviços/métodos responsáveis pela importação dos dados do dataset. A importação do arquivo CSV foi realizada utilizando Threads, permitindo que o processo possa ser executado a qualquer momento pelo usuário.

<div id='etapas3'/>  
 
## Etapa 3: Operações de CRUD
Implementamos serviços/métodos para:
  - Cadastrar itens individualmente;
  - Alterar registros;
  - Excluir registros;
  - Localizar itens individualmente.

 <div id='etapas4'/>
  
## Etapa 4: Pesquisas
Criamos métodos/serviços de pesquisa utilizando diferentes parâmetros para localização de documentos. As consultas permitem múltiplos critérios de busca.

<div id='requisitos'/>
 
# 📋 Requisitos Gerais:
- Importação de dados via CSV utilizando Threads;
- Cadastro, alteração, exclusão e localização de registros individuais (CRUD);
- Pesquisas utilizando múltiplos parâmetros;
- Controle de relacionamento entre collections para garantir integridade dos dados; e
- Os dados são validados para que não sejam permitidas inserções com dados inválidos.

<div id='tecnologias-utilizadas'/>
 
# 💻 Tecnologias utilizadas
## IDE's
* [`Visual Studio Code`](https://code.visualstudio.com/download)
* [`Eclipse`](https://eclipseide.org/)
## Linguagem
* [`Java`](https://www.java.com/pt-br/)

<div id='autores'/>
 
# ✒️ Autores
[![Linkedin Badge](https://img.shields.io/badge/-Guilherme_Mota-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/guilhermemotasilva/)](https://www.linkedin.com/in/guilhermemotasilva/) <br/>
[![Linkedin Badge](https://img.shields.io/badge/-Luiza_Gabriella_dos_S._Santos-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/luizagabriella/)](https://www.linkedin.com/in/luizagabriella/) <br/>
[![Linkedin Badge](https://img.shields.io/badge/-Maria_Eduarda_C._Miranda-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/meducmiranda/)](https://www.linkedin.com/in/meducmiranda/) <br/>
[![Linkedin Badge](https://img.shields.io/badge/-Thársyla_Jones-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/tharsyla-jones/)](https://www.linkedin.com/in/tharsyla-jones/)
