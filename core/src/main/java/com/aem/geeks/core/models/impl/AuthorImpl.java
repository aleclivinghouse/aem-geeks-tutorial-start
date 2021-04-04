package com.aem.geeks.core.models.impl;

import com.aem.geeks.core.models.Author;
import com.day.cq.wcm.api.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.*;
import org.apache.sling.models.annotations.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = Author.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class AuthorImpl implements Author {
    private static final Logger LOG = LoggerFactory.getLogger(AuthorImpl.class);

    @SlingObject
    ResourceResolver resourceResolver;

    @ScriptVariable
    Page currentPage;


    @Inject
    @Via("resource")
    @Default(values = "AEM")
    String fname;

    @Inject
    @Via("resource")
    @Required
    @Default(values = "Geeks")
    String lname;

    @Inject
    @Via("resource")
    boolean professor;

    @Override
    public String getFirstName(){
        return fname;
    }

    @Override
    public String getLastName(){
        return lname;
    }

    @Override
    public boolean getIsProfessor(){
        return professor;
    }

    @Override
    public String getPageTitle(){
        return currentPage.getTitle();
    }

}
