<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.clientcrud.controller.*, com.clientcrud.domain.*, com.clientcrud.domain.enums.*, java.util.*, java.time.format.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	ReadAllController readAll = new ReadAllController();
	
	List<DomainEntity> phoneTypes = readAll.exec(new PhoneType());
	request.setAttribute("phoneTypes", phoneTypes);
	
	List<DomainEntity> cardFlags = readAll.exec(new CardFlag());
	request.setAttribute("cardFlags", cardFlags);
	
	List<DomainEntity> countries = readAll.exec(new Country());
	request.setAttribute("countries", countries);
	List<DomainEntity> states = readAll.exec(new State());
	request.setAttribute("states", states);
	List<DomainEntity> cities = readAll.exec(new City());
	request.setAttribute("cities", cities);
	List<DomainEntity> publicPlaceTypes = readAll.exec(new PublicPlaceType());
	request.setAttribute("publicPlaceTypes", publicPlaceTypes);
	List<DomainEntity> residenceTypes = readAll.exec(new ResidenceType());
	request.setAttribute("residenceTypes", residenceTypes);
		
	String entity = request.getParameter("entity");
	String clientId = request.getParameter("clientId");
		
	CreditCard creditCard = new CreditCard();
	creditCard.setId(Integer.parseInt(clientId));
	
	Address address = new Address();
	address.setId(Integer.parseInt(clientId));
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;700&display=swap" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="styles/styles.css">
	<link rel="stylesheet" type="text/css" href="styles/registerInfo.css">

<%
	if (entity.equals(creditCard.getClass().getName())) {
		%>
		<title>CRUD - Cartões</title>
		<%
	}
	if (entity.equals(address.getClass().getName())) {
		%>
		<title>CRUD - Endereços</title>
		<%
	}
%>
</head>
<body>
  <main class="container">
