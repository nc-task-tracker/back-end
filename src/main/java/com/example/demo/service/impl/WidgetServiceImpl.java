package com.example.demo.service.impl;

import com.example.demo.model.Widget;
import com.example.demo.repository.WidgetRepository;
import com.example.demo.service.FilterService;
import com.example.demo.service.WidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WidgetServiceImpl implements WidgetService {

    private WidgetRepository widgetRepository;

    private FilterService filterService;

    @Autowired
    public WidgetServiceImpl(WidgetRepository widgetRepository,
                             FilterService filterService){
        this.widgetRepository = widgetRepository;
        this.filterService = filterService;
    }

    @Override
    public Widget create(Widget widget) {
        widget.setFilter(filterService.getFilterById(null));
        return widgetRepository.save(widget);
    }

    @Override
    public Widget getWidgetById(String id) {
        return widgetRepository.findWidgetById(id);
    }

    @Override
    public Widget update(Widget widget) {
        return widgetRepository.save(widget);
    }

    @Override
    public List<Widget> getAllWidgets() {
        return widgetRepository.findAll();
    }

    @Override
    public void delete(String id) {
        widgetRepository.deleteById(id);
    }

    @Override
    public List<Widget> getAllWidgetsById(String id) {
        return widgetRepository.findAllByDashboardId(id);
    }
}
