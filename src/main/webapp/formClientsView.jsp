<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;700&display=swap" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="styles/styles.css">
  <link rel="stylesheet" type="text/css" href="styles/registerInfo.css">
  <title>CRUD - Clientes</title>
</head>
<%@ page import="com.clientcrud.controller.*, com.clientcrud.domain.*, com.clientcrud.domain.enums.*, java.util.*, java.time.*, java.time.format.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	// dropdown lists
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
%>

<%
	// process forms
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
		String nameBilling = request.getParameter("name-billing");
		String cepBilling = request.getParameter("cep-billing");
		String publicPlaceBilling = request.getParameter("public-place-billing");
		String numberBilling = request.getParameter("number-billing");
		String neighborhoodBilling = request.getParameter("neighborhood-billing");
		String cityBilling = request.getParameter("city-billing");
		String publicPlaceTypeBilling = request.getParameter("public-place-type-billing");
		String residenceTypeBilling = request.getParameter("residence-type-billing");
		String notesBilling = request.getParameter("notes-billing");
		
		// delivery address
		String nameDelivery = request.getParameter("name-delivery");
		String cepDelivery = request.getParameter("cep-delivery");
		String publicPlaceDelivery = request.getParameter("public-place-delivery");
		String numberDelivery = request.getParameter("number-delivery");
		String neighborhoodDelivery = request.getParameter("neighborhood-delivery");
		String cityDelivery = request.getParameter("city-delivery");
		String publicPlaceTypeDelivery = request.getParameter("public-place-type-delivery");
		String residenceTypeDelivery = request.getParameter("residence-type-delivery");
		String notesDelivery = request.getParameter("notes-delivery");
		
		// instances
		Address billingAddress = new Address(nameBilling, publicPlaceBilling, numberBilling, neighborhoodBilling, 
				cepBilling, notesBilling, new City(Integer.parseInt(cityBilling)), 
				new PublicPlaceType(EnumPublicPlaceType.fromValue(Integer.parseInt(publicPlaceTypeBilling))), 
				new ResidenceType(EnumResidenceType.fromValue(Integer.parseInt(residenceTypeBilling))),
				EnumAddressType.COBRANCA);
		
		Address deliveryAddress = new Address(nameDelivery, publicPlaceDelivery, numberDelivery, neighborhoodDelivery, 
				cepDelivery, notesDelivery, new City(Integer.parseInt(cityDelivery)), 
				new PublicPlaceType(EnumPublicPlaceType.fromValue(Integer.parseInt(publicPlaceTypeDelivery))), 
				new ResidenceType(EnumResidenceType.fromValue(Integer.parseInt(residenceTypeDelivery))),
				EnumAddressType.ENTREGA);
		
		CreditCard creditCard = new CreditCard(cardNumber, holderName, cvv, true, 
				new CardFlag(EnumCardFlag.fromValue(Integer.parseInt(cardFlag))));
		
		Phone phone = new Phone(ddd, phoneNumber, new PhoneType(EnumPhoneType.fromValue(Integer.parseInt(phoneType))));
		
		
		LocalDate formattedBirthDate = birthDate.isEmpty() ? LocalDate.now() : LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		List<Address> addresses = new ArrayList<>();
		addresses.add(billingAddress);
		addresses.add(deliveryAddress);
		
		
		Client client = new Client(name, formattedBirthDate, cpf, email, password, 
				new Gender(EnumGender.fromValue(Integer.parseInt(gender))), 
				new ClientStatus(EnumClientStatus.fromValue(1)),
				phone, creditCard, addresses);
		
		CreateController create = new CreateController();
		String result = create.exec(client);

		%>
		<script>window.location.href="clientsView.jsp"</script>
		<%
	}
%>


