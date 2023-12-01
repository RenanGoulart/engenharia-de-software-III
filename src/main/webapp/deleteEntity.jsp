<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="com.clientcrud.controller.*, com.clientcrud.domain.*, com.clientcrud.domain.enums.*, java.util.*, java.time.format.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	String entity = request.getParameter("entity");
	String clientId = request.getParameter("clientId");
	String id = request.getParameter("id");
	
	Client client = new Client();
	CreditCard creditCard = new CreditCard();
	Address address = new Address();
	
	client.setId(Integer.parseInt(id));
	creditCard.setId(Integer.parseInt(id));
	address.setId(Integer.parseInt(id));
	
	List<Address> clientAddresses = new ArrayList<>();
	clientAddresses.add(address);
	
	client.setCreditCard(creditCard);
	client.setAddresses(clientAddresses);
	
	DeleteController delete = new DeleteController();
	
	if (entity.equals(client.getClass().getName())) {
		delete.exec(client);
		%>
		<script>window.location.href="clientsView.jsp"</script>
		<%
	}
	if (entity.equals(creditCard.getClass().getName())){
		delete.exec(creditCard);
		%>
		<script>window.location.href="cardsView.jsp?id=<%=clientId%>"</script>
		<%
	}
	if (entity.equals(address.getClass().getName())) {
		delete.exec(address);
		%>
		<script>window.location.href="addressesView.jsp?id=<%=clientId%>"</script>
		<%
	}
	
%>