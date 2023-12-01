package com.clientcrud.controller;

public abstract class BaseController implements IBaseController {
	protected IFacade facade = new Facade();
}
