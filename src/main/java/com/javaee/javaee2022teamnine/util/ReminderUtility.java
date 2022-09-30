/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javaee.javaee2022teamnine.util;

import com.javaee.javaee2022teamnine.dao.ReminderDao;
import com.javaee.javaee2022teamnine.model.User;
import java.sql.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;

/**
 *
 * @author prajwolthapa
 */
public class ReminderUtility extends TimerTask{

    @Override
    public void run() {

        System.out.println("cron job running.......");
        ReminderDao reminderDao = new ReminderDao();
        //In Progress Reminder
        List<User> users = reminderDao.getAllInProgressReminderUsers();
        users.forEach(user -> {
            try {
                SendEmail.sendAsHtml(user.getEmail(),
                        "Reminder email",
                        "<h2>Please complete your timesheet ASAP</h2>");
            } catch (MessagingException ex) {
                Logger.getLogger(ReminderUtility.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        //signed by employee
        users = reminderDao.getAllSignedByEmployeeReminderUsers();
        List<User> supervisorAssistantusers = reminderDao.getSupervisorAndAssistantUsers();
        users.forEach(user -> {
            supervisorAssistantusers.forEach(supervisor ->{
                try {
                    SendEmail.sendAsHtml(supervisor.getEmail(),
                            "Reminder email",
                            "<h2>Please review the timesheet which is assigned to you and approve or reject it as a supervisor for" +user.getEmail() + "</h2>");
                } catch (MessagingException ex) {
                    Logger.getLogger(ReminderUtility.class.getName()).log(Level.SEVERE, null, ex);
                }


            });
        });

        //signed by supervisor
        users = reminderDao.getAllSignedBySupervisorReminderUsers();
        List<User> secretaryusers = reminderDao.getSecretaryUsers();
        users.forEach(user -> {
            secretaryusers.forEach(secretary ->{
                try {
                    SendEmail.sendAsHtml(secretary.getEmail(),
                            "Reminder email",
                            "<h2>Please review the timesheet which is assigned to you and approve or reject it as a secretary for " +user.getEmail() + "</h2>");
                } catch (MessagingException ex) {
                    Logger.getLogger(ReminderUtility.class.getName()).log(Level.SEVERE, null, ex);
                }


            });
        });



    }

    public static void runTimer(){
        Timer timer = new Timer();
        TimerTask task = new ReminderUtility();
        //timer.schedule(task, 0, 10000);
        timer.schedule(task, new Date(2022, 10, 1), 604800000);
        System.out.println("finished -----------");


    }
}
