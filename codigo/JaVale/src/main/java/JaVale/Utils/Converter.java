package JaVale.Utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import JaVale.DTOs.LocationDTO;
import JaVale.DTOs.LogisticNetworkDTO;
import JaVale.DTOs.RouteDTO;
import JaVale.Model.Location;
import JaVale.Model.LogisticNetwork;
import JaVale.Model.Route;

/** <b>Conversor para valores utilizados no sistema e classes</b><p></p>
 * Propriedades:
 * <ul>
 * <li>batchInTons : double = valor para conversão entre lotes e toneladas
 * </ul>
 * 
 * Métodos públicos:
 * <ul> 
 * <li>fromBatchesToTons(int) : double
 * <li>fromTonsToBatches(double) : int
 * <li>fromHoursToDays(int) : double
 * <li>convertToDTO(LogisticNetwork || Route || Location) : LogisticNetworkDTO || RouteDTO || LocationDTO
 * </ul>
*/
public class Converter {
    private static final double batchInTons = 1000.0;

    /** Converte lotes para o valor correspondente em toneladas.
     * 
     * @param batches : int = lotes para conversão
     * 
     * @return valor convertido : double
    */
    public static double fromBatchesToTons(int batches) {
        return batchInTons * batches;
    }

    /** Converte toneladas para o valor correspondente em lotes.
     * 
     * @param tons : double = toneladas para conversão
     * 
     * @return valor convertido : double
    */
    public static int fromTonsToBatches(double tons) {
        return (int) Math.floor(tons / batchInTons);
    }

    /** Converte horas para o valor correspondente em dias.<p></p>
     * O valor retornado pode ser uma fração de dia.
     * 
     * @param hours : int = horas para conversão<p></p>
     * Se hours < 0, retorna 0.
     * 
     * @return valor convertido : double
    */
    public static double fromHoursToDays(int hours) {
        return Math.max(hours / 24D, 0);
    }

    /** Converte dias para o valor correspondente em horas.<p></p>
     * O valor retornado é arredondado para cima.
     * 
     * @param days : double = dias para conversão<p></p>
     * Se days < 0, retorna 0.
     * 
     * @return valor convertido : int
    */
    public static int fromDaysToHours(double days) {
        return (int) Math.max(Math.ceil(days * 24), 0);
    }


    /**Converte um objeto do tipo LogisticNetwork para LogisticNetworkDTO<p></p>
     * 
     * @param logisticNetwork : LogisticNetwork = grafo a ser convertido para DTO
     * @param maxFlow : double                  = valor do fluxo máximo para o grafo
     * @param executionTime : double                  = tempo de execução do algoritmo utilizado
     * 
     * @return DTO da malha logística : LogisticNetworkDTO
     * 
     */
    public static LogisticNetworkDTO convertToDTO(LogisticNetwork logisticNetwork, double maxFlow, double executionTime) {
      if (logisticNetwork == null) {
        return null;
      }

      LogisticNetworkDTO logisticNetworkDTO = new LogisticNetworkDTO();
      logisticNetworkDTO.setVerticesAmount(logisticNetwork.getVerticesAmount() - 2);
      logisticNetworkDTO.setMaxFlow(maxFlow);
      logisticNetworkDTO.setExecutionTime(executionTime);

      List<Route>[] adjList = logisticNetwork.getAdjacencyList();
      List<RouteDTO> routeDTOsAdjList = new ArrayList<RouteDTO>();
      Set<LocationDTO> locationDTOs = new HashSet<LocationDTO>();

      for (int i = 0; i < adjList.length; i++) {
        for (Route r : adjList[i]) {
          if(r.getSource().getIndex() == 0 || r.getTarget().getIndex() == logisticNetwork.getVerticesAmount() - 1) {
            continue;
          }
          
            routeDTOsAdjList.add(Converter.convertToDTO(r));
            locationDTOs.add(Converter.convertToDTO(r.getSource()));
            locationDTOs.add(Converter.convertToDTO(r.getTarget()));
        }
      }

      logisticNetworkDTO.setRoutes(routeDTOsAdjList);
      logisticNetworkDTO.setLocations(locationDTOs.stream().toList());

      return logisticNetworkDTO;
    }


    /**Converte um objeto do tipo Route para RouteDTO<p></p>
     * 
     * @param route : Route = aresta a ser convertida para DTO
     * 
     * @return DTO de rota : RouteDTO
     * 
     */
    public static RouteDTO convertToDTO(Route route) {
      if (route == null) {
        return null;
      }
      RouteDTO routeDTO = new RouteDTO();
      routeDTO.setSource(route.getSource().getIndex());
      routeDTO.setTarget(route.getTarget().getIndex());
      routeDTO.setFlow(route.getFlow());
      routeDTO.setSubmodal(route.getSubmodal());
      routeDTO.setTravelTime(route.getTravelTime());

      return routeDTO;
    }


    /**Converte um objeto do tipo Location para LocationDTO<p></p>
     * 
     * @param location : Location = vértice a ser convertido para DTO
     * 
     * @return DTO de localidade : LocationDTO
     * 
     */
    public static LocationDTO convertToDTO(Location location) {
        if (location == null) {
            return null;
        }
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setType(location.getType());
        locationDTO.setI(location.getIndex());
        locationDTO.setId(location.getId());
        locationDTO.setStorageMax(location.getStorageMax());
        locationDTO.setDescription(location.getDescription());

        return locationDTO;
    }
}
