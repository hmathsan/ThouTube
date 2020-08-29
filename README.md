# PT-BR
# ThouTube

ThouTube é uma API clone do YouTube (Thou é um trocadilho com You do inglês) construida com Spring Boot.

Eu imaginei ThouTube tendo dois tipos de publicações, os Videos e os Posts. Bem como o YouTube, onde canais podem criar tanto videos como Posts da Comunidade (representado simplesmente por Posts no ThouTube), ambos possuem suas respectivas listas de comentários e também likes

A API tem uma lista básica de funções de uma Rede Social, algumas delas são: 

- Criar e visualizar uma lista de usuários (tomando os devidos cuidados com a privacidade da senha).
- Criar e visualizar uma lista de videos e posts.
- Criar e visualizar uma lista de comentários.
- Adicionar likes a posts e videos.
- Atualizar a senha do usuário (Usando a criptografia BCrypt do Spring).
- Atualizar títulos e o testo de posts e vídeos.

## AWS S3

ThouTube usa o AWS S3 para armazenar arquivos. Você pode armazenar videos de imagens (usada em imagens de perfil e thumbnail de videos).
O endpoint irá retornar uma url do arquivo.

## Autenticação

A API usa Bearer Token para sua autenticação de acesso. Apenas alguns endpoints GET são liberados para uso sem autenticação.

## Documentação

A API está totalmente no ar usando Heroku no link https://thoutube.herokuapp.com/ 
Toda [a documentação pode ser encontrada aqui](https://thoutube.herokuapp.com/swagger-ui.html).

#

# EN

ThouTube is a YouTube API clone (hence Thou and You pun) built with Spring Boot.

I've imagined ThouTube to have two types of posts, the Videos and Posts, just like YouTube, where a channel can create both a Video and a Community Post (represented simply by Post in ThouTube), both with a list of comments and likes.

The API has a basic list of features for a Social Network, some of them are:

- Create and Visualize a list of users (I've taken the care to keep the password private).
- Create and Visualize a list of posts an videos.
- Create and Visualize a list of Comments.
- Add likes to Posts and Videos.
- Update a user password (Using Spring's BCrypt encryption).
- Update Posts and Videos titles and text.

## AWS S3

ThouTube uses AWS S3 for file storing. You can store videos and images (for a profile picture and video thumbnails), and the endpoint will return a full URL of the file.

## Authentication

The API uses Bearer Token authentication. Only some GET endpoints are available without authentication.

## Documentation

ThouTube is up and running on Heroku at https://thoutube.herokuapp.com/ . 
You can find the [full documentation here](https://thoutube.herokuapp.com/swagger-ui.html).