package com.codegym.controller;

import com.codegym.model.Smartphone;
import com.codegym.service.ISmartphoneService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
@RequestMapping("/smartphones")
public class SmartphoneController {
    @Autowired
    private ISmartphoneService smartphoneService;

    @GetMapping("/list")
    public ModelAndView getAllSmartphonePage() {
        ModelAndView modelAndView = new ModelAndView("/phones/list");
        modelAndView.addObject("smartphones", smartphoneService.findAll());
        return modelAndView;
    }


    @PostMapping
    public ResponseEntity<Smartphone> createSmartphone(@RequestBody Smartphone smartphone) {
        return new ResponseEntity<>(smartphoneService.save(smartphone), HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<Iterable<Smartphone>> allPhones(@RequestParam(required = false) String search){
        if (!search.isEmpty()){
            return new ResponseEntity<>(smartphoneService.findAllByProducerContaining(search), HttpStatus.OK);
        }

        Iterable<Smartphone> smartphones = smartphoneService.findAll();
        if (!smartphones.iterator().hasNext()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(smartphoneService.findAll(), HttpStatus.OK);
    }


//    @GetMapping("/search")
//    public ResponseEntity<Iterable<Smartphone>> findByProducer(@RequestParam(required = false) String search){
//        Iterable<Smartphone> smartphones;
//        if (search != null || search.equals("")){
//            smartphones = smartphoneService.findAllByProducerContaining(search);
//        } else {
//            smartphones = smartphoneService.findAll();
//        }
//        return new ResponseEntity<>(smartphones, HttpStatus.OK);
//    }


    @GetMapping("/{id}")
    public ResponseEntity<Smartphone> findSmartphoneById(@PathVariable("id") Long id){
        Optional<Smartphone> smartphoneOptional = smartphoneService.findById(id);
        if (!smartphoneOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(smartphoneOptional.get(), HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Smartphone> editSmartphone(@RequestBody Smartphone smartphone,@PathVariable("id") Long id){
        Optional<Smartphone> smartphoneOptional = smartphoneService.findById(id);
        if (!smartphoneOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        smartphone.setId(smartphoneOptional.get().getId());
        return new ResponseEntity<>(smartphoneService.save(smartphone), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Smartphone> deleteSmartphone(@PathVariable("id") Long id) {
        Optional<Smartphone> smartphoneOptional = smartphoneService.findById(id);
        if (!smartphoneOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        smartphoneService.remove(id);
        return new ResponseEntity<>(smartphoneOptional.get(), HttpStatus.NO_CONTENT);
    }
}
