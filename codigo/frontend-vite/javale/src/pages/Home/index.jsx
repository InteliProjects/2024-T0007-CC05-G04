import styles from './style.module.css';
import logo from '../../assets/logo.png';
import javale from '../../assets/javale.svg';


/**
 * Página de Home
 * 
 */
export default function Home() {
    return (
        <div className={styles.container}>
            <div className={styles.div1}>
                <div className={styles.form}>
                    <div className={styles.palavra}>
                    <h1 className={styles.palavra_titulo}>Bem-vindo!</h1>
                    </div>
                    <div className={styles.botao}>
                        <a href='graph?algorithm=dinic&fromHome=true'>
                            <button className={styles.botao_impl}>Entrar</button>
                        </a>
                    </div>
                </div>
            </div>
            <div className={styles.div2}>
                <div className={styles.img}>
                    <div className={styles.logo}>
                        <img src={logo} alt="Logo" className={styles.logo_img} />
                        </div>
                        <div className={styles.javale}>
                            <img src={javale} alt="Javale" className={styles.titulo_javale}/>
                        </div>
                    </div>
                </div>
            </div>
    );
}