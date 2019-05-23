package com.example.demo.dto.util;

import lombok.*;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
public class TableSortParametersDTO {

    @NotNull
    private String _columnName;

    @NotNull
    private String _direction;

    @NotNull
    private int _page;

    @NotNull
    private int _maxElemOnPage;

    public String get_columnName() {
        return _columnName;
    }

    public void set_columnName(String _columnName) {
        this._columnName = _columnName;
    }

    public String get_direction() {
        return _direction;
    }

    public void set_direction(String _direction) {
        this._direction = _direction;
    }

    public int get_page() {
        return _page;
    }

    public void set_page(int _page) {
        this._page = _page;
    }

    public int get_maxElemOnPage() {
        return _maxElemOnPage;
    }

    public void set_maxElemOnPage(int _maxElemOnPage) {
        this._maxElemOnPage = _maxElemOnPage;
    }

    @Override
    public String toString() {
        return "TableSortParametersDTO{" +
                "_columnName='" + _columnName + '\'' +
                ", _direction='" + _direction + '\'' +
                ", _page=" + _page +
                ", _maxElemOnPage=" + _maxElemOnPage +
                '}';
    }
}
