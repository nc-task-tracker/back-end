package com.example.demo.controller;

import com.example.demo.dto.FilterDto;
import com.example.demo.model.Filter;
import com.example.demo.service.FilterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
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
    public Filter getFilterById(@PathVariable(name = "id") String id) {
        return service.getFilterById(id);
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
    public FilterDto createFilter(@RequestBody FilterDto filter) {
        System.out.println("filter search");
        return modelMapper.map(service.saveFilter(modelMapper.map(filter, Filter.class)), FilterDto.class);
    }

}
