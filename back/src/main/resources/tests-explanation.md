
## 🔁 1) ¿Cómo `verify` sabe si se ejecutó `save(...)`, si `save` pertenece al repositorio?

Porque `save` es un **método de un mock**, no del repositorio real. Cuando haces esto:

```java
@Mock
UserRepository userRepository;
```

Mockito **crea un objeto simulado** de `UserRepository`, y ese objeto:

* **No ejecuta lógica real**, sino que **registra** internamente cada método que se llama sobre él.
* Cada vez que llamas `userRepository.save(...)`, Mockito **anota que se llamó ese método con esos argumentos**.

Entonces, cuando luego haces:

```java
verify(userRepository).save(user);
```

Mockito revisa su **registro interno** y verifica si `save(...)` fue llamado con ese `user`.

---

## 🔍 2) ¿Qué hacen `verify`, `never`, y `save` por dentro?

### ✅ `verify(...)`

Es un **método de Mockito** que:

* Accede al historial de llamadas del mock.
* Compara si se hizo (o no) una llamada con los argumentos que se le pasaron.

> Internamente usa *proxy dinámico* o *bytecode manipulation* (como con ByteBuddy o CGLIB) para interceptar llamadas y guardarlas.

---

### 🔁 `never()`

Es un **verificador especial** que se usa junto con `verify(...)`.

Ejemplo:

```java
verify(userRepository, never()).save(any());
```

Significa: *“Verifica que nunca se haya llamado `save(...)` con ningún argumento.”*

> Internamente es solo una forma de decirle a `verify(...)`: espera **cero invocaciones**.

---

### 💾 `save(...)`

Este es un **método de la interfaz `UserRepository`**, pero en este contexto, **es un método simulado**.

Como `userRepository` es un mock, llamar a `save(...)` **no ejecuta lógica real**, solo registra que fue llamado.

---

## 🧠 ¿Cómo se relaciona todo?

1. Declara un mock:

   ```java
   @Mock UserRepository userRepository;
   ```

2. Llamas a un método del mock durante tu test:

   ```java
   userRepository.save(user);
   ```

3. Mockito guarda eso internamente.

4. Luego verificas si ocurrió:

   ```java
   verify(userRepository).save(user);       // lo llamó
   verify(userRepository, never()).save();  // no lo llamó
   ```