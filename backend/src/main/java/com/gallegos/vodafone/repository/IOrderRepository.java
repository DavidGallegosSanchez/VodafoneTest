package com.gallegos.vodafone.repository;

import com.gallegos.vodafone.model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepository extends CrudRepository<Order,Long> {

    @Query("select o from Order o order by o.createAt DESC")
    List<Order> getOrderDesc();
}
