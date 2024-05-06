package tn.esprit.vitanova.models;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Data
public class PagedResponse<T> {
    private List<T> content = new ArrayList<>();

    private int page;

    private int size;

    private long totalElements;


    private int totalPages;

    private boolean last;

    // pageNumber+1 because default jpa starts with 0
    public PagedResponse(Page<T> dbPage) {
        this.content = dbPage.getContent();
        this.page = dbPage.getNumber() + 1;
        this.size = dbPage.getSize();
        this.totalElements = dbPage.getTotalElements();
        this.totalPages = dbPage.getTotalPages();
        this.last = dbPage.isLast();
    }

}
