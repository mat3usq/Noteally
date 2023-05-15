package pl.noteally.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.noteally.data.Catalog;

public interface CatalogRepository extends JpaRepository<Catalog, Integer> {
}
