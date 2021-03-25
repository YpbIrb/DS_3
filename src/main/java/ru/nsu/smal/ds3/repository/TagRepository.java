package ru.nsu.smal.ds3.repository;

import ru.nsu.smal.ds3.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Long> {}
