# Almoxarifado-CRP
## Descrição
Um sistema feito para o trabalho final de da disciplina de Desenvolvimento de Software para WEB e para gerenciar o almoxarifado da Comunidade Regina Pacis.

O sistema é simples e consiste no cadastro de produtos e entradas e saídas dos produtos no almoxarifado. O sistema também tem um gerenciamento simples de usuários e avisa para o usuário quando algum produto está perto do prazo de validade (30 dias).

## Execução
Para executar o sistema, você deve incluir ele em um projeto no eclipse com maven e depois executar no postgres o script para criar o banco que está na pasta src>main>resources>sqlBanco. O script cria um usuário com login: "admin" e senha: "admin".

A compilação deve ser feita com java 8, executando apenas a classe App.
