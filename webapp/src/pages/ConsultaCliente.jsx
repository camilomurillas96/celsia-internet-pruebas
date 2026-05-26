import { useState } from 'react';
import api from '../services/api';

const ConsultaCliente = () => {
    const [identificacion, setIdentificacion] = useState('');
    const [resultado, setResultado] = useState(null);
    const [error, setError] = useState('');

    const handleBuscar = async (e) => {
        e.preventDefault();
        setError('');
        setResultado(null);
        try {
            const response = await api.get(`/clientes/${identificacion}/servicios`);
            setResultado(response.data);
        } catch (err) {
            setError(err.response?.data?.error || 'Cliente no encontrado o sin servicios.');
        }
    };

    return (
        <div className="card shadow-sm">
            <div className="card-header bg-dark text-white">
                <h4 className="mb-0">Consulta de Servicios Contratados</h4>
            </div>
            <div className="card-body">
                <form onSubmit={handleBuscar} className="d-flex gap-2 mb-4">
                    <input 
                        type="text" 
                        className="form-control" 
                        placeholder="Ingrese la identificación del cliente" 
                        value={identificacion}
                        onChange={(e) => setIdentificacion(e.target.value)}
                        required 
                    />
                    <button type="submit" className="btn btn-primary px-4">Buscar</button>
                </form>

                {error && <div className="alert alert-warning">{error}</div>}

                {resultado && (
                    <div className="mt-4">
                        <div className="row mb-4">
                            <div className="col-md-6">
                                <h5 className="text-secondary border-bottom pb-2">Datos Personales</h5>
                                <p className="mb-1"><strong>Nombre:</strong> {resultado.cliente.nombres} {resultado.cliente.apellidos}</p>
                                <p className="mb-1"><strong>Identificación:</strong> {resultado.cliente.tipoIdentificacion} {resultado.cliente.identificacion}</p>
                            </div>
                            <div className="col-md-6">
                                <h5 className="text-secondary border-bottom pb-2">Contacto</h5>
                                <p className="mb-1"><strong>Correo:</strong> {resultado.cliente.correoElectronico}</p>
                                <p className="mb-1"><strong>Celular:</strong> {resultado.cliente.numeroCelular}</p>
                            </div>
                        </div>

                        <h5 className="text-secondary border-bottom pb-2">Portafolio de Servicios</h5>
                        <div className="table-responsive">
                            <table className="table table-hover table-bordered">
                                <thead className="table-light">
                                    <tr>
                                        <th>Servicio</th>
                                        <th>Fecha de Inicio</th>
                                        <th>Última Facturación</th>
                                        <th>Último Pago</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {resultado.servicios.map((srv, index) => (
                                        <tr key={index}>
                                            <td><span className="badge bg-info text-dark">{srv.servicio}</span></td>
                                            <td>{srv.fechaInicio}</td>
                                            <td>{srv.ultimaFacturacion}</td>
                                            <td>${srv.ultimoPago}</td>
                                        </tr>
                                    ))}
                                </tbody>
                            </table>
                        </div>
                    </div>
                )}
            </div>
        </div>
    );
};

export default ConsultaCliente;