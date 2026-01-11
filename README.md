

## 📝 Guía de Configuración para el Equipo

Para que el proyecto funcione en tu computadora, sigue estos pasos:

### 1. Configuración de la Base de Datos

1. Asegúrate de tener instalado **MySQL**
2. Ejecuta el script SQL que se encuentra en la carpeta del proyecto ( `database_setup.sql`) para crear las tablas en tu servidor local.

### 2. Archivo de Credenciales (IMPORTANTE)

Como medida de seguridad, el archivo que contiene la contraseña de la base de datos no se sube a GitHub. Debes crearlo tú mismo:

1. En la carpeta raíz del proyecto (donde está el archivo `pom.xml`), crea un archivo nuevo llamado **`db.properties`**.
2. Copia y pega el siguiente contenido


*Si no tienes contraseña en tu MySQL local, deja el espacio después del `=` vacío.*

### 3. Dependencias

Este proyecto utiliza **Maven**. Al abrirlo por primera vez en NetBeans, haz clic derecho en el proyecto y selecciona **"Clean and Build"**. Esto descargará automáticamente el conector de MySQL (versión 9.x) necesario para la conexión.

---

### Resumen final para ti:

* **En tu código:** Usa `Conexion.getConexion()` cada vez que necesites hablar con la base de datos.
* **En Git:** Asegúrate de que el archivo `.gitignore` tenga la línea `db.properties`.
* **Seguridad:** Al dejar el campo `db.password=` vacío en tu archivo local, tu código funcionará perfecto y no arriesgas la seguridad de otros.

