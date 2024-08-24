package br.com.picpay_simplificado.DTOS;

import java.math.BigDecimal;

public record DTOTransaction(BigDecimal amount, long pagadorId, long destinatarioId) {
}
