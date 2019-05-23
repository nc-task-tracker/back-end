package com.example.demo.service;

import com.example.demo.model.Widget;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WidgetService {
    Widget addWidget(Widget widget);
    Widget getWidgetById(String id);
    Widget updateWidget(Widget widget);
    List<Widget> getAllWidgets();
    void deleteWidget(String id);
}
