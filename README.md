# 🏛️ TurismoSF — SICAT (Sistema de Información Categórica de Turismo)

![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3.0-6DB33F?style=flat-square&logo=springboot&logoColor=white)
![Java](https://img.shields.io/badge/Java-21-ED8B00?style=flat-square&logo=openjdk&logoColor=white)
![Angular](https://img.shields.io/badge/Angular-21-DD0031?style=flat-square&logo=angular&logoColor=white)
![TypeScript](https://img.shields.io/badge/TypeScript-5.9-3178C6?style=flat-square&logo=typescript&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-4169E1?style=flat-square&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-Enabled-2496ED?style=flat-square&logo=docker&logoColor=white)
![Node.js](https://img.shields.io/badge/Node.js-10.9.3-339933?style=flat-square&logo=nodedotjs&logoColor=white)
![Git](https://img.shields.io/badge/Git-VCS-F05032?style=flat-square&logo=git&logoColor=white)

> Plataforma web para la gestión, parametrización y consulta centralizada de categorías y recursos turísticos de la provincia de Santa Fe.

---

## 📋 Tabla de Contenidos

- [🏛️ TurismoSF — SICAT (Sistema de Información Categórica de Turismo)](#️-turismosf--sicat-sistema-de-información-categórica-de-turismo)
  - [📋 Tabla de Contenidos](#-tabla-de-contenidos)
  - [Acerca del Proyecto](#acerca-del-proyecto)
  - [🏗️ Arquitectura del Sistema](#️-arquitectura-del-sistema)
  - [🛠️ Requisitos Previos](#️-requisitos-previos)
  - [🚀 Guía de Instalación y Ejecución](#-guía-de-instalación-y-ejecución)
  - [🌐 Puertos Utilizados](#-puertos-utilizados)
  - [📂 Estructura del Repositorio](#-estructura-del-repositorio)
  - [🧪 API Endpoints y Pruebas](#-api-endpoints-y-pruebas)
    - [Endpoints principales de Categorías (`/api/categorias`):](#endpoints-principales-de-categorías-apicategorias)
  - [🤝 Guía de Contribución y Flujo de Trabajo](#-guía-de-contribución-y-flujo-de-trabajo)
  - [✒️ Autores](#️-autores)

---

## Acerca del Proyecto

**TurismoSF** aborda la necesidad de digitalizar y administrar de forma ordenada los catálogos y clasificaciones turísticas de la provincia. La aplicación permite:

- **Administración de Categorías:** Alta, modificación, baja y búsqueda parametrizada de categorías turísticas.
- **Integración y Persistencia:** Manejo automatizado del esquema relacional en PostgreSQL.
- **Consumo Dinámico:** Frontend reactivo construido en Angular para operar los datos de la API REST.

---

## 🏗️ Arquitectura del Sistema

El proyecto sigue una arquitectura desacoplada basada en contenedores:

```text
               ┌─────────────────────────────────┐
               │     Cliente / Web (Angular)     │
               │ Port 4200 -> Nginx en contenedor│
               └───────────────┬─────────────────┘
                               │  HTTP / REST
                               ▼
               ┌─────────────────────────────────┐
               │   Backend (Spring Boot 3.3.0)   │
               │           Port 8080             │
               └───────────────┬─────────────────┘
                               │  JDBC
                               ▼
               ┌─────────────────────────────────┐
               │   PostgreSQL 16 (db service)    │
               │           Port 5432             │
               └─────────────────────────────────┘
```

---

## 🛠️ Requisitos Previos

Para ejecutar la aplicación completa solo necesitás tener instalado **Docker Desktop**:

- 🐋 **Windows / Mac:** Descargar e instalar [Docker Desktop](https://www.docker.com/products/docker-desktop/).
- 🐧 **Linux:** Instalar `docker` y el plugin `docker-compose-plugin` según tu distribución.

---

## 🚀 Guía de Instalación y Ejecución

1. Cloná el repositorio:

   ```bash
   git clone https://github.com/RamassafraGH/TurismoSF.git
   cd sicat-sf
   ```

2. Levantá todo el sistema con un solo comando:

   ```bash
   docker compose up -d --build
   ```

> ⏳ _La primera vez puede demorar un par de minutos mientras se descargan las imágenes base y se compilan tanto el backend como el frontend._

3. Verificá que los tres servicios estén corriendo:

   ```bash
   docker compose ps
   ```

   Deberías ver `sicat_db`, `sicat_backend` y `sicat_frontend`, todos con estado `Up`.

4. Abrí la aplicación en el navegador:
   ```
   http://localhost:4200
   ```


## 🌐 Puertos Utilizados

| Servicio     | Puerto Local | Tecnología      | Descripción                     |
| ------------ | ------------ | --------------- | ------------------------------- |
| **Frontend** | `4200`       | Angular / Nginx | Interfaz web de usuario         |
| **Backend**  | `8080`       | Spring Boot     | API RESTful y lógica de negocio |
| **Database** | `5432`       | PostgreSQL      | Base de datos relacional        |

> [!WARNING]
> Si alguno de estos puertos ya está en uso en tu PC por otra aplicación, `docker compose up` fallará con un error de conflicto de puertos. Avisa al grupo para ajustar el mapeo en `docker-compose.yml`.

---

## 📂 Estructura del Repositorio

```text
TurismoSF/
├── 📁 backend/                  # Proyecto Spring Boot 3.3.0 (Java 21)
│   ├── 📁 .mvn/                 # Wrapper de Maven
│   ├── 📁 src/
│   │   ├── 📁 main/
│   │   │   ├── 📁 java/
│   │   │   │   └── 📁 com/municipalidad/sicatbackend/
│   │   │   │       ├── 📁 config/        # Configuración de CORS y ajustes de MVC
│   │   │   │       ├── 📁 controller/    # Controladores REST (`CategoriaTuristicaController`)
│   │   │   │       ├── 📁 entity/        # Entidades JPA (`CategoriaTuristica`)
│   │   │   │       ├── 📁 repository/    # Repositorios Spring Data JPA
│   │   │   │       └── 📁 service/       # Lógica de negocio y servicios
│   │   │   ├── 📁 resources/            # Configuración de aplicación
│   │   │   │   └── 📄 application.properties
│   │   ├── 📁 test/                    # Pruebas unitarias y de integración
│   ├── 📄 Dockerfile                  # Imagen Docker del backend
│   ├── 📄 mvnw                       # Wrapper Maven para Linux/macOS
│   ├── 📄 mvnw.cmd                   # Wrapper Maven para Windows
│   ├── 📄 pom.xml                    # Gestor de dependencias Maven
│   └── 📄 SicatBackendApplication.java # Clase principal de Spring Boot
├── 📁 db/                       # Scripts SQL y persistencia
│   └── 📁 init/                 # DDL/DML para inicializar la base de datos
├── 📁 frontend/                 # Aplicación web en Angular 21
│   ├── 📁 src/                  # Código fuente del frontend
│   │   ├── 📁 app/              # Lógica de la aplicación Angular
│   │   │   ├── 📁 components/    # Componentes UI reutilizables
│   │   │   │   ├── 📁 categoria-form/
│   │   │   │   └── 📁 categoria-list/
│   │   │   ├── 📁 shared/        # Modelos y servicios compartidos
│   │   │   ├── app.ts
│   │   │   ├── app.html
│   │   │   ├── app.routes.ts
│   │   │   ├── app.config.ts
│   │   │   └── app.css
│   ├── 📄 Dockerfile            # Imagen Docker del frontend
│   ├── 📄 nginx.conf           # Configuración de Nginx para servir el frontend
│   └── 📄 package.json          # Dependencias y scripts de Node
├── 📄 docker-compose.yml        # Orquestación de contenedores Docker
└── 📄 README.md                 # Documentación del proyecto

```

---

## 🧪 API Endpoints y Pruebas

Para probar las rutas de la API backend sin necesidad del frontend, puedes utilizar el archivo de pruebas HTTP incluido o importar la colección en herramientas como Postman.

### Endpoints principales de Categorías (`/api/categorias`):

| Método   | Endpoint               | Descripción                       |
| -------- | ---------------------- | --------------------------------- |
| `GET`    | `/api/categorias`      | Lista todas las categorías        |
| `GET`    | `/api/categorias/{id}` | Busca una categoría por ID        |
| `POST`   | `/api/categorias`      | Crea una nueva categoría          |
| `PUT`    | `/api/categorias/{id}` | Actualiza una categoría existente |
| `DELETE` | `/api/categorias/{id}` | Elimina una categoría             |

---

## 🤝 Guía de Contribución y Flujo de Trabajo

Para mantener el código ordenado entre todos los integrantes del equipo:

1. **Crear una rama para la tarea:**

```bash
git checkout -b feature/nombre-de-la-tarea

```

2. **Realizar commits claros:**

```bash
git commit -m "feat: agregar validación en creación de categorías"

```

3. **Subir los cambios y abrir Pull Request (PR):**

```bash
git push origin feature/nombre-de-la-tarea

```

---

## ✒️ Autores

- [RamassafraGH](https://www.google.com/search?q=https://github.com/RamassafraGH)
- _Equipo de Desarrollo - Proyecto TurismoSF_
