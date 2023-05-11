package com.akuetedegboe.getionlocations.serviceImpl;

import com.akuetedegboe.getionlocations.model.Voiture;
import com.akuetedegboe.getionlocations.repository.VoitureRepository;
import com.akuetedegboe.getionlocations.service.VoitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class VoitureServiceImpl implements VoitureService {

    @Autowired
    private VoitureRepository voitureRepository;

    @Override
    public List<Voiture> getAllVoitures() {
        return voitureRepository.findAll();
    }

    @Override
    public void saveVoiture(Voiture voiture) {
        this.voitureRepository.save(voiture);
    }

    @Override
    public Voiture getVoitureById(long id) {
        Optional<Voiture> optional = voitureRepository.findById(id);
        Voiture voiture = null;
        if (optional.isPresent()) {
            voiture = optional.get();
        } else {
            throw new RuntimeException(" Voiture not found for id :: " + id);
        }
        return voiture;
    }

    @Override
    public void deleteVoitureById(long id) {
        this.voitureRepository.deleteById(id);
    }

    @Override
    public Page<Voiture> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.voitureRepository.findAll(pageable);
    }
}
