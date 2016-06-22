package com.app.server.service.appbasicsetup.userrolemanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.userrolemanagement.AppMenusRepository;
import com.app.shared.appbasicsetup.userrolemanagement.AppMenus;
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
public class AppMenusTestCase extends EntityTestCriteria {

    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

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

    private AppMenus createAppMenus(Boolean isSave) throws Exception {
        AppMenus appmenus = new AppMenus();
        appmenus.setAutoSave(true);
        appmenus.setMenuHead(true);
        appmenus.setAppType(2);
        appmenus.setRefObjectId("AVac6Jh4KGRR9QxteRkMnRIN0JmUHHajkbLMlNIOWTvSEUTnZy");
        appmenus.setMenuIcon("rrKMTki4cHmkQMH9Rx9IIZIDBrfmrxbKtMkIKKYYTgZ9xOS92V");
        appmenus.setMenuAccessRights(2);
        appmenus.setMenuLabel("BqDdqFRnm65XO646MLuVIHJuO4iF2XePCRlN61tUohpijJSEcS");
        appmenus.setAppId("T7k0uo8X8OjXj2YuVFf7V7zwug6HJOAcShJZo9yDtRdohvnZaY");
        appmenus.setMenuAction("rVd1Yeffq43vShTHmINARz0E2t9dIgnJr7B7Gp5oB4I8Xg9tlY");
        appmenus.setMenuTreeId("ZcTgSgsSv0Y7dHzOhWlejQbwi6qwsY0h0DI9QJRVWBoTgNlD75");
        appmenus.setMenuCommands("QIdXVhiawQjvan4a3A0Uf6gyYX2JmkDfntlG8I6KkBsc35XWm5");
        appmenus.setMenuDisplay(true);
        appmenus.setUiType("SS5");
        appmenus.setEntityValidator(entityValidator);
        return appmenus;
    }

