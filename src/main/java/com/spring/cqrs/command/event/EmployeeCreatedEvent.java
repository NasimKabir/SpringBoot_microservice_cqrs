package com.spring.cqrs.command.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeCreatedEvent {
    private String uid;
    private String name;
    private String address;
    private String role;
}
