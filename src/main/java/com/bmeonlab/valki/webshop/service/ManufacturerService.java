package com.bmeonlab.valki.webshop.service;

import com.bmeonlab.valki.webshop.model.Manufacturer;
import com.bmeonlab.valki.webshop.repository.ManufacturerRepository;
import com.bmeonlab.valki.webshop.utils.NullAwareBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerService {

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    public Manufacturer createManufacturer(Manufacturer manufacturer) { return manufacturerRepository.saveAndFlush(manufacturer); }

    public Manufacturer getManufacturerById(Long id) { return manufacturerRepository.getOne(id); }

    public List<Manufacturer> getAllManufacturers() {return  manufacturerRepository.findAll(); }

    public Manufacturer updateManufacturer(Long id, Manufacturer manufacturer) {
        Manufacturer existingManufacturer = manufacturerRepository.findById(id).orElse(new Manufacturer());
        NullAwareBeanUtils.copyNonNullProperties(manufacturer, existingManufacturer);
        return manufacturerRepository.saveAndFlush(existingManufacturer);
    }

    public void deleteManufacturer(Long id) { manufacturerRepository.deleteById(id); }
}
