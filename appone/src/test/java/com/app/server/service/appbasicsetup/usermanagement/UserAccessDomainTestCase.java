package com.app.server.service.appbasicsetup.usermanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
import org.springframework.beans.factory.annotation.Autowired;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.app.server.service.RandomValueGenerator;
import java.util.HashMap;
import java.util.List;
import com.spartan.healthmeter.entity.scheduler.ArtMethodCallStack;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;
import org.springframework.mock.web.MockServletContext;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManager;
import com.spartan.pluggable.logger.event.RequestHeaderBean;
import com.spartan.pluggable.logger.api.RuntimeLogUserInfoBean;
import com.athena.server.pluggable.interfaces.CommonEntityInterface.RECORD_TYPE;
import org.junit.Test;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class UserAccessDomainTestCase extends EntityTestCriteria {

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private EntityValidatorHelper<Object> entityValidator;

    private RandomValueGenerator valueGenerator = new RandomValueGenerator();

    private static HashMap<String, Object> map = new HashMap<String, Object>();

    private static List<EntityTestCriteria> entityContraint;

    @Autowired
    private ArtMethodCallStack methodCallStack;

    protected MockHttpSession session;

    protected MockHttpServletRequest request;

    protected MockHttpServletResponse response;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        MockServletContext mockServletContext = new MockServletContext("file:src/main/webapp");
        try {
            String _path = mockServletContext.getRealPath("/WEB-INF/conf/");
            LogManagerFactory.createLogManager(_path, AppLoggerConstant.LOGGER_ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void startSession() {
        session = new MockHttpSession();
    }

    protected void endSession() {
        session.clearAttributes();
        session.invalidate();
        session = null;
    }

    protected void startRequest() {
        request = new MockHttpServletRequest();
        request.setSession(session);
        org.springframework.web.context.request.RequestContextHolder.setRequestAttributes(new org.springframework.web.context.request.ServletRequestAttributes(request));
    }

    protected void endRequest() {
        ((org.springframework.web.context.request.ServletRequestAttributes) org.springframework.web.context.request.RequestContextHolder.getRequestAttributes()).requestCompleted();
        org.springframework.web.context.request.RequestContextHolder.resetRequestAttributes();
        request = null;
    }

    @Before
    public void before() {
        startSession();
        startRequest();
        setBeans();
    }

    @After
    public void after() {
        endSession();
        endRequest();
    }

    private void setBeans() {
        runtimeLogInfoHelper.createRuntimeLogUserInfo("customer", "AAAAA", request.getRemoteHost());
        org.junit.Assert.assertNotNull(runtimeLogInfoHelper);
        methodCallStack.setRequestId(java.util.UUID.randomUUID().toString().toUpperCase());
        entityContraint = addingListOfFieldForNegativeTesting();
        runtimeLogInfoHelper.setRequestHeaderBean(new RequestHeaderBean(new RuntimeLogUserInfoBean("AAAA", "AAAA", request.getRemoteHost(), 0, 0, 0), "", methodCallStack.getRequestId()));
    }

    private UserAccessDomain createUserAccessDomain(Boolean isSave) throws Exception {
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainDescription("p9c4ITzWgKvwFCQ313yEjLdyiztYHcpVivTesYofRCTYvB698F");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainHelp("SY7mY7AzMmSyyCstIGJldJ8BWnCjCdKBSyD1NMKH3AcFLiwe6d");
        useraccessdomain.setDomainIcon("Mf5AsC5gIgkeKmtMfx7NfUn66ockY4qSzY79Ebkbgt19pjuqxO");
        useraccessdomain.setDomainName("vutzYxFlMsdnEWeX7VO13QzMrvyHhyoFsU8mGNSXCJOmPHsq0y");
        useraccessdomain.setEntityValidator(entityValidator);
        return useraccessdomain;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessDomain useraccessdomain = createUserAccessDomain(true);
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccessdomain.isValid();
            useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            UserAccessDomain useraccessdomain = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
            useraccessdomain.setDomainDescription("5s4ZZ6r1plJAe1LVI7v8Jm8KCPLCthhTotbZvLLWw9mqtur2kn");
            useraccessdomain.setUserAccessDomain(79233);
            useraccessdomain.setDomainHelp("XjgOR7LQGZVsbCgJpe2JNXHY5K3IAQwfvrvTGMVExy12TtZ1ag");
            useraccessdomain.setVersionId(1);
            useraccessdomain.setDomainIcon("xk2OfYUh5z3zYAD0obk4HIiQD75GpUj9spAJwkmVxWDWbyr4qH");
            useraccessdomain.setDomainName("OI9rQpnwcNEHThPKEFgqqEzcqjyWhgS7b0pkGWcxbavpzMqCcw");
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccessdomainRepository.update(useraccessdomain);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessDomain(EntityTestCriteria contraints, UserAccessDomain useraccessdomain) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccessdomainRepository.save(useraccessdomain);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessDomain", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessDomain", 189645));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "domainName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "domainName", "M12dCLbau4fp6oykvQTtIgbA0XSdKdGTL783Ibmjv74DSa3pe4suruhePCcINHQtFhnQ0L9Ikhmis7fyTKJ8WJOdZhwfj7pCxDlCe0DPkrFjKgdMROpYlaGR5140TV9hEsg1Yujp7Ara13ZdILwWINcSIi5IDkrQtjMQ2Sj7sLclJnOxjlUqSsFxvNpsvkADbXtMsZ6GGlvLMqEvv2b3RNOgWs1uecNfCX7F75NWINiGFh9mckPd1F2C0UnHmwgZa"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "domainDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "domainDescription", "G0R295viEmRUIESLAZL843YAdgbL60iKFHrzRT1gbE7Dd8vx2pvj9uCqe7WuKGmdm8CBdi79ZT3nkX5hcFNeDCnaER9Ju5fAF1MBqQmFOAaydnITVKs1AiThqIt6qO365b9Suo3w5PvcW6OLQFPDaB9hrjEpmuAFohXomudY0PAu1lCXEaiOprfSMQ67aI8AkTxb0i2IeENugDjo8AH25Z0O4x2L2C2MKqQ3yGSr9Wf4NMeu5fHn4aLXwq0Cx72pK"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "domainHelp", "YMDQSrWMEIl1kVbK9s2M5cvPiyGiuzE93RzsAiDPbYhN1SqKMP2nN6CmjHkPLwW6eGxutch49pe3CVYUzq5Lg946GedlDZXMfkKdaGVKZVlIA7Mgo3jmh5vEOrBAQCaCxSIBJWGlpwSY9j04RbEKqJ1JwGQTkgCtxjtxsQ2VeJlNDSnLQA2gzUALV3EeX27mksCBI6DZIS6EzOqndZg6E1oRVzNM5vwyHlrLHK32Enun1hph3FQHtUS4TJGPtrKWUpEYaeGTRAHjqz78yeKXbRtjvpG1bKvnsKcsQTjDfObcH6FgLoXLgKR4V97AyReUr434V1Z0EKNgiuaMzxH7hH363WsQLdqCXLdhORKJbOp9U6a0PKeB1gZpWFqHDwf0IGrlRQNGoPnD42tRoor5oAUGC0uVNKG4lYl6s6awV9IWtZSGgH8xzNZeTp4IvPe3gXFueg6Pg2SaOwLOW1dVKW763kUeCJxX5VhzSsSILpHOwhSG8YFn7EgNqIIvJE8XOVJVGaftm8UA8jtp2FynTVHGvYP1dbkfhSbCLEurbwH5iILuZ56bB1nub8jxzLqQJU7ycoxWNKGXxuNr2odTVHSvAeQZhUdNDp6JOWGTSoKwobE5Pg0hWAtI37zoU8hNXm1skXkJowb7Dpm6Hke35WX3fukV0fuXgXfm5CQFPaBgRuAhQ1y5P4lxKbZ6HzXGnWjHDsbubZLYYoF4DtQGlB5e7MtG3mdJ80m6zti9rxFUil3O0KXg2795pD2UivBXkKP16DF1nW3usFhZWYMYuLwKqviKDOar04vJX9jdDutm7Lc0aajpyphW2jsoaQ20qulMJt7GhDxeX7HnMzAnrj1JVXmQYo9F2PRvmAPY61Mkaysj4fMJ4BjWZFKOciYx0xvw9HBHctNGupf7Xi7AC1hKXJoCCTZRIvcndqeEoNKkCwU4yPXODDmGXUdmT28HLJCw1iop3Vxipxrb5RyTGej9H1emqNVsVfSRKBn54gCCplrTTuW1pygy1cOWkONzwW2OW5LTw2ddkfzXpkOYAmE6447xQ0lvmq4Zwje31TPeZJv0l5eazdkysvOKRUK2HCWumgMOSpWHq2oBkikzsdMxaJc9AGOrE5qvQXhkVs2zZckCmghcUnFXt5NPdmIORRvWqkjIgQG4Ruiwy1t4PuSxiJLGrpl2E2cEi3IPVJs42XrJ1LPzYCBzyaG6DyT27SVvd4SqtONGpjuYQ1J4YZPEH2M4KZL9eA6M9bfOoYBWexEpjr9BHWBQgDkk7dMoIRZhp406pc4tyrvrXNabiqCSLSWTERCzTne78mZxD9Nl85O0Ks6qudsJA8jng5ywdq0GGn0NFcMsw4FNKj1N0StcbdpcyT9CcM9p2QWwYxVOx5X0zmRmhlFL7vQEIaU7uiaNPgoByX2VC7JadhWBaAb20x3N711Z4xLSOdKAIyBdWsLyXfrqsLLw2vW5DoGhYX4cEkhbmoXAOqcBZ2r7BUt0rkPFlQBJ9DEjIzafGMfBRIbUhxSQgjUXv0aNpKnOup5f54Aw1SKEEPLaTHsv6jT0lre2IYCe4NjqzFgTbvDI3MUELebuBPBJtKjCpgGp8geu9SOBzZ95hbvokMJrC8vyCoVRQ5wsuOkCu16X0Un5Jud7EbJchOpGTfb80S3ZryePpT1Kwy6vTkyZDcfdGXr0795LWdoKVePDj3YhJvs1sSzDvDprqkOJdXVp9jjYq2jxeUMf6MJ4gabVg0GWezJ11fmf0RJkxhM0pEqP34YpIESSq5GHsXNxU7svzj7RqeGeJ9U6C6T2kTlqYemq6tFAsSFD5ph8APfWxQl9fA0Rgcx2gbiO4vxn7t01nciP2EwMsBuBs54qht2E2Xqnl4ammop1yj5PU2gW89FTZKzPZhtjAaWVMcjbY1qDtqH6C98gGBJygIPAYBTiIUuIhQ8RZXxtnSeb90CWjfk4ganNrzdmnepjYM6c6WLdVRr4fZmCGsfU7fJIxTv8zUSFOb2OhMA1RbFGGcCA6G1GFiNtAkJT6SJKF0azu1mNwii76"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "domainIcon", "8biJZO13MgrAxNe4vJyZ1LzQny73c4Af2CcwwolfeeXWCwozGBOaNmHjkWU43w5pMIwyq1FNKb9KCkYngWxN8tlH8DWC3a1XiPSU4PIt5C8y4nUNucVms3r4PpSjIqvD19WNQwxjnbUn6kVzsvlgwRIkjik5U4kdp0cSWb1tQ6Pras17m32E13oJoErnu8rRg05zOUVlP7srh8XY4ZIdium5WzH2QopV5Shb0jwFb1dxQlH7OEIbJxUROo4EnoxQh"));
        entityContraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        UserAccessDomain useraccessdomainUnique = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                UserAccessDomain useraccessdomain = createUserAccessDomain(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccessdomain.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 2:
                        useraccessdomain.setUserAccessDomain(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 4:
                        useraccessdomain.setDomainName(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 6:
                        useraccessdomain.setDomainDescription(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 7:
                        useraccessdomain.setDomainHelp(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 8:
                        useraccessdomain.setDomainIcon(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 9:
                        useraccessdomain.setUserAccessDomain(useraccessdomainUnique.getUserAccessDomain());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (failureCount > 0) {
            org.junit.Assert.fail();
        }
    }
}
