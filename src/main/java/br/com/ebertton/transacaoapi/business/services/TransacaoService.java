package br.com.ebertton.transacaoapi.business.services;

import br.com.ebertton.transacaoapi.controller.dtos.TransacaoRequestDTO;
import br.com.ebertton.transacaoapi.infraestructure.exceptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransacaoService {

    private final List<TransacaoRequestDTO> listaTransacoes = new ArrayList<>();

    public void adicionarTransacao(TransacaoRequestDTO dto) {

        log.info("Inicial o processamento de gravação de transações " + dto);
        if(dto.dataHora().isAfter(OffsetDateTime.now())){
            log.error("Data e hora maiores que a data e hora atual");
            throw new UnprocessableEntity("Data e hora maiores que a data e hora atuais");
        }
        if(dto.valor() < 0){
            log.error("Valor não pode ser menor que 0");
            throw new UnprocessableEntity("Valor não pode ser menor que 0");
        }

        listaTransacoes.add(dto);
        log.info("transaçoes aciononadas com sucesso " + dto);
    }


    public void limparTransacoes() {
        log.info("Iniciado processamento para deletar transaçoes ");
        listaTransacoes.clear();
        log.info("Transações deletada com sucesso ");
    }

    public List<TransacaoRequestDTO> buscarTransacoes(Integer intervaloBusca){
        log.info("Iniciado buscas de transações por tempo " + intervaloBusca);
        OffsetDateTime dataHoraIntervalo = OffsetDateTime.now().minusSeconds(intervaloBusca);
        log.info("retorno de transações com sucesso ");
        return listaTransacoes.stream().filter(transacoes -> transacoes.dataHora()
                .isAfter(dataHoraIntervalo)).toList();
    }



}
