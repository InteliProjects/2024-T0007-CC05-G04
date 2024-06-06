import React from "react";
import WhiteBackground from "../white_background/white_background";
import styles from './style.module.css';


/**
 * Componente de sumário das rotas do grafo
 * 
 * Aceita como props:
 * 
 * data = dados da tabela (json | objeto)
 * 
 */
export default class Summary extends React.Component {
    render() {
        const headers = ["Nº", "Origem", "Destino", "Fluxo", "Submodal", "Dias"];
        const routes = this.props.data.routes;
        const locations = this.props.data.locations;
        
        return (
            <WhiteBackground id="summary" width="98%" height="fit-content" padding="18px" minHeight="50vh">
                <p className={styles.title}>Resultados</p>
                <table className={styles.summaryTable}>
                    <thead className={styles.cabecalho}>
                        <tr>
                            {headers.map(header => (
                                <th key={header}>{header}</th>
                            ))}
                        </tr>
                    </thead>
                    <tbody>
                        {routes.length ? routes.sort((a, b) => b.flow - a.flow).map((route, index) => (
                            <tr key={index}>
                                <td>{index + 1}</td>
                                <td>{locations.find((location) => location.i === this.getIndex(route.source)).description}</td>
                                <td>{locations.find((location) => location.i === this.getIndex(route.target)).description}</td>
                                <td>{route.flow.toFixed(2)}kT</td>
                                <td>{route.submodal}</td>
                                <td>{route.travelTime.toFixed(2)}</td>
                            </tr>
                        )) : null}
                    </tbody>
                </table>
            </WhiteBackground>
        );
    }


    // Como a simulação do d3 altera a estrutura dos dados, source e target, que eram ids inteiros, tornam-se objetos
    getIndex(vertice) {
        return vertice.i !== undefined ? vertice.i : vertice;
    }
}