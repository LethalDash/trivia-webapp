# 🧠 Trivia de Cultura General — Proyecto Java Web

Aplicación web de trivia (preguntas de cultura general) hecha con **HTML + CSS + JavaScript** en el frontend y **Java Web (Servlets + JSP + JDBC/MySQL)** en el backend, desplegable en Tomcat 9.

## Características
- Set de preguntas guardado en **MySQL** (tabla `preguntas`), con 20 preguntas de ejemplo ya cargadas.
- **Cronómetro** de 15 segundos por pregunta (JavaScript), con bonus de puntos por responder rápido.
- **Puntaje** en tiempo real durante la partida.
- **Ranking** (tabla `ranking`) con el Top 10 histórico de jugadores.
- Formulario para **agregar nuevas preguntas** al set (`/agregar`), guardadas directamente en la base de datos.

## Estructura del proyecto
```
trivia-webapp/
├── pom.xml
├── Dockerfile
├── database/trivia_db.sql        <- script de la base de datos + preguntas iniciales
└── src/main/
    ├── java/com/trivia/
    │   ├── model/       (Question, RankingEntry)
    │   ├── dao/         (QuestionDAO, RankingDAO)
    │   ├── servlet/     (QuestionsServlet, AddQuestionServlet, RankingServlet)
    │   └── util/        (DBConnection)
    └── webapp/
        ├── index.jsp, trivia.jsp, ranking.jsp, agregar.jsp
        ├── WEB-INF/web.xml
        └── resources/css/style.css, resources/js/trivia.js
```

---

## 1. Cómo correrlo localmente en NetBeans

1. Abre NetBeans → **File → Open Project** → selecciona la carpeta `trivia-webapp`.
2. Verifica en **Services → Databases** que tienes MySQL corriendo en `localhost:3306` con usuario `root` (o ajusta las variables de entorno, ver abajo).
3. Crea la base de datos ejecutando el script `database/trivia_db.sql` (puedes hacerlo desde MySQL Workbench, la terminal `mysql`, o el panel de Services de NetBeans → clic derecho en tu conexión → **Execute Command...** pegando el script).
4. Clic derecho en el proyecto → **Properties → Run** → asegúrate que el servidor sea **Apache Tomcat 9**.
5. Clic derecho → **Run** (o Shift+F6). Se abrirá `http://localhost:8080/trivia-webapp/`.

Por defecto la app se conecta a `localhost:3306/trivia_db` con usuario `root` y contraseña vacía. Si tu MySQL usa otra configuración, define estas variables de entorno antes de arrancar Tomcat (o en las propiedades del servidor en NetBeans):
```
DB_HOST=localhost
DB_PORT=3306
DB_NAME=trivia_db
DB_USER=root
DB_PASSWORD=tu_password
```

---

## 2. Subir el proyecto a GitHub desde cero

Abre una terminal en la carpeta `trivia-webapp` y ejecuta:

```bash
# 1. Inicializar el repositorio local
git init
git add .
git commit -m "Proyecto inicial: Trivia de Cultura General"

# 2. Crear el repositorio en GitHub (opción A: manual)
#    - Ve a https://github.com/new
#    - Ponle un nombre, por ejemplo "trivia-webapp"
#    - NO marques "Add a README" (ya tenemos uno)
#    - Crea el repositorio

# 3. Conectar tu repo local con GitHub
git remote add origin https://github.com/TU_USUARIO/trivia-webapp.git
git branch -M main
git push -u origin main
```

Si prefieres usar el GitHub CLI (`gh`) en vez del paso 2 manual:
```bash
gh repo create trivia-webapp --public --source=. --remote=origin --push
```

A partir de aquí, cada vez que hagas cambios:
```bash
git add .
git commit -m "Descripción del cambio"
git push
```

---

## 3. Despliegue: por qué Netlify NO sirve aquí

Netlify solo aloja **sitios estáticos** (HTML/CSS/JS puro) o **funciones serverless** (Node, Go, etc.). Esta aplicación necesita un **contenedor de servlets Java (Tomcat)** corriendo de forma continua más una **base de datos MySQL**, algo que Netlify no ofrece. Si intentas subir este proyecto tal cual a Netlify, no funcionará.

Para este tipo de proyecto (Java + MySQL) las opciones típicas son **Render**, Railway, Fly.io o un VPS. Abajo el paso a paso con **Render**, usando el `Dockerfile` ya incluido.

## 4. Desplegar en Render (backend Java + Tomcat)

### Paso A: Base de datos MySQL
Render no ofrece MySQL administrado gratuito (solo PostgreSQL). Opciones sencillas y gratuitas para un proyecto académico:
- **Railway** (plan gratuito con MySQL) → https://railway.app
- **Aiven** (free tier de MySQL) → https://aiven.io
- **Clever Cloud** (free tier MySQL) → https://www.clever-cloud.com

Crea la base de datos en cualquiera de esos servicios, ejecuta ahí el script `database/trivia_db.sql`, y anota: host, puerto, usuario, contraseña y nombre de la base.

### Paso B: Desplegar la app Java en Render
1. Sube el proyecto a GitHub (paso 2 de arriba).
2. Entra a https://render.com → **New → Web Service**.
3. Conecta tu repositorio de GitHub `trivia-webapp`.
4. En **Environment**, elige **Docker** (Render detectará el `Dockerfile` automáticamente).
5. En **Environment Variables**, agrega:
   ```
   DB_HOST=<host de tu MySQL externo>
   DB_PORT=3306
   DB_NAME=trivia_db
   DB_USER=<tu usuario>
   DB_PASSWORD=<tu contraseña>
   ```
6. Deja el puerto por defecto (Render detecta el `EXPOSE 8080` del Dockerfile).
7. Clic en **Create Web Service**. Render construirá la imagen y desplegará automáticamente.
8. Cuando termine, te dará una URL pública tipo `https://trivia-webapp.onrender.com`.

Cada `git push` a la rama `main` disparará un nuevo build y despliegue automático en Render.

---

## 5. Notas para la sustentación académica
- La validación de la respuesta correcta ocurre en el navegador (JavaScript) para simplificar el flujo con `fetch`. En un entorno de producción real, esa validación debería hacerse en el servidor para evitar que alguien vea la respuesta correcta inspeccionando el código.
- El cronómetro, el puntaje y el set de preguntas cumplen con los requisitos pedidos: preguntas persistidas en MySQL, cronómetro y puntaje en JS, ranking persistido en MySQL, y formulario de alta de preguntas vía Servlet + JDBC.
