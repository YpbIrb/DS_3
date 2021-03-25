package ru.nsu.smal.ds3.repository;


import org.springframework.data.jpa.repository.Query;
import ru.nsu.smal.ds3.entity.NodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface NodeRepository extends JpaRepository<NodeEntity, BigInteger> {
    @Query(value = "select * from nodes " +
            "where 6371000 * 2 * ASIN(" +
            "sqrt(power(SIN((nodes.lat - ABS(?2)) * PI() / 180 / 2), 2) + " +
            "cos(nodes.lat * PI() / 180) * cos(?2 * PI() / 180) * power(SIN((nodes.lon - ?1) * PI() / 180 / 2), 2))) < ?3", nativeQuery = true)
    List<NodeEntity> getNodesByRadius(double lon, double lat, double radius);
}