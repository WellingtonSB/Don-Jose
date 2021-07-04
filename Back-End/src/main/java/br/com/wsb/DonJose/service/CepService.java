package br.com.wsb.DonJose.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.wsb.DonJose.model.Cliente;


@FeignClient(url= "https://viacep.com.br/ws/", name = "viacep")
public interface CepService {

    @GetMapping("{cep}/json")
    Cliente buscaEnderecoPorCep(@PathVariable("cep") String cep);
}