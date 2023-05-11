package com.akuetedegboe.getionlocations.serviceImpl;

import com.akuetedegboe.getionlocations.model.Employe;
import com.akuetedegboe.getionlocations.repository.EmployeRepository;
import com.akuetedegboe.getionlocations.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class EmployeServiceImpl implements EmployeService {

    @Autowired
    private EmployeRepository employeRepository;

    @Override
    public List<Employe> getAllEmployes() {
        return employeRepository.findAll();
    }

    @Override
    public void saveEmploye(Employe employe) {
        this.employeRepository.save(employe);
    }

    @Override
    public Employe getEmployeById(long id) {
        Optional<Employe> optional = employeRepository.findById(id);
        Employe employe = null;
        if (optional.isPresent()) {
            employe = optional.get();
        } else {
            throw new RuntimeException(" Employe not found for id :: " + id);
        }
        return employe;
    }

    @Override
    public void deleteEmployeById(long id) {
        this.employeRepository.deleteById(id);
    }

    @Override
    public Page<Employe> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.employeRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Employe getConnect(String login,String mdp){
        return employeRepository.connect(login, mdp);
    }
}
