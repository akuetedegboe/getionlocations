package com.akuetedegboe.getionlocations.service;

import com.akuetedegboe.getionlocations.model.Client;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ClientService {
    List<Client> getAllClients();

    void saveClient(Client client);

    Client getClientById(long id);
    void deleteClientById(long id);

    Page<Client> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
