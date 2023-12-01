<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;700&display=swap" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="styles/styles.css">
  <title>CRUD - Clientes</title>
</head>
<%@ page import="com.clientcrud.controller.*, com.clientcrud.domain.*, com.clientcrud.domain.enums.*, java.util.*, java.time.format.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	// clients listing
	ReadAllController readAll = new ReadAllController();
	
	List<DomainEntity> clients = readAll.exec(new Client());
	request.setAttribute("clients", clients);
%>

<body>
  <main class="container">
    <section class="section">
      <h1 class="title">Consultar Clientes</h1>
      <a class="button registerButton" href="formClientsView.jsp">Cadastrar Clientes</a>
      <!-- <input type="text" id="phone" name="phone" placeholder="Pesquise cliente" class="input"> -->

      <table class="table">
        <tr>
          <th>Nome</th>
          <th>CPF</th>
          <th>Data de Nascimento</th>
          <th>E-mail</th>
          <th>Telefone</th>
          <th>Genêro</th>
          <th>Status</th>
          <th>Cartões</th>
          <th>Endereços</th>
          <th>Editar</th>
          <th>Excluir</th>
        </tr>
        <c:forEach items="${clients}" var="client">
	        <tr>
	          <td>${client.getName()}</td>
	          <td>${client.getCpf()}</td>
	          <td>${client.getBirthDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))}</td>
	          <td>${client.getEmail()}</td>
	          <td>(${client.getPhone().getDdd()}) ${client.getPhone().getNumber()}</td>
	          <td>${client.getGender().getName()}</td>
	          <td>${client.getClientStatus().getName()}</td>
	          <td><a href="cardsView.jsp?id=${client.getId()}" class="detail-button">Detalhar</a></td>
	          <td><a href="addressesView.jsp?id=${client.getId()}" class="detail-button">Detalhar</a></td>
	          <td><a href="updateEntity.jsp?entity=${client.getClass().getName()}&id=${client.getId()}" 
	          class="detail-button" style="background-color: #edb03e;">Editar</a></td>
	          <td><a href="deleteEntity.jsp?entity=${client.getClass().getName()}&id=${client.getId()}"  
	          class="detail-button" style="background-color: #eb5649;">Excluir</a></td>
	        </tr>
        </c:forEach>
      </table>      
  	</section>
  </main>
</body>

</html>