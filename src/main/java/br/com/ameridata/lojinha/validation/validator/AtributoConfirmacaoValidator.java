package br.com.ameridata.lojinha.validation.validator;

import java.lang.reflect.InvocationTargetException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;

import org.apache.commons.beanutils.BeanUtils;

import br.com.ameridata.lojinha.validation.AtributoConfirmacao;

/**
 * Atributo confirmação validator: Utilizado para validação do campo senha no
 * cadastro de Usuários
 * 
 * @author Paulo R. Batalhão
 * @version 1.0.0
 * @since 0.1.1
 */

public class AtributoConfirmacaoValidator implements ConstraintValidator<AtributoConfirmacao, Object> {

	private String atributo;

	private String atributoConfirmacao;

	@Override
	public void initialize(AtributoConfirmacao constraintAnnotation) {
		this.atributo = constraintAnnotation.atributo();
		this.atributoConfirmacao = constraintAnnotation.atributoConfirmacao();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		boolean valido = false;

		try {
			var valorAtributo = BeanUtils.getProperty(value, this.atributo);
			var valorAtributoConfirmacao = BeanUtils.getProperty(value, this.atributoConfirmacao);

			valido = ambosSaoNull(valorAtributo, valorAtributoConfirmacao)
					|| ambosSaoIguais(valorAtributo, valorAtributoConfirmacao);

		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			throw new RuntimeException("Erro: Recuperando valores dos atributos", e);
		}

		if (!valido) {
			context.disableDefaultConstraintViolation();
			String messageTemplate = context.getDefaultConstraintMessageTemplate();
			ConstraintViolationBuilder violationBuilder = context.buildConstraintViolationWithTemplate(messageTemplate);
			violationBuilder.addPropertyNode(atributoConfirmacao).addConstraintViolation();
		}

		return valido;
	}

	/**
	 * @author Paulo R. Batalhão
	 * @param valorAtributo            - Atributo principal
	 * @param valorAtributoConfirmacao - Atributo para confirmação
	 * @return boolean - Retorna se os valores são iguais.
	 */

	private boolean ambosSaoIguais(String valorAtributo, String valorAtributoConfirmacao) {
		return valorAtributo != null && valorAtributo.equals(valorAtributoConfirmacao);
	}

	private boolean ambosSaoNull(String valorAtributo, String valorAtributoConfirmacao) {
		return valorAtributo == null && valorAtributoConfirmacao == null;
	}

}
