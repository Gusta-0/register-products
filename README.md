# Respostas – Simulado

1.  POO é o ato de programar com foco em resolver problemas do mundo real. Agora não se trata apenas de 0 ou 1, e sim de representar uma entidade do mundo real através do código. Isso facilita a organização do sistema e a reutilização de código, expandindo assim as possibilidades de transformar ideias em soluções através da programação.

2. Esse conceito explica a possibilidade de manter classes, métodos e atributos encapsulados e protegidos. Esse é um conceito muito utilizado na programação, pois o objeto encapsulado torna-se inacessível para quem tenta acessá-lo fora do contexto em que ele foi criado.

3. Princípio SOLID violado: Single Responsibility Principle (SRP), pois a entidade está exposta e pode acabar assumindo responsabilidades que não são dela.

4. Porque mantém a entidade protegida. Muitas vezes essa entidade tem forte ligação com o banco de dados, o que a torna ainda mais delicada. Logo, essa entidade não pode ser manipulada ou acessada diretamente sem as devidas precauções.

5. private String nomeProtegido;

6. Spring Boot é um dos principais módulos do Spring Framework. Ele foi criado basicamente para facilitar o desenvolvimento de aplicações, como APIs RESTful. Agora o desenvolvedor passa menos tempo configurando ambientes e configurações e passa a se dedicar mais ao projeto, tornando essa a sua principal vantagem. Além disso, facilita a integração tanto com outros módulos do framework quanto com tecnologias externas mais atualizadas.

7. @RestController. Essas anotações são autodescritivas, o que deixa o código mais claro e fácil de entender.

8. Essa anotação serve para definir qual URL aquele método pode ser chamado, além de permitir e definir o recebimento de parâmetros.

9. O método que recebe uma requisição do tipo GET está fazendo mais de uma função: recebendo a requisição e manipulando o repositório. Esse código quebra o primeiro conceito do SOLID, onde cada classe deve ter apenas uma única responsabilidade. O correto seria o método listar receber apenas a requisição e deixar a regra de negócio para a classe de serviço, deixando o código mais organizado, limpo e escalável.

10. GET – Pega informações de algum local
    POST – Envia dados em algum formato (ex: JSON) para algum lugar (ex: banco de dados, api)
    PUT – Serve para atualizar totalmente alguma informação (ex: formulário completo)
    DELETE – Apaga alguma informação

11. JPA (Java Persistence API) é um dos módulos do Spring Framework responsável pela manipulação de dados. Já o Hibernate é uma implementação do JPA. Ele facilita as consultas, evitando a escrita de SQL nativo, trazendo mais segurança, agilidade e melhor manutenção das consultas.

12. @Repository

13. @Id define o campo como chave primária no banco, e @GeneratedValue define a estratégia de geração do ID (ex: UUID ou auto incremento).

14. Funções:
            1. Faz a comunicação com o banco de dados
            2. Manipula os dados da entidade Product, como salvar, listar, atualizar e deletar

15) Pois, ao implementar o JPA, o desenvolvedor tem acesso a diversas consultas prontas, trazendo mais segurança, agilidade e produtividade. Além disso, evita erros comuns das consultas SQL nativas.

16. DTO (Data Transfer Object) serve como uma cópia da classe ou entidade original. A entidade muitas vezes possui informações sensíveis que não devem ou não precisam ser acessadas externamente. O DTO é responsável pela comunicação externa e pela conversão de dados, resolvendo o problema de deixar a entidade original exposta e vulnerável.

17. Essa abordagem é incorreta, pois o método que lida com requisição do tipo POST está fazendo mais de uma função: recebendo a requisição e manipulando o repositório. Esse código quebra o primeiro conceito do SOLID. O correto seria o método salvar receber apenas a requisição externa, enquanto a classe de serviço ficaria responsável pela manipulação do repositório, deixando o código mais organizado, limpo e escalável.

18. A camada de serviço é responsável por lidar com toda a regra de negócio da aplicação, manipular o repositório e aplicar tratamentos e exceções específicas do projeto.

19. A camada de serviço é responsável por lidar com toda a regra de negócio da aplicação, manipular o repositório e aplicar tratamentos e exceções específicas do projeto.

20. Porque cada camada deve ter a sua própria responsabilidade: o controller lida com requisições e o repository faz a comunicação com o banco de dados.

21. Cada parte do sistema deve lidar com apenas uma função. Exemplo: o controller lida com requisições e o repository faz a comunicação com o banco de dados.

22. Single Responsibility Principle (SRP).

23. Esse conceito do SOLID explica que uma classe deve estar aberta para extensão e fechada para modificação. Ou seja, devemos conseguir adicionar novos comportamentos sem precisar alterar o código já existente, evitando quebrar funcionalidades que já funcionam.

24. O Liskov Substitution Principle diz que uma classe filha deve poder substituir a classe pai sem quebrar o funcionamento do sistema. Quem usa a classe base não deve perceber diferença ao usar a classe derivada.

25. O Interface Segregation Principle diz que uma classe não deve ser obrigada a implementar métodos que ela não utiliza. É melhor ter várias interfaces menores e específicas do que uma interface grande e genérica.

26. Princípio SOLID violado: Interface Segregation Principle.

27. O Dependency Inversion Principle (DIP) diz que classes de alto nível não devem depender de classes de baixo nível, mas sim de abstrações. Isso reduz o acoplamento e torna o sistema mais flexível e fácil de manter.

28. Utilizar interfaces em vez de depender diretamente de implementações concretas é uma prática alinhada ao DIP.

29. O fluxo de uma requisição em uma API Spring Boot começa no controller, que recebe a requisição. Em seguida, o controller chama a camada de serviço, onde fica a regra de negócio. A service então acessa o repository, que se comunica com o banco de dados. Após isso, a resposta retorna pelo mesmo caminho até o cliente.

30. DTO, Service e SOLID tornam o sistema mais fácil de manter e evoluir porque cada parte tem uma responsabilidade bem definida. O DTO protege a entidade, a service centraliza a regra de negócio e os princípios SOLID reduzem o acoplamento, facilitando testes, manutenção e evolução do sistema sem grandes impactos.


