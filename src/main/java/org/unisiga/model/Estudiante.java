package org.unisiga.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Representa al alumno de la universidad.
 * [EVALUACIÓN]: El estudiante debe implementar el encapsulamiento y el método de inscripción.
 */
public class Estudiante extends MiembroUniversitario {
    private String matricula;
    private int anioIngreso;
    private float promedioPpa;
    private List<Inscripcion> inscripciones;

    public Estudiante(String rut, String nombre, String correo, String password, String matricula, int anioIngreso, float promedioPpa) {
        super(rut, nombre, correo, password);
        this.matricula = matricula;
        this.anioIngreso = anioIngreso;
        this.promedioPpa = promedioPpa;
        this.inscripciones = new ArrayList<>();
    }

    @Override
    public boolean login(String password) {
        // TODO: Implementar validación simulada de clave del estudiante (largo mínimo de 8 caracteres)
        //throw new UnsupportedOperationException("Método login() no implementado aún.");
        
        if (password == null || password.length() < 8) {
            return false;
        }
        return this.password.equals(password);
    }

    /**
     * Realiza el proceso de inscripción en una sección.
     * [REGLAS]: Validar que la sección no sea nula y que cuente con cupos disponibles.
     */
    public boolean inscribirSeccion(Seccion seccion) {
        // TODO: Implementar la lógica del control de cupos y la creación de la clase de asociación 'Inscripcion'
        // No olvides agregar la nueva inscripción tanto a la lista de este estudiante como a la de la sección.
        //throw new UnsupportedOperationException("Método inscribirSeccion() no implementado aún.");
        
        //Validando si la seccion es nula
        if (seccion == null) {
            return false;
        }
        
        if (seccion.getInscripciones().size() >= seccion.getCupoMaximo()) {
            return false; //Sin cupos disponibles
        }
        
        //Evitar doble matricula
        for (Inscripcion insc : this.inscripciones) {
            if (insc.getSeccion().equals(seccion)) {
                return false; //Ya está inscrito en esta seccion
            }
        }
        
        Inscripcion nuevaInscripcion = new Inscripcion(this, seccion);

        this.inscripciones.add(nuevaInscripcion);
        seccion.getInscripciones().add(nuevaInscripcion);

        return true;
    }


    // Getters y Setters
    public String getMatricula() { return matricula; }
    public float getPromedioPpa() { return promedioPpa; }
    public List<Inscripcion> getInscripciones() { return inscripciones; }
}
