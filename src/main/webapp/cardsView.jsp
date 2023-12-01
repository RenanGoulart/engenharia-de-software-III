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
  <title>CRUD - Cartões</title>
</head>
<%@ page import="com.clientcrud.controller.*, com.clientcrud.domain.*, com.clientcrud.domain.enums.*, java.util.*, java.time.format.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%

	String clientId = request.getParameter("id");
		
	// cards listing
	ReadAllController readAll = new ReadAllController();
	CreditCard creditCard = new CreditCard();
	creditCard.setId(Integer.parseInt(clientId));
	
	List<DomainEntity> cards = readAll.exec(creditCard);
	
	request.setAttribute("cards", cards);
%>

<body>
  <main class="container">
    <section class="section">
      <a style="left: 2rem; right: auto;" class="button registerButton" href="clientsView.jsp">Voltar</a>
      <h1 class="title">Consultar Cartões</h1>
      <a class="button registerButton" 
      	href="createEntity.jsp?entity=<%=creditCard.getClass().getName()%>&clientId=<%=clientId%>">Cadastrar Cartões</a>

      <table class="table">
        <tr>
          <th>Número</th>
          <th>Nome Impresso</th>
          <th>Bandeira</th>
          <th>CVC</th>
          <th>Principal</th>
          <th>Editar</th>
          <th>Excluir</th>
        </tr>
        <c:forEach items="${cards}" var="card">
	        <tr>
	          <td>${card.getNumber()}</td>
	          <td>${card.getHolderName()}</td>
	          <td>${card.getCardFlag().getName()}</td>
	          <td>${card.getCvv()}</td>
	          <td>${card.getIsMain() ? 'Sim' : 'Não'}</td>
	          <td><a href="updateEntity.jsp?entity=${card.getClass().getName()}&clientId=<%=clientId%>&id=${card.getId()}" 
	          class="detail-button" style="background-color: #edb03e;">Editar</a></td>
	          <td>
	          	<a href="deleteEntity.jsp?entity=${card.getClass().getName()}&clientId=<%=clientId%>&id=${card.getId()}"  
	          	class="detail-button" style="background-color: #eb5649;">Excluir</a>
	          </td>
	        </tr>
        </c:forEach>
      </table>      
  	</section>
  </main>
</body>

</html>