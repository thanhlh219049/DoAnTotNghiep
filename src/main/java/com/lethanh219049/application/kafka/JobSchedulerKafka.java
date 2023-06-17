package com.lethanh219049.application.kafka;


import com.lethanh219049.application.repository.TaskBatchRepository;
import com.lethanh219049.application.entity.TaskBatch;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.kafka.KafkaException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.lethanh219049.application.config.Constant.*;


//@Component
//@Configurable
//@EnableScheduling
public class JobSchedulerKafka {
    private final Log LOGGER = LogFactory.getLog(getClass());

    @Autowired
    private TaskBatchRepository taskBatchRepository;

    @Autowired
    KafkaSender kafkaSender;

    @Scheduled(fixedDelay = 3000)
    public void run() {

        LOGGER.info("=============================chay vao scheduler ===============================");
        List<TaskBatch> listTasks = taskBatchRepository.findTop20ByAndStatusOrderById(NEW);

        List<Long> processingList = new ArrayList<Long>();

        for (TaskBatch task: listTasks) {
            try {
                kafkaSender.sendObject(CHANGE_PASSWORD, task);
                processingList.add(task.getId());
            }catch (KafkaException ex){
                ex.getMessage();
            }
        }

        // UPDATE TRANG THAI Task = PROCESSING
        List<TaskBatch> listDto = taskBatchRepository.findAllByIdIsIn(processingList);
        listDto.forEach(taskBatch -> {
            taskBatch.setStatus(PROCESSING);
        });

        taskBatchRepository.saveAll(listDto);
    }
}
