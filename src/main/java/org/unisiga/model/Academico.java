package org.unisiga.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa al profesor encargado de dictar cátedras.
 */
public class Academico extends MiembroUniversitario {
    private String idEmpleado;
    private String tipoContrato;
    private Departamento departamento; // Relación de agregación
    private List<Seccion> seccionesDictadas;

    public Academico(String rut, String nombre, String correo, String password, String idEmpleado, String tipoContrato) {
        super(rut, nombre, correo, password);
        this.idEmpleado = idEmpleado;
        this.tipoContrato = tipoContrato;
        this.seccionesDictadas = new ArrayList<>();
    }

    @Override
    public boolean login(String password) {
        // TODO: Implementar validación simulada de MFA docente (requiere que el password contenga '@')
        //throw new UnsupportedOperationException("Método login() no implementado aún.");
        
        if (password == null || !password.contains("@")) {
            return false;
        }
        return this.password.equals(password);
    }

    /**
     * Registra la nota de un estudiante para una evaluación de la asignatura.
     * [REGLAS]: Validar parámetros, rango de notas [1.0, 7.0] y que la evaluación pertenezca a la asignatura.
     */
    public boolean registrarNota(Inscripcion inscripcion, Evaluacion evaluacion, float valorNota) {
        // TODO: Implementar la validación e inserción/actualización de la nota (Tres Vías)
        //throw new UnsupportedOperationException("Método registrarNota() no implementado aún.");
        
        if (inscripcion == null || evaluacion == null) {
            return false;
        }

        if (valorNota < 1.0f || valorNota > 7.0f) {
            return false;
        }
        
        Academico docenteDeLaSeccion = inscripcion.getSeccion().getDocenteDicta();
        
        if (docenteDeLaSeccion == null || !docenteDeLaSeccion.equals(this)) {
            return false; //El profesor no dicta esta sección o no está asignado
        }

        //La evaluación debe pertenecer estrictamente a la misma asignatura de la sección inscrita
        Asignatura asignaturaCursada = inscripcion.getSeccion().getAsignatura();
        if (!evaluacion.getAsignatura().equals(asignaturaCursada)) {
            return false; //La evaluación corresponde a otra asignatura diferente
        }

        for (Calificacion califExistente : inscripcion.getCalificaciones()) {
            if (califExistente.getEvaluacion().equals(evaluacion)) {
                califExistente.setNota(valorNota); //Actualiza la nota
                return true;
            }
        }

        Calificacion nuevaCalificacion = new Calificacion(valorNota, inscripcion, evaluacion);

        inscripcion.getCalificaciones().add(nuevaCalificacion);
        evaluacion.getCalificaciones().add(nuevaCalificacion);

        return true;
    }

    // Getters y Setters
    public String getIdEmpleado() { return idEmpleado; }
    public Departamento getDepartamento() { return departamento; }
    public void setDepartamento(Departamento depto) { this.departamento = depto; }
    public List<Seccion> getSeccionesDictadas() { return seccionesDictadas; }
}
