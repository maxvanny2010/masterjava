package ru.javaops.masterjava.service.mail.rest;


import com.google.common.collect.ImmutableList;
import org.glassfish.jersey.media.multipart.BodyPartEntity;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataParam;
import ru.javaops.masterjava.service.mail.Attachment;
import ru.javaops.masterjava.service.mail.GroupResult;
import ru.javaops.masterjava.service.mail.MailServiceExecutor;
import ru.javaops.masterjava.service.mail.util.MailUtils;
import ru.javaops.masterjava.service.mail.util.MailUtils.ProxyDataSource;
import ru.javaops.masterjava.web.WebStateException;
import javax.activation.DataHandler;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Path("/")
public class MailRS {
    @GET
    @Path("test")
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {
        return "Test";
    }

    @POST
    @Path("send")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public GroupResult send(@NotBlank @FormDataParam("users") String users,
                            @FormDataParam("subject") String subject,
                            @NotBlank @FormDataParam("body") String body,
                            @FormDataParam("attach") FormDataBodyPart attachBodyPart) throws WebStateException {

        final List<Attachment> attachments;
        if (attachBodyPart == null) {
            attachments = ImmutableList.of();
        } else {
            try {
                String attachName = attachBodyPart.getContentDisposition().getFileName();
//          UTF-8 encoding workaround: https://java.net/jira/browse/JERSEY-3032
                String utf8name = new String(attachName.getBytes("ISO8859_1"), StandardCharsets.UTF_8);
                BodyPartEntity bodyPartEntity = ((BodyPartEntity) attachBodyPart.getEntity());

                attachments = ImmutableList.of(new Attachment(utf8name, new DataHandler((ProxyDataSource) bodyPartEntity::getInputStream)));
            } catch (UnsupportedEncodingException e) {
                throw new IllegalStateException(e);
            }
        }
        return MailServiceExecutor.sendBulk(MailUtils.split(users), subject, body, attachments);
    }
}