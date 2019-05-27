package com.example.demo.dto.util;

import com.example.demo.dto.IssueDto;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PageDto<T> {

    private long totalElem;
    private int totalPages;
    private int pageSize;
    private List<T> list;

}
