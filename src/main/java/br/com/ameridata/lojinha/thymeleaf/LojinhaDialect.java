package br.com.ameridata.lojinha.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import br.com.ameridata.lojinha.thymeleaf.processor.ClassForErrorAttributeTagProcessor;
import br.com.ameridata.lojinha.thymeleaf.processor.MenuAttributeTagProcessor;
import br.com.ameridata.lojinha.thymeleaf.processor.MessageElementTagProcessor;
import br.com.ameridata.lojinha.thymeleaf.processor.OrderElementTagProcessor;
import br.com.ameridata.lojinha.thymeleaf.processor.PaginationElementTagProcessor;

public class LojinhaDialect extends AbstractProcessorDialect {

	public LojinhaDialect() {
		super("Ameridata Lojinha Dialect", "lojinha", StandardDialect.PROCESSOR_PRECEDENCE);
	}

	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processors = new HashSet<>();
		processors.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));
		processors.add(new MessageElementTagProcessor(dialectPrefix));
		processors.add(new OrderElementTagProcessor(dialectPrefix));
		processors.add(new PaginationElementTagProcessor(dialectPrefix));
		processors.add(new MenuAttributeTagProcessor(dialectPrefix));

		return processors;
	}

}
