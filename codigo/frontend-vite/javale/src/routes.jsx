import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from './pages/Home';
import Graph from './pages/Graph';
import './reset.css';
import './styles.css';

/**
 * 
 * Definição de rotas das páginas da aplicação
 */
export default function AppRoutes(){
    return (
        <BrowserRouter>
            <Routes>
                <Route index element={ <Home /> } />
                <Route path="graph" element={ <Graph /> } />
            </Routes>
        </BrowserRouter>
    );
}