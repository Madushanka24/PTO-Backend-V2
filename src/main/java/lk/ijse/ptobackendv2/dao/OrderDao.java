package lk.ijse.ptobackendv2.dao;

import lk.ijse.ptobackendv2.entity.impl.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao extends JpaRepository<OrderEntity, String> {
}
