package com.codegym.service;

import com.codegym.model.Smartphone;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ISmartphoneService {
    Iterable<Smartphone> findAll();

    Page<Smartphone> findAllWithPagination(Integer page, Integer size);

    Optional<Smartphone> findById(Long id);

    Smartphone save(Smartphone smartPhone);

    void remove(Long id);

    Iterable<Smartphone> findAllByProducerContaining(String producer);

}
