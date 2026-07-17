package org.unisiga.model;

/**
 * Clase Abstracta Base que representa a cualquier miembro de la universidad.
 * [EVALUACIÓN]: El estudiante debe comprender el concepto de herencia y atributos protegidos.
 */
public abstract class MiembroUniversitario {
    protected String rut;
    protected String nombre;
    protected String correoInstitucional;
    protected String password;

    public MiembroUniversitario(String rut, String nombre, String correoInstitucional, String password) {
        this.rut = rut;
        this.nombre = nombre;
        this.correoInstitucional = correoInstitucional;
        this.password = password;
    }

    /**
     * TODO: Firma abstracta para el inicio de sesión.
     * Cada subclase (Estudiante/Académico) debe sobreescribirla con sus reglas de negocio.
     */
    public abstract boolean login(String password);

    // Getters y Setters
    public String getRut() { return rut; }
    public void setRut(String rut) { this.rut = rut; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCorreoInstitucional() { return correoInstitucional; }
    public void setCorreoInstitucional(String correo) { this.correoInstitucional = correo; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
