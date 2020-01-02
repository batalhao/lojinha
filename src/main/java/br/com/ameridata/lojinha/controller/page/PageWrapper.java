package br.com.ameridata.lojinha.controller.page;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

public class PageWrapper<T> {

	private Page<T> page;

	private UriComponentsBuilder uriComponentsBuilder;

	public PageWrapper(Page<T> page, HttpServletRequest httpServletRequest) {
		this.page = page;
		this.uriComponentsBuilder = ServletUriComponentsBuilder.fromRequest(httpServletRequest);
	}

	public List<T> getConteudo() {
		return page.getContent();
	}

	public boolean isVazia() {
		return page.getContent().isEmpty();
	}

	public int getAtual() {
		return page.getNumber();
	}

	public boolean isPrimeira() {
		return page.isFirst();
	}

	public boolean isUltima() {
		return page.isLast();
	}

	public int getTotal() {
		return page.getTotalPages();
	}

	public String urlParaPagina(int pagina) {
		return uriComponentsBuilder.replaceQueryParam("page", pagina).build(true).encode().toUriString();
	}

	public String urlOrdenada(String propriedade) {
		UriComponentsBuilder builderOrder = UriComponentsBuilder
				.fromUriString(uriComponentsBuilder.build(true).encode().toUriString());

		String valorSort = String.format("%s,%s", propriedade, inverterOrdenacao(propriedade));

		return builderOrder.replaceQueryParam("sort", valorSort).build(true).encode().toUriString();
	}

	public String inverterOrdenacao(String propriedade) {
		String direcao = "asc";

		Order order = !page.getSort().isEmpty() ? page.getSort().getOrderFor(propriedade) : null;

		if (order != null) {
			direcao = Sort.Direction.ASC.equals(order.getDirection()) ? "desc" : "asc";
		}

		return direcao;
	}

	public boolean descendente(String propriedade) {
		return inverterOrdenacao(propriedade).equals("asc");

//		Order order = !page.getSort().isEmpty() ? page.getSort().getOrderFor(propriedade) : null;
//		boolean direcaoDesc = false;
//		if (order != null) {
//			direcaoDesc = Sort.Direction.DESC.equals(order.getDirection()) ? true : false;
//		}
//		return direcaoDesc;
	}

	public boolean ordenada(String propriedade) {
		Order order = !page.getSort().isEmpty() ? page.getSort().getOrderFor(propriedade) : null;

		if (order == null) {
			return false;
		}

		return (page.getSort().getOrderFor(propriedade) != null);
	}

}
