package br.com.ameridata.lojinha.repository.helper.produto;

import br.com.ameridata.lojinha.dto.ProdutoDTO;
import br.com.ameridata.lojinha.model.Produto;
import br.com.ameridata.lojinha.repository.filter.ProdutoFilter;
import br.com.ameridata.lojinha.repository.paginacao.PaginacaoUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ProdutosImpl implements ProdutosQueries {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private PaginacaoUtil paginacaoUtil;

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public Page<Produto> filtrar(ProdutoFilter filtro, Pageable pageable) {
        @SuppressWarnings("deprecation")
        Criteria criteria = manager.unwrap(Session.class).createCriteria(Produto.class);

        paginacaoUtil.preparar(criteria, pageable);

        adicionarFiltro(filtro, criteria);

        return new PageImpl<>(criteria.list(), pageable, total(filtro));
    }

    @Override
    public List<ProdutoDTO> porSkuOuNome(String skuOuNome) {
        String jpql = "select new br.com.ameridata.lojinha.dto.ProdutoDTO(id, sku, nome, origem, precoVenda, foto) " +
                "from Produto where lower(sku) like :skuOuNome or lower(nome) like :skuOuNome order by nome asc ";
        String pesqSkuOuNome = ("%" + skuOuNome + "%").toLowerCase();
        List<ProdutoDTO> produtoDTOList = manager.createQuery(jpql, ProdutoDTO.class)
                .setParameter("skuOuNome", pesqSkuOuNome).setMaxResults(2).getResultList();
        return produtoDTOList;
    }

    private Long total(ProdutoFilter filtro) {
        @SuppressWarnings("deprecation")
        Criteria criteria = manager.unwrap(Session.class).createCriteria(Produto.class);
        adicionarFiltro(filtro, criteria);
        criteria.setProjection(Projections.rowCount());

        return (Long) criteria.uniqueResult();
    }

    private void adicionarFiltro(ProdutoFilter filtro, Criteria criteria) {
        if (filtro != null) {
            if (!StringUtils.isEmpty(filtro.getSku())) {
                criteria.add(Restrictions.eq("sku", filtro.getSku()));
            }

            if (!StringUtils.isEmpty(filtro.getGtin())) {
                criteria.add(Restrictions.eq("gtin", filtro.getGtin()));
            }

            if (!StringUtils.isEmpty(filtro.getNome())) {
                criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
            }

            if (isFabricantePresente(filtro)) {
                criteria.add(Restrictions.eq("fabricante", filtro.getFabricante()));
            }

            if (isFornecedorPresente(filtro)) {
                criteria.add(Restrictions.eq("fornecedor", filtro.getFornecedor()));
            }

            if (isUnidadePresente(filtro)) {
                criteria.add(Restrictions.eq("unidade", filtro.getUnidade()));
            }

            if (filtro.getOrigem() != null) {
                criteria.add(Restrictions.eq("origem", filtro.getOrigem()));
            }

            if (filtro.getCustoDe() != null) {
                criteria.add(Restrictions.ge("custoUnitario", filtro.getCustoDe()));
            }

            if (filtro.getCustoAte() != null) {
                criteria.add(Restrictions.le("custoUnitario", filtro.getCustoAte()));
            }

            if (filtro.getPrecoDe() != null) {
                criteria.add(Restrictions.ge("precoVenda", filtro.getPrecoDe()));
            }

            if (filtro.getPrecoAte() != null) {
                criteria.add(Restrictions.le("precoVenda", filtro.getPrecoAte()));
            }

            if (filtro.getComissaoDe() != null) {
                criteria.add(Restrictions.ge("comissao", filtro.getComissaoDe()));
            }

            if (filtro.getComissaoAte() != null) {
                criteria.add(Restrictions.le("comissao", filtro.getComissaoAte()));
            }

            if (filtro.getEstoqueDe() != null) {
                criteria.add(Restrictions.ge("quantidadeEstoque", filtro.getEstoqueDe()));
            }

            if (filtro.getEstoqueAte() != null) {
                criteria.add(Restrictions.le("quantidadeEstoque", filtro.getEstoqueAte()));
            }

        }
    }

    private boolean isFabricantePresente(ProdutoFilter filtro) {
        return filtro.getFabricante() != null && filtro.getFabricante().getId() != null;
    }

    private boolean isFornecedorPresente(ProdutoFilter filtro) {
        return filtro.getFornecedor() != null && filtro.getFornecedor().getId() != null;
    }

    private boolean isUnidadePresente(ProdutoFilter filtro) {
        return filtro.getUnidade() != null && filtro.getUnidade().getDescricao() != null;
    }

}
