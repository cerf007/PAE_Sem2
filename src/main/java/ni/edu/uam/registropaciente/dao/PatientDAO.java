package ni.edu.uam.registropaciente.dao;

import ni.edu.uam.registropaciente.interfaces.Crud;
import ni.edu.uam.registropaciente.modelos.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientDAO implements Crud<Patient> {
    private List<Patient> patients;

    public PatientDAO(){
        patients = new ArrayList<>();
    }

    @Override
    public void agregar(Patient entidad) {
        patients.add(entidad);

    }

    @Override
    public List<Patient> obtenerRegistros() {
        return patients;
    }
}