    @Test
    public void test1Save() {
        try {
            AppMenus appmenus = createAppMenus(true);
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            appmenus.isValid();
            appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            AppMenus appmenus = appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
            appmenus.setAppType(2);
            appmenus.setRefObjectId("Lph8wO2ja9g28YLW5bbipY86Rwko7D1n5yAFQLwY093f43al25");
            appmenus.setMenuIcon("AuuMFYxG1MTHBH6NImwEvZHdr8uz8an1OWtxJiu3nyyCDgip5D");
            appmenus.setMenuAccessRights(3);
            appmenus.setMenuLabel("fhbnBWn3Yk35ONoVTOhtAOBxXjfH8bZi1rpamg61Enccotlqlg");
            appmenus.setVersionId(1);
            appmenus.setAppId("Xg3qUPMv5m4bqUvRjj6qMD7Rg48dhisuGAUXvhiwVp236uk62U");
            appmenus.setMenuAction("CawPk3B4r6P98gRaX41Id9T25ni2G0fvCpl5e7FsbdkttMuZaV");
            appmenus.setMenuTreeId("KmyA7c5tOnyiz1W1algLpygzdjq7XNQYXviGiKadNU5w1JLOk7");
            appmenus.setMenuCommands("UAtFWlLm77ftJLxMHAC89Fk7KpLD7izxjhjHsj6Nia8RvYnCBn");
            appmenus.setUiType("1q6");
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            appmenusRepository.update(appmenus);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateAppMenus(EntityTestCriteria contraints, AppMenus appmenus) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            appmenusRepository.save(appmenus);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "menuTreeId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "menuTreeId", "vg0XG6b6rWeYhJuhnx4jhoR2TCv2NNpx5o49GmeR74DlFXiiN3vvg58ZyQiQtWoJccadulWwOWC7KbN5w1B5SRraVszFiHJGHvaT8lAFq55JxGUS1m90kCKV8zhHz6OEKRZGnLc5hXaVky65sjJmRJfvdEhcQr5Y062UzxggSV3uTJQZy0vumUVEWipozI5MZ6H0d5PG29OL99XCVOgvYxcY244pPdB025wrs9BtfrosQsVgZ0lZu0hAB2UNREnCT"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "menuIcon", "8yELRL4QBpuJbkiVu7dpze0o9BQMePTtR8qqUKrezWAyOPEtZCCerD0ny6rD127oEAjm0W23eiXITCmShkmIgpPrsoaePcucVBCYppIaYfrNML00WyesEuri40LBaP75t1NHXNd1kcmS5jnzN75dpdYDAEL92I2BqmbOKR1SlZS8OMCFF3ye1c2R9AdFEAFuR6vuAUzLPRGCgswKEQFTobAYgDzUuHB6Zn3TP4XKFza7VXJsa0UdRkgA2agmyN8nz"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "menuAction", "JOpq733fCEeHHxvj9bWu3A7AWnIE3I9WKtGt3aSTr72lq4w0nfV1K5rXVVymyY5EATeINypcyw8sXBhCdkcyDbK7Bn29wYVqpm8IoRw1fKF0qdNxYmkl0lAtJ3hFFc4oCBTuCwVa8gyqJv0tKgE5LZekZDpzclss35Min3iQeKgotnPXnNfieH32KmeUZpOUhms3GmFIvwlgK1I7zwioVHV8LVfZAOKBhJMTOYP7Ut0xc56Gq9cZtzFk8hVAMprBj"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "menuCommands", "3EZ7NYeCWRn8Zoz6HqVCicZmNnGsWqqRA5LV7na7MFxRIOISBZdABMLttStGrDWIn"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 6, "menuDisplay", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "menuDisplay", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 8, "menuHead", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "menuHead", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 10, "menuLabel", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "menuLabel", "CaswuTHMEqp7ZqsDAnUEWGKWPcoOW3TNk11wS0vwPfYvyqfKi5iEk72j5qazTSqMjUhkJaSHD9vobZ8FATKWIIRuUrFvotwBjhmVOo68VevSqk9hlPEXGuF0Ri0FDJwj1D2tSJG7Y9m5WgbyEeuzehdz4MVfATorqzYANkHThAyGoHPpv6K0Wskg8bc5JmHhppdchrJ9td8Q8qTe1xmw8woabFCjeD3bhyTr8zMXxyqAWUTSXdbMAPUL6byJyauF1"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "uiType", "7Cai"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 13, "refObjectId", "JLWolgOWgE2GEcYkWkjdQGSGOtQQkkCtnf9XvZ6LJyEmeiOga5iEOgqg1dQYs2d7nSOrOG0LjYEMHihcs6oiuql3xVjh1FfNAPsCC73vP58mOpdAddTVw3Av2t4Ks3cQWUgW34H5ilAv1JA6fB1XDuDdefipuyCke6mETnAkz3UkUN5WdqHDj1dZASAfizXInyjcV9z83BqikfG15U0QDS0GG7BGI440lgI9ZrOztq8atrPrheycAOM2PyBlJv4xX"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 14, "menuAccessRights", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 15, "menuAccessRights", 13));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 16, "appType", 3));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 17, "appId", "wnsFFhZb7OeosJ9RJmtyhQXEeoExhhZSRb99TseyycY26C8FmKU9LELJCxg0zz1XKnpjgGOjfnPOIo8EJxh4tSyVwCmMl29jV5OujCvrl0NJbc2MgaUp00cfmPDtPJJlPcv4mB5PBRkX1YJGk4q9qYRS7gse0AjgUvxiFDmZhjPmGAFpmnDuVxhxGsONcL3yVNp7ERMuiKoZo48xnN6WGw2RB2uYUx1eFDSGastx7jE9Mucxrmlt9Z7LGbtjp6ZX4"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 18, "autoSave", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 19, "autoSave", true));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                AppMenus appmenus = createAppMenus(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = appmenus.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 2:
                        appmenus.setMenuTreeId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 3:
                        appmenus.setMenuIcon(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 4:
                        appmenus.setMenuAction(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 5:
                        appmenus.setMenuCommands(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 6:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 7:
                        break;
                    case 8:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 9:
                        break;
                    case 10:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 11:
                        appmenus.setMenuLabel(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 12:
                        appmenus.setUiType(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 13:
                        appmenus.setRefObjectId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 14:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 15:
                        appmenus.setMenuAccessRights(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 16:
                        appmenus.setAppType(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 17:
                        appmenus.setAppId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 18:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 19:
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
