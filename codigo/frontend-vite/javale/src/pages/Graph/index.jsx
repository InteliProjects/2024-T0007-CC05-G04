import styles from './style.module.css';
import Sidebar from '../../components/sidebar/sidebar.jsx';
import Header from '../../components/header/header.jsx';
import Graph from '../../components/graph/graph.jsx';
import Filter from '../../components/filter/filter.jsx';
import Summary from '../../components/summary/summary.jsx';
import React from 'react';
import { useParams } from 'react-router';
import InstructionModal from '../../components/instruction_modal/modal.jsx';

// Permite a passagem de parâmetros de rota como props para a página do grafo
function withParams(Component) {
    return props => <Component {...props} params={useParams()}/>;
}

/**
 * Página do grafo
 * 
 * É necessário especificar o algoritmo a ser utilizado na rota de acesso a esta página
 * 
 * Rotas possíveis:
 * 
 * "graph?algorithm=edmonds" -> Edmonds-Karp
 * 
 * "graph?algorithm=dinic" -> Dinic
 * 
 * "graph?algorithm=dinic&fromHome=true" -> primeiro acesso ao dashboard com Dinic
 */
class GraphPage extends React.Component {

    algorithm;
    fromHome;
    constructor(props) {
        super(props);
        const params = new URLSearchParams(window.location.search);
        this.algorithm = params.get("algorithm");
        this.fromHome = params.get("fromHome");
        this.state = { graphData: null };
        this.setGraphData = this.setGraphData.bind(this);

        fetch(import.meta.env.VITE_APP_URL + "/graph/" + this.algorithm)
        .then(response => response.json())
        .then(responseJson => this.setGraphData(responseJson));
    }
        
    
    setGraphData(data) {
        this.fixDescriptions(data.locations);
        this.setState((state) => ({
            ...state,
            graphData: data 
        }));
    }


    render() {
        if (this.state.graphData === null) {
            return null;
        }

        return (
            <>
                {this.fromHome ? <InstructionModal/> : null}
                <Header />
                <Sidebar highlighted={this.algorithm}/>
                <div className={styles.container}>
                    <div className={styles.graph_container}>
                        <p className={styles.graph_title}>Visualização do Grafo</p>
                        <Graph data={ this.state.graphData } filterData={ this.setGraphData }/>
                        <p className={styles.maxFlow_text}>Fluxo máximo: {this.state.graphData.maxFlow.toFixed(2)} quilotoneladas | Tempo de execução do algoritmo: {this.state.graphData.executionTime.toFixed(3)}ms</p>
                    </div>
                    <Summary data={ this.state.graphData }/>
                </div>
            </>
        );
    }


    // Substitui caracteres estranhos gerados pelo encoding das strings
    fixDescriptions(locations) {
        locations.forEach((l) => {
            l.description = l.description.replaceAll("�?", "Á");
            l.description = l.description.replaceAll("AÁ", "AÍ");
        })
    }
}

export default withParams(GraphPage);