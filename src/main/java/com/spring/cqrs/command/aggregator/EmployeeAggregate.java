package com.spring.cqrs.command.aggregator;

import com.spring.cqrs.command.controller.createCommand.CreateEmployeeCommand;
import com.spring.cqrs.command.event.EmployeeCreatedEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class EmployeeAggregate {
        @AggregateIdentifier
        private String uid;
        private String name;
        private String address;
        private String role;
        private String status;

        public EmployeeAggregate() {
        }

        @CommandHandler
        public EmployeeAggregate(CreateEmployeeCommand createEmployeeCommand) {
                //Here we can handle validation logic on createEmployeeCommand object
                EmployeeCreatedEvent employeeCreatedEvent = new EmployeeCreatedEvent();
                BeanUtils.copyProperties(createEmployeeCommand, employeeCreatedEvent);
                //it invokes event source handler method (employeeEvent) with employee created event
                AggregateLifecycle.apply(employeeCreatedEvent);
        }

        /**
         * Method to store the EmployeeCreatedEvent in the eventstore
         *
         * @param employeeCreatedEvent
         */
        @EventSourcingHandler
        public void employeeEvent(EmployeeCreatedEvent employeeCreatedEvent) {
                this.uid = employeeCreatedEvent.getUid();
                this.name = employeeCreatedEvent.getName();
                this.address = employeeCreatedEvent.getAddress();
                this.role = employeeCreatedEvent.getRole();

        }
}
