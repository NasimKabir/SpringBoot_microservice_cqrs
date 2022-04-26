package com.spring.cqrs.command.controller;

import com.spring.cqrs.command.controller.createCommand.CreateEmployeeCommand;
import com.spring.cqrs.command.controller.model.EmployeeRequest;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/employee")
public class EmployeeCommandController {
    private CommandGateway commandGateway;

    public EmployeeCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String newEmployee(@Valid @RequestBody EmployeeRequest employee) {

        CreateEmployeeCommand newEmployee = CreateEmployeeCommand.builder()
                .uid(UUID.randomUUID().toString())
                .name(employee.getName())
                .address(employee.getAddress())
                .role(employee.getRole())
                .status("CREATED")
                .build();

        try {
            return commandGateway.sendAndWait(newEmployee);
        }catch (Exception exception){
            return exception.getMessage();
        }
    }
}
