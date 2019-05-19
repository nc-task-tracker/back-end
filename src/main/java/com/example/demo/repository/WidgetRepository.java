package com.example.demo.repository;

import com.example.demo.model.Widget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WidgetRepository extends JpaRepository<Widget, String> {
    Widget findWidgetById(String id);
    List<Widget> findAllByDashboardId(String id);
}

