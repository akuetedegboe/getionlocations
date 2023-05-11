package com.akuetedegboe.getionlocations.service;

import com.akuetedegboe.getionlocations.model.Location;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LocationService {
    List<Location> getAllLocations();

    void saveLocation(Location location);

    Location getLocationById(long id);
    void deleteLocationById(long id);

    Page<Location> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}