<body>
  <main class="container">
    <form method="post">
      <div class="form active" id="form1">
        <h1 class="title">Cadastro de Clientes</h1>
        <h3 class="section-title">Informações Pessoais</h3>
        <div class="form-row">
          <div class="input-wrapper">
            <label for="name">Nome</label>
            <input type="text" id="name" name="name" placeholder="Digite seu nome" class="input" required>
          </div>
          <div class="input-wrapper">
            <label for="cpf">CPF</label>
            <input type="text" id="cpf" name="cpf" placeholder="Digite seu CPF" class="input" required>
          </div>
        </div>

        <div class="form-row">
          <div class="input-wrapper">
            <label for="birth-date">Data de Nascimento</label>
            <input type="date" id="birth-date" name="birth-date" class="input" required>
          </div>
          <div class="input-wrapper">
            <label for="email">E-mail</label>
            <input type="email" id="email" name="email" placeholder="Digite seu e-mail" class="input" required>
          </div>
        </div>

        <div class="form-row">
          <div class="input-wrapper">
            <label for="password">Senha</label>
            <input type="password" id="password" name="password" placeholder="Digite sua senha" class="input" required>
          </div>
          <div class="input-wrapper">
            <label for="password-confirm">Confirmar Senha</label>
            <input type="password" id="password-confirm" name="password-confirm" placeholder="Digite sua senha novamente" class="input" required>
          </div>
        </div>

        <div class="form-row">
          <div class="input-wrapper-horizontal">
            <div class="input-wrapper">
              <label for="ddd">DDD</label>
              <input type="text" id="ddd" name="ddd" placeholder="Digite o seu DDD" class="input" required>
            </div>
            <div class="input-wrapper">
              <label for="phone">Telefone</label>
              <input type="text" id="phone" name="phone" placeholder="Digite seu telefone" class="input" required>
            </div>
          </div>
          <div class="input-wrapper">
            <label for="phone-type">Tipo de Telefone</label>
            <select name="phone-type" id="phone-type" class="input">
              <c:forEach items="${phoneTypes}" var="phoneType">
                <option value="${phoneType.getId()}">${phoneType.getName()}</option>
              </c:forEach>
            </select>
          </div>
        </div>

        <div class="input-wrapper">
          <label>Gênero</label>
          <div class="input-wrapper-radio">
            <input type="radio" id="male" name="gender" value="1">
            <label for="male">Masculino</label>
          </div>
          <div class="input-wrapper-radio">
            <input type="radio" id="female" name="gender" value="2">
            <label for="female">Feminino</label>
          </div>
          <div class="input-wrapper-radio">
            <input type="radio" id="uninformed" name="gender" value="3" checked>
            <label for="uninformed">Prefiro não informar</label>
          </div>
        </div>

        <button class="button">Continuar</button>
      </div>

      <div class="form" id="form2">
        <button class="form-back">Voltar</button>
        <h1 class="title">Cadastro de Cartões</h1>
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

        <button class="button">Continuar</button>
      </div>

      <div class="form" id="form3">
        <button class="form-back">Voltar</button>
        <h1 class="title">Cadastro de Endereços</h1>
        <h3 class="section-title">Endereço de Cobrança</h3>
        
        <div class="form-row">
          <div class="input-wrapper">
            <label for="name-billing">Nome do Endereço</label>
            <input type="text" id="name-billing" name="name-billing" placeholder="Digite o nome do endereço" class="input" required>
          </div>
        </div>

        <div class="form-row">
          <div class="input-wrapper">
            <label for="cep-billing">CEP</label>
            <input type="text" id="cep-billing" name="cep-billing" placeholder="Digite o CEP" class="input" required>
          </div>
          <div class="input-wrapper">
            <label for="public-place-billing">Logradouro</label>
            <input type="text" id="public-place-billing" name="public-place-billing" placeholder="Digite o logradouro" class="input" required>
          </div>
        </div>

        <div class="form-row">
          <div class="input-wrapper">
            <label for="number-billing">Número</label>
            <input type="number" id="number-billing" name="number-billing" placeholder="Digite o número" class="input" required>
          </div>
          <div class="input-wrapper">
            <label for="neighborhood-billing">Bairro</label>
            <input type="text" id="neighborhood-billing" name="neighborhood-billing" placeholder="Digite o bairro" class="input" required>
          </div>
        </div>

        <div class="form-row">
          <div class="input-wrapper">
            <label for="country-billing">País</label>
            <select name="country-billing" id="country-billing" class="input">
              <c:forEach items="${countries}" var="country">
                <option value="${country.getId()}">${country.getName()}</option>
              </c:forEach>
            </select>

          </div>
          <div class="input-wrapper">
            <label for="state-billing">Estado</label>
            <select name="state-billing" id="state-billing" class="input">
              <c:forEach items="${states}" var="state">
                <option aria-billing-country-id="${state.getCountry().getId()}" value="${state.getId()}">${state.getName()}</option>
              </c:forEach>
            </select>
          </div>
        </div>

        <div class="form-row">
          <div class="input-wrapper">
            <label for="city-billing">Cidade</label>
            <select name="city-billing" id="city-billing" class="input">
              <c:forEach items="${cities}" var="city">
                <option aria-billing-state-id="${city.getState().getId()}" value="${city.getId()}">${city.getName()}</option>
              </c:forEach>
            </select>
          </div>
          <div class="input-wrapper">
            <label for="public-place-type-billing">Tipo de logradouro</label>
            <select name="public-place-type-billing" id="public-place-type-billing" class="input">
              <c:forEach items="${publicPlaceTypes}" var="publicPlaceType">
                <option value="${publicPlaceType.getId()}">${publicPlaceType.getName()}</option>
              </c:forEach>
            </select>
          </div>
        </div>

        <div class="form-row">
          <div class="input-wrapper">
            <label for="residence-type-billing">Tipo de Residência</label>
            <select name="residence-type-billing" id="residence-type-billing" class="input">
              <c:forEach items="${residenceTypes}" var="residenceType">
                <option value="${residenceType.getId()}">${residenceType.getName()}</option>
              </c:forEach>
            </select>
          </div>
          <div class="input-wrapper">
            <label for="notes-billing">Observações</label>
            <input type="text" id="notes-billing" name="notes-billing" placeholder="Digite as observações" class="input">
          </div>
        </div>

        <button class="button">Continuar</button>
      </div>

      <div class="form" id="form4">
        <button class="form-back">Voltar</button>
        <h1 class="title">Cadastro de Endereços</h1>
        <h3 class="section-title">Endereço de Entrega</h3>
        
        <div class="form-row">
          <div class="input-wrapper">
            <label for="name-delivery">Nome do Endereço</label>
            <input type="text" id="name-delivery" name="name-delivery" placeholder="Digite o nome do endereço" class="input" required>
          </div>
        </div>

        <div class="form-row">
          <div class="input-wrapper">
            <label for="cep-delivery">CEP</label>
            <input type="text" id="cep-delivery" name="cep-delivery" placeholder="Digite o CEP" class="input" required>
          </div>
          <div class="input-wrapper">
            <label for="public-place-delivery">Logradouro</label>
            <input type="text" id="public-place-delivery" name="public-place-delivery" placeholder="Digite o logradouro" class="input" required>
          </div>
        </div>

        <div class="form-row">
          <div class="input-wrapper">
            <label for="number-delivery">Número</label>
            <input type="number" id="number-delivery" name="number-delivery" placeholder="Digite o número" class="input" required>
          </div>
          <div class="input-wrapper">
            <label for="neighborhood-delivery">Bairro</label>
            <input type="text" id="neighborhood-delivery" name="neighborhood-delivery" placeholder="Digite o bairro" class="input" required>
          </div>
        </div>

        <div class="form-row">
          <div class="input-wrapper">
            <label for="country-delivery">País</label>
            <select name="country-delivery" id="country-delivery" class="input">
              <c:forEach items="${countries}" var="country">
                <option value="${country.getId()}">${country.getName()}</option>
              </c:forEach>
            </select>

          </div>
          <div class="input-wrapper">
            <label for="state-delivery">Estado</label>
            <select name="state-delivery" id="state-delivery" class="input">
              <c:forEach items="${states}" var="state">
                <option aria-delivery-country-id="${state.getCountry().getId()}" value="${state.getId()}">${state.getName()}</option>
              </c:forEach>
            </select>
          </div>
        </div>

        <div class="form-row">
          <div class="input-wrapper">
            <label for="city-delivery">Cidade</label>
            <select name="city-delivery" id="city-delivery" class="input">
              <c:forEach items="${cities}" var="city">
                <option aria-delivery-state-id="${city.getState().getId()}" value="${city.getId()}">${city.getName()}</option>
              </c:forEach>
            </select>
          </div>
          <div class="input-wrapper">
            <label for="public-place-type-delivery">Tipo de logradouro</label>
            <select name="public-place-type-delivery" id="public-place-type-delivery" class="input">
              <c:forEach items="${publicPlaceTypes}" var="publicPlaceType">
                <option value="${publicPlaceType.getId()}">${publicPlaceType.getName()}</option>
              </c:forEach>
            </select>
          </div>
        </div>

        <div class="form-row">
          <div class="input-wrapper">
            <label for="residence-type-delivery">Tipo de Residência</label>
            <select name="residence-type-delivery" id="residence-type-delivery" class="input">
              <c:forEach items="${residenceTypes}" var="residenceType">
                <option value="${residenceType.getId()}">${residenceType.getName()}</option>
              </c:forEach>
            </select>
          </div>
          <div class="input-wrapper">
            <label for="notes-delivery">Observações</label>
            <input type="text" id="notes-delivery" name="notes-delivery" placeholder="Digite as observações" class="input">
          </div>
        </div>

        <button class="button">Finalizar Cadastro</button>
      </div>
    </form>
  </main>
