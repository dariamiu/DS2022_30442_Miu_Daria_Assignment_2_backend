package ds.entity_utility_platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface AbstractRepository<T> extends JpaRepository<T, Long> {

}