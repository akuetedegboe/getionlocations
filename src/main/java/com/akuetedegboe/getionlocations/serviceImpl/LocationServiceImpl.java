package com.akuetedegboe.getionlocations.serviceImpl;

import com.akuetedegboe.getionlocations.model.Location;
import com.akuetedegboe.getionlocations.repository.LocationRepository;
import com.akuetedegboe.getionlocations.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @Override
    public void saveLocation(Location location) {
        this.locationRepository.save(location);
    }

    @Override
    public Location getLocationById(long id) {
        Optional<Location> optional = locationRepository.findById(id);
        Location location = null;
        if (optional.isPresent()) {
            location = optional.get();
        } else {
            throw new RuntimeException(" Location not found for id :: " + id);
        }
        return location;
    }

    @Override
    public void deleteLocationById(long id) {
        this.locationRepository.deleteById(id);
    }

    @Override
    public Page<Location> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.locationRepository.findAll(pageable);
    }
}
