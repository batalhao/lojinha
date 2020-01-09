package br.com.ameridata.lojinha.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

import br.com.ameridata.lojinha.validation.validator.AtributoConfirmacaoValidator;

/**
 * Atributo confirmação: Utilizado para validação do campo senha no cadastro de
 * Usuários
 * 
 * @author Paulo R. Batalhão
 * @version 1.0.0
 * @since 0.1.1
 */

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { AtributoConfirmacaoValidator.class })
public @interface AtributoConfirmacao {

	@OverridesAttribute(constraint = Pattern.class, name = "message")
	String message();

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String atributo();

	String atributoConfirmacao();

}
