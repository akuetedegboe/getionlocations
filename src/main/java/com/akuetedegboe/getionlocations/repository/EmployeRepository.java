package com.akuetedegboe.getionlocations.repository;

import com.akuetedegboe.getionlocations.model.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeRepository extends JpaRepository<Employe,Long> {

    @Query("select e from Employe e where e.login = ?1 AND e.mdp = ?2")
    public Employe connect(String login,String mdp);
}
