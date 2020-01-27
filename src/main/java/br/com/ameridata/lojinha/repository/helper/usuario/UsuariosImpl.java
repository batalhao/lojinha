package br.com.ameridata.lojinha.repository.helper.usuario;

import br.com.ameridata.lojinha.model.Grupo;
import br.com.ameridata.lojinha.model.Usuario;
import br.com.ameridata.lojinha.model.UsuarioGrupo;
import br.com.ameridata.lojinha.repository.filter.UsuarioFilter;
import br.com.ameridata.lojinha.repository.paginacao.PaginacaoUtil;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuariosImpl implements UsuariosQueries {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private PaginacaoUtil paginacaoUtil;

    @Override
    public Optional<Usuario> buscaPorEmailEAtivo(String email) {
        return manager.createQuery("from Usuario where lower(email) = lower(:email) and ativo = true", Usuario.class)
                .setParameter("email", email).getResultList().stream().findFirst();
    }

    @Override
    public List<String> permissoes(Usuario usuario) {
        return manager.createQuery(
                "select distinct p.nome from Usuario u inner join u.grupos g inner join g.permissoes p where u = :usuario",
                String.class).setParameter("usuario", usuario).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Usuario> filtrar(UsuarioFilter filtro, Pageable pageable) {
        @SuppressWarnings("deprecation")
        Criteria criteria = manager.unwrap(Session.class).createCriteria(Usuario.class);

//        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        paginacaoUtil.preparar(criteria, pageable);
        adicionarFiltro(criteria, filtro);

        criteria.addOrder(Order.asc("nome"));

        List<Usuario> usuariosFiltrados = criteria.list();
        usuariosFiltrados.forEach(u -> Hibernate.initialize(u.getGrupos()));

        return new PageImpl<>(usuariosFiltrados, pageable, total(filtro));
    }

    private Long total(UsuarioFilter filtro) {
        @SuppressWarnings("deprecation")
        Criteria criteria = manager.unwrap(Session.class).createCriteria(Usuario.class);
        adicionarFiltro(criteria, filtro);
        criteria.setProjection(Projections.rowCount());

        return (Long) criteria.uniqueResult();
    }

    private void adicionarFiltro(Criteria criteria, UsuarioFilter filtro) {
        if (filtro != null) {
            if (!StringUtils.isEmpty(filtro.getNome())) {
                criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
            }

            if (!StringUtils.isEmpty(filtro.getEmail())) {
                criteria.add(Restrictions.ilike("email", filtro.getEmail(), MatchMode.START));
            }

//            criteria.createAlias("grupos", "g", JoinType.LEFT_OUTER_JOIN);

            if (filtro.getGrupos() != null && !filtro.getGrupos().isEmpty()) {
                List<Criterion> criterionList = new ArrayList<>();
                for (Long grupoId : filtro.getGrupos().stream().mapToLong(Grupo::getId).toArray()) {
                    DetachedCriteria detachedCriteria = DetachedCriteria.forClass(UsuarioGrupo.class);
                    detachedCriteria.add(Restrictions.eq("id.grupo.id", grupoId));
                    detachedCriteria.setProjection(Projections.property("id.usuario"));

                    criterionList.add(Subqueries.propertiesIn(new String[]{ "id" }, detachedCriteria));
                }

                Criterion[] criterions = new Criterion[criterionList.size()];
                criteria.add(Restrictions.and(criterionList.toArray(criterions)));
            }
        }
    }

}
