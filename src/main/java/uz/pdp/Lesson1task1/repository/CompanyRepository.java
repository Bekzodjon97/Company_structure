package uz.pdp.Lesson1task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.Lesson1task1.entity.Company;

public interface CompanyRepository extends JpaRepository<Company,Integer> {
    boolean existsByCorpName(String corpName);
}
