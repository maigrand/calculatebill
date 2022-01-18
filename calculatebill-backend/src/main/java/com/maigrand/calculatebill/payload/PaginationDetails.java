package com.maigrand.calculatebill.payload;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

@Getter
public class PaginationDetails<T> {

    private final List<T> items;

    private final PaginationMetaDetails meta;

    public PaginationDetails(Page<T> page) {
        items = page.getContent();
        meta = new PaginationMetaDetails();

        meta.setTotalCount(page.getTotalElements());
        meta.setPageCount(page.getTotalPages());
        meta.setCurrentPage(page.getPageable().getPageNumber());
        meta.setPerPage(page.getPageable().getPageSize());
    }

    private PaginationDetails(List<T> items, PaginationMetaDetails meta) {
        this.items = items;
        this.meta = meta;
    }

    /**
     * Useful for view generating
     *
     * @return PaginationDetails with mappedList
     */
    public <I, R> PaginationDetails<R> map(Function<? super T, ? extends R> mapper) {

        return new PaginationDetails<>(
                items.stream().map(mapper).collect(toList()),
                meta);
    }
}
