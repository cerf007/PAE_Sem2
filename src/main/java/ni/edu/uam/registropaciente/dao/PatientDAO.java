package ni.edu.uam.registropaciente.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ni.edu.uam.registropaciente.interfaces.Crud;
import ni.edu.uam.registropaciente.modelos.Patient;

public class PatientDAO implements Crud<Patient> {
    private ObservableList<Patient> patients;

    public PatientDAO(){
        patients = FXCollections.observableArrayList();
    }

    @Override
    public void agregar(Patient entidad) {
        patients.add(entidad);
    }

    @Override
    public ObservableList<Patient> obtenerRegistros() {
        return patients;
    }
}