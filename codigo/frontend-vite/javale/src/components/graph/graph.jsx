import * as d3 from 'd3'
import React from 'react';
import WhiteBackground from '../white_background/white_background';
import NodeModal from '../node_modal/modal';
import Filter from '../filter/filter';
import styles from './style.module.css';


/**
 * Componente de representação do grafo
 * 
 * Aceita como props:
 * 
 * data = dados do grafo (json | objeto)
 * 
 */
export default class Graph extends React.Component {

    constructor(props) {
        super(props);

        this.state = { transform: "translate(0, 0) scale(1)",
                        selectedNode: null,
                        modalVisible: false };
        this.toggleModalView = this.toggleModalView.bind(this);
    }


    componentDidMount() {
        // Configurando comportamento de zoom
        const zoomBehaviour = d3.zoom().scaleExtent([0.03, 7]).translateExtent([[-8000, -8000], [8000, 8000]]).on("zoom", (event) => {
            this.setState((state) => ({
                ...state,
                transform: event.transform,
            }));
        });

        let graph = d3.select("#graph_container");
    
        let svg = graph.append("svg")
                        .attr("id", "graph_svg")
                        .style("width", "100%")
                        .style("height", "100%")
                        .style("cursor", "all-scroll");
        
        svg.call(zoomBehaviour);
        
        let g = svg.append("g")
                    .attr("id", "graph_group")
                    .attr("transform", "translate(0, 0) scale(1)")
        
        this.addLocationsAndRoutes(g);
    }


    render() {
        return (
            <>
                <WhiteBackground width= "98%" height= "55vh" padding= "18px" children= {
                    <div id="graph_container" className={styles.graph_container}>
                        <Filter data={ this.props.data } filterData={ this.props.filterData }/>
                    </div>
                }/>
                {this.state.modalVisible ? <NodeModal node={this.state.selectedNode} setVisibility={this.toggleModalView}/> : null}
            </>
        );
    }


    componentDidUpdate(prevProps) {
        let g = d3.select("#graph_group");
        
        // Se os dados mudaram, reconstrói o grafo
        if (prevProps.data != this.props.data) {
            g.selectChildren().remove();
            this.addLocationsAndRoutes(g);
        }
        
        g.attr("transform", this.state.transform);
    }


    // Função para mudar a visibilidade do modal que abre ao se clicar em um vértice do grafo
    toggleModalView(node, visibility) {
        this.setState((state) => ({
            ...state,
            selectedNode: node,
            modalVisible: visibility
        }))
    }


