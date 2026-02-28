package com.ndlcommerce.config;

import java.util.List;
import java.util.function.Function;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginatedResult<N> {

  private final List<N> content;
  private final int page;
  private final int size;
  private final long totalElements;
  private final int totalPages;
  private final boolean isFirst;
  private final boolean isLast;

  public PaginatedResult(
      List<N> content,
      int page,
      int size,
      long totalElements,
      int totalPages,
      boolean isFirst,
      boolean isLast) {
    this.content = content;
    this.page = page;
    this.size = size;
    this.totalElements = totalElements;
    this.totalPages = totalPages;
    this.isFirst = isFirst;
    this.isLast = isLast;
  }

  public <R> PaginatedResult<R> map(Function<N, R> mapper) {

    List<R> mapped = this.content.stream().map(mapper).toList();

    return new PaginatedResult<>(
        mapped,
        this.page,
        this.size,
        this.totalElements,
        this.totalPages,
        this.isFirst,
        this.isLast);
  }
}
