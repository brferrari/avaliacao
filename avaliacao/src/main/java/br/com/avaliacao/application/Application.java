package br.com.avaliacao.application;

import java.util.HashSet;
import java.util.Set;

import br.com.avaliacao.view.ReprovacaoService;

public class Application extends javax.ws.rs.core.Application{

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> clazz = new HashSet<>();
		clazz.add(ReprovacaoService.class);
		return clazz;
	}
	
}
