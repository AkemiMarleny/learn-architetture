package co.idesoft.architetture.common.factories;

import org.springframework.data.domain.Pageable;

import co.idesoft.architetture.common.Page;
import co.idesoft.architetture.common.Pagination;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PageableFactory {
    
    public static Pageable from(Pagination pagination) {
        return Pageable.ofSize(pagination.getPageSize())
            .withPage(pagination.getPage());
    }  

    public static <T> Page<T> from(org.springframework.data.domain.Page<T> page) {
        return new Page<T>(
            page.getPageable().getPageNumber(), 
            page.getPageable().getPageSize(), 
            page.getTotalElements(),
            page.getContent());
    }

    public static Pagination from(Pageable pageable){
        return new Pagination(
            pageable.getPageNumber(),
            pageable.getPageSize());
    }
}
