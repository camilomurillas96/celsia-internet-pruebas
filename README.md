# Prueba Técnica - Celsia Internet (Desarrollo y Operaciones)

Este repositorio contiene la solución a la prueba técnica para el cargo de **Desarrollo y Operaciones Aplicaciones III**. La solución está implementada bajo una arquitectura orientada a microservicios, separando el Frontend y el Backend en contenedores independientes de Docker.

## 🚀 Tecnologías Utilizadas

* **Backend:** Java 21, Spring Boot 3, Spring Data JPA, Hibernate, Validation.
* **Frontend:** React, Vite, Bootstrap, Axios.
* **Base de Datos:** PostgreSQL 15.
* **Infraestructura:** Docker, Docker Compose.

## 🛠️ Instrucciones de Despliegue

Para levantar la infraestructura de forma desacoplada, ejecute los siguientes comandos desde la raíz del proyecto:

1. **Levantar Base de Datos y API:**
   ```bash
   cd api
   docker-compose up -d --build

2. **Levantar Frontend:**
   ```bash
   cd webapp
   docker-compose up -d --build


# 📄 2. PRUEBA TEÓRICO-PRÁCTICA

## 2.1. Diagrama de Componentes

## 2.2. Mecanismos de seguridad para protección de datos

Para garantizar un acceso seguro a los datos en un entorno productivo, implementaría:

Autenticación y Autorización: Uso de JWT (JSON Web Tokens) para validar la identidad y aplicar RBAC (Control de Acceso Basado en Roles) en los endpoints de Spring Security.

Cifrado: Uso estricto de HTTPS/TLS para cifrar los datos en tránsito entre el frontend y el backend. Para datos sensibles en reposo (como contraseñas), utilizar algoritmos de hashing como BCrypt.

Protección contra Inyecciones: El uso actual de Spring Data JPA/Hibernate previene ataques de SQL Injection mediante consultas parametrizadas.

Políticas de Red: Configuración estricta de CORS permitiendo peticiones únicamente desde el dominio oficial del frontend, además de implementar un Rate Limiter para mitigar ataques DDoS.

## 2.3. Estrategia de escalabilidad (Crecimiento de 1,000,000 de clientes/año)

Dado el alto volumen proyectado, la aplicación debe escalar horizontalmente:

Orquestación de Contenedores: Migrar de Docker Compose a un clúster de Kubernetes (K8s) o AWS ECS, lo que permite crear réplicas del Backend dinámicamente basadas en el consumo de CPU/Memoria (HPA - Horizontal Pod Autoscaler).

Balanceo de Carga: Colocar un Application Load Balancer (ALB) delante de los microservicios para distribuir el tráfico uniformemente.

Escalabilidad de Base de Datos: Implementar un esquema Master-Slave (Read Replicas) en PostgreSQL, dirigiendo todas las consultas de lectura a las réplicas y dejando el Master exclusivamente para escrituras y registros nuevos.

Caché Distribuida: Integrar Redis para almacenar consultas repetitivas (como catálogos de planes de internet) y reducir la carga en la base de datos.

## 2.4. Patrones de Diseño Recomendados
Arquitectura Hexagonal (Puertos y Adaptadores): Utilizada para aislar la lógica de negocio (Core) del framework (Spring) y de la base de datos, facilitando pruebas unitarias y futuras migraciones.

Patrón DTO (Data Transfer Object): Implementado en esta solución. Evita exponer las entidades directamente (y posibles datos sensibles o metadatos de persistencia) a través de los controladores REST.

Patrón Repository: Implementado mediante JpaRepository para centralizar y abstraer la lógica de acceso a datos de la capa de servicio.

## 2.5. Recomendaciones para optimizar el manejo y persistencia de datos
Considerando la alta transaccionalidad:

Connection Pooling: Configurar eficientemente HikariCP (por defecto en Spring Boot) ajustando el tamaño del pool (maximum-pool-size) según las capacidades del servidor para reutilizar conexiones a la BD.

