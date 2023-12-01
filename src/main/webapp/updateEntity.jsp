<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.clientcrud.controller.*, com.clientcrud.domain.*, com.clientcrud.domain.enums.*, java.util.*, java.time.format.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	ReadAllController readAll = new ReadAllController();
	ReadController read = new ReadController();
	
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

	// get params
	String entity = request.getParameter("entity");
	String clientId = request.getParameter("clientId");
	String id = request.getParameter("id");
	
	Client client = new Client();
	client.setId(Integer.parseInt(id));
	
	CreditCard creditCard = new CreditCard();
	creditCard.setId(Integer.parseInt(id));
	
	Address address = new Address();
	address.setId(Integer.parseInt(id));
	
	DomainEntity clientInfo = read.exec(client);
	request.setAttribute("clientInfo", clientInfo);
	
	DomainEntity cardInfo = read.exec(creditCard);
	request.setAttribute("cardInfo", cardInfo);
	
	DomainEntity addressInfo = read.exec(address);
	request.setAttribute("addressInfo", addressInfo);
	
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
	if (entity.equals(client.getClass().getName())) {
		%>
		<title>CRUD - Clientes</title>
		<%
	}
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
	if (entity.equals(client.getClass().getName())) {
		%>
		<form method="post" action="processUpdateEntity.jsp?entity=${clientInfo.getClass().getName()}&id=${clientInfo.getId()}" 
			class="form active" id="form1">
		<a href="javascript:history.back()" class="form-back">Voltar</a>
        <h1 class="title">Alteração de Clientes</h1>
        <h3 class="section-title">Informações Pessoais</h3>
        <div class="form-row">
          <div class="input-wrapper">
            <label for="name">Nome</label>
            <input type="text" id="name" name="name" placeholder="Digite seu nome" class="input" required value="${clientInfo.getName()}">
          </div>
          <div class="input-wrapper">
            <label for="cpf">CPF</label>
            <input type="text" id="cpf" name="cpf" placeholder="Digite seu CPF" class="input" required value="${clientInfo.getCpf()}">
          </div>
        </div>

        <div class="form-row">
          <div class="input-wrapper">
            <label for="birth-date">Data de Nascimento</label>
            <input type="date" id="birth-date" name="birth-date" class="input" required value="${clientInfo.getBirthDate()}">
          </div>
          <div class="input-wrapper">
            <label for="email">E-mail</label>
            <input type="email" id="email" name="email" placeholder="Digite seu e-mail" class="input" required value="${clientInfo.getEmail()}">
          </div>
        </div>

        <div class="form-row">
          <div class="input-wrapper">
            <label for="password">Senha</label>
            <input type="password" id="password" name="password" placeholder="Digite sua senha" class="input" required value="${clientInfo.getPassword()}">
          </div>
          <div class="input-wrapper">
            <label for="password-confirm">Confirmar Senha</label>
            <input type="password" id="password-confirm" name="password-confirm" placeholder="Digite sua senha novamente" class="input" required value="${clientInfo.getPassword()}">
          </div>
        </div>

        <div class="form-row">
          <div class="input-wrapper-horizontal">
            <div class="input-wrapper">
              <label for="ddd">DDD</label>
              <input type="text" id="ddd" name="ddd" placeholder="Digite o seu DDD" class="input" required value="${clientInfo.getPhone().getDdd()}">
            </div>
            <div class="input-wrapper">
              <label for="phone">Telefone</label>
              <input type="text" id="phone" name="phone" placeholder="Digite seu telefone" class="input" required value="${clientInfo.getPhone().getNumber()}">
            </div>
          </div>
          <div class="input-wrapper">
            <label for="phone-type">Tipo de Telefone</label>
            <select name="phone-type" id="phone-type" class="input">
              <c:forEach items="${phoneTypes}" var="phoneType">
              	<c:choose>
				    <c:when test="${phoneType.getName() eq clientInfo.getPhone().getPhoneType().getName()}">
				        <option value="${phoneType.getId()}" selected>${phoneType.getName()}</option>
				    </c:when>
				    <c:otherwise>
				        <option value="${phoneType.getId()}">${phoneType.getName()}</option>
				    </c:otherwise>
				</c:choose>
              </c:forEach>
            </select>
          </div>
        </div>

        <div class="input-wrapper">
          <label>Gênero</label>
          <div class="input-wrapper-radio">
          	<c:choose>
			    <c:when test="${'MASCULINO' eq clientInfo.getGender().getName()}">
			        <input type="radio" id="male" name="gender" value="1" checked>
			    </c:when>
			    <c:otherwise>
			        <input type="radio" id="male" name="gender" value="1">
			    </c:otherwise>
			</c:choose>
            <label for="male">Masculino</label>
          </div>
          <div class="input-wrapper-radio">
          	<c:choose>
			    <c:when test="${'FEMININO' eq clientInfo.getGender().getName()}">
			        <input type="radio" id="female" name="gender" value="2" checked>
			    </c:when>
			    <c:otherwise>
			        <input type="radio" id="female" name="gender" value="2">
			    </c:otherwise>
			</c:choose>          
            <label for="female">Feminino</label>
          </div>
          <div class="input-wrapper-radio">
          	<c:choose>
			    <c:when test="${'NAO_INFORMADO' eq clientInfo.getGender().getName()}">
			        <input type="radio" id="uninformed" name="gender" value="3" checked>
			    </c:when>
			    <c:otherwise>
			        <input type="radio" id="uninformed" name="gender" value="3">
			    </c:otherwise>
			</c:choose>
            <label for="uninformed">Prefiro não informar</label>
          </div>
        </div>

        <button class="button">Salvar</button>
      </form>
		<%
	}
	if (entity.equals(creditCard.getClass().getName())) {
		%>
		<form method="post" action="processUpdateEntity.jsp?entity=${cardInfo.getClass().getName()}&clientId=<%=clientId%>&id=${cardInfo.getId()}" 
			class="form active" id="form2">
	    <a href="javascript:history.back()" class="form-back">Voltar</a>
        <h1 class="title">Alteração de Cartões</h1>
        <h3 class="section-title">Cartão de Crédito</h3>

        <div class="form-row">
          <div class="input-wrapper">
            <label for="card-number">Número Cartão</label>
            <input type="text" id="card-number" name="card-number" placeholder="Digite número do cartão" class="input" maxlength="16" required value="${cardInfo.getNumber()}">
          </div>
          <div class="input-wrapper">
            <label for="holder-name">Nome Impresso</label>
            <input type="text" id="holder-name" name="holder-name" placeholder="Digite o nome impresso no cartão" class="input" required value="${cardInfo.getHolderName()}">
          </div>
        </div>

        <div class="form-row">
          <div class="input-wrapper">
            <label for="card-flag">Bandeira do cartão</label>
            <select name="card-flag" id="card-flag" class="input">
              <c:forEach items="${cardFlags}" var="cardFlag">
	              <c:choose>
				    <c:when test="${cardFlag.getName() eq cardInfo.getCardFlag().getName()}">
				        <option value="${cardFlag.getId()}" selected>${cardFlag.getName().toString().replace('_', ' ')}</option>
				    </c:when>
				    <c:otherwise>
				        <option value="${cardFlag.getId()}">${cardFlag.getName().toString().replace('_', ' ')}</option>
				    </c:otherwise>
			  	  </c:choose>   
              </c:forEach>
            </select>
          </div>
          <div class="input-wrapper">
            <label for="cvv">Código de segurança</label>
            <input type="text" id="cvv" name="cvv" placeholder="Digite o código de segurança" class="input" maxlength="3" required value="${cardInfo.getCvv()}">
          </div>
        </div>

        <button class="button">Salvar</button>
	  </form>
	<%
	}
	if (entity.equals(address.getClass().getName())) {
		%>
		<form method="post" 
			action="processUpdateEntity.jsp?entity=${addressInfo.getClass().getName()}&clientId=<%=clientId%>&id=${addressInfo.getId()}&addressType=${addressInfo.getAddressType()}"
			 class="form active" id="form3">
	    <a href="javascript:history.back()" class="form-back">Voltar</a>
        <h1 class="title">Alteração de Endereços</h1>
        <h3 class="section-title">Endereço</h3>
        
        <div class="form-row">
          <div class="input-wrapper">
            <label for="name-address">Nome do Endereço</label>
            <input type="text" id="name-address" name="name-address" placeholder="Digite o nome do endereço" class="input" required value="${addressInfo.getName()}">
          </div>
        </div>

        <div class="form-row">
          <div class="input-wrapper">
            <label for="cep-address">CEP</label>
            <input type="text" id="cep-address" name="cep-address" placeholder="Digite o CEP" class="input" required value="${addressInfo.getCep()}">
          </div>
          <div class="input-wrapper">
            <label for="public-place-address">Logradouro</label>
            <input type="text" id="public-place-address" name="public-place-address" placeholder="Digite o logradouro" class="input" required value="${addressInfo.getPublicPlace()}">
          </div>
        </div>

        <div class="form-row">
          <div class="input-wrapper">
            <label for="number-address">Número</label>
            <input type="number" id="number-address" name="number-address" placeholder="Digite o número" class="input" required value="${addressInfo.getNumber()}">
          </div>
          <div class="input-wrapper">
            <label for="neighborhood-">Bairro</label>
            <input type="text" id="neighborhood-address" name="neighborhood-address" placeholder="Digite o bairro" class="input" required value="${addressInfo.getNeighborhood()}">
          </div>
        </div>

        <div class="form-row">
          <div class="input-wrapper">
            <label for="country-address">País</label>
            <select name="country-address" id="country-address" class="input">
              <c:forEach items="${countries}" var="country">
              	  <c:choose>
				    <c:when test="${country.getName() eq addressInfo.getCity().getState().getCountry().getName()}">
				        <option value="${country.getId()}" selected>${country.getName()}</option>
				    </c:when>
				    <c:otherwise>
				        <option value="${country.getId()}">${country.getName()}</option>
				    </c:otherwise>
			  	  </c:choose>
              </c:forEach>
            </select>

          </div>
          <div class="input-wrapper">
            <label for="state-address">Estado</label>
            <select name="state-address" id="state-address" class="input">
              <c:forEach items="${states}" var="state">
              	  <c:choose>
				    <c:when test="${state.getName() eq addressInfo.getCity().getState().getName()}">
				        <option aria-country-id="${state.getCountry().getId()}" value="${state.getId()}" selected>${state.getName()}</option>
				    </c:when>
				    <c:otherwise>
				        <option aria-country-id="${state.getCountry().getId()}" value="${state.getId()}">${state.getName()}</option>
				    </c:otherwise>
			  	  </c:choose>
              </c:forEach>
            </select>
          </div>
        </div>

        <div class="form-row">
          <div class="input-wrapper">
            <label for="city-address">Cidade</label>
            <select name="city-address" id="city-address" class="input">
              <c:forEach items="${cities}" var="city">
                  <c:choose>
				    <c:when test="${city.getName() eq addressInfo.getCity().getName()}">
				        <option aria-state-id="${city.getState().getId()}" value="${city.getId()}" selected>${city.getName()}</option>
				    </c:when>
				    <c:otherwise>
				        <option aria-state-id="${city.getState().getId()}" value="${city.getId()}">${city.getName()}</option>
				    </c:otherwise>
			  	  </c:choose>
              </c:forEach>
            </select>
          </div>
          <div class="input-wrapper">
            <label for="public-place-type-address">Tipo de logradouro</label>
            <select name="public-place-type-address" id="public-place-type-address" class="input">
              <c:forEach items="${publicPlaceTypes}" var="publicPlaceType">
              	  <c:choose>
				    <c:when test="${publicPlaceType.getName() eq addressInfo.getPublicPlaceType().getName()}">
				        <option value="${publicPlaceType.getId()}" selected>${publicPlaceType.getName()}</option>
				    </c:when>
				    <c:otherwise>
				        <option value="${publicPlaceType.getId()}">${publicPlaceType.getName()}</option>
				    </c:otherwise>
			  	  </c:choose>
              </c:forEach>
            </select>
          </div>
        </div>

        <div class="form-row">
          <div class="input-wrapper">
            <label for="residence-type-address">Tipo de Residência</label>
            <select name="residence-type-address" id="residence-type-address" class="input">
              <c:forEach items="${residenceTypes}" var="residenceType">
              	  <c:choose>
				    <c:when test="${residenceType.getName() eq addressInfo.getResidenceType().getName()}">
				        <option value="${residenceType.getId()}" selected>${residenceType.getName()}</option>
				    </c:when>
				    <c:otherwise>
				        <option value="${residenceType.getId()}">${residenceType.getName()}</option>
				    </c:otherwise>
			  	  </c:choose>
              </c:forEach>
            </select>
          </div>
          <div class="input-wrapper">
            <label for="notes-address">Observações</label>
            <input type="text" id="notes-address" name="notes-address" placeholder="Digite as observações" class="input" value="${addressInfo.getNotes()}">
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