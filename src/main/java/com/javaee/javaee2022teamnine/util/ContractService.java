package com.javaee.javaee2022teamnine.util;

import com.javaee.javaee2022teamnine.enums.Roles;
import com.javaee.javaee2022teamnine.model.Contract;
import com.javaee.javaee2022teamnine.model.User;

import javax.ejb.Remote;
import javax.mail.MessagingException;
import java.util.Date;
import java.util.List;

//@Remote
public interface ContractService {
    public static final String ROLE_ASSISTANT = String.valueOf(Roles.ASSISTANT);


    Contract createContract(User user);

    List<Contract> getContractList();

    List<Contract> search(String name);

    Contract getContract(String uuid);

    String storeContract(Contract Contract);

    boolean deleteContract(int contract_id);

    int calculateWorkingDaysPerWeek();

    User getCurrentUser();

    int calculateVacationDaysPerYear();

    double calculateHoursPerWeek();

    double calculateHoursDue();

    double calculateVacationHours(Date startDate, Date endDate);

    Contract getContractById(int id);

    boolean updateContract(Contract contract);
}
