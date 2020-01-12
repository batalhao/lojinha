//package br.com.ameridata.lojinha.security;
//
//import java.util.Optional;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//import org.springframework.transaction.annotation.Transactional;
//
//import br.com.ameridata.lojinha.model.Usuario;
//import br.com.ameridata.lojinha.repository.helper.usuario.UsuariosQueries;
//
//public class UsuariosImpl implements UsuariosQueries {
//
//	@PersistenceContext
//	private EntityManager manager;
//
//	@Override
//	@Transactional(readOnly = true)
//	public Optional<Usuario> buscaPorEmailEAtivo(String email) {
//		return manager.createQuery("from Usuario where lower(email) = lower(:email) and ativo = true", Usuario.class)
//				.setParameter("email", email).getResultList().stream().findFirst();
//	}
//
//}
