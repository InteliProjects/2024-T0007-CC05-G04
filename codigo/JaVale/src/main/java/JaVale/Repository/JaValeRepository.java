package JaVale.Repository;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Repository;

import JaVale.Enums.LocationTypes;
import JaVale.Enums.SubmodalTypes;
import JaVale.Model.Location;
import JaVale.Model.LogisticNetwork;
import JaVale.Model.Route;
import static java.nio.charset.StandardCharsets.UTF_8;

/**<b>Repositório da aplicação, que lê diretamente os dados do problema</b><p></p>
 * Propriedades:
 * <ul>
 * <li> routes : List<Route> = rotas (arestas) presentes nos dados
 * <li> locations : Set<Location> = localidades (vértices) presentes nos dados
 * </ul>
 * 
 * Métodos públicos:
 * <ul>
 * <li>getAllRoutes() : ArrayList<Route>
 * <li>getLogisticNetwork() : LogisticNetwork
 * <li>getTotalLocations() : int
 * </ul>
 */
@Repository
public class JaValeRepository {

    private List<Route> routes;
    private Set<Location> locations;


    public JaValeRepository() {
        this.locations = new HashSet<Location>();

        try {
            URL resource = JaValeRepository.class.getResource("dadosTratados.csv");
            String path = Paths.get(resource.toURI()).toString();
            this.routes = this.getRoutesDataFromCSV(path);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**Retorna uma lista de todas as arestas presentes nos dados.
     * 
     * @return arestas (rotas) : List<Route>
     */
    public List<Route> getAllRoutes() {
        return this.routes;
    }

    /**Retorna o total de nós presentes nos dados.
     * 
     * @return total de nós (localidades) : int
     */
    public int getTotalLocations() {
        return this.locations.size();
    }


    /**Retorna o grafo construído a partir dos dados.
     * 
     * @return grafo (malha logística) : LogisticNetwork
     */
    public LogisticNetwork getLogisticNetwork() {
        LogisticNetwork result = new LogisticNetwork(this.getTotalLocations() + 2); // Mais dois: superfonte e super-sorvedouro
        Location source = new Location(LocationTypes.super_fonte, 0, 0, 0, "Superfonte");
        Location sink = new Location(LocationTypes.super_sorvedouro, this.getTotalLocations() + 1, 1, 0, "Super-sorvedouro");

        for(Route r : this.routes) {
            result.addRoute(r);
        }

        for(Location l : this.locations) {
            if (!l.getIsDestination()) {
                LocationTypes type = l.getType();
                if (type.equals(LocationTypes.fornecedor) || type.equals(LocationTypes.pelotizacao) || type.equals(LocationTypes.briquete) || type.equals(LocationTypes.beneficiamento)) {
                    result.addRoute(new Route(source, l, SubmodalTypes.SUPER, Double.POSITIVE_INFINITY));
                }
            }
            
            if (l.getType().equals(LocationTypes.cliente)) {
                result.addRoute(new Route(l, sink, SubmodalTypes.SUPER, Double.POSITIVE_INFINITY));
            }
        }

        return result;
    }

    /**
     * Método para ler rotas a partir de um arquivo CSV.
     *
     * @return Uma lista de objetos Route contendo as rotas lidas do arquivo CSV.
     */
   private List<Route> getRoutesDataFromCSV(String filePath) {
    List<Route> routes = new ArrayList<>();

    try {
        BufferedReader scanner = new BufferedReader(new FileReader(filePath));
        scanner.readLine(); // Discarding headers

        String line = scanner.readLine();

        while (line != null) {
            line = new String(line.getBytes(), UTF_8);
            String[] parts = line.split(",");
            Route route = this.createRoute(parts); 
            routes.add(route);
            line = scanner.readLine();
        }

        scanner.close();

    } catch (FileNotFoundException e) {
        System.out.println("Arquivo não foi localizado: " + e.getMessage());
    } catch (Exception e) {
        System.out.println("Um erro ocorreu: " + e.getMessage());
    }

    return routes;
}

    /**
     * Método para criar um objeto Route a partir de uma linha do arquivo CSV.
     *
     * @param attributes Um array de strings contendo os atributos de uma rota.
     * 
     * @return Um objeto Route contendo os atributos lidos do arquivo CSV.
     */
    private Route createRoute(String[] attributes) {
        Location source = new Location();
        source.setId(Integer.parseInt(attributes[0]));
        source.setDescription(attributes[1]);
        source.setType(LocationTypes.valueOf(attributes[2]));
        source.setStorageMax(Double.parseDouble(attributes[3]));

        Location target = new Location();
        target.setId(Integer.parseInt(attributes[4]));
        target.setDescription(attributes[5]);
        target.setType(LocationTypes.valueOf(attributes[6]));
        target.setStorageMax(Double.parseDouble(attributes[7]));
        target.setIsDestination(true);
        
        SubmodalTypes submodal = SubmodalTypes.valueOf(attributes[8]);
        double maxFlow = Double.parseDouble(attributes[9]);

        boolean addedSource = this.locations.add(source);
        boolean addedTarget = this.locations.add(target);
        if (addedSource) {
            source.assignIndex();
        }
        else {
            source.setIndex(this.locations.stream().filter(l -> l.equals(source)).findFirst().get().getIndex());
        }
        if (addedTarget) {
            target.assignIndex();
        }
        else {
            target.setIndex(this.locations.stream().filter(l -> l.equals(target)).findFirst().get().getIndex());
        }
        
        return new Route(source, target, submodal, maxFlow);
    }
}
