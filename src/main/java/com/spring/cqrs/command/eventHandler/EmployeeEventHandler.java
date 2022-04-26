package com.spring.cqrs.command.eventHandler;

import com.spring.cqrs.command.event.EmployeeCreatedEvent;
import com.spring.cqrs.model.Employee;
import com.spring.cqrs.repository.EmployeeRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeEventHandler {
    @Autowired
    private EmployeeRepository employeeRepository;

    @EventHandler
    public void employeCretedEvent(EmployeeCreatedEvent employeeCreatedEvent){
        Employee emp = new Employee();
        BeanUtils.copyProperties(employeeCreatedEvent, emp);
        employeeRepository.save(emp);
    }
}
