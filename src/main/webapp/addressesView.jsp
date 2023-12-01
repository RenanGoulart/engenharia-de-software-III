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
  <title>CRUD - Endereços</title>
</head>
<%@ page import="com.clientcrud.controller.*, com.clientcrud.domain.*, com.clientcrud.domain.enums.*, java.util.*, java.time.format.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%

	String clientId = request.getParameter("id");
		
	// cards listing
	ReadAllController readAll = new ReadAllController();
	Address address = new Address();
	address.setId(Integer.parseInt(clientId));
	
	List<DomainEntity> addresses = readAll.exec(address);
	
	
	request.setAttribute("addresses", addresses);
%>

<body>
  <main class="container">
    <section class="section">
      <a style="left: 2rem; right: auto;" class="button registerButton" href="clientsView.jsp">Voltar</a>
      <h1 class="title">Consultar Endereços</h1>
      <a class="button registerButton" 
      	href="createEntity.jsp?entity=<%=address.getClass().getName()%>&clientId=<%=clientId%>">Cadastrar Endereços</a>

      <table class="table">
        <tr>
          <th>CEP</th>
          <th>Logradouro</th>
          <th>Nº</th>
          <th>Bairro</th>
          <th>Cidade</th>
          <th>Estado</th>
          <th>País</th>
          <th>Observações</th>
          <th>Tipo Logradouro</th>
          <th>Tipo Residência</th>
          <th>Tipo Endereço</th>
          <th>Editar</th>
          <th>Excluir</th>
        </tr>
        <c:forEach items="${addresses}" var="address">
	        <tr>
	          <td>${address.getCep()}</td>
	          <td>${address.getPublicPlace()}</td>
	          <td>${address.getNumber()}</td>
	          <td>${address.getNeighborhood()}</td>
	          <td>${address.getCity().getName()}</td>
	          <td>${address.getCity().getState().getUf()}</td>
	          <td>${address.getCity().getState().getCountry().getName()}</td>
	          <td>${address.getNotes()}</td>
	          <td>${address.getPublicPlaceType().getName()}</td>
	          <td>${address.getResidenceType().getName()}</td>
	          <td>${address.getAddressType()}</td>
	          <td><a href="updateEntity.jsp?entity=${address.getClass().getName()}&clientId=<%=clientId%>&id=${address.getId()}" 
	          class="detail-button" style="background-color: #edb03e;">Editar</a></td>
	          <td>
	          	<a href="deleteEntity.jsp?entity=${address.getClass().getName()}&clientId=<%=clientId%>&id=${address.getId()}" 
	          	class="detail-button" style="background-color: #eb5649;">Excluir</a>
	          </td>
	        </tr>
        </c:forEach>
      </table>      
  	</section>
  </main>
</body>

</html>