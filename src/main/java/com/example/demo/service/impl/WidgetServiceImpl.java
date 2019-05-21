package com.example.demo.service.impl;

import com.example.demo.model.Widget;
import com.example.demo.repository.WidgetRepository;
import com.example.demo.service.WidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WidgetServiceImpl implements WidgetService {

    private WidgetRepository widgetRepository;

    @Autowired
    public WidgetServiceImpl(WidgetRepository widgetRepository){
        this.widgetRepository = widgetRepository;
    }

    @Override
    public Widget addWidget(Widget widget) {
        return widgetRepository.save(widget);
    }

    @Override
    public Widget getWidgetById(String id) {
        return widgetRepository.findWidgetById(id);
    }

    @Override
    public Widget updateWidget(Widget widget) {
        return widgetRepository.save(widget);
    }

    @Override
    public List<Widget> getAllWidgets() {
        return widgetRepository.findAll();
    }

    @Override
    public void deleteWidget(String id) {
        widgetRepository.deleteById(id);
    }
}
