package com.example.demo.service.impl;

import com.example.demo.model.Filter;
import com.example.demo.model.Parameter;
import com.example.demo.model.ParameterType;
import com.example.demo.repository.FilterRepository;
import com.example.demo.repository.ParameterRepository;
import com.example.demo.service.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ParameterServiceImpl implements ParameterService {

    private FilterRepository filterRepository;

    private ParameterRepository repository;

//    @Autowired
//    public ParameterServiceImpl(ParameterRepository repository) {
//        this.repository = repository;
//    }

    @Autowired
    public ParameterServiceImpl(FilterRepository filterRepository, ParameterRepository repository) {
        this.filterRepository = filterRepository;
        this.repository = repository;
    }




//    @Override
//    public Parameter saveParameter(Parameter parameter, String authorId)
//    {
//        Set<Parameter> books = new HashSet<>();
//        Filter author1 = new Filter();
//
//        Optional<Filter> byId = filterRepository.findById(authorId);
////        if (!byId.isPresent()) {
////            throw new ResourceNotFoundException("Author with id " + authorId + " does not exist");
////        }
//        Filter author = byId.get();
//
//        //tie Author to Book
//        parameter.setFilter(author);
//
//        Parameter book1 = repository.save(parameter);
//        //tie Book to Author
//        books.add(book1);
//        author1.setParameters(books);
//
//        return book1;
////        return repository.save(parameter);
//    }

    @Override
    public Parameter saveParameter(Parameter parameter, String authorId)
    {
        return repository.save(parameter);
    }
    @Override
    public Parameter getParameterById(String id) {
        return repository.findParameterById(id);
    }

    @Override
    public Parameter updateParameter(Parameter parameter) {
        return repository.save(parameter);
    }

    @Override
    public List<Parameter> getAllParameters() {
        return (List<Parameter>) repository.findAll();
    }

    @Override
    public void deleteParameter(String id) {
        repository.deleteById(id);
    }
}
