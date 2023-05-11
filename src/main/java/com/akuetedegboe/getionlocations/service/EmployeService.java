package com.akuetedegboe.getionlocations.service;

import com.akuetedegboe.getionlocations.model.Employe;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeService {
    List<Employe> getAllEmployes();

    void saveEmploye(Employe employe);

    Employe getEmployeById(long id);
    void deleteEmployeById(long id);

    Page<Employe> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
    public Employe getConnect(String login,String mdp);
}
