package com.lethanh219049.application.kafka;

import com.lethanh219049.application.entity.TaskBatch;
import com.lethanh219049.application.entity.TaskBatchDone;
import com.lethanh219049.application.entity.User;
import com.lethanh219049.application.repository.TaskBatchDoneRepository;
import com.lethanh219049.application.repository.TaskBatchRepository;
import com.lethanh219049.application.repository.UserRepository;
import com.lethanh219049.application.service.impl.EmailSenderService;
import com.lethanh219049.application.utils.BeanCopyUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;


//@Component
@Slf4j
public class SendEmailListener {
    @Autowired
    TaskBatchRepository taskBatchRepository;

    @Autowired
    TaskBatchDoneRepository taskBatchDoneRepository;

    @Autowired
    EmailSenderService emailSenderService;

    @Autowired
    UserRepository userRepository;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    private final Log LOGGER = LogFactory.getLog(getClass());

    @KafkaListener(groupId = "send-email-consumer",topics = "send-email", containerFactory = "taskBatchKafkaListenerContainerFactory")
    public void sendMail(@Payload TaskBatch taskBatch) {
        try {
            if(taskBatch == null)

            log.info("=====================================Start KafkaListener send-email======================================");
            log.info("====================================="+taskBatch+"======================================");

//            taskBatch.setStatus(COMPLETED);
//            taskBatchRepository.save(taskBatch);


            // Check null for task
            if (taskBatch == null) {
                LOGGER.info(String.format("================ ::CONNECT JOB - MAIN STEP - END [%d][%s]", taskBatch.getId()));
                throw new RuntimeException("NOT_FOUND_TASK");
            }

            // Check tasks status
            if (taskBatch.getStatus().equals("FAILED") && taskBatch.getStatus().equals("COMPLETED")) {
                LOGGER.info(String.format("================ ::CONNECT JOB - MAIN STEP - END [%d][%s]", taskBatch.getId()));
                taskBatch.setMessageError("Task is Completed");
                taskBatchRepository.save(taskBatch);
                throw new RuntimeException("TASK_IS_LOCKED");
            }

            if (taskBatch.getRetry() == 0) {
                LOGGER.info("Retry null");
                taskBatch.setMessageError("Retry null");
                throw new RuntimeException("TASK_IS_LOCKED");
            }

            Long id  = Long.valueOf(taskBatch.getParameters());
            User user = userRepository.findUserById(id);

            try {
                log.info("============Chay vao service gui mail kafka =============================");
//                emailSenderService.sendEmail(user.getEmail(),taskBatch, user);
            } catch (Exception e) {
                log.info("chay loi khi nhan topic gui mail: "+e);
                if(taskBatch.getRetry() > 0){
                    taskBatch.setRetry(taskBatch.getRetry() - 1L);
                    taskBatchRepository.save(taskBatch);
                }else {
                    taskBatch.setMessageError(e.getMessage());
                    taskBatch.setStatus("FAILED");
                    taskBatch.setUpdatedAt(LocalDateTime.now());
                    taskBatchRepository.save(taskBatch);

                    TaskBatchDone taskBatchDone = new TaskBatchDone();
                    BeanCopyUtils.copyProperties(taskBatch, taskBatchDone);
                    taskBatchDoneRepository.save(taskBatchDone);
                }
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }
}
