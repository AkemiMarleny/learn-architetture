package co.idesoft.architetture.common;

import lombok.Getter;

@Getter
public class Pagination {
    private int page;
    private int pageSize;
   
    public Pagination(int page, int pageSize){
        this.page = page;
        this.pageSize = pageSize;
    }
}