    // Adiciona vértices e arestas
    addLocationsAndRoutes(g) {
        const locations = [...this.props.data.locations];
        let routes = [...this.props.data.routes];

        let svgRect = d3.select("#graph_svg").node().getBoundingClientRect();

        const simulation = d3.forceSimulation(locations)
                             .force("link", d3.forceLink()
                                               .id(function (d) { return d.i })
                                               .links(routes))
                             .force("charge", d3.forceManyBody().strength(-20000))
                             .force("center", d3.forceCenter((svgRect.x + svgRect.width) / 2, (svgRect.y + svgRect.height) / 2));

        // Deleting loops
        let filteredRoutes = [];
        routes.forEach((r) => {
            if (filteredRoutes.find(f => f.source.i === r.target.i && f.target.i === r.source.i) === undefined) {
                filteredRoutes.push(r);
            }
        })
        routes = filteredRoutes;
        filteredRoutes = null

        // Adicionando arestas
        let links = g.selectAll("line")
                        .data(routes)
                        .enter()
                        .append("line")
                        .style("stroke", (d) => d.flow > 0 ? "#545454" : "#858585")
                        .style("stroke-width", (d) => d.flow > 0 ? "10px" : "5px")
        
        // Adicionando máscara para hover de arestas
        let hoverLinks = g.selectAll("masks").data(routes).enter().append("line")
        .style("opacity", 0)
        .style("stroke", "#FFFFFF")
        .style("stroke-width", "40px")
        .on("mouseover", (event, link) => {
            d3.select("#graph_svg")
                .append("text")
                .text("Fluxo: " + link.flow + "t")
                .attr("id", "flow")
                .attr("x", event.offsetX - 70)
                .attr("y", event.offsetY + 10)
                .style("stroke", "#202020")
                .style("font-size", "12px")
                .style("text-anchor", "middle")
                .style("pointer-events", "none")
                .style("opacity", 0)
                .transition().duration(200).style("opacity", 1);
        })
        .on("mouseout", (event) => {
            let flowLabel = d3.selectAll("#flow");
            flowLabel.transition().duration(200).style("opacity", 0).on("end", () => flowLabel.remove());
        });

        // Adicionando vértices
        let nodes = g.selectAll("circle")
                        .data(locations)
                        .enter()
                        .append("circle")
                        .attr("r", 50)
                        .style("cursor", "pointer")
                        .style("fill", function(d) { 
                            switch (d.type) {
                                case "patio":
                                    return "#008f83";
                                    break;
                                case "beneficiamento":
                                    return "#df0c0c";
                                    break;
                                case "pelotizacao":
                                    return "#df0cb1";
                                    break;
                                case "briquete":
                                    return "#c74966";
                                    break;
                                case "mina":
                                    return "#859b07";
                                    break;
                                case "fornecedor":
                                    return "#32C9C1";
                                    break;
                                case "cliente":
                                    return "#5796E6";
                                    break;
                                case "porto":
                                    return "#004539";
                                    break;
                                }
                        })
                        // Colore arestas conectadas (origem e destino até os nós finais)
                        .on("mouseover", (event, node) => {
                            g.selectAll("line").transition()
                                .duration(300)
                                .style("stroke", function(d) {
                                    if (node.i === d.source.i || node.i === d.target.i) {
                                        return "#fad721";
                                    }

                                    let targetRoutes = [d];
                                let visitedIndexes = [node.i];

                                while (targetRoutes.length) {
                                    let route = targetRoutes.pop();

                                    if (node.i === route.source.i || node.i === route.target.i) {
                                        return "#fad721";
                                    }

                                    if (route.source.i in visitedIndexes) {
                                        return d.flow > 0 ? "#545454" : "#858585";
                                    }
                                    visitedIndexes.push(route.source.i);
                                    
                                    let targetIndex = route.source.i;
                                    targetRoutes.push(...routes.filter((r) => r.target.i === targetIndex));
                                }

                                    return d.flow > 0 ? "#545454" : "#858585";
                            });    
                        })
                        .on("mouseout", (event) => {
                            g.selectAll("line").transition().duration(300).style("stroke", (d) => d.flow > 0 ? "#545454" : "#858585");
                        })
                        .on("click", (event, node) => {
                            this.toggleModalView(node, true)
                        });

        // Adicionando labels aos vértices
        let labels = g.selectAll("text")
                        .data(locations)
                        .enter()
                        .append("text")
                        .text(function(d) {
                            let description = d.description.trim(); 
                            return description.length > 10 ? description.substring(0, d.description.indexOf(" ")) + "..." : description
                        })
                        .style("stroke", "white")
                        .style("font-size", "12px")
                        .style("text-anchor", "middle")
                        .style("pointer-events", "none");

        
        simulation.on('tick', function() { //para cada tick, define os atributos dos links
                links.attr('x1', function(d) { return d.source.x })
                    .attr('y1', function(d) { return d.source.y })
                    .attr('x2', function(d) { return d.target.x })
                    .attr('y2', function(d) { return d.target.y });
                    
                hoverLinks.attr('x1', function(d) { return d.source.x })
                    .attr('y1', function(d) { return d.source.y })
                    .attr('x2', function(d) { return d.target.x })
                    .attr('y2', function(d) { return d.target.y })

                nodes.attr('transform', (d) => `translate(${d.x},${d.y})`);

                labels.attr('x', (d) => d.x)
                    .attr('y', (d) => d.y + 3);
            }
        )
    }
}