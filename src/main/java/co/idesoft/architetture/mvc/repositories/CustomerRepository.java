package co.idesoft.architetture.mvc.repositories;

import co.idesoft.architetture.mvc.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
