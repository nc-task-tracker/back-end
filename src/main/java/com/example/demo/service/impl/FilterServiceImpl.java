package com.example.demo.service.impl;

import com.example.demo.model.Filter;
import com.example.demo.repository.FilterRepository;
import com.example.demo.service.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilterServiceImpl implements FilterService {
    private FilterRepository repository;

    @Autowired
    public FilterServiceImpl(FilterRepository repository) {
        this.repository = repository;
    }

    @Override
    public Filter saveFilter(Filter filter) {
        return repository.save(filter);
    }

    @Override
    public Filter getFilterById(String id) {
        return repository.findFilterById(id);
    }

    @Override
    public Filter updateFilter(Filter filter) {
        return repository.save(filter);
    }

    @Override
    public List<Filter> getAllFilters() {
        return (List<Filter>) repository.findAll();
    }

    @Override
    public void deleteFilter(String id) {
        repository.deleteById(id);
    }
}
