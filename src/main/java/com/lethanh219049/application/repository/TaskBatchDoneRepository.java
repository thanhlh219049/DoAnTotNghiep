package com.lethanh219049.application.repository;


import com.lethanh219049.application.entity.TaskBatchDone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskBatchDoneRepository extends JpaRepository<TaskBatchDone, Long> {

}
