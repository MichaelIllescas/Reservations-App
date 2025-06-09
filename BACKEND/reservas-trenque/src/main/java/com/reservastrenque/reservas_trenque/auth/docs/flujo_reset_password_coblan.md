# Flujo completo de restablecimiento de contraseña – COBLAN

## 🧩 FASE 1: Solicitud de restablecimiento de contraseña

### 1.1 Frontend – Usuario solicita recuperar contraseña
- El usuario accede a la ruta `/forgotPassword` e ingresa su dirección de correo.
- El formulario realiza una solicitud `POST` al backend con el email:

```
POST /auth/forgot-password
Body: { email: "usuario@correo.com" }
```

### 1.2 Backend – Generación y envío del token

**Controlador: `AuthController`**
```java
@PostMapping("/forgot-password")
public void forgotPassword(@RequestBody EmailDTO request) {
    authenticationService.sendPasswordResetToken(request.getEmail());
}
```

**Servicio: `AuthenticationService.sendPasswordResetToken(String email)`**
- Busca el usuario en la base de datos (`UserRepository.findByEmail`).
- Busca si ya existe un token para ese usuario (`tokenRepository.findByUser`).
- Si existe y está vencido, lo elimina.
- Si existe y aún está vigente, lanza excepción (opcional).
- Si no existe o fue eliminado, genera un nuevo token (`UUID`).
- Crea una instancia de `PasswordResetToken` con:
  - `token`
  - `user`
  - `expirationDate = ahora + 30 minutos`
- Guarda el token (`tokenRepository.save`).
- Envía un email al usuario con el link: `frontendURL?token=...`

### Modelo: `PasswordResetToken`
```java
@Entity
public class PasswordResetToken {
    @Id @GeneratedValue private Long id;
    private String token;
    private LocalDateTime expirationDate;
    
    @OneToOne
    @JoinColumn(unique = true)
    private User user;
}
```

---

## 🧩 FASE 2: Restablecimiento de contraseña

### 2.1 Frontend – Usuario accede al enlace
- El usuario hace clic en el enlace recibido.
- Es dirigido a `/reset-password?token=abc123...`
- El frontend extrae el token con `useSearchParams`.
- Muestra formulario con nueva contraseña y confirmación.

### 2.2 Envío de nueva contraseña
- Al enviar el formulario, se realiza un `POST` (o `PUT`):
```
POST /auth/reset-password
Body: { token: "abc123", newPassword: "nuevaClave" }
```

### 2.3 Backend – Validación y actualización

**Controlador: `AuthController`**
```java
@PostMapping("/reset-password")
public void resetPassword(@RequestBody ResetPasswordDTO request) {
    authenticationService.resetPassword(request.getToken(), request.getNewPassword());
}
```

**Servicio: `AuthenticationService.resetPassword(String token, String newPassword)`**
- Busca el token por su string (`tokenRepository.findByToken`).
- Verifica si existe.
- Verifica si está vencido (`expirationDate < now`).
- Si está vencido, lanza excepción.
- Si es válido:
  - Obtiene el usuario relacionado.
  - Encripta la nueva contraseña (`PasswordEncoder`).
  - Actualiza y guarda el usuario.
  - Elimina el token.

### Entidades involucradas
- `User`: se actualiza la contraseña.
- `PasswordResetToken`: se crea al solicitar el cambio y se elimina al usarse o expirar.

---

  ## 🛡 Seguridad y mejoras posibles

- Solo se permite un token por usuario (restricción `unique`).
- El token expira en 30 minutos (`LocalDateTime`).
- Es recomendable eliminar tokens expirados periódicamente (cron).
- Se puede permitir sobrescribir tokens activos o mostrar un mensaje claro si ya existe uno.
