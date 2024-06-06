import WhiteBackground from '../white_background/white_background';
import styles from './style.module.css';
import React from 'react';
import * as d3 from 'd3';

/**
 * Componente de modal que surge ao se abrir o dashboard pela primeira vez
 */
export default class InstructionModal extends React.Component {

    constructor(props) {
        super(props);
        this.closeModal = this.closeModal.bind(this);
    }

    render() {
        return (
            <div id="modal_instruction" className={styles.background}>
                <div className={styles.modal}>
                    <WhiteBackground id="modal" width="36vw" height="17vh" padding="15px" zIndex="10">
                        <button className={styles.close_button} onClick={this.closeModal}>X</button>
                        <div className={styles.instructions}>
                            <h2 className={styles.text}>Para navegar pelo grafo, utilize o zoom com a roda do mouse!</h2>
                            <img src='/mouse.png' className={styles.mouse}/>
                        </div>
                    </WhiteBackground>
                </div>
            </div>
        );
    }

    
    // Transiciona para fechar o modal
    closeModal() {
        d3.select("#modal").style("opacity", 0)
        let modal = d3.select("#modal_instruction"); 
        modal.transition().duration(150).style("background-color", "rgba(66, 66, 66, 0)")
                .on("end", () => modal.remove());
    }
}