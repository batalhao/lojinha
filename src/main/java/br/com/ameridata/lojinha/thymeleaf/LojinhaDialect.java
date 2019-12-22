package br.com.ameridata.lojinha.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import br.com.ameridata.lojinha.thymeleaf.processor.ClassForErrorAttributeTagProcessor;
import br.com.ameridata.lojinha.thymeleaf.processor.MessageElementTagProcessor;

public class LojinhaDialect extends AbstractProcessorDialect {

	public LojinhaDialect() {
		super("Ameridata Lojinha Dialect", "lojinha", StandardDialect.PROCESSOR_PRECEDENCE);
	}

	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processors = new HashSet<>();
		processors.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));
		processors.add(new MessageElementTagProcessor(dialectPrefix));

		return processors;
	}

}
