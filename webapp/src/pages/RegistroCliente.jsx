import { useState } from 'react';
import api from '../services/api';

const RegistroCliente = () => {
    const [cliente, setCliente] = useState({
        identificacion: '', nombres: '', apellidos: '', tipoIdentificacion: 'CC',
        fechaNacimiento: '', numeroCelular: '', correoElectronico: ''
    });
    
    const [servicio, setServicio] = useState({
        servicio: 'Internet 200 MB', fechaInicio: '', ultimaFacturacion: ''
    });

    const [mensaje, setMensaje] = useState({ texto: '', tipo: '' });

    const handleClienteChange = (e) => setCliente({ ...cliente, [e.target.name]: e.target.value });
    const handleServicioChange = (e) => setServicio({ ...servicio, [e.target.name]: e.target.value });

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            // crear el cliente
            await api.post('/clientes', cliente);
            
            // Si el cliente se crea (o ya existía pero lo controlamos en backend), creamos el servicio
            await api.post('/servicios', {
                identificacion: cliente.identificacion,
                ...servicio
            });

            setMensaje({ texto: '¡Registro exitoso!', tipo: 'success' });
            
            // Limpiar formularios
            setCliente({ ...cliente, identificacion: '', nombres: '', apellidos: '', fechaNacimiento: '', numeroCelular: '', correoElectronico: ''});
            setServicio({ ...servicio, fechaInicio: '', ultimaFacturacion: '' });
            
        } catch (error) {
            const errorMsg = error.response?.data?.error || 'Error al procesar la solicitud';
            setMensaje({ texto: errorMsg, tipo: 'danger' });
        }
    };

    return (
        <div className="card shadow-sm mb-5">
            <div className="card-header bg-primary text-white">
                <h4 className="mb-0">Captura de Información y Contratación</h4>
            </div>
            <div className="card-body">
                {mensaje.texto && <div className={`alert alert-${mensaje.tipo}`}>{mensaje.texto}</div>}
                
                <form onSubmit={handleSubmit}>
                    <h5 className="border-bottom pb-2 text-primary">1. Datos del Cliente</h5>
                    <div className="row mb-3">
                        <div className="col-md-4">
                            <label>Tipo Documento</label>
                            <select name="tipoIdentificacion" className="form-select" value={cliente.tipoIdentificacion} onChange={handleClienteChange} required>
                                <option value="CC">Cédula</option>
                                <option value="TI">Tarjeta de Identidad</option>
                                <option value="CE">Cédula de Extranjería</option>
                                <option value="RC">Registro Civil</option>
                            </select>
                        </div>
                        <div className="col-md-4">
                            <label>Identificación</label>
                            <input type="text" name="identificacion" className="form-control" value={cliente.identificacion} onChange={handleClienteChange} required />
                        </div>
                        <div className="col-md-4">
                            <label>Fecha de Nacimiento</label>
                            <input type="date" name="fechaNacimiento" className="form-control" value={cliente.fechaNacimiento} onChange={handleClienteChange} required />
                        </div>
                    </div>
                    <div className="row mb-3">
                        <div className="col-md-6">
                            <label>Nombres</label>
                            <input type="text" name="nombres" className="form-control" value={cliente.nombres} onChange={handleClienteChange} required />
                        </div>
                        <div className="col-md-6">
                            <label>Apellidos</label>
                            <input type="text" name="apellidos" className="form-control" value={cliente.apellidos} onChange={handleClienteChange} required />
                        </div>
                    </div>
                    <div className="row mb-4">
                        <div className="col-md-6">
                            <label>Número Celular</label>
                            <input type="text" name="numeroCelular" className="form-control" value={cliente.numeroCelular} onChange={handleClienteChange} required />
                        </div>
                        <div className="col-md-6">
                            <label>Correo Electrónico</label>
                            <input type="email" name="correoElectronico" className="form-control" value={cliente.correoElectronico} onChange={handleClienteChange} required />
                        </div>
                    </div>

                    <h5 className="border-bottom pb-2 text-primary">2. Servicio a Contratar</h5>
                    <div className="row mb-4">
                        <div className="col-md-4">
                            <label>Plan / Servicio</label>
                            <select name="servicio" className="form-select" value={servicio.servicio} onChange={handleServicioChange} required>
                                <option value="Internet 200 MB">Internet 200 MB</option>
                                <option value="Internet 400 MB">Internet 400 MB</option>
                                <option value="Internet 600 MB">Internet 600 MB</option>
                                <option value="Directv Go">Directv Go</option>
                                <option value="Paramount+">Paramount+</option>
                                <option value="Win+">Win+</option>
                            </select>
                        </div>
                        <div className="col-md-4">
                            <label>Fecha de Inicio</label>
                            <input type="date" name="fechaInicio" className="form-control" value={servicio.fechaInicio} onChange={handleServicioChange} required />
                        </div>
                        <div className="col-md-4">
                            <label>Última Facturación</label>
                            <input type="date" name="ultimaFacturacion" className="form-control" value={servicio.ultimaFacturacion} onChange={handleServicioChange} required />
                        </div>
                    </div>

                    <button type="submit" className="btn btn-success w-100 py-2 fw-bold">Guardar Registro y Contratar Servicio</button>
                </form>
            </div>
        </div>
    );
};

export default RegistroCliente;