Indexación Estratégica: Crear índices (B-Tree) en PostgreSQL para los campos que son criterio de búsqueda frecuente, específicamente el campo identificacion en ambas tablas.

Procesamiento Asíncrono: Delegar procesos no bloqueantes a un Message Broker como RabbitMQ, permitiendo que la API responda inmediatamente al cliente.

Paginación: Implementar Pageable de Spring Data en los endpoints de consulta masiva para evitar sobrecargar la memoria RAM del servidor.

# 🌐 3. REDES

## 3.1. Diferencia entre un router y un switch
Switch: Opera en la Capa 2 (Enlace de Datos). Conecta múltiples dispositivos dentro de una misma red local (LAN) y dirige el tráfico internamente usando las direcciones físicas MAC de los dispositivos. Se usa para interconectar los computadores y servidores dentro de un mismo edificio u oficina.

Router: Opera en la Capa 3 (Red). Conecta diferentes redes entre sí y enruta paquetes de datos utilizando direcciones lógicas IP. Se usa para conectar la red LAN de la oficina con el exterior (Internet) o con otras sucursales.

## 3.2. Siete capas del modelo OSI

Física: Transmisión de señales físicas (bits) a través del medio (cables de fibra óptica, cobre, ondas de radio).

Enlace de Datos: Direccionamiento físico (MAC), control de flujo y detección de errores nodo a nodo (ej. Ethernet).

Red: Enrutamiento de paquetes entre diferentes redes usando direcciones IP (ej. IPv4, IPv6, ICMP).

Transporte: Conexión de extremo a extremo, segmentación y garantía de entrega (ej. TCP, UDP).

Sesión: Establece, mantiene y termina las sesiones de comunicación entre aplicaciones.

Presentación: Traduce, formatea y cifra los datos para que la capa de aplicación los entienda (ej. SSL/TLS, JPEG, ASCII).

Aplicación: Interfaz directa con los procesos de red del usuario o software (ej. HTTP, FTP, DNS, SMTP).

## 3.3. Diferencias entre TCP y UDP
TCP (Transmission Control Protocol): Es orientado a conexión y garantiza la entrega. Verifica que los paquetes lleguen en orden y sin errores (si falta uno, pide retransmisión). Ejemplo: Navegación web (HTTP), transferencia de archivos (FTP) o envío de correos.

UDP (User Datagram Protocol): Es sin conexión. Envía datos constantemente sin verificar si el receptor los recibe, lo que lo hace mucho más rápido pero menos confiable. Ejemplo: Transmisiones en vivo (Streaming de video) o llamadas VoIP.

## 3.4. Máscara de subred y su uso

Una máscara de subred es un número de 32 bits que acompaña a una dirección IP para distinguir qué porción de dicha IP identifica a la "Red" y qué porción identifica al "Host" (dispositivo).
Al tomar bits prestados de la porción de host y asignarlos a la porción de red (proceso llamado subnetting), se divide una red principal grande en subredes lógicas más pequeñas. Esto se utiliza para:

Mejorar el rendimiento al reducir los dominios de broadcast.

Aumentar la seguridad aislando departamentos (ej. separar la red de contabilidad de la de invitados).

## 3.5. Protocolos de enrutamiento dinámico
A diferencia del enrutamiento estático, los protocolos dinámicos permiten a los routers descubrir automáticamente rutas y adaptarse a cambios.

OSPF (Open Shortest Path First): Protocolo de estado de enlace (Link-State). Los routers comparten un mapa completo de su área y utilizan el algoritmo de Dijkstra para calcular la ruta más corta y eficiente basada en el costo (ancho de banda).

BGP (Border Gateway Protocol): Protocolo de vector de ruta (Path-Vector). Es el protocolo principal que hace funcionar Internet. Enruta tráfico entre diferentes Sistemas Autónomos basándose en políticas de red y cantidad de saltos, no solo en la velocidad del enlace.