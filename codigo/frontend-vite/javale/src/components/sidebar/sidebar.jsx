import styles from './style.module.css';
import logo from '../../assets/logo.png';
import React from 'react';

/**
 * Componente de sidebar da página
 */
export default class Sidebar extends React.Component {
    
    render() {
        return (
            <div className={styles.sidebar}>
                <div className={styles.img_container_sidebar}>
                    <img src={logo} alt="Logo" className={styles.img_sidebar} />
                    </div>
                
                <a href="/graph?algorithm=dinic" className={this.props.highlighted === "dinic" ? styles.highlighted_btn_sidebar : styles.btn_sidebar}>
                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 20 20" fill="none">
                    <path d="M11 18V20H9V18H1C0.734784 18 0.48043 17.8946 0.292893 17.7071C0.105357 17.5196 0 17.2652 0 17V3H20V17C20 17.2652 19.8946 17.5196 19.7071 17.7071C19.5196 17.8946 19.2652 18 19 18H11ZM2 16H18V5H2V16ZM11 7H16V9H11V7ZM11 11H16V13H11V11ZM7 7V10H10C10 10.5933 9.82405 11.1734 9.49441 11.6667C9.16476 12.1601 8.69623 12.5446 8.14805 12.7716C7.59987 12.9987 6.99667 13.0581 6.41473 12.9424C5.83279 12.8266 5.29824 12.5409 4.87868 12.1213C4.45912 11.7018 4.1734 11.1672 4.05764 10.5853C3.94189 10.0033 4.0013 9.40013 4.22836 8.85195C4.45542 8.30377 4.83994 7.83524 5.33329 7.50559C5.82664 7.17595 6.40666 7 7 7ZM0 0H20V2H0V0Z" fill="#008F83"/>
                </svg>
                Grafo Dinic
                </a>
                <a href="/graph?algorithm=edmonds" className={this.props.highlighted === "edmonds" ? styles.highlighted_btn_sidebar : styles.btn_sidebar}>
                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 20 20" fill="none">
                    <path d="M11 18V20H9V18H1C0.734784 18 0.48043 17.8946 0.292893 17.7071C0.105357 17.5196 0 17.2652 0 17V3H20V17C20 17.2652 19.8946 17.5196 19.7071 17.7071C19.5196 17.8946 19.2652 18 19 18H11ZM2 16H18V5H2V16ZM11 7H16V9H11V7ZM11 11H16V13H11V11ZM7 7V10H10C10 10.5933 9.82405 11.1734 9.49441 11.6667C9.16476 12.1601 8.69623 12.5446 8.14805 12.7716C7.59987 12.9987 6.99667 13.0581 6.41473 12.9424C5.83279 12.8266 5.29824 12.5409 4.87868 12.1213C4.45912 11.7018 4.1734 11.1672 4.05764 10.5853C3.94189 10.0033 4.0013 9.40013 4.22836 8.85195C4.45542 8.30377 4.83994 7.83524 5.33329 7.50559C5.82664 7.17595 6.40666 7 7 7ZM0 0H20V2H0V0Z" fill="#008F83"/>
                </svg>
                Grafo Edmonds-Karp
                </a>
                <a href="/" className={styles.btn_sidebar}>
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" height="24" viewBox="0 0 24 24" width="24">
                    <path d="M17 16L21 12M21 12L17 8M21 12L7 12M13 16V17C13 18.6569 11.6569 20 10 20H6C4.34315 20 3 18.6569 3 17V7C3 5.34315 4.34315 4 6 4H10C11.6569 4 13 5.34315 13 7V8" stroke="#008F83" strokeLinecap="round" strokeLinejoin="round" strokeWidth="2"/>
                </svg>
                Logout
                </a>
            </div>
        );
    }
}