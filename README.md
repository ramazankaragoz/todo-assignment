## Todo Assignment

### Table of contents
* [Kullanılan Teknolojiler](#kullanlan-teknolojiler)
* [Setup](#setup)

### Kullanılan Teknolojiler
* Java 11
* Spring Boot
* h2 database
* RabbitMQ
* Docker
* Junit 5
* Spring Doc Open Api Ui
* Lombok
* Spring security JWT and Spring security oauth2 authorization server
* Audit Log is used for entity log

### Setup

 projeyi çalıştırmak için ana dizinde .\mvnw clean install yapıp jarları oluşturduktan sonra docker compose up ile uygulamayı çalıştırabilirsiniz.
*
 http://localhost:8080/gateway/swagger-ui.html ile gateway erişip kullanıcı oluşturabilirsiniz.
*
 http://localhost:8080/gateway/login
```
{
    "email": "ramazan.karagoz@gmail.com",
    "password": "123123"
}
```
request body olacak şekilde post request atıp header dan bearer token alabilirsiniz.Örnek postman collection proje dizininde bulabilirsiniz.
*
 Rest kullanımları swagger da example da gösterilmektedir.
*
Todo filter için entity field yapısı ile filtreleme geliştirdim.örnek olarak;
```
@Entity
public class Todo {
    private Long groupId;
    private String todoName;
    private Date dueDate;
    private StatusEnum status;
    private PriorityEnum priority;
```
```
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
  } 
```