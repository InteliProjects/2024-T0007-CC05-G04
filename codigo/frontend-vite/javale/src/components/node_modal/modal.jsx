import WhiteBackground from '../white_background/white_background';
import styles from './style.module.css';
import React from 'react';
import * as d3 from 'd3';

/**
 * Componente de modal que surge ao clicar em um nó do grafo
 * 
 * Aceita como props:
 * 
 * node             = dados do nó do grafo (json | objeto)
 * 
 * setVisibility = função para esconder o modal
 */
export default class NodeModal extends React.Component {

    constructor(props) {
        super(props);
        this.closeModal = this.closeModal.bind(this);
    }

    render() {
        return (
            <div id="modal_background" className={styles.background}>
                <div className={styles.modal}>
                    <WhiteBackground id="modal" width="32vw" height="30vh" padding="15px" zIndex="10">
                        <button className={styles.close_button} onClick={this.closeModal}>X</button>
                        <table className={styles.modal_table}>
                            <tbody>
                                <tr>
                                    <td>Nome:</td>
                                    <td>{this.props.node.description}</td>
                                </tr>
                                <tr>
                                    <td>Tipo:</td>
                                    <td>{this.props.node.type}</td>
                                </tr>
                                <tr>
                                    <td>Capacidade:</td>
                                    <td>{this.props.node.storageMax} quilotoneladas</td>
                                </tr>
                            </tbody>
                        </table>
                    </WhiteBackground>
                </div>
            </div>
        );
    }

    
    // Transiciona para fechar o modal
    closeModal() {
        d3.select("#modal").style("opacity", 0)
        d3.select("#modal_background").transition().duration(150).style("background-color", "rgba(66, 66, 66, 0)")
                .on("end", () => this.props.setVisibility(null, false));
    }
}