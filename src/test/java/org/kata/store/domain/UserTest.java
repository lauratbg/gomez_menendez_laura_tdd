package org.kata.store.domain;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/*
 * TODO: Tests unitarios de User.
 *
 * Ideas de tests:
 *   - shouldCreateUserWithIdNameAndSurname
 *   - shouldRejectEmptyName
 *   - shouldRejectNullSurname
 *
 * Pista: assertThrows(...) te devuelve la excepción, así puedes comprobar también su mensaje.
 */
class UserTest {

    @Test
    void shouldCreateUserWithIdNameAndSurname() {
		// Arrange
		String name = "Nombre";
        String surname = "Apellido";
        String id = "58799654Y";

        // Act
        User user = new User(id, name, surname);

        // Assert
        assertNotNull(user);
        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(surname, user.getSurname());

    }

    @Test
    void shouldRejectEmptyName() {
		// Arrange
		String name = "";
        String surname = "Apellido";
        String id = "58799654Y";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new User(id, name, surname)
        );
        assertNotNull(exception.getMessage());
        assertEquals("The name must not be empty or null", exception.getMessage());

    }

     @Test
    void shouldRejectNullName() {
		// Arrange
		String name = null;
        String surname = "Apellido";
        String id = "58799654Y";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new User(id, name, surname)
        );
        assertNotNull(exception.getMessage());
        assertEquals("The name must not be empty or null", exception.getMessage());

    }

    @Test
    void shouldRejectEmptySurname() {
		// Arrange
		String name = "Nombre";
        String surname = "";
        String id = "58799654Y";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new User(id, name, surname)
        );
        assertNotNull(exception.getMessage());
        assertEquals("The surname must not be empty or null", exception.getMessage());

    }

     @Test
    void shouldRejectNullSurname() {
		// Arrange
		String name = "Nombre";
        String surname = null;
        String id = "58799654Y";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new User(id, name, surname)
        );
        assertNotNull(exception.getMessage());
        assertEquals("The surname must not be empty or null", exception.getMessage());

    }

    @Test
    void shouldRejectEmptyId() {
		// Arrange
		String name = "Nombre";
        String surname = "Apellido";
        String id = "";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new User(id, name, surname)
        );
        assertNotNull(exception.getMessage());
        assertEquals("The id must not be empty or null", exception.getMessage());
    }

    @Test
    void shouldRejectNullId() {
		// Arrange
		String name = "Nombre";
        String surname = "Apellido";
        String id = null;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new User(id, name, surname)
        );
        assertNotNull(exception.getMessage());
        assertEquals("The id must not be empty or null", exception.getMessage());
    }
}
