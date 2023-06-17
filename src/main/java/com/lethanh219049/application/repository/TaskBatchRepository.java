package com.lethanh219049.application.repository;


import com.lethanh219049.application.entity.TaskBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskBatchRepository extends JpaRepository<TaskBatch, Long> {

    TaskBatch findTaskBatchById(Long id);

    List<TaskBatch> findTop20ByAndStatusOrderById (String status);


    List<TaskBatch> findAllByIdIsIn(List<Long> processingList);



}
