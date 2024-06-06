import React from 'react';
import * as d3 from 'd3';
import WhiteBackground from '../white_background/white_background.jsx';
import styles from './style.module.css';

/**
 * Componente de filtro do grafo, que permite busca por nome, zoom e filtro por grupo de arestas
 * 
 * Aceita como props:
 * 
 * data         = dados do grafo para filtrar
 * 
 * filterData   = função para enviar dados filtrados aos outros componentes da página
 * 
 */
export default class Filter extends React.Component {
    data;

    constructor(props) {
        super(props);
        this.data = this.props.data;
   }

    render() {
        return (
                <div className={styles.container}>
                    <h2 className={styles.titulo}>Filtros</h2>
                    <div className={styles.buscador}>
                        <h2 className={styles.titulo_busca}>Submodal</h2>
                        <input id="submodal" type="text" className={styles.input} onChange={() => {
                            // Texto inputado
                            let submodal = d3.select("#submodal").property("value").trim().toLowerCase();

                            // Copiando os dados para não alterá-los diretamente e conservá-los
                            let data = {...this.data};

                            if (submodal) {
                                // Rotas são mantidas de acordo com submodal especificado
                                data.routes = data.routes.filter(r => r.submodal.toLowerCase().includes(submodal));

                                // Vértice é mantido se seu id estiver contido nos ids de origem ou destino das rotas filtradas
                                data.locations = data.locations.filter(l => {
                                    return data.routes.find((r) => this.getIndex(r.source) === l.i || this.getIndex(r.target) === l.i) !== undefined;
                                });
                            }

                            this.props.filterData(data);
                            }
                        } placeholder="Código de um submodal" />
                    </div>

                    <div className={styles.buscador}>
                        <h2 className={styles.titulo_busca}>Nome</h2>
                        <input id="locationName" type="text" className={styles.input} onChange={() => {
                            // Texto inputado
                            let description = d3.select("#locationName").property("value").trim().toLowerCase();
                            
                            // Copiando os dados para não alterá-los diretamente e conservá-los
                            let data = {...this.data};

                            if (description) {
                                // Vértices são mantidos de acordo com o nome especificado
                                data.locations = data.locations.filter(l => l.description.toLowerCase().includes(description));

                                // Rotas são mantidas se seus ids de origem e destino pertencerem a vértices do nome especificado
                                data.routes = data.routes.filter(r => { 
                                    return data.locations.find((l) => l.description.toLowerCase().includes(description) && this.getIndex(r.source) === l.i) !== undefined &&
                                    data.locations.find((l) => l.description.toLowerCase().includes(description) && this.getIndex(r.target) === l.i) !== undefined;
                                });
                            }

                            this.props.filterData(data);
                            }
                        } placeholder="Nome de um vértice" />
                    </div>

                    <div className={styles.buscador}>
                        <h2 className={styles.titulo_busca}>Tipo</h2>
                        <input id="locationType" type="text" className={styles.input} onChange={() => {
                            // Texto inputado
                            let type = d3.select("#locationType").property("value").trim().toLowerCase();
                            
                            // Copiando os dados para não alterá-los diretamente e conservá-los
                            let data = {...this.data};

                            if (type) {
                                // Rotas são mantidas se seus ids de origem e destino pertencerem a vértices do tipo especificado
                                data.routes = data.routes.filter(r => { 
                                    return data.locations.find((l) => l.type.toLowerCase().includes(type) && this.getIndex(r.source) === l.i) !== undefined &&
                                    data.locations.find((l) => l.type.toLowerCase().includes(type) && this.getIndex(r.target) === l.i) !== undefined;
                                });

                                // Vértices são mantidos de acordo com o tipo especificado
                                data.locations = data.locations.filter(l => l.type.toLowerCase().includes(type));
                            }

                            this.props.filterData(data);
                            }
                        } placeholder="Tipo de um vértice" />
                    </div>
                </div>
        );
    }


    // Como a simulação do d3 altera a estrutura dos dados, source e target, que eram ids inteiros, tornam-se objetos
    getIndex(vertice) {
        return vertice.i !== undefined ? vertice.i : vertice;
    }
}