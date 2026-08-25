package ni.edu.uam.registropaciente.dao;

import ni.edu.uam.registropaciente.modelos.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientDAO {
    private List<Patient> patients;

    public PatientDAO() {
        patients = new ArrayList<>();
    }

    public void agregarPatiente(Patient patient){
        patients.add(patient);
    }

    public List<Patient> listarPatient(){
        return patients;
    }



}
