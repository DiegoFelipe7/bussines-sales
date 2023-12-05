package co.com.bussine.model.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationDTO {
    private String search;
    private Long offset;
    private Long limit;
    private Long totalPages;
    private Long totalElements;
    private Long numberPage;

    public PaginationDTO(String search, Long offset, Long limit, Long totalElements, Long numberPage) {
        this.search = search;
        this.offset = offset;
        this.limit = limit;
        this.totalElements = totalElements;
        this.totalPages = (long) Math.ceil((double) totalElements / limit);
        this.numberPage = numberPage;
    }

    public PaginationDTO(String search, Long offset, Long limit, Long numberPage) {
        this.search = search;
        this.offset = offset;
        this.limit = limit;
        this.totalPages = (long) Math.ceil((double) offset / limit);
        this.numberPage = numberPage;
    }

    public PaginationDTO pagination(Long totalPages) {
        return new PaginationDTO(this.getSearch(), this.getOffset(), this.getLimit(), totalPages, this.numberPage);
    }

    public PaginationDTO nextPage() {
        if (numberPage < totalPages) {
            return new PaginationDTO(this.getSearch(), this.getOffset() + this.getLimit(), this.getLimit(), this.totalElements, this.numberPage + 1);
        }
        return this;
    }

    public PaginationDTO previousPage() {
        if (numberPage > 1) {
            return new PaginationDTO(this.getSearch(), this.getOffset() - this.getLimit(), this.getLimit(), this.totalElements, this.numberPage - 1);
        }
        return this;
    }

    public PaginationDTO goToPage(Long pageNumber) {
        if (pageNumber >= 1 && pageNumber <= totalPages) {
            return new PaginationDTO(this.getSearch(), (pageNumber - 1) * this.getLimit(), this.getLimit(), this.totalElements, pageNumber);
        }
        return this;
    }
}

