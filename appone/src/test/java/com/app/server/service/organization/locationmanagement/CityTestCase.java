package com.app.server.service.organization.locationmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.locationmanagement.CityRepository;
import com.app.shared.organization.locationmanagement.City;
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
import com.app.shared.organization.locationmanagement.State;
import com.app.server.repository.organization.locationmanagement.StateRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class CityTestCase extends EntityTestCriteria {

    @Autowired
    private CityRepository<City> cityRepository;

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

    private City createCity(Boolean isSave) throws Exception {
        Country country = new Country();
        country.setCountryCode1("8Iu");
        country.setCapitalLatitude(11);
        country.setCurrencyCode("oIU");
        country.setIsoNumeric(842);
        country.setCapital("IJfm6PMc5RXtPMGFlg7RvaC5jQiVc7QT");
        country.setCountryCode2("WqH");
        country.setCapitalLongitude(10);
        country.setCountryName("8rInMZIwG4sGwwhJiFXEC8y0bDiybI6sVBigxngoSbf6b4mtqV");
        country.setCurrencyName("2Whnc92hksPKPAo5z21uDucsptZntMQnsiNG48p7uo9uJmjhu0");
        country.setCountryFlag("DMONHAYAZHaaSewuvT2QQoc9uy3d3WMiCZWUHF52EmszLIaqh6");
        country.setCurrencySymbol("P1v5QE8dBxTbqPTvZAVa70l5xumlWKMe");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateName("wRrq5QqchfFssxb8R9KBRPMqwhszyf4lAPCjVE1MwfpFxEmLYb");
        state.setStateFlag("7GDOrh7Ifl9rxOQdRRshQd7GflOEZPpiEduztJLINos2L4iOOi");
        state.setStateCapital("kgFL8FxoU1o2pKTXRSLS5igvf8FvGTT9CSezt93LeTJeIEvvRD");
        state.setStateCapitalLongitude(9);
        state.setStateCodeChar3("D0bkKnZ5CEKrxp098aCoezR8PsaT5nPW");
        state.setStateCode(2);
        state.setStateCodeChar2("BCZJ9mAJipS5ZMzTFe8QLOMMcYNUZuWx");
        state.setStateCapitalLatitude(3);
        state.setStateDescription("ZF5ohr94ZmsbisDfxF41s5XDTgbaHLZQ6GGJ2f6IuXwKgZhpG5");
        state.setStateName("QiyZi5ITjTa4BScIiDeO4yFSWbzzk5WcrMOdWH9h48xGU72g8k");
        state.setStateFlag("vDIMGeNY88VfrvJlsKfd1Asf7uxHfpSCfLRYRQj9q3kRl2RPQw");
        state.setStateCapital("TXo4qUI2ofc29L9QOuikLBlOTNB2vxHNvRh4hdcBK1KkBxBvIk");
        state.setStateCapitalLongitude(3);
        state.setStateCodeChar3("beRKKPei23Gx20BXTeZCeYx3m62pgfED");
        state.setStateCode(1);
        state.setStateCodeChar2("iUgtMt9uXb2Oxmy2wgjw5TKhCPUBNKD7");
        state.setStateCapitalLatitude(7);
        state.setStateDescription("Tqsfvhb3LcLh9UncS1XJdNotkSN6w7ewKvCkR1NSvu54lpTDhH");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        City city = new City();
        city.setCityLongitude(10);
        city.setCityLatitude(6);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("dXThPAGFAUZOQk3XlEvKvanO3ptm9AEfp6wmqs8sqoWWsoOf53");
        city.setCityCodeChar2("FSNHDNu1YWMvJgf3lv3sDIF1vELlgRkO");
        city.setCityCode(1);
        city.setStateId((java.lang.String) StateTest._getPrimarykey());
        city.setCityDescription("rA9BM1le7PBZLuH9v1XCcin0ewN7oTAkNGXDYRwRAztMbLeDXu");
        city.setCityName("4dJJileEF3c4LbPJbUG2tVM1V5KtQZTjUxXckLbpkCO3Hy0mxZ");
        city.setEntityValidator(entityValidator);
        return city;
    }

    @Test
    public void test1Save() {
        try {
            City city = createCity(true);
            city.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            city.isValid();
            cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("CityPrimaryKey"));
            City city = cityRepository.findById((java.lang.String) map.get("CityPrimaryKey"));
            city.setCityLongitude(8);
            city.setCityLatitude(2);
            city.setCityFlag("3qiNB3XL75ZJMH3IgpZoB8fIFktJCNDlkHB7nosrurDKa3oodG");
            city.setCityCodeChar2("W2wYoXcny3M6VtItfq2ndTADb8fdYRL1");
            city.setVersionId(1);
            city.setCityCode(3);
            city.setCityDescription("hPx2HIa2T69EjH0glcIn8UN9udwopw7X1siq7JFzjkVUHd4fjZ");
            city.setCityName("CzPzAL2tcnYv7yIZ6DhA1Z0arexsQCRRGBX4bS3cjJwL60AxZ0");
            city.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            cityRepository.update(city);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<City> listofcountryId = cityRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
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
            org.junit.Assert.assertNotNull(map.get("CityPrimaryKey"));
            cityRepository.findById((java.lang.String) map.get("CityPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<City> listofstateId = cityRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
            if (listofstateId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("CityPrimaryKey"));
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateCity(EntityTestCriteria contraints, City city) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            city.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            city.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            city.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            cityRepository.save(city);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "cityName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "cityName", "ZljK0UntqpzbGCPKra72XfBNtyorBIEdic4uK2hiU9uOi3DavSzxvXcUh9qThCRnVJJvRltuApgrRdHH8SqfYKVIRxPxtuzUA0uDHhwSLvzJTcJbPl5gUG2OEC6XSo9mcjcOG047JAMioMBEC8PDTRAzbvo5juz8BiftIwAZwdeom3bXzF1wKzfga3jnPOfzP77GmbiRf0QfL5GqjB0bgn0kt3N7M13No4Tl3CBgTNyrmT5Mn7VKzGLjl9hxSlJem"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "cityCodeChar2", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "cityCodeChar2", "8LaLEPzGPS7Up0oDeu9zvkyzODzMKduR3"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "cityCode", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "cityCode", 5));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "cityDescription", "T9YlkZFwPH4YF27ywhS4vgdDDOChFEijqvPBkqRAYxZ0hTU5h8JzQMn0GeKmPt4j5YHkvjW72b4Tt29Ege7QbrGyCl03TpfsrQqSrUclkzNqLIFRntgiaZzCyYz4M0JUf"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "cityFlag", "NksUPWQFLyy20ewzpayTRdypBIGhkcclBfVs8fggkA8jv4pJ2Gx6ni72BhUmYytEr8khZfnTMcfAq0iU0B3JNAqTKHokFpSExobpXvhebk6w1Vjomr4s3MTO26CrzkUaW"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "cityLatitude", 16));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "cityLongitude", 21));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                City city = createCity(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = city.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 2:
                        city.setCityName(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 4:
                        city.setCityCodeChar2(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 6:
                        city.setCityCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 7:
                        city.setCityDescription(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 8:
                        city.setCityFlag(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 9:
                        city.setCityLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 10:
                        city.setCityLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
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
