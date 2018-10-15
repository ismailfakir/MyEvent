package net.cloudcentrik.myevent.example;

import net.cloudcentrik.myevent.db.event.Event;
import net.cloudcentrik.myevent.db.user.User;
import net.cloudcentrik.myevent.example.builder.Builder;
import net.cloudcentrik.myevent.example.builder.Email;
import net.cloudcentrik.myevent.example.builder.GenericBuilder;

public class TestMain {
    public static void main(String[] args) {
        User value=GenericBuilder.of(User::new).
                with(User::setName,"ismail").
                build();

        Event testEvent=Builder.build(Event.class).
                with(j->j.setDate("test")).
                get();

        Email email=new Email.EmailBuilder()
                .addRecipient("test@example.com")
                .build();
    }
}
