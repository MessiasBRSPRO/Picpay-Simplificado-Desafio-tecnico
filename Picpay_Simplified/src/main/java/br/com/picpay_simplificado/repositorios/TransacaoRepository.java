package br.com.picpay_simplificado.repositorios;

import br.com.picpay_simplificado.Entidades.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
