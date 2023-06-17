package com.lethanh219049.application.service.impl;

import com.google.gson.Gson;
import com.lethanh219049.application.entity.*;
import com.lethanh219049.application.repository.SettingRepository;
import com.lethanh219049.application.repository.TaskBatchDoneRepository;
import com.lethanh219049.application.repository.TaskBatchRepository;
import com.lethanh219049.application.repository.UserRepository;
import com.lethanh219049.application.service.BaseService;
import com.lethanh219049.application.utils.BeanCopyUtils;
import com.lethanh219049.application.utils.CommonPassWord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static javax.management.remote.JMXConnectionNotification.FAILED;


@Service
@Slf4j
public class EmailSenderService extends BaseService {
    @Autowired
    TaskBatchRepository taskBatchRepository;

    @Autowired
    TaskBatchDoneRepository taskBatchDoneRepository;

//    @Autowired
//    JavaMailSender mailSender;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    SettingRepository settingRepository;

    @Autowired
    UserRepository userRepository;

//    public String sendEmail(String to, TaskBatch taskBatch, User user)  {
//
//        //setup retry
//        Long retry = taskBatch.getRetry();
//        taskBatch.setRetry(retry - 1L);
//        if (taskBatch.getRetry() < 0) {
//            taskBatch.setStatus(FAILED);
//            taskBatch.setMessageError("Retry null");
//            taskBatchRepository.save(taskBatch);
//
//            // luu du lieu vao done
//            TaskBatchDone taskBatchDone = new TaskBatchDone();
//            BeanCopyUtils.copyProperties(taskBatchDone, taskBatch);
//            taskBatchDone.setIsDeleted(false);
//            taskBatchDoneRepository.save(taskBatchDone);
//
//            throw new RuntimeException("Retry null");
//        }
//
//        //setup text body mail
//        TemplateMess templateMess = taskBatch.getBody();
//        String textBody = templateMess.getBody();
//        String subject = null;
//
//        if (taskBatch.getName().equals("SEND_EMAIL")) {
//            // xu ly data users
//            CommonPassWord.RandomStringExmple rand = new CommonPassWord.RandomStringExmple();
//            String pass = rand.randomPassword(8).toString();
//            user.setPassword(encoder.encode(pass));
//            userRepository.save(user);
//            textBody = textBody.replace("[name]", user.getFullName()).replace("[password]", pass);
//            subject = "RESET_PASSWORD";
//
//        }
//
//        taskBatch.setBody(templateMess);
//
//        // gửi email
//        String code = taskBatch.getName();
//        Setting item = settingRepository.findByCode(code);
//        ObjectEmail target2 = new Gson().fromJson(item.getObject(), ObjectEmail.class);
//        String msg = "";
//        SimpleMailMessage message = new SimpleMailMessage();
//
//        message.setTo(to);
//        message.setText(textBody);
//        message.setSubject(subject);
//        message.setFrom(target2.getUserName());
//        mailSender.send(message);
//        log.info("================mail triggered successfully to======================");
//        msg = "mail triggered successfully to : " + to;
//
//        taskBatch.setStatus("COMPLETED");
//        taskBatch.setIsDeleted(true);
//        taskBatch.setUpdatedAt(LocalDateTime.now());
//        taskBatch.setDescription(null);
//        // luu du lieu vao kill
//        log.info("=======Luu du lieu vao kill " + taskBatch.getRetry().toString() + " =======");
//        taskBatchRepository.save(taskBatch);
//
//        // luu du lieu vao done
//        TaskBatchDone taskBatchDone = new TaskBatchDone();
//        BeanCopyUtils.copyProperties(taskBatchDone, taskBatch);
//        taskBatchDone.setIsDeleted(false);
//        taskBatchDoneRepository.save(taskBatchDone);
//
//        return msg;
//    }


}
