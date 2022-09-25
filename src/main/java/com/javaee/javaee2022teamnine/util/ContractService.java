package com.javaee.javaee2022teamnine.util;

import com.javaee.javaee2022teamnine.model.Contract;
import com.javaee.javaee2022teamnine.model.User;

import java.util.Date;
import java.util.List;

//@Remote
public interface ContractService {
    Contract createContract(User user);

    /**
     * @return List of Contract
     */
    List<Contract> getContractList();

    /**
     * @param contract_id The id of the contract to be deleted
     * @return Deletes a contract by matching the id of the contract
     */
    boolean deleteContract(int contract_id);

    /**
     * @return The working days of an employee per week
     */
    int calculateWorkingDaysPerWeek();

    /**
     * @return The total vacation days per year of an employee
     */
    int calculateVacationDaysPerYear();

    /**
     * @return The working hours per week
     */
    double calculateHoursPerWeek();

    double calculateHoursDue();

    /**
     * @param startDate The start date of the contract
     * @param endDate   The end date of the contract
     * @return Total vacation hours during the contract
     */
    double calculateVacationHours(Date startDate, Date endDate);

    /**
     * @param id The contract id
     * @return Contract object fetched from the contract id
     */
    Contract getContractById(int id);

    /**
     * @param contract The contract object to be updated
     * @return Updated contract
     */
    boolean updateContract(Contract contract);

    /**
     * @param existingContract The existing contract object in the contract table
     * @return Changes the contract status to STARTED (2)
     */
    boolean startContract(Contract existingContract);

    /**
     * @return The list of contracts which are in STARTED state
     */
    List<Contract> getStartedContractList();

    /**
     * @param existingContract The existing contract object in the contract table
     * @return Changes the contract status to TERMINATED (3)
     */
    boolean terminateContract(Contract existingContract);

    /**
     * @param userId User id of the user
     * @return Contract object according to the user id provided
     */
    Contract getContractByUserId(int userId);
}
