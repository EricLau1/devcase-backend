package com.vsm.devcase.info;

import java.util.ArrayList;
import java.util.List;

public class Route {

	private String path;
	private String method;
	private String description;
	
	public Route(String path, String method, String description) {
		this.path = path;
		this.method = method;
		this.description = description;
	}
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public static List<Route> defaultRoutes() {
		
		List<Route> routes = new ArrayList<>();
		
		routes.add(new Route("/v1/api/customers", "GET", 
				"Retorna uma lista de clientes paginada. Informe a numero da paǵina pela Uri: /v1/api/customers?page=1"));
		routes.add(new Route("/v1/api/customers/1", "GET", 
				"Retorna um cliente pelo ID"));
		routes.add(new Route("/v1/api/customers/gender/F?page=1", "GET", 
				"Retorna uma lista paginada de clientes de um determinado gênero: M ou F") );
		routes.add(new Route("/v1/api/customers", 
				"POST", "Recebe um JSON com: { firstName, lastName, address, phone, email, cpf, rg, gender }"));
		
		routes.add(new Route("/v1/api/dataload/random", "GET",
				"Retorna uma objeto de cliente randomico"));
		
		routes.add(new Route("/v1/api/dataload/random/F", "GET",
				"Retorna uma objeto de cliente randômico dependendo do gênero informado na Uri: M ou F"));
		
		routes.add(new Route("/v1/api/purchases", "GET", 
				"Retorna uma lista paginada de compras. Informe a numero da paǵina pela Uri: /v1/api/purchases?page=1"));
		
		routes.add(new Route("/v1/api/purchases/1", "GET", 
				"Retorna uma compra pelo ID informado na Uri"));
		
		routes.add(new Route("/v1/api/purchases/gender/F?page=1", "GET", 
				"Retorna uma lista paginada de compras de acordo com o gênero informado pela Uri: F ou M"));
		
		routes.add(new Route("/v1/api/purchases/customer/1", "POST", 
				"Recebe um JSON com: { cashSpent }. Representando o dinheiro gasto na compra, e o ID cliente pela Uri."));
		
		routes.add(new Route("/v1/api/admin", "POST", 
				"Recebe um JSON com: { email, password }"));
		
		routes.add(new Route("/v1/api/login", "POST", 
				"Recebe um JSON com: { email, password }. Retorna um Token JWT caso seja autenticado com sucesso."));
		
		return routes;
	}
}
