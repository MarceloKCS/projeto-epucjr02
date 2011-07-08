package com.epucjr.engyos.tecnologia.dao;

import com.epucjr.engyos.dominio.modelo.Congregacao;
import java.util.List;

/**
 *
 * @author Rogerio
 */
public interface ICongregacaoDAO {

    public void insert(Congregacao congregacao);
    public void update(Congregacao congregacao);
    public void delete(long idCongregacao);
    public List<Congregacao> findAll();
    public Congregacao findByPrimaryKey(long idCongregacao);
    public boolean isCongregacaoExistente(long idCongregacao);
    public boolean isConregacaoPadraoDefinida();
    public Congregacao obteCongregacaoPadrao();
}
