# ⚽ ScoreSim 2026 - Control Panel

**ScoreSim 2026** es una plataforma de análisis predictivo y gestión deportiva diseñada para administrar plantillas de fútbol y simular escenarios competitivos a gran escala. La aplicación combina una interfaz de gestión de datos SQL con un potente motor de cálculo probabilístico.

---

## 📝 Descripción General
El sistema permite a los analistas y usuarios gestionar selecciones nacionales de cara al ciclo mundialista, permitiendo no solo la edición de datos, sino la proyección de resultados mediante simulaciones masivas (Monte Carlo).



## ✨ Funciones Principales

### 1. Gestión de Plantillas
* **Edición en Tiempo Real:** Ajuste de atributos clave como valoración global (**OVR**), dorsales y roles tácticos.
* **Roles Definidos:** Clasificación de jugadores en *Titular, Suplente, Reserva o Desconvocado*.
* **Persistencia:** Guardado directo en base de datos con actualización de caché automática.

### 2. Filtrado y Búsqueda Inteligente
* **Multifiltro:** Selección por nacionalidad y líneas de campo (Ataque, Medio, Defensa, Portero).
* **Buscador Dinámico:** Filtro por texto en tiempo real sobre el nombre y apellido del jugador.

### 3. Motor de Simulación (Monte Carlo)
* **Simulaciones Masivas:** Ejecución de **10,000 partidos** de forma asíncrona.
* **Algoritmo Probabilístico:** Procesa las medias y configuraciones de los equipos para generar reportes detallados de victoria, empate y rendimiento esperado.

### 4. Interfaz Profesional
* **Diseño Moderno:** UI optimizada con feedback visual, efectos *hover* en botones y renderizado condicional de celdas.
* **Experiencia de Usuario:** Uso de `SwingWorker` para evitar bloqueos de la interfaz durante procesos pesados.

---

## 🛠️ Requisitos Técnicos

* **Lenguaje:** Java 17 o superior.
* **Base de Datos:** MySQL / MariaDB (conector JDBC incluido).
* **Librerías:** Swing (Standard Java GUI).
* **Arquitectura:** Orientada a objetos con separación de estilos (`EstiloUI`) y acceso a datos (`DAO`).

---

## 📂 Instalación y Uso

1. **Base de Datos:** Importa el esquema SQL proporcionado en tu servidor local.
2. **Configuración:** Ajusta la clase `ConexionBD.java` con tus credenciales.
3. **Ejecución:** Inicia la aplicación desde la clase principal `ScoreSimApp`.
4. **Interacción:** Selecciona un jugador en la tabla para habilitar el panel de edición inferior.

------------------------------------------------------------
*Desarrollado para el análisis de rendimiento deportivo 2026.*
