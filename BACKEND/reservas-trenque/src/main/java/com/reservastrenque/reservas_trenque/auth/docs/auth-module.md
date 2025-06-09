# Módulo Auth

Este módulo se encarga de todas las operaciones relacionadas con la **autenticación de usuarios** dentro del sistema Coblan. Permite iniciar y cerrar sesión mediante JWT almacenado en cookies seguras, validar la sesión actual y preparar endpoints para funcionalidades de recuperación de contraseña.

---

## 🔍 Responsabilidades

- Autenticar usuarios y generar JWT
- Almacenar el token en cookies seguras (HTTP-only)
- Eliminar cookies para cerrar sesión
- Validar sesión activa desde el token
- Retornar información del usuario autenticado

---

## 📚 Estructura general del controlador

```
com.coblan.auth.controller
└── AuthController.java
```

---

## 🔧 Endpoints documentados

### POST `/auth/login`
- ✨ Autentica al usuario y devuelve sus datos en caso de éxito.
- Genera y setea un JWT en una cookie segura.

#### Respuestas posibles:
| Código | Descripción                          | Formato JSON                        |
|--------|-----------------------------------|-------------------------------------|
| 200    | Autenticación exitosa               | `UserDTO`                           |
| 401    | Credenciales inválidas               | `{ "error": "Credenciales inválidas" }` |
| 403    | Usuario deshabilitado              | `{ "error": "Usuario deshabilitado" }` |
| 500    | Error interno del servidor         | `{ "error": "Error al conectarse..." }` |

---

### POST `/auth/logout`
- ❌ Elimina la cookie de sesión y cierra la sesión del usuario.

#### Respuestas posibles:
| Código | Descripción         |
|--------|--------------------|
| 200    | Logout exitoso     |

---

### GET `/auth/me`
- 👤 Retorna información básica del usuario autenticado.
- Requiere tener la cookie `authToken` presente y válida.

#### Respuestas posibles:
| Código | Descripción                              | Formato JSON                                |
|--------|-----------------------------------------|---------------------------------------------|
| 200    | Usuario autenticado                     | `{ "username": "...", "roles": [...], ... }` |
| 401    | Token ausente o sesión inválida         | `{ "error": "No hay sesión activa" }`        |

---

## 🚫 Formato de error documentado

Para Swagger y respuestas de error JSON se usa:

```json
{
  "error": "Mensaje descriptivo del error"
}
```

### DTO para documentar errores:
```java
public record ErrorResponseDTO(
  String error
) {}
```

---

## ✏️ Notas finales

- Todas las respuestas de error están documentadas con `@ApiResponse` y `@Schema` en Swagger.
- Se utilizan cookies HTTP-only para evitar ataques XSS.
- El token JWT es generado y validado mediante el `JwtService`.
- El controlador está desacoplado y preparado para extender funcionalidades como restablecimiento de contraseña o doble autenticación.