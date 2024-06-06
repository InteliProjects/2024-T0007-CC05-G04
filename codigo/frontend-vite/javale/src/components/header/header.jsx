import React from 'react';
import styles from './style.module.css';

/**
 * Componente de header da página
 */
export default class Header extends React.Component {
    
    render() {
        return (
            <div className={styles.header}>
                <p className={styles.title}>Painel de Controle</p>
            </div>
        );
    }
}