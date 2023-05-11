package com.akuetedegboe.getionlocations.service;

import com.akuetedegboe.getionlocations.model.Voiture;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VoitureService {
    List<Voiture> getAllVoitures();

    void saveVoiture(Voiture voiture);

    Voiture getVoitureById(long id);
    void deleteVoitureById(long id);

    Page<Voiture> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}
