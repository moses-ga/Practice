package io.kenzo.learning.utils;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;

/**
 * Works around an incompatibility between spring-data-jpa-datatables and Spring Data JPA 4.
 *
 * The library's plain {@code repository.findAll(input)} passes {@code null} for the extra
 * specifications and Spring Data JPA 4 now rejects a null in {@code Specification.and()}.
 * Passing {@link Specification#unrestricted()} explicitly restores the old behaviour.
 * Always call this instead of {@code repository.findAll(input)}.
 */
public final class DataTablesUtils {

    private DataTablesUtils() {
    }

    public static <T, ID extends Serializable> DataTablesOutput<T> findAll(DataTablesRepository<T, ID> repository,
                                                                           DataTablesInput input) {
        return repository.findAll(input, Specification.unrestricted(), Specification.unrestricted());
    }

    /** Same, with an extra filter applied before search/ordering (e.g. only active rows). */
    public static <T, ID extends Serializable> DataTablesOutput<T> findAll(DataTablesRepository<T, ID> repository,
                                                                           DataTablesInput input,
                                                                           Specification<T> preFilter) {
        return repository.findAll(input, Specification.unrestricted(),
                preFilter == null ? Specification.unrestricted() : preFilter);
    }
}
