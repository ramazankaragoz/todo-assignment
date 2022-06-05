# todo-assignment
# projeyi çalıştırmak için ana dizinde .\mvnw clean install yapıp jarları oluşturduktan sonra docker compose up ile uygulamayı çalıştırabilirsiniz.
# http://localhost:8080/gateway/swagger-ui.html ile gateway erişip kullanıcı oluşturabilirsiniz.
# http://localhost:8080/gateway/login request body {
    "email": "ramazan.karagoz@gmail.com",
    "password": "123123"
} olacak şekilde post request atıp header dan bearer token alabilirsiniz.Örnek postman collection proje dizininde bulabilirsiniz.
# Rest kullanımları swagger da example da gösterilmektedir.Todo filter için field yapısı ile filtreleme geliştirdim.örnek olarak
{
    "field": "priority",
    "operation": "EQUALS",
    "value": "HIGH"
  },
  {
    "field": "groupId",
    "operation": "EQUALS",
    "value": "1"
  },
  {
    "field": "dueDate",
    "operation": "EQUALS",
    "value": "2022-06-05"
  } bu şekilde.

