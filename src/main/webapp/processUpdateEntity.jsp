<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="com.clientcrud.controller.*, com.clientcrud.domain.*, com.clientcrud.domain.enums.*, java.util.*, java.time.*, java.time.format.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	String entity = request.getParameter("entity");
	String clientId = request.getParameter("clientId");
	String id = request.getParameter("id");
	String addressType = request.getParameter("addressType");
	
	Client updatedClient = new Client();
	CreditCard updatedCreditCard = new CreditCard();
	Address updatedAddress = new Address();
	
	updatedClient.setId(Integer.parseInt(id));
	updatedCreditCard.setId(Integer.parseInt(id));
	updatedAddress.setId(Integer.parseInt(id));
	
	
	if (request.getMethod().equalsIgnoreCase("post")) {
	    // personal data
	    String name = request.getParameter("name");
		String cpf = request.getParameter("cpf");
		String birthDate = request.getParameter("birth-date");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String ddd = request.getParameter("ddd");
		
		// phone
		String phoneNumber = request.getParameter("phone");
		String phoneType = request.getParameter("phone-type");
		String gender = request.getParameter("gender");
		
		// credit card
		String cardNumber = request.getParameter("card-number");
		String holderName = request.getParameter("holder-name");
		String cardFlag = request.getParameter("card-flag");
		String cvv = request.getParameter("cvv");
		
		// billing address
		String nameAddress = request.getParameter("name-address");
		String cepAddress = request.getParameter("cep-address");
		String publicPlaceAddress = request.getParameter("public-place-address");
		String numberAddress = request.getParameter("number-address");
		String neighborhoodAddress = request.getParameter("neighborhood-address");
		String cityAddress = request.getParameter("city-address");
		String publicPlaceTypeAddress = request.getParameter("public-place-type-address");
		String residenceTypeAddress = request.getParameter("residence-type-address");
		String notesAddress = request.getParameter("notes-address");
		
		UpdateController update = new UpdateController();
		
		if (entity.equals(updatedClient.getClass().getName())) {
			Phone updatedPhone = new Phone(ddd, phoneNumber, new PhoneType(EnumPhoneType.fromValue(Integer.parseInt(phoneType))));

			LocalDate formattedBirthDate = birthDate.isEmpty() ? LocalDate.now() : LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			
			updatedClient.setName(name);
			updatedClient.setBirthDate(formattedBirthDate);
			updatedClient.setCpf(cpf);
			updatedClient.setEmail(email);
			updatedClient.setPassword(password);
			updatedClient.setGender(new Gender(EnumGender.fromValue(Integer.parseInt(gender))));
			updatedClient.setClientStatus(new ClientStatus(EnumClientStatus.fromValue(1)));
			updatedClient.setPhone(updatedPhone);
			
			update.exec(updatedClient);
			%>
			<script>window.location.href="clientsView.jsp"</script>
			<%
		}
		if (entity.equals(updatedCreditCard.getClass().getName())){
			updatedCreditCard.setNumber(cardNumber);
			updatedCreditCard.setHolderName(holderName);
			updatedCreditCard.setCvv(cvv);
			updatedCreditCard.setIsMain(true);
			updatedCreditCard.setCardFlag(new CardFlag(EnumCardFlag.fromValue(Integer.parseInt(cardFlag))));
			
			update.exec(updatedCreditCard);
			%>
			<script>window.location.href="cardsView.jsp?id=<%=clientId%>"</script>
			<%
		}
		if (entity.equals(updatedAddress.getClass().getName())) {
			updatedAddress.setName(nameAddress);
			updatedAddress.setPublicPlace(publicPlaceAddress);
			updatedAddress.setNumber(numberAddress);
			updatedAddress.setNeighborhood(neighborhoodAddress);
			updatedAddress.setCep(cepAddress);
			updatedAddress.setNotes(notesAddress);
			updatedAddress.setCity(new City(Integer.parseInt(cityAddress)));
			updatedAddress.setPublicPlaceType(new PublicPlaceType(EnumPublicPlaceType.fromValue(Integer.parseInt(publicPlaceTypeAddress))));
			updatedAddress.setResidenceType(new ResidenceType(EnumResidenceType.fromValue(Integer.parseInt(residenceTypeAddress))));
			updatedAddress.setAddressType(EnumAddressType.valueOf(addressType));
			
			update.exec(updatedAddress);
			%>
			<script>window.location.href="addressesView.jsp?id=<%=clientId%>"</script>
			<%
		}
	}
	
	
	
%>