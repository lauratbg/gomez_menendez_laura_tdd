package org.kata.store.domain;

/*
 * TODO: Usuario de la tienda.
 *
 * Datos: id, nombre y apellidos.
 *
 * Requisitos (escribe primero el test de cada uno):
 *   - El nombre y los apellidos son obligatorios: ni nulos ni vacíos.
 *   - Si no se cumple, se lanza una excepción con un mensaje claro.
 */
public class User {
    private final String name;
	private final String surname;
	private final String id;

	public User(String id, String name, String surname) {
        if(name.isEmpty() || name.isBlank()){
			throw new IllegalArgumentException("The name must not be empty");
		}
		this.name = name;
		this.surname = surname;
		this.id = id;
	}

    public String getName() {
       return this.name;
    }

    public String getSurname() {
       return this.surname;
    }

    public String getId() {
       return this.id;
    }


}