</body>

<script>
  const $formNextButtons = document.querySelectorAll('.button');
  const $formBackButtons = document.querySelectorAll('.form-back');
  const $globalForm = document.querySelector('form');
  let isValidPassword;

  const verifyPassword = () => {
    const $password = document.querySelector('#password');
    const $passwordConfirm = document.querySelector('#password-confirm');
    if ($password.value !== $passwordConfirm.value) {
      window.alert('As senhas não coincidem, por favor, digite novamente.');
      return false;
    }
    return true;
  }

  // go to next form
  $formNextButtons.forEach(($formButton, index) => {
    $formButton.addEventListener('click', (event) => {
      event.preventDefault();
      const $currentForm = $formButton.parentElement;
      const currentFormIndex = Array.from($globalForm.children).indexOf($currentForm);

      if (currentFormIndex === 0) {
        isValidPassword = verifyPassword();
      }
      if (!isValidPassword) {
        return;
      }

      const $nextForm = $globalForm.children[currentFormIndex + 1];
      if ($nextForm) {
        $currentForm.classList.remove('active');
        $nextForm.classList.add('active');
      } else {
        $globalForm.submit();
      }
    });
  });

  // go to previous form
  $formBackButtons.forEach(($formBack, index) => {
    $formBack.addEventListener('click', (event) => {
      event.preventDefault();
      const $currentForm = $formBack.parentElement;
      const currentFormIndex = Array.from($globalForm.children).indexOf($currentForm);
      const $previousForm = $globalForm.children[currentFormIndex - 1];
      if ($previousForm) {
        $currentForm.classList.remove('active');
        $previousForm.classList.add('active');
      }
    });
  });
  
  // aplicar para os outros selects de endereço de cobrança (cidade, estado, país)
  const $countriesBillingSelect = document.querySelector('#country-billing');
  const $statesBillingSelect = document.querySelector('#state-billing');
  const $citiesBillingSelect = document.querySelector('#city-billing');
  
  const $allBillingStates = [...$statesBillingSelect.children];
  const $allBillingCities = [...$citiesBillingSelect.children];
  
  const getCitiesByStateBilling = () => {
	  const $newOptions = $allBillingCities.filter((option) => option.getAttribute('aria-billing-state-id') == $statesBillingSelect.value);
	  $citiesBillingSelect.innerHTML = '';
	  
	  $newOptions.forEach(newOption => $citiesBillingSelect.appendChild(newOption));
  }
  
  getCitiesByStateBilling();  
  $statesBillingSelect.addEventListener('change', getCitiesByStateBilling);
  
  const getStatesByCountryBilling = () => {
	  const $newOptions = $allBillingStates.filter((option) => option.getAttribute('aria-billing-country-id') == $countriesBillingSelect.value);
	  $statesBillingSelect.innerHTML = '';
	  
	  $newOptions.forEach(newOption => $statesBillingSelect.appendChild(newOption));
	  getCitiesByStateBilling();
  } 
  
  getStatesByCountryBilling();  
  $countriesBillingSelect.addEventListener('change', getStatesByCountryBilling);  
  
  //aplicar para os outros selects de endereço de cobrança (cidade, estado, país)
  const $countriesDeliverySelect = document.querySelector('#country-delivery');
  const $statesDeliverySelect = document.querySelector('#state-delivery');
  const $citiesDeliverySelect = document.querySelector('#city-delivery');
  
  const $allDeliveryStates = [...$statesDeliverySelect.children];
  const $allDeliveryCities = [...$citiesDeliverySelect.children];
  
  const getCitiesByStateDelivery = () => {
	  const $newOptions = $allDeliveryCities.filter((option) => option.getAttribute('aria-delivery-state-id') == $statesDeliverySelect.value);
	  $citiesDeliverySelect.innerHTML = '';
	  
	  $newOptions.forEach(newOption => $citiesDeliverySelect.appendChild(newOption));
  }
  
  getCitiesByStateDelivery();  
  $statesDeliverySelect.addEventListener('change', getCitiesByStateDelivery);
  
  const getStatesByCountryDelivery = () => {
	  const $newOptions = $allDeliveryStates.filter((option) => option.getAttribute('aria-delivery-country-id') == $countriesDeliverySelect.value);
	  $statesDeliverySelect.innerHTML = '';
	  
	  $newOptions.forEach(newOption => $statesDeliverySelect.appendChild(newOption));
	  getCitiesByStateDelivery();
  } 
  
  getStatesByCountryDelivery();  
  $countriesDeliverySelect.addEventListener('change', getStatesByCountryDelivery); 
  
</script>

</html>