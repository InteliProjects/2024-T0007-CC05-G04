package JaVale.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import JaVale.Algoritmos.Dinic;
import JaVale.Algoritmos.EdmondsKarp;
import JaVale.DTOs.LogisticNetworkDTO;
import JaVale.Model.Location;
import JaVale.Model.LogisticNetwork;
import JaVale.Repository.JaValeRepository;
import JaVale.Utils.Converter;

/**<b>Serviço da aplicação, que consome os dados do repositório e os retorna para o controlador</b><p></p>
 * Propriedades:
 * <ul>
 * <li>repository : JaValeRepository = repositório da aplicação
 * </ul>
 * 
 * Métodos públicos:<p></p>
 * <ul>
 * <li><i>getLogisticNetwork</i> : LogisticNetwork
 * </ul>
 */
@Service
@RequiredArgsConstructor
public class JaValeService {

    private JaValeRepository repository;

    /**Consulta os dados do repositório para construir e retornar o grafo que representa a malha logística da Vale.
     * 
     * @return malha logística : LogisiticNetworkDTO
     * 
     */
    public LogisticNetworkDTO getLogisticNetwork(String algorithm) {
        Location.resetIndex();
        this.repository = new JaValeRepository();
        LogisticNetwork graph = repository.getLogisticNetwork();
        int source = 0;
        int sink = graph.getVerticesAmount() - 1;
        double maxFlow = 0;
        double executionTime = 0;

        if (algorithm.equals("edmonds")) {
            EdmondsKarp edmonds = new EdmondsKarp(graph);
            maxFlow = edmonds.edmondsKarp(source, sink);
            executionTime = edmonds.getExecutionTime();
        }

        if (algorithm.equals("dinic")) {
            Dinic dinic = new Dinic(graph, source, sink);
            dinic.solve();
            maxFlow = dinic.getMaxFlow();
            executionTime = dinic.getExecutionTime();
        }

        return Converter.convertToDTO(graph, maxFlow, executionTime);
    }
}
