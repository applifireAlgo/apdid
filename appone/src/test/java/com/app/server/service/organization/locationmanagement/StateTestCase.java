package com.app.server.service.organization.locationmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.locationmanagement.StateRepository;
import com.app.shared.organization.locationmanagement.State;
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
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class StateTestCase extends EntityTestCriteria {

    @Autowired
    private StateRepository<State> stateRepository;

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

    private State createState(Boolean isSave) throws Exception {
        Country country = new Country();
        country.setCountryCode1("Ei0");
        country.setCapitalLatitude(2);
        country.setCurrencyCode("T6r");
        country.setIsoNumeric(172);
        country.setCapital("0zj8ZnbtDY82Ye6X7Rqew3TFuMiBAIbc");
        country.setCountryCode2("b5d");
        country.setCapitalLongitude(6);
        country.setCountryName("85JU3LJsDEyTiwtcVSYpmwQ27rTXaTY2OEQcUboO7Te6TYY79c");
        country.setCurrencyName("Ctx2Oj7IK3mUCRwrtqMhe7XEtQ4PGwQV3QOBsf8QEza6ThExQK");
        country.setCountryFlag("Buy3PV3c15qZTDMnrfsA2rmeQmwawrWR8cCuBQXrQXMKCtAp7Q");
        country.setCurrencySymbol("XCUZ26XObBGFOZ9ZGAE342CcmYjJtrKi");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateName("0pYqiSoiR1FkgH3Ofv3xLjNhrGpa6Y1pJA7wBg9t9l29z6go9u");
        state.setStateFlag("Fk0qXV5H3oXpMUh1RqgsLxz3cwXayY83qifevv3vvY78UioQyE");
        state.setStateCapital("A1JU6stn1MxRyt9iBWho9qTfFsZVj29jzfhH3Xkwi2cRnCO5n4");
        state.setStateCapitalLongitude(2);
        state.setStateCodeChar3("GUh1WwlLSHGSpZMkt6ADsk1Bk4jXOEMM");
        state.setStateCode(1);
        state.setStateCodeChar2("1hc6LBgDE6jV0YbmMDL3vBjvRTSxOXdK");
        state.setStateCapitalLatitude(3);
        state.setStateDescription("oOpGJqQ0xu61RqjDgDahT51k54CtEvF28vuoEXQixk1JzZmwnI");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        state.setEntityValidator(entityValidator);
        return state;
    }

    @Test
    public void test1Save() {
        try {
            State state = createState(true);
            state.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            state.isValid();
            stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            State state = stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
            state.setVersionId(1);
            state.setStateName("vnfpLAVsiypbxZAiq5u9gwtG1TAwMiSFyE9kO0YjKiZnbBLG6R");
            state.setStateFlag("ZQCmxN7WT7xj7u9zyZWls4R79LtG8hXKOsXMorSA78n3LhM1LG");
            state.setStateCapital("TEjFrAgpUqaH1ekl4JWDS76FRmBVt0odV2VuXFsGoiNdablQFN");
            state.setStateCapitalLongitude(9);
            state.setStateCodeChar3("tGcNDLYDfyBzKHdmKyn6AkYRyhlBLlAL");
            state.setStateCode(1);
            state.setStateCodeChar2("MQ4EvzsQxCokMfVN1zzNyQaOkco26hDH");
            state.setStateCapitalLatitude(8);
            state.setStateDescription("T2QSXvRQvaMgyzFkYAq8E79QACm0F8xQuY1LvoptbMUZDb5UVo");
            state.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            stateRepository.update(state);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<State> listofcountryId = stateRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateState(EntityTestCriteria contraints, State state) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            state.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            state.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            state.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            stateRepository.save(state);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "stateName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "stateName", "ip6jqhmNFh1Bu2BKbncYtSIbifFmmuJrvSwQkfTsvj8XSYudSMEyNiaTacgDtDULOxz94rHIl5qGs6i9hQRn5WpKW1zx3kbwLp1zULlHI10nbRnbIKTPu0fpxGvogfCvA4RFa1krLOkI6avz9DdKOK01MeyUzXTuQNw3x4k3b96DOWQvvbn1BbvUEYv8NQ6jucDpnaYv7QMFJgEYu1nB3mIAHOLHCrKYBJv4wgbLSq7NvZ6ZURk8hg5YVLJgdIh3e"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "stateCode", 4));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "stateCodeChar2", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "stateCodeChar2", "nkB4qreOSPSxA4s0CDJ6GQYEk2HFOySUA"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "stateCodeChar3", "bcwdmn1aNsgQeDyKW71YLVLZb4YyT883D"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "stateDescription", "1LjIUmcod7UmZeWjOWuO0VQgDxdgnFtfoOBvj5ifdJ62Y5GfEsZvw9LWtR6IBlG4JN6UPzGwCkjnNP2uyeZ1AoocTrGjVwmkwhXLQAG0AVrt2oWe3ubObPyrYJmnTmjKgzt2SSi3mB1oxYUWhrBkgJmI5AZofNnvOrLTl0jrM4vepiIAhCU2WheJY5uAwesmgosdRUS4v5IV0yk7sTgVKslj9k8D1sMsezOeP4DOKK3dxCM9efqLQWqlwOAJughmz"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "stateFlag", "2PZ8e3aJ6zBRzWzLxb7jzzQMThdvExjuggJPikEVkiVe5kUat2zZeZZ6Y2v54xlmWbNF7vDsYK52edEUKUcEHALvoZzReM6LgW28IwEEsE9BVBihDasbT4Obe5BhnEobF"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "stateCapital", "emIhuuPWdfGAxPYqBAlwBLcfiZfCqIJ07QYWziBWHMevF6x0aXiyq7S9YK8GWUKpQk2jNqGwwy7dEqHmYnWu9a9TuSQxyfK5EYKCSlVvnJ9WCw18emEXn0hTGvXrmwUJM"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "stateCapitalLatitude", 20));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "stateCapitalLongitude", 12));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                State state = createState(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = state.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 2:
                        state.setStateName(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 3:
                        state.setStateCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 5:
                        state.setStateCodeChar2(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 6:
                        state.setStateCodeChar3(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 7:
                        state.setStateDescription(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 8:
                        state.setStateFlag(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 9:
                        state.setStateCapital(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 10:
                        state.setStateCapitalLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 11:
                        state.setStateCapitalLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
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
