package controllers;

import com.google.inject.Inject;
import play.Logger;
import play.api.libs.mailer.MailerClient;
import play.libs.mailer.Email;
import play.mvc.Controller;
import play.mvc.Result;

public class EmailController extends Controller{

    private static final Logger.ALogger LOG = Logger.of(EmailController.class);

    private final MailerClient mailer;

    @Inject
    public EmailController(MailerClient mailer) {
        this.mailer = mailer;
    }

    public Result sendMail() {
        String cid = "1234";
        final Email email = new Email()
                .setSubject("Simple email")
                .setFrom("Mister FROM <info.cloudcentrik@gmail.com>")
                .addTo("Miss TO <ismail7043@gmail.com>")
                .setBodyText("A text message")
                .setBodyHtml("<html><body><p>An <b>html</b> message with cid <img src=\"cid:" + cid + "\"></p></body></html>");
        String id = mailer.send(email);
        return ok("Email " + id + " sent!");
    }
}
