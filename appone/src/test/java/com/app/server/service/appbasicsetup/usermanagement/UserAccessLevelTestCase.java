package com.app.server.service.appbasicsetup.usermanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
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
public class UserAccessLevelTestCase extends EntityTestCriteria {

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

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

    private UserAccessLevel createUserAccessLevel(Boolean isSave) throws Exception {
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelIcon("lEdvJKCNz5se8OiTR9YXEYb44kuKGXPKOk18npSdv6qYnQFW6m");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelHelp("K0nj6qSGIY7gcpoeZDHFkjWWs8GkPL4iqO33mpf7ogOFc2CwaX");
        useraccesslevel.setLevelName("xGaCNZaquDRlj3nb2aZKzPbRgv1UaINM2tbtIbhXN1Ib7F7ngW");
        useraccesslevel.setLevelDescription("YWSGrxsH2IfnzYJBfI9C5dsf5EYhRaKGLne9ET3u4itaEvdWUU");
        useraccesslevel.setEntityValidator(entityValidator);
        return useraccesslevel;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessLevel useraccesslevel = createUserAccessLevel(true);
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccesslevel.isValid();
            useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            UserAccessLevel useraccesslevel = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
            useraccesslevel.setLevelIcon("QHRu6mOExW0GywG3fDJj6gHKbx6j94dNE3BiKwvouCEz1bDDgg");
            useraccesslevel.setUserAccessLevel(39791);
            useraccesslevel.setLevelHelp("cV1bAtqSAeHDPU1qrZJ8JyVFpTGobEnQ7Jr41SaXg9en69ujuR");
            useraccesslevel.setLevelName("EYDrwsBI1l7hCXVNN0RhXvS8dGpzn4iMEwUzjRcwB92Rd6G7If");
            useraccesslevel.setVersionId(1);
            useraccesslevel.setLevelDescription("jcCrXGjD76abDqT6ivlDpPKKeKW2uRLx6mpvU5xJrbc5tNWskT");
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccesslevelRepository.update(useraccesslevel);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessLevel(EntityTestCriteria contraints, UserAccessLevel useraccesslevel) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccesslevelRepository.save(useraccesslevel);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessLevel", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessLevel", 187832));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "levelName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "levelName", "8j0Qy8OK4zYftbNGMZgJjG9SLqwlysnfbPgFo9xszLMTIispPWNhsOCsl3Bq019sj5VBvjzxTNQGtMpN4j8r3DREKC1ff4t2BHHtF8jEeQXejSbj3GY8NPiDel2FLAwz3qtnxhgDAGBMtbmrKZRFPCQelfqwz19hiEDmmBmnXTs2WFO6GsscbjsYrVZpCLsegt7zhpU5HyGva0nAH0yte2YondBQXoNXibTYJn323Xl3I5syx6UGa8p8FF33NPrSZ"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "levelDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "levelDescription", "yEpbL3mcg4H5nsCdq8PnIEYB4yIy5ApFQCKROBdbIqIKMSoZogifuJNS8PeL1WpFgXDOemi6fjyytaHSyt1GNBXT8av92NFSwQSRMxXOIHnLPJgQT5aRMNIk4AoHmaGk8LZnN0m9GGxC0casocVKJohA4MHcpiKLzA5h59RnBqAsOBo9n6SsKcyhlMMilm0wNNbPVQuad6AiMBmTFD10MAsYzEOC5k3e81N8FQWYQjFbVjAbWfvjJRAZj3hB6haHc"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "levelHelp", "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"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "levelIcon", "2QQLPwAEKA6kBiPaJPJQszCdlnHcb7XLlL51n20n4q6LFSrTqMMwlCwAkOmYlHK3C9mzV8R9TnXsbbpzHYkH95XiFxpzEQwyEAx8xbRWY4FPjwDeLcdTJONOz0845fCwPPTTuGlv3Y0Ji96auKTdaZbAaMTATOBmXwhQylUxLtjzakvsidEqj5OqTIM9REbrwVb8mz2ZYOBWc32xBPAwvUcFbnU8gLWcgIoU86nFxPi6b6ApvOgir0xxOi5PMrzFg"));
        entityContraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        UserAccessLevel useraccesslevelUnique = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                UserAccessLevel useraccesslevel = createUserAccessLevel(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccesslevel.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 2:
                        useraccesslevel.setUserAccessLevel(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 4:
                        useraccesslevel.setLevelName(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 6:
                        useraccesslevel.setLevelDescription(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 7:
                        useraccesslevel.setLevelHelp(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 8:
                        useraccesslevel.setLevelIcon(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 9:
                        useraccesslevel.setUserAccessLevel(useraccesslevelUnique.getUserAccessLevel());
                        validateUserAccessLevel(contraints, useraccesslevel);
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
