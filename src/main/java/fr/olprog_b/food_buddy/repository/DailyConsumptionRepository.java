package fr.olprog_b.food_buddy.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.olprog_b.food_buddy.enums.ProductType;
import fr.olprog_b.food_buddy.model.DailyConsumption;
import fr.olprog_b.food_buddy.model.User;

public interface DailyConsumptionRepository extends JpaRepository<DailyConsumption, Long> {
  @Query("SELECT d FROM DailyConsumption d WHERE d.user = :user AND d.consumptionType = :consumptionType AND DATE(d.consumptionDate) = :today")
  DailyConsumption findByUserAndConsumptionTypeAndToday(@Param("user") User user, @Param("consumptionType") ProductType consumptionType, @Param("today") LocalDate today);
}
