package br.com.tech4me.servico.HttpClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import br.com.tech4me.servico.shared.SofaDTO;

@FeignClient("sofa")
public interface SofaClient {

    @RequestMapping(method = RequestMethod.GET, value = "/sofas/{id}")
    SofaDTO obterSofa(@PathVariable String id);

}
