package com.akuetedegboe.getionlocations.repository;

import com.akuetedegboe.getionlocations.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location,Long> {
}
