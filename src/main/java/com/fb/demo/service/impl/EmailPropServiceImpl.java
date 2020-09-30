package com.fb.demo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fb.demo.entity.EmailProp;
import com.fb.demo.exception.EmailPropNotFoundException;
import com.fb.demo.model.request.EmailPropCreateRequest;
import com.fb.demo.model.request.EmailPropUpdateRequest;
import com.fb.demo.model.response.EmailPropResponse;
import com.fb.demo.repository.EmailPropRepository;
import com.fb.demo.service.EmailPropService;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EmailPropServiceImpl implements EmailPropService {

    @Autowired
    private EmailPropRepository emailPropRepository;

    @Override
    public EmailPropResponse createEmailProp(EmailPropCreateRequest emailPropRequest)
                    throws Exception {
        EmailPropResponse emailPropResponse = new EmailPropResponse();
        if (emailPropRequest != null) {
            EmailProp emailProp = new EmailProp();
            emailProp.setApplication(emailPropRequest.getApplication());
            emailProp.setEmail(emailPropRequest.getEmail());
            emailProp.setEmailTemplate(emailPropRequest.getEmailTemplate());
            emailProp.setHost(emailPropRequest.getHost());
            emailProp.setParentTenant(emailPropRequest.getParentTenant());
            emailProp.setPassword(emailPropRequest.getPassword());
            emailProp.setPort(emailPropRequest.getPort());
            emailProp.setProvider(emailPropRequest.getProvider());
            emailProp.setSslPort(emailPropRequest.getSslPort());
            emailProp.setUserName(emailPropRequest.getUserName());
            emailPropRepository.save(emailProp);

            emailPropResponse.setId(emailProp.getId());
            emailPropResponse.setMsg("Successfully created emailProp for tenant : "
                            + emailProp.getParentTenant().getId());
            return emailPropResponse;
        } else {
            log.info(":::::Something went wrong could not store emailProp in DB");
            throw new InvalidInputException("Invalid input for emailProp");
        }
    }

    @Override
    public EmailProp getEmailProp(Integer id) throws Exception {
        if (id != null) {
            EmailProp emailProp = emailPropRepository.findEmailPropById(id);
            if (emailProp != null) {
                return emailProp;
            } else {
                throw new EmailPropNotFoundException("Email prop with id :" + id + " not found");
            }
        } else {
            throw new InvalidInputException("Invalid input");
        }
    }

    @Override
    public List<EmailProp> getAllEmailProp() {
        List<EmailProp> listOfEmailProp = emailPropRepository.findAll();
        return listOfEmailProp;
    }

    @Override
    public void deleteEmilProp(Integer id) throws Exception {
        if (id != null) {
            EmailProp emailProp = emailPropRepository.findEmailPropById(id);
            if (emailProp != null) {
                emailPropRepository.delete(emailProp);
            } else {
                throw new EmailPropNotFoundException("Email prop with id :" + id + " not found");
            }
        } else {
            throw new InvalidInputException("EmailProp id must not be null");
        }

    }

    @Override
    public void updateEmailProp(EmailPropUpdateRequest emailPropUpdateRequest, Integer id) {

    }

}