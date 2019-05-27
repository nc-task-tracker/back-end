package com.example.demo.service;

import com.example.demo.model.Widget;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WidgetService {
    Widget create(Widget widget);
    Widget getWidgetById(String id);
    Widget update(Widget widget);
    List<Widget> getAllWidgets();
    void delete(String id);
    List<Widget> getAllWidgetsById(String id);
}
