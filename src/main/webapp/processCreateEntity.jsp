<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="com.clientcrud.controller.*, com.clientcrud.domain.*, com.clientcrud.domain.enums.*, java.util.*, java.time.*, java.time.format.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	String entity = request.getParameter("entity");
	String clientId = request.getParameter("clientId");	
	
	CreditCard createdCreditCard = new CreditCard();
	Address createdAddress = new Address();
	
	createdCreditCard.setId(Integer.parseInt(clientId));
	createdAddress.setId(Integer.parseInt(clientId));
	
	
	if (request.getMethod().equalsIgnoreCase("post")) {	
		// credit card
		String cardNumber = request.getParameter("card-number");
		String holderName = request.getParameter("holder-name");
		String cardFlag = request.getParameter("card-flag");
		String cvv = request.getParameter("cvv");
		
		// billing address
		String nameAddress = request.getParameter("name-address");
		String addressType = request.getParameter("address-type-address");
		String cepAddress = request.getParameter("cep-address");
		String publicPlaceAddress = request.getParameter("public-place-address");
		String numberAddress = request.getParameter("number-address");
		String neighborhoodAddress = request.getParameter("neighborhood-address");
		String cityAddress = request.getParameter("city-address");
		String publicPlaceTypeAddress = request.getParameter("public-place-type-address");
		String residenceTypeAddress = request.getParameter("residence-type-address");
		String notesAddress = request.getParameter("notes-address");
		
		CreateController create = new CreateController();
		
		if (entity.equals(createdCreditCard.getClass().getName())){
			createdCreditCard.setNumber(cardNumber);
			createdCreditCard.setHolderName(holderName);
			createdCreditCard.setCvv(cvv);
			createdCreditCard.setIsMain(true);
			createdCreditCard.setCardFlag(new CardFlag(EnumCardFlag.fromValue(Integer.parseInt(cardFlag))));
			
			create.exec(createdCreditCard);
			%>
			<script>window.location.href="cardsView.jsp?id=<%=clientId%>"</script>
			<%
		}
		if (entity.equals(createdAddress.getClass().getName())) {
			createdAddress.setName(nameAddress);
			createdAddress.setPublicPlace(publicPlaceAddress);
			createdAddress.setNumber(numberAddress);
			createdAddress.setNeighborhood(neighborhoodAddress);
			createdAddress.setCep(cepAddress);
			createdAddress.setNotes(notesAddress);
			createdAddress.setCity(new City(Integer.parseInt(cityAddress)));
			createdAddress.setPublicPlaceType(new PublicPlaceType(EnumPublicPlaceType.fromValue(Integer.parseInt(publicPlaceTypeAddress))));
			createdAddress.setResidenceType(new ResidenceType(EnumResidenceType.fromValue(Integer.parseInt(residenceTypeAddress))));
			createdAddress.setAddressType(EnumAddressType.fromValue(Integer.parseInt(addressType)));
			
			create.exec(createdAddress);
			%>
			<script>window.location.href="addressesView.jsp?id=<%=clientId%>"</script>
			<%
		}
	}
	
	
	
%>