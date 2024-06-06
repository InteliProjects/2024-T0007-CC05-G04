package JaVale.Controller;

import JaVale.DTOs.LogisticNetworkDTO;
import JaVale.Service.JaValeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


/**
 * <b>Controlador da aplicação para a malha logística (LogisticNetwork)</b><p></p>
 * 
 * Métodos públicos:
 * <ul>
 * <li>getLogisticNetwork : LogisticNetworkDTO
 * </ul>
 */
@RestController
public class JaValeController {

    
    // Injeção de dependência para a camada de serviço.
    @Autowired
    private JaValeService jaValeService;

    /**
     * Método que retorna os dados acerca da malha logística da Vale
     * @return malha logística : LogisticNetwork
     */
    @GetMapping("/graph/{algorithm}")
    public LogisticNetworkDTO getLogisticNetwork(@PathVariable("algorithm") String algorithm) {
        return jaValeService.getLogisticNetwork(algorithm);
    }
}