<%
	if (entity.equals(creditCard.getClass().getName())) {
		%>
		<form method="post" action="processCreateEntity.jsp?entity=<%=creditCard.getClass().getName()%>&clientId=<%=clientId%>" 
			class="form active" id="form2">
	    <a href="javascript:history.back()" class="form-back">Voltar</a>
        <h1 class="title">Alteração de Cartões</h1>
        <h3 class="section-title">Cartão de Crédito</h3>

        <div class="form-row">
          <div class="input-wrapper">
            <label for="card-number">Número Cartão</label>
            <input type="text" id="card-number" name="card-number" placeholder="Digite número do cartão" class="input" maxlength="16" required>
          </div>
          <div class="input-wrapper">
            <label for="holder-name">Nome Impresso</label>
            <input type="text" id="holder-name" name="holder-name" placeholder="Digite o nome impresso no cartão" class="input" required>
          </div>
        </div>

        <div class="form-row">
          <div class="input-wrapper">
            <label for="card-flag">Bandeira do cartão</label>
            <select name="card-flag" id="card-flag" class="input">
              <c:forEach items="${cardFlags}" var="cardFlag">
				  <option value="${cardFlag.getId()}">${cardFlag.getName().toString().replace('_', ' ')}</option>
              </c:forEach>
            </select>
          </div>
          <div class="input-wrapper">
            <label for="cvv">Código de segurança</label>
            <input type="text" id="cvv" name="cvv" placeholder="Digite o código de segurança" class="input" maxlength="3" required>
          </div>
        </div>

        <button class="button">Salvar</button>
	  </form>
	<%
	}
	if (entity.equals(address.getClass().getName())) {
		%>
		<form method="post" 
			action="processCreateEntity.jsp?entity=<%=address.getClass().getName()%>&clientId=<%=clientId%>"
			 class="form active" id="form3">
	    <a href="javascript:history.back()" class="form-back">Voltar</a>
        <h1 class="title">Alteração de Endereços</h1>
        <h3 class="section-title">Endereço</h3>
        
        <div class="form-row">
          <div class="input-wrapper">
            <label for="name-address">Nome do Endereço</label>
            <input type="text" id="name-address" name="name-address" placeholder="Digite o nome do endereço" class="input" required>
          </div>
          <div class="input-wrapper">
          	  <label for="address-type-address">Tipo Endereço</label>
	          <select name="address-type-address" id="address-type-address" class="input">
				  <option value="1">ENTREGA</option>
				  <option value="2">COBRANCA</option>
	           </select>
           </div>
        </div>

        <div class="form-row">
          <div class="input-wrapper">
            <label for="cep-address">CEP</label>
            <input type="text" id="cep-address" name="cep-address" placeholder="Digite o CEP" class="input" required>
          </div>
          <div class="input-wrapper">
            <label for="public-place-address">Logradouro</label>
            <input type="text" id="public-place-address" name="public-place-address" placeholder="Digite o logradouro" class="input" required>
          </div>
        </div>

        <div class="form-row">
          <div class="input-wrapper">
            <label for="number-address">Número</label>
            <input type="number" id="number-address" name="number-address" placeholder="Digite o número" class="input" required>
          </div>
          <div class="input-wrapper">
            <label for="neighborhood-">Bairro</label>
            <input type="text" id="neighborhood-address" name="neighborhood-address" placeholder="Digite o bairro" class="input" required>
          </div>
        </div>

        <div class="form-row">
          <div class="input-wrapper">
            <label for="country-address">País</label>
            <select name="country-address" id="country-address" class="input">
              <c:forEach items="${countries}" var="country">
				  <option value="${country.getId()}">${country.getName()}</option>
              </c:forEach>
            </select>

          </div>
          <div class="input-wrapper">
            <label for="state-address">Estado</label>
            <select name="state-address" id="state-address" class="input">
              <c:forEach items="${states}" var="state">
				  <option aria-country-id="${state.getCountry().getId()}" value="${state.getId()}">${state.getName()}</option>
              </c:forEach>
            </select>
          </div>
        </div>

        <div class="form-row">
          <div class="input-wrapper">
            <label for="city-address">Cidade</label>
            <select name="city-address" id="city-address" class="input">
              <c:forEach items="${cities}" var="city">
				    <option aria-state-id="${city.getState().getId()}" value="${city.getId()}">${city.getName()}</option>
              </c:forEach>
            </select>
          </div>
          <div class="input-wrapper">
            <label for="public-place-type-address">Tipo de logradouro</label>
            <select name="public-place-type-address" id="public-place-type-address" class="input">
              <c:forEach items="${publicPlaceTypes}" var="publicPlaceType">
                <option value="${publicPlaceType.getId()}">${publicPlaceType.getName()}</option>
              </c:forEach>
            </select>
          </div>
        </div>

        <div class="form-row">
          <div class="input-wrapper">
            <label for="residence-type-address">Tipo de Residência</label>
            <select name="residence-type-address" id="residence-type-address" class="input">
              <c:forEach items="${residenceTypes}" var="residenceType">
				  <option value="${residenceType.getId()}">${residenceType.getName()}</option>
              </c:forEach>
            </select>
          </div>
          <div class="input-wrapper">
            <label for="notes-address">Observações</label>
            <input type="text" id="notes-address" name="notes-address" placeholder="Digite as observações" class="input">
          </div>
        </div>
        <button class="button">Salvar</button>
	  </form>
	<%
	}
%>
  </main>
</body>
<script>
	//aplicar para os outros selects de endereço de cobrança (cidade, estado, país)
	const $countriesSelect = document.querySelector('#country-address');
	const $statesSelect = document.querySelector('#state-address');
	const $citiesSelect = document.querySelector('#city-address');
	
	const $allStates = [...$statesSelect.children];
	const $allCities = [...$citiesSelect.children];
	
	const getCitiesByState = () => {
		  const $newOptions = $allCities.filter((option) => option.getAttribute('aria-state-id') == $statesSelect.value);
		  $citiesSelect.innerHTML = '';
		  
		  $newOptions.forEach(newOption => $citiesSelect.appendChild(newOption));
	}
	
	getCitiesByState();  
	$statesSelect.addEventListener('change', getCitiesByState);
	
	const getStatesByCountry = () => {
		  const $newOptions = $allStates.filter((option) => option.getAttribute('aria-country-id') == $countriesSelect.value);
		  $statesSelect.innerHTML = '';
		  
		  $newOptions.forEach(newOption => $statesSelect.appendChild(newOption));
		  getCitiesByState();
	} 
	
	getStatesByCountry();  
	$countriesSelect.addEventListener('change', getStatesByCountry);  
</script>
</html>