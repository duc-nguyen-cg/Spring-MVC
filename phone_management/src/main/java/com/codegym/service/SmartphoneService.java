package com.codegym.service;

import com.codegym.model.Smartphone;
import com.codegym.repository.ISmartphoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SmartphoneService implements ISmartphoneService{
    @Autowired
    private ISmartphoneRepository smartphoneRepository;

    @Override
    public Iterable<Smartphone> findAll() {
        return smartphoneRepository.findAll();

    }

    @Override
    public Page<Smartphone> findAllWithPagination(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Smartphone> smartphones = smartphoneRepository.findAll(pageRequest);
        return smartphones;
    }

    @Override
    public Optional<Smartphone> findById(Long id) {
        return smartphoneRepository.findById(id);
    }

    @Override
    public Smartphone save(Smartphone smartPhone) {
        return smartphoneRepository.save(smartPhone);
    }

    @Override
    public void remove(Long id) {
        smartphoneRepository.deleteById(id);
    }

    @Override
    public Iterable<Smartphone> findAllByProducerContaining(String producer){
        return smartphoneRepository.findAllByProducerContaining(producer);
    }
}
