package com.andiv.example.springdataelasticsearch.listener;

import com.andiv.example.springdataelasticsearch.event.UserSavedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
public class UserSavedListener {

    @Async
    @TransactionalEventListener
    public void onTransactional(UserSavedEvent event) {
        log.info("[TransactionalEventListener] User {} saved!", event.getUsername());
    }

    @Async
    @EventListener
    public void on(UserSavedEvent event) {
        log.info("[EventListener] User {} saved!", event.getUsername());
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void onRollback(UserSavedEvent event) {
        log.info("[TransactionalEventListener] User {} rollback!", event.getUsername());
    }
}
