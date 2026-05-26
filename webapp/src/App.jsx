import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import RegistroCliente from './pages/RegistroCliente';
import ConsultaCliente from './pages/ConsultaCliente';

function App() {
  return (
    <BrowserRouter>
      <nav className="navbar navbar-expand-lg navbar-dark bg-dark mb-4">
        <div className="container">
          <Link className="navbar-brand" to="/">Celsia Internet</Link>
          <div className="collapse navbar-collapse">
            <ul className="navbar-nav me-auto">
              <li className="nav-item">
                <Link className="nav-link" to="/">Registro de Servicios</Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to="/consulta">Consulta de Clientes</Link>
              </li>
            </ul>
          </div>
        </div>
      </nav>

      <div className="container">
        <Routes>
          <Route path="/" element={<RegistroCliente />} />
          <Route path="/consulta" element={<ConsultaCliente />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;