package br.com.picpay_simplificado.Entidades;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transacaos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private BigDecimal valorTransferencia;

    @ManyToOne
    @JoinColumn(name = "pagador_id")
    private Usuario pagador;

    @OneToOne
    @JoinColumn(name = "destinatario_id")
    private Usuario destinatario;

    private LocalDate hora;
}
