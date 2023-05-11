package com.akuetedegboe.getionlocations.repository;

import com.akuetedegboe.getionlocations.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
}
