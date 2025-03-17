package br.com.ebertton.transacaoapi.controller.dtos;

import java.time.OffsetDateTime;

public record TransacaoRequestDTO(
        Double valor,
        OffsetDateTime dataHora
) { }
