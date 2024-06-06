import React from "react";
import styles from './style.module.css';


/**
 * Componente de fundo branco retangular com bordas arredondadas
 * 
 * Aceita como props:
 * 
 * id           = identificador único do elemento
 * 
 * width        = largura do elemento
 * 
 * height       = altura do elemento
 * 
 * padding      = padding em todas as direções do elemento
 * 
 * minHeight    = altura mínima do elemento
 * 
 * zIndex       = z index, usado para definir qual elemento está a frente de qual
 * 
 * children     = elementos que devem estar dentro deste fundo branco
 */
export default class WhiteBackground extends React.Component {

    render() {
        return (
            <div id={this.props.id} className={styles.white_bg} style={{width: `${this.props.width}`, height: `${this.props.height}`, padding: `${this.props.padding}`, minHeight: `${this.props.minHeight}`, zIndex: `${this.props.zIndex}`}}>
                {this.props.children}
            </div>
        );
    }
}