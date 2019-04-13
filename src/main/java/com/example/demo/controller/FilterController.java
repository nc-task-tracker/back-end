package com.example.demo.controller;

import com.example.demo.dto.FilterDto;
import com.example.demo.model.Filter;
import com.example.demo.service.FilterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/api/filter")
public class FilterController {
    private FilterService service;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public FilterController(FilterService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public FilterDto getFilterById(@PathVariable(name = "id") String id) {
        return modelMapper.map(service.getFilterById(id), FilterDto.class);
    }

    @GetMapping(value = "/all")
    public List<FilterDto> getAllFilters() {
        List<FilterDto> filtersDto = new ArrayList<>();
        List<Filter> filters = service.getAllFilters();
        for(Filter item : filters) {
            filtersDto.add(modelMapper.map(item, FilterDto.class));
        }
        return filtersDto;
    }

    @PostMapping
    public Filter saveFilter(@RequestBody Filter filter) {
        return service.saveFilter(filter);
    }

    @PutMapping
    public FilterDto updateFilter(@RequestBody FilterDto filterForUpdate) {
        Filter filter = modelMapper.map(service.getFilterById(filterForUpdate.getId()), Filter.class);
        return modelMapper.map(service.updateFilter(filter), FilterDto.class);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteFilter(@PathVariable(name = "id") String id) {
        service.deleteFilter(id);
        return ResponseEntity.noContent().build();
    }
}